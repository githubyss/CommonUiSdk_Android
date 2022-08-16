package com.githubyss.mobile.common.ui.dialog.hint

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelDialogFragment
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.kit.util.showToast
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiDialogHintBinding


/**
 * HintDialog
 * 一个标题、一个内容、两个按钮的对话框
 *
 * 不要用单例实现，因为单例的对话框会使多个弹框的成员变量混乱。
 * 但是可以使用 instance 的 get() 方法，将对话框的创建放在对话框内部，暴露 instance 供外部调用。效果同 newInstance()。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/02 15:30:25
 */
class HintDialog : BaseReflectBindingViewModelDialogFragment<ComuiDialogHintBinding>() {

    /** ****************************** Companion ****************************** */

    companion object {
        val instance get() = HintDialog()
        // fun newInstance() = HintDialog()
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val TAG = HintDialog::class.java.simpleName

    private val KEY_TITLE = "title"
    private val KEY_CONTENT = "content"
    private val KEY_BTN_LEFT = "btnLeft"
    private val KEY_BTN_RIGHT = "btnRight"

    /**  */
    private lateinit var onBtnLeftClick: () -> Unit
    private lateinit var onBtnRightClick: () -> Unit

    /**  */
    private val hintDialogVm by viewModels<HintDialogVm>()

    /**  */
    private val onClickListener by lazy {
        object : OnClickListener {
            override fun onBtnLeftClick() {
                this@HintDialog.onBtnLeftClickDefault()
                this@HintDialog.onBtnLeftClick()
            }

            override fun onBtnRightClick() {
                this@HintDialog.onBtnRightClickDefault()
                this@HintDialog.onBtnRightClick()
            }
        }
    }

    private val list = arrayListOf<Int>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setStyle(STYLE_NO_FRAME, R.style.comui_dialog_style)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**  */
    override fun setupData() {
        val bundle = arguments ?: return

        val titleStr = bundle.getString(KEY_TITLE) ?: ""
        val contentStr = bundle.getString(KEY_CONTENT) ?: ""
        val btnLeftStr = bundle.getString(KEY_BTN_LEFT) ?: ""
        val btnRightStr = bundle.getString(KEY_BTN_RIGHT) ?: ""
        hintDialogVm.setupData(titleStr, contentStr, btnLeftStr, btnRightStr)
    }

    /**  */
    override fun bindXmlData() {
        binding.hintDialogVm = hintDialogVm
        // binding.onClickPresenter = onClickPresenter
        binding.onClickListener = onClickListener
        binding.hintDialogView = this
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
    @JvmOverloads
    fun showDialog(fragmentManager: FragmentManager?, titleStr: String = "", contentStr: String = "", btnLeftStr: String = "", btnRightStr: String = "", onBtnLeftClick: () -> Unit = {}, onBtnRightClick: () -> Unit = {}, cancelable: Boolean = true): HintDialog {
        this.isCancelable = cancelable
        this.onBtnLeftClick = onBtnLeftClick
        this.onBtnRightClick = onBtnRightClick

        val bundle = Bundle()
        bundle.putString(KEY_TITLE, titleStr)
        bundle.putString(KEY_CONTENT, contentStr)
        bundle.putString(KEY_BTN_LEFT, btnLeftStr)
        bundle.putString(KEY_BTN_RIGHT, btnRightStr)

        fragmentManager?.executePendingTransactions()
        fragmentManager?.let {
            if (!this.isAdded) {
                this.arguments = bundle
                this.show(fragmentManager, TAG)
            }
        }

        return this
    }


    /** ****************************** Class ****************************** */

    /**  */
    private fun onBtnLeftClickDefault() {
        logD(msg = "点击了左按钮")
        showToast("点击了左按钮")
        dismissAllowingStateLoss()
    }

    private fun onBtnRightClickDefault() {
        logD(msg = "点击了右按钮")
        showToast("点击了右按钮")
        dismissAllowingStateLoss()
    }


    /** ****************************** Interface ****************************** */

    /**  */
    interface OnClickListener {
        fun onBtnLeftClick()
        fun onBtnRightClick()
    }
}
