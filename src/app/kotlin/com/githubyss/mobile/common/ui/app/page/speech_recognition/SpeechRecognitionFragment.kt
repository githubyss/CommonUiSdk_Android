package com.githubyss.mobile.common.ui.app.page.speech_recognition

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.bindView
import com.githubyss.mobile.common.kit.manager.speech_recognition.SpeechRecognitionManager
import com.githubyss.mobile.common.kit.manager.speech_recognition.VoiceJsonParser
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.kit.util.logE
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.speech_recognition.manager.VoiceIntentManager
import com.githubyss.mobile.common.ui.app.page.speech_recognition.manager.VoiceLocalMatch
import com.githubyss.mobile.common.ui.app.page.speech_recognition.manager.VoiceToTarget
import com.githubyss.mobile.common.ui.app.page.speech_recognition.util.VoiceError
import com.githubyss.mobile.common.ui.app.page.speech_recognition.widget.RecordImageButton
import com.githubyss.mobile.common.ui.app.page.speech_recognition.widget.VoiceHomeLayout
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentSpeechRecognitionBinding


/**
 * SpeechRecognitionFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/27 14:36:49
 */
class SpeechRecognitionFragment : BaseInlineBindingToolbarFragment(R.layout.comui_fragment_speech_recognition) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = SpeechRecognitionFragment::class.java.simpleName
    }

    private val binding by bindView<ComuiFragmentSpeechRecognitionBinding>()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.recordImageButton?.setVoiceListener(voiceListener)
        binding?.voiceBtnHelp?.setOnClickListener(onClickListener)
        binding?.voiceBtnClose?.setOnClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmPhoneChargeClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmTransferClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmLifePaymentClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmPlayClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmTravelClickListener(onClickListener)
        // binding.viewVoiceTalking.voiceHomeLayout.setmCreditCardClickListener(onClickListener)

        initSpeechRecognition()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_speech_recognition_title)
    }

    override fun onResume() {
        super.onResume()
        showView(VoiceHomeLayout.ViewType.Guide)
    }

    override fun onDestroy() {
        SpeechRecognitionManager.destroy()
        super.onDestroy()
    }


    /** ****************************** Functions ****************************** */

    private fun initSpeechRecognition() {
        SpeechRecognitionManager.configSDK(activity ?: return)

        SpeechRecognitionManager.onSpeechRecognizerCallback = object : SpeechRecognitionManager.OnSpeechRecognizerCallback {
            override fun onBeginOfSpeech() {
            }

            override fun onError(errorCode: Int, errorDescription: String) {
                binding?.recordImageButton?.hideLoading()
                when (errorCode) {
                    10118 -> showView(VoiceHomeLayout.ViewType.Unheard)
                    20001, 20002, 20003, 20004, 20005 -> showView(VoiceHomeLayout.ViewType.NoNetwork)
                    else -> showView(VoiceHomeLayout.ViewType.Unheard)
                }
            }

            override fun onEndOfSpeech() {
                binding?.recordImageButton?.hideLoading()
            }

            override fun onResult(result: String, isLast: Boolean) {
                binding?.viewVoiceTalking?.voiceHomeLayout?.hideAllView()
                binding?.viewVoiceTalking?.question?.text = "${binding?.viewVoiceTalking?.question?.text}${VoiceJsonParser.parseIatResult(result)}"
                if (isLast) {
                    // TODO 最后的结果
                    binding?.recordImageButton?.hideLoading()
                    val questionText: String = binding?.viewVoiceTalking?.question?.text.toString()
                    val actionCode: Int = VoiceLocalMatch.getActionCode(questionText)
                    when (actionCode) {
                        VoiceIntentManager.ACTION_DEFAULT ->
                            // 走科大讯飞的对话功能
                            SpeechRecognitionManager.understandText(questionText)
                        else -> {
                            val voiceManager = VoiceIntentManager(activity, actionCode)
                            binding?.viewVoiceTalking?.answer?.text = voiceManager.answer
                            voiceManager.doAction()
                        }
                    }
                }
            }

            override fun onVolumeChanged(volume: Int, data: ByteArray) {
                binding?.recordImageButton?.setVolum(volume)
            }

            override fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle?) {
            }
        }

        SpeechRecognitionManager.onTextUnderstanderCallback = object : SpeechRecognitionManager.OnTextUnderstanderCallback {
            override fun onResult(result: String) {
                if (null != result) {
                    // 显示
                    logE(TAG, "OnTextUnderstanderCallback.onResult >>> $result")
                    if (!TextUtils.isEmpty(result) && VoiceJsonParser.getResultRCCode(result) === 0) {
                        //0	操作成功
                        val vendor = VoiceJsonParser.getVendor(result)
                        if (!TextUtils.isEmpty(vendor) && "SUNING" == vendor) {
                            //走技能业务  这个版本可能走不到，产品不想配置技能库，太麻烦，等以后版本再改
                            try {
                                val snIntentCode = VoiceJsonParser.getIntentCode(result)
                                val voiceManager = VoiceIntentManager(activity, Integer.valueOf(snIntentCode))
                                binding?.viewVoiceTalking?.answer?.text = voiceManager.answer
                                voiceManager.doAction()
                            }
                            catch (e: Exception) {
                                //防止产品配置出错
                                binding?.viewVoiceTalking?.answer?.text = VoiceError.getErrorMsg(VoiceError.ERROR_CANNNOT_UNDERSTAND)
                            }
                        }
                        else {
                            //走科大讯飞提供的开放技能服务
                            binding?.viewVoiceTalking?.answer?.text = VoiceJsonParser.getAnswerResult(result)
                        }
                    }
                    else {
                        //  1	输入异常  2	系统内部异常  3	业务操作失败，错误信息在error字段描述  4	文本没有匹配的技能场景，技能不理解或不能处理该文本
                        //当前产品不配置，所以科大大部分都会提示不识别，需要客户端本地去做下关键词拦截，跳转模块儿
                        val actionCode = VoiceLocalMatch.getActionCode(result)
                        val voiceIntentManager = VoiceIntentManager(activity, actionCode)
                        binding?.viewVoiceTalking?.answer?.text = voiceIntentManager.answer //VoiceError.getErrorMsg(VoiceJsonParser.getResultRCCode(resultText))
                        voiceIntentManager.doAction()
                    }
                }
                else {
                    logD(TAG, "understander result:null")
                    binding?.viewVoiceTalking?.answer?.text = VoiceError.getErrorMsg(VoiceError.ERROR_CANNNOT_UNDERSTAND)
                }
            }

            override fun onError(errorCode: Int, errorDescription: String) {
                binding?.viewVoiceTalking?.answer?.text = VoiceError.getErrorMsg(errorCode)
            }
        }
    }

    private fun showView(viewType: VoiceHomeLayout.ViewType) {
        if (viewType === VoiceHomeLayout.ViewType.More) {
            binding?.voiceBtnHelp?.visibility = View.INVISIBLE
        }
        else {
            binding?.voiceBtnHelp?.visibility = View.VISIBLE
        }
        binding?.viewVoiceTalking?.voiceHomeLayout?.showView(viewType)
    }

    private fun resetTalk() {
        binding?.viewVoiceTalking?.question?.text = ""
        binding?.viewVoiceTalking?.answer?.text = ""
    }


    /** ****************************** Implementations ****************************** */

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.voice_btn_close -> {
                val viewType: VoiceHomeLayout.ViewType? = binding?.viewVoiceTalking?.voiceHomeLayout?.currentViewType
                if (binding?.viewVoiceTalking?.voiceHomeLayout?.visibility === View.VISIBLE && viewType == VoiceHomeLayout.ViewType.More) {
                    //监测如果当前停留在更多页，返回到语音引导页
                    showView(VoiceHomeLayout.ViewType.Guide)
                }
                else {
                    activity?.finish()
                }
            }
            R.id.voice_btn_help -> {
                showView(VoiceHomeLayout.ViewType.More)
            }
            R.id.creditcard -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=xykhk")
            }
            R.id.travel -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=traintickets")
            }
            R.id.play -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=chwl")
            }
            R.id.lifepayment -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=shjf")
            }
            R.id.transfer -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=zz")
            }
            R.id.phone_charge -> {
                VoiceToTarget.instance?.toApp(activity, "com.suning.jr://?key=sjcz")
            }
        }
    }

    private val voiceListener: RecordImageButton.Listener = object : RecordImageButton.Listener {
        override fun onVoiceStart() {
            SpeechRecognitionManager.setSpeechRecognizerParam(activity ?: return)
            if (SpeechRecognitionManager.isSpeechRecognizerNull()) {
                return
            }
            showView(VoiceHomeLayout.ViewType.BeginSpeaking)
            if (SpeechRecognitionManager.isSpeaking) {
                return
            }
            SpeechRecognitionManager.startListening()
            resetTalk()
        }

        override fun onVoiceEnd() {
            if (SpeechRecognitionManager.isSpeechRecognizerNull()) {
                return
            }
            if (!SpeechRecognitionManager.isSpeaking) return
            binding?.recordImageButton?.showLoading()
            SpeechRecognitionManager.stopListening()
        }
    }
}
