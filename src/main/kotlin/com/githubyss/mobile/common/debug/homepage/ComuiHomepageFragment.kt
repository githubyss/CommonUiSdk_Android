package com.githubyss.mobile.common.debug.homepage

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.githubyss.mobile.common.debug.animation.property.ComuiPropertyAnimationFragment
import com.githubyss.mobile.common.debug.recyclerview.ComuiMultiFragmentRecyclerViewFragment
import com.githubyss.mobile.common.debug.recyclerview.ComuiRecyclerViewFragment
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floatingview.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingIcon
import com.githubyss.mobile.common.ui.floatingview.container.FloatingIconListener
import com.githubyss.mobile.common.ui.floatingview.container.system.SystemFloatingIcon
import kotlinx.android.synthetic.main.comui_debug_fragment_homepage.*
import java.util.*


/**
 * ComuiHomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:22
 */
class ComuiHomepageFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        private val TAG = ComuiHomepageFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.button_propertyAnimation -> replaceFragment(ComuiPropertyAnimationFragment(), ComuiPropertyAnimationFragment.TAG, true)
            R.id.button_tweenAnimation -> return@OnClickListener
            R.id.button_recyclerView -> replaceFragment(ComuiMultiFragmentRecyclerViewFragment(), ComuiMultiFragmentRecyclerViewFragment.TAG, true)
            
            R.id.button_showFloatingWithinApp -> {
                val layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (ScreenUtils.dp2Px(14.0f) ?: return@OnClickListener), (ScreenUtils.dp2Px(14.0f) ?: return@OnClickListener))
                AppFloatingIcon.getInstance(activity as Context).show()?.layoutParams(layoutParams)?.setMovable(true)?.customIcon("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")?.setFloatingListener(object : FloatingIconListener {
                    override fun onShow() {}
                    override fun onRemove() {}
                    override fun onIconClick() {}
                })
                // SystemFloatingIcon.getInstance(activity as Context)
                //         .show()
                //         ?.setMovable(true)
                //         ?.customIcon(R.drawable.comui_lucky_money)
                //         ?.setFloatingListener(object : FloatingIconListener {
                //             override fun onShow() {}
                //             override fun onRemove() {}
                //             override fun onIconClick() {}
                //         })
            }
            R.id.button_closeFloatingWithinApp -> {
                AppFloatingIcon.getInstance(activity as Context).close()
                // SystemFloatingIcon.getInstance(activity as Context)
                //         .close()
            }
            R.id.button_showFloatingWithinSystem -> {
                SystemFloatingIcon.getInstance(activity as Context).show()?.setMovable(true)?.customIcon(R.drawable.comui_lucky_money)?.setFloatingListener(object : FloatingIconListener {
                    override fun onShow() {}
                    override fun onRemove() {}
                    override fun onIconClick() {}
                })
            }
            R.id.button_closeFloatingWithinSystem -> {
                SystemFloatingIcon.getInstance(activity as Context).close()
            }
            
            R.id.button_showAutoShortedFloatingView -> {
                // Fake data
                val audioList: MutableList<AudioModel> = ArrayList()
                audioList.add(AudioModel("00000", "测图", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/voice/male/1608360545089.wav", "http://ossprexg.cnsuning.com/fipcms/media/voice/female/1608360545089.wav"))
                audioList.add(AudioModel("00000", "汽车", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/voice/male/160877400382021.wav", "http://ossprexg.cnsuning.com/fipcms/media/voice/female/160877400382021.wav"))
                // audioList.add(AudioModel("", "ID空", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "Url空", "", "", "", "", ""))
                // audioList.add(AudioModel("00002", "单女声-唱歌-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3"))
                // audioList.add(AudioModel("00001", "单男声-播报-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", ""))
                // audioList.add(AudioModel("00001", "单男声-播报-错误Url-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/11.wav", ""))
                // audioList.add(AudioModel("00001", "单女声-播报-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "单女声-播报-错误Url-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/22.wav"))
                // audioList.add(AudioModel("00001", "男&女声-播报-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "男&女声-播报-男声错误Url-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/11.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                
                /** 先 setContext 再 show 后 initData，顺序不能乱 */
                AppFloatingAudioPlayer.getInstance(activity as Context).show()?.setAppFloatingForNativeListener(object : FloatingAudioPlayerListener {
                    override fun onShow() {}
                    override fun onRemove() {}
                    
                    override fun onUpdateAudioInfo(audioModel: AudioModel?) {
                        run {
                            LogcatUtils.d(TAG, "FloatingAudioPlayerListener >>> onUpdateAudioInfo >> audioModel: $audioModel")
                            if (audioModel?.isPlaying ?: return) {
                                button_playPauseController.text = "暂停"
                            } else {
                                button_playPauseController.text = "播放"
                            }
                        }
                    }
                })?.designateView?.play(audioList)
            }
            R.id.button_closeAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).close()
            }
            R.id.button_lengthenAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.lengthen()
            }
            R.id.button_shortenAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.shorten()
            }
            
            R.id.button_playPauseController -> {
                when (button_playPauseController.text) {
                    "播放" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.start()
                    }
                    "暂停" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.pause()
                    }
                }
            }
            R.id.button_previous -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.previous()
            }
            R.id.button_next -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.next()
            }
            R.id.button_switchVoice -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.switchVoice()
            }
            R.id.button_stop -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.stop()
            }
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.comui_debug_fragment_homepage, container, false)
        return rootView
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    
    override fun initView() {
        button_propertyAnimation.setOnClickListener(onClickListener)
        button_tweenAnimation.setOnClickListener(onClickListener)
        button_recyclerView.setOnClickListener(onClickListener)
        
        button_showFloatingWithinApp.setOnClickListener(onClickListener)
        button_closeFloatingWithinApp.setOnClickListener(onClickListener)
        button_showFloatingWithinSystem.setOnClickListener(onClickListener)
        button_closeFloatingWithinSystem.setOnClickListener(onClickListener)
        
        button_showAutoShortedFloatingView.setOnClickListener(onClickListener)
        button_closeAutoShortedFloatingView.setOnClickListener(onClickListener)
        button_lengthenAutoShortedFloatingView.setOnClickListener(onClickListener)
        button_shortenAutoShortedFloatingView.setOnClickListener(onClickListener)
        
        button_playPauseController.setOnClickListener(onClickListener)
        button_previous.setOnClickListener(onClickListener)
        button_next.setOnClickListener(onClickListener)
        button_switchVoice.setOnClickListener(onClickListener)
        button_stop.setOnClickListener(onClickListener)
    }
}