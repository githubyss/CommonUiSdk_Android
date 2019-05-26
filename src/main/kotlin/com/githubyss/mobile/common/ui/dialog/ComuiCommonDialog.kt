package com.githubyss.mobile.common.ui.dialog

import android.annotation.SuppressLint
import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.resource.ComkitResUtils
import com.githubyss.mobile.common.ui.R

/**
 * ComuiCommonDialog
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiCommonDialog @SuppressLint("ValidFragment")
private constructor() : DialogFragment() {
    companion object {
        var instance = Holder.INSTANCE

        val TAG = ComuiCommonDialog::class.java.simpleName

        private val KEY_TITLE = "title"
        private val KEY_FIRST_LINE = "firstLine"
        private val KEY_SECOND_LINE = "secondLine"
        private val KEY_BTN_LEFT = "btnLeft"
        private val KEY_BTN_RIGHT = "btnRight"
        private val KEY_BTN_LEFT_HIGHLIGHT_STATUS = "btnLeftHighlightStatus"
        private val KEY_BTN_RIGHT_HIGHLIGHT_STATUS = "btnRightHighlightStatus"
        private val KEY_CENTER_STATUS = "centerStatus"


        fun showByMsg(manager: FragmentManager?,
                      titleStr: String = "", firstLineStr: String = "", secondLineStr: String = "",
                      btnLeftStr: String = "", btnRightStr: String = "",
                      onBtnLeftClickListener: View.OnClickListener? = null,
                      onBtnRightClickListener: View.OnClickListener? = null,
                      btnLeftHighlightStatus: Boolean = false, btnRightHighlightStatus: Boolean = true,
                      centerStatus: Boolean = true, cancelale: Boolean = true): ComuiCommonDialog {
            checkOld(manager)

            instance.onBtnLeftClickListener = onBtnLeftClickListener
            instance.onBtnRightClickListener = onBtnRightClickListener
            instance.isCancelable = cancelale

            val bundle = Bundle()
            bundle.putString(KEY_TITLE, titleStr)
            bundle.putString(KEY_FIRST_LINE, firstLineStr)
            bundle.putString(KEY_SECOND_LINE, secondLineStr)
            bundle.putString(KEY_BTN_LEFT, btnLeftStr)
            bundle.putString(KEY_BTN_RIGHT, btnRightStr)
            bundle.putBoolean(KEY_BTN_LEFT_HIGHLIGHT_STATUS, btnLeftHighlightStatus)
            bundle.putBoolean(KEY_BTN_RIGHT_HIGHLIGHT_STATUS, btnRightHighlightStatus)
            bundle.putBoolean(KEY_CENTER_STATUS, centerStatus)

            manager?.executePendingTransactions()
            if (!instance.isAdded) {
                instance.arguments = bundle
                instance.show(manager, TAG)
            }

            return instance
        }

        private fun checkOld(manager: FragmentManager?) {
            manager?.findFragmentByTag(TAG) ?: return

            val dialog = manager.findFragmentByTag(TAG)
            try {
                manager.executePendingTransactions()
                manager.beginTransaction().remove(dialog).commitAllowingStateLoss()
            } catch (exception: IllegalStateException) {
                ComkitLogcatUtils.e(t = exception)
            } catch (exception: Exception) {
                ComkitLogcatUtils.e(t = exception)
            }
        }
    }

    private object Holder {
        val INSTANCE = ComuiCommonDialog()
    }


    private var rootView: View? = null

    private var tvTitle: TextView? = null
    private var tvFirstLine: TextView? = null
    private var tvSecondLine: TextView? = null
    private var btnLeft: Button? = null
    private var btnRight: Button? = null
    private var vSeparator: View? = null

    private var onBtnLeftClickListener: View.OnClickListener? = null
    private var onBtnRightClickListener: View.OnClickListener? = null

    private var bundle: Bundle? = null


    private fun initView(view: View?) {
        tvTitle = view?.findViewById(R.id.tvTitle)
        tvFirstLine = view?.findViewById(R.id.tvFirstLine)
        tvSecondLine = view?.findViewById(R.id.tvSecondLine)
        btnLeft = view?.findViewById(R.id.btnLeft)
        btnRight = view?.findViewById(R.id.btnRight)
        vSeparator = view?.findViewById(R.id.vSeparator)

        tvTitle?.visibility = View.GONE
        tvFirstLine?.visibility = View.GONE
        tvSecondLine?.visibility = View.GONE
        btnLeft?.visibility = View.GONE
        btnRight?.visibility = View.GONE
        vSeparator?.visibility = View.GONE

        btnLeft?.setOnClickListener(onBtnLeftClickListener)
        btnRight?.setOnClickListener(onBtnRightClickListener)
    }

    private fun initData() {
        bundle = arguments
    }

    private fun refreshDialog() {
        val titleStr = bundle?.getString(KEY_TITLE, "")
        val firstLineStr = bundle?.getString(KEY_FIRST_LINE, "")
        val secondLineStr = bundle?.getString(KEY_SECOND_LINE, "")
        val btnLeftStr = bundle?.getString(KEY_BTN_LEFT, "")
        val btnRightStr = bundle?.getString(KEY_BTN_RIGHT, "")
        val btnLeftHighlightStatus = bundle?.getBoolean(KEY_BTN_LEFT_HIGHLIGHT_STATUS, false) ?: false
        val btnRightHighlightStatus = bundle?.getBoolean(KEY_BTN_RIGHT_HIGHLIGHT_STATUS, true) ?: true
        val centerStatus = bundle?.getBoolean(KEY_CENTER_STATUS, true) ?: true

        if (!TextUtils.isEmpty(titleStr)) {
            tvTitle?.text = titleStr
            tvTitle?.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(firstLineStr)) {
            tvFirstLine?.text = firstLineStr
            tvFirstLine?.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(secondLineStr)) {
            tvSecondLine?.text = secondLineStr
            tvSecondLine?.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(btnLeftStr)) {
            btnLeft?.text = btnLeftStr
            btnLeft?.visibility = View.VISIBLE
            if (btnLeftHighlightStatus) {
                btnLeft?.setTextColor(ComkitResUtils.getColor(resId = R.color.comresColor1F86ED))
            } else {
                btnLeft?.setTextColor(ComkitResUtils.getColor(resId = R.color.comresColor353D44))
            }
        }

        if (!TextUtils.isEmpty(btnRightStr)) {
            btnRight?.text = btnRightStr
            btnRight?.visibility = View.VISIBLE
            if (btnRightHighlightStatus) {
                btnRight?.setTextColor(ComkitResUtils.getColor(resId = R.color.comresColor1F86ED))
            } else {
                btnRight?.setTextColor(ComkitResUtils.getColor(resId = R.color.comresColor353D44))
            }
        }

        if (!TextUtils.isEmpty(btnLeftStr) && !TextUtils.isEmpty(btnRightStr)) {
            vSeparator?.visibility = View.VISIBLE
        }

        if (centerStatus) {
            tvTitle?.gravity = Gravity.CENTER_HORIZONTAL
            tvFirstLine?.gravity = Gravity.CENTER_HORIZONTAL
            tvSecondLine?.gravity = Gravity.CENTER_HORIZONTAL
        } else {
            tvTitle?.gravity = Gravity.START
            tvFirstLine?.gravity = Gravity.START
            tvSecondLine?.gravity = Gravity.START
        }

        btnLeft?.setOnClickListener { v ->
            dismissAllowingStateLoss()
            onBtnLeftClickListener?.onClick(v)
        }

        btnRight?.setOnClickListener { v ->
            dismissAllowingStateLoss()
            onBtnRightClickListener?.onClick(v)
        }
    }


    override fun show(manager: FragmentManager?, tag: String?) {
        val transaction = manager?.beginTransaction()
        transaction?.add(this, tag)
        transaction?.commitAllowingStateLoss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.comuiCommonDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.comui_dialog_common, container, false)

        initView(rootView)
        initData()
        refreshDialog()

        return rootView
    }
}
