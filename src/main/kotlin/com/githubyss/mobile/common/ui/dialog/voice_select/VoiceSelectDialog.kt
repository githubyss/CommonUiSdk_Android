package com.githubyss.mobile.common.ui.dialog.voice_select

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.kit.util.showToast
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiDialogVoiceSelectBinding
import com.githubyss.mobile.common.ui.dialog.binding_reflect.BaseReflectBindingViewModelDialogFragment
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BaseBindingRecyclerViewAdapter


/**
 * VoiceSelectDialog
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/25 16:34:10
 */
class VoiceSelectDialog @SuppressLint("ValidFragment") private constructor() : BaseReflectBindingViewModelDialogFragment<ComuiDialogVoiceSelectBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        var instance = Holder.INSTANCE

        private val TAG = VoiceSelectDialog::class.java.simpleName

        private const val KEY_TITLE = "title"
        private const val KEY_BTN_CONFIRM = "btnConfirm"
        private const val KEY_BTN_CANCEL = "btnCancel"
        private const val KEY_VOICE_TONE_LIST = "voiceToneList"
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private lateinit var bundle: Bundle
    private val voiceSelectDialogVm by viewModels<VoiceSelectDialogVm>()
    private val onClickPresenter by lazy { OnClickPresenter() }
    private val voiceToneListAdapter by lazy { VoiceToneListAdapter() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setStyle(STYLE_NO_FRAME, R.style.comui_dialog_style)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**  */
    override fun setupData() {
        bundle = arguments ?: return

        val titleStr = bundle.getString(KEY_TITLE) ?: ""
        val btnConfirmStr = bundle.getString(KEY_BTN_CONFIRM) ?: ""
        val btnCancelStr = bundle.getString(KEY_BTN_CANCEL) ?: ""
        val voiceToneList = bundle.getParcelableArrayList<VoiceTone>(KEY_VOICE_TONE_LIST) ?: ArrayList()
        voiceSelectDialogVm.setupData(titleStr, btnConfirmStr, btnCancelStr, voiceToneList)
        voiceToneListAdapter.updateDataList(voiceToneList)
        voiceToneListAdapter.onItemClickListener = object : BaseBindingRecyclerViewAdapter.OnItemClickListener<VoiceTone> {
            override fun onItemClick(data: VoiceTone) {
                logD(TAG, "点击了项目")
                showToast("点击了项目")
                voiceSelectDialogVm.voiceToneListSelected(data)
            }
        }
    }

    /**  */
    override fun bindXmlData() {
        binding.voiceSelectDialogVm = voiceSelectDialogVm
        binding.onClickPresenter = onClickPresenter
        binding.voiceToneListAdapter = voiceToneListAdapter
    }

    /**  */
    override fun observeViewModelData() {
    }

    /**  */
    override fun removeViewModelObserver() {
    }

    /**  */
    override fun show(manager: FragmentManager, tag: String?) {
        // 父类中的 show 方法使用的是 ft.commit()，而使用这个方法，有可能会出现 Can not perform this action after onSaveInstanceState 报错。
        // super.show(manager, tag)

        // 稳定起见，使用 ft.commitAllowingStateLoss() 来重写覆盖。
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun showDialog(fragmentManager: FragmentManager?, titleStr: String = "", btnConfirmStr: String = "", btnCancelStr: String = "", voiceToneList: ArrayList<VoiceTone>? = ArrayList(), cancelable: Boolean = true): VoiceSelectDialog {
        instance.isCancelable = cancelable

        val bundle = Bundle()
        bundle.putString(KEY_TITLE, titleStr)
        bundle.putString(KEY_BTN_CONFIRM, btnConfirmStr)
        bundle.putString(KEY_BTN_CANCEL, btnCancelStr)
        bundle.putParcelableArrayList(KEY_VOICE_TONE_LIST, voiceToneList)

        fragmentManager?.executePendingTransactions()
        fragmentManager?.let {
            if (!instance.isAdded) {
                instance.arguments = bundle
                instance.show(fragmentManager, TAG)
            }
        }

        return instance
    }


    /** ****************************** Class ****************************** */

    /**  */
    private object Holder {
        val INSTANCE = VoiceSelectDialog()
    }

    /**  */
    inner class OnClickPresenter {
        fun onConfirmClick(v: View) {
            logD(msg = "点击了确认")
            showToast("点击了确认")
            dismissAllowingStateLoss()
        }

        fun onCancelClick(v: View) {
            logD(msg = "点击了取消")
            showToast("点击了取消")
            dismissAllowingStateLoss()
        }
    }
}
