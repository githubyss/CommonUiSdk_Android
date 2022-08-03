package com.githubyss.mobile.common.ui.dialog.hint

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.util.getColorFromRes
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.kit.util.showToast
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiDialogHintBinding
import com.githubyss.mobile.common.ui.dialog.binding_reflect.BaseReflectBindingViewModelDialogFragment
import java.util.*


/**
 * HintDialog
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/02 15:30:25
 */
object HintDialog : BaseReflectBindingViewModelDialogFragment<ComuiDialogHintBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    private val TAG = HintDialog::class.java.simpleName

    private const val KEY_TITLE = "title"
    private const val KEY_CONTENT = "content"
    private const val KEY_BTN_LEFT = "btnLeft"
    private const val KEY_BTN_RIGHT = "btnRight"


    /** ****************************** Properties ****************************** */

    /**  */
    private lateinit var bundle: Bundle
    private val hintDialogVm by viewModels<HintDialogVm>()
    private val onClickPresenter by lazy { OnClickPresenter() }

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
        bundle = arguments ?: return

        val titleStr = bundle.getString(KEY_TITLE) ?: ""
        val contentStr = bundle.getString(KEY_CONTENT) ?: ""
        val btnLeftStr = bundle.getString(KEY_BTN_LEFT) ?: ""
        val btnRightStr = bundle.getString(KEY_BTN_RIGHT) ?: ""
        hintDialogVm.setupData(titleStr, contentStr, btnLeftStr, btnRightStr)
    }

    /**  */
    override fun bindXmlData() {
        binding.hintDialogVm = hintDialogVm
        binding.onClickPresenter = onClickPresenter
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
    fun showDialog(fragmentManager: FragmentManager?, titleStr: String = "", contentStr: String = "", btnConfirmStr: String = "", btnCancelStr: String = "", cancelable: Boolean = true): HintDialog {
        this.isCancelable = cancelable

        val bundle = Bundle()
        bundle.putString(KEY_TITLE, titleStr)
        bundle.putString(KEY_CONTENT, contentStr)
        bundle.putString(KEY_BTN_LEFT, btnConfirmStr)
        bundle.putString(KEY_BTN_RIGHT, btnCancelStr)

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
    class OnClickPresenter {
        fun onBtnLeftClick(v: View) {
            logD(msg = "点击了左按钮")
            showToast("点击了左按钮")
            binding.tvTitle.setTextColor(getColorFromRes(R.color.comres_color_red))
            list.add(Random().nextInt())
            logD(msg = list.toString())
        }

        fun onBtnRightClick(v: View) {
            logD(msg = "点击了右按钮")
            showToast("点击了右按钮")
            binding.tvTitle.setTextColor(getColorFromRes(R.color.comres_color_blue))
            dismissAllowingStateLoss()
        }
    }
}
