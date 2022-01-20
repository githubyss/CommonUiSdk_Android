package com.githubyss.mobile.common.ui.app.page.speech_recognition.widget

import android.content.Context
import android.util.AttributeSet
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.speech_recognition.util.GuideUtil


class VoiceHomeLayout : FrameLayout {
    enum class ViewType(val nativeInt: Int) {
        /**
         * 默认引导页
         */
        Guide(0),

        /**
         * 用户已经按住语音按钮，准备开始讲话
         */
        BeginSpeaking(1),

        /**
         * 用户开始松开语音，SDK未能识别到语音
         */
        Unheard(2),

        /**
         * 更多信息
         */
        More(3),

        /**
         * 无网络
         */
        NoNetwork(4);
    }

    /** ****************************** Properties ****************************** */

    private val defaultLayout = SparseIntArray(5)
    private val cachedLayout = SparseArray<View?>(5)
    private var inflater: LayoutInflater? = null

    var currentViewType: ViewType? = null
        private set

    var mPhoneChargeClickListener: OnClickListener? = null
    var mTransferClickListener: OnClickListener? = null
    var mLifePaymentClickListener: OnClickListener? = null
    var mPlayClickListener: OnClickListener? = null
    var mTravelClickListener: OnClickListener? = null
    var mCreditCardClickListener: OnClickListener? = null

    // 控制引导页切换引导文本
    private var guideUtil: GuideUtil? = null


    /** ****************************** Constructors ****************************** */

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }


    /** ****************************** Functions ****************************** */

    private fun init(context: Context, attrs: AttributeSet?) {
        if (isInEditMode) return
        inflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.VoiceHomeLayout)
            if (a != null) {
                setDefaultView(ViewType.Guide, a.getResourceId(
                    R.styleable.VoiceHomeLayout_guideView, R.layout.view_voice_guide))
                setDefaultView(ViewType.BeginSpeaking, a.getResourceId(
                    R.styleable.VoiceHomeLayout_beginSpeakingView, R.layout.view_voice_begin_speaking))
                setDefaultView(ViewType.Unheard, a.getResourceId(
                    R.styleable.VoiceHomeLayout_unHeardView, R.layout.view_voice_unheard))
                setDefaultView(ViewType.More, a.getResourceId(
                    R.styleable.VoiceHomeLayout_moreView, R.layout.view_voice_more))
                setDefaultView(ViewType.NoNetwork, a.getResourceId(
                    R.styleable.VoiceHomeLayout_noNetWorkView, R.layout.view_voice_no_network))
                a.recycle()
            }
        }
    }

    fun setDefaultView(viewType: ViewType, resLayout: Int) {
        defaultLayout.put(viewType.nativeInt, resLayout)
    }

    fun showView(viewType: ViewType) {
        currentViewType = viewType
        val count = defaultLayout.size()
        for (i in 0 until count) {
            val key = defaultLayout.keyAt(i)
            if (key == viewType.nativeInt) {
                doShowView(viewType)
            }
            else {
                hideViewByKey(key)
            }
        }
    }

    fun hideAllView() {
        val count = defaultLayout.size()
        for (i in 0 until count) {
            val key = defaultLayout.keyAt(i)
            hideViewByKey(key)
        }
    }

    private fun hideViewByKey(key: Int) {
        val view = cachedLayout[key] ?: return
        view.visibility = GONE
    }

    private fun doShowView(viewType: ViewType) {
        val resLayoutId = defaultLayout[viewType.nativeInt]
        check(resLayoutId > 0) { "layout is not set for $viewType" }
        var view = cachedLayout[viewType.nativeInt]
        if (view == null) {
            view = inflater!!.inflate(resLayoutId, null)
            cachedLayout.put(viewType.nativeInt, view)
            addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER))
        }
        initListener(view)
        view!!.visibility = VISIBLE
        view.bringToFront()
    }

    private fun initListener(view: View?) {
        val guideView = view!!.findViewById<View>(R.id.guideView)
        if (guideView != null) {
            if (guideUtil == null) guideUtil = GuideUtil(guideView)
            guideUtil!!.startGuide()
        }
        else {
            guideUtil!!.cancel()
        }
        val phoneCharge = view.findViewById<View>(R.id.phone_charge)
        if (phoneCharge != null && mPhoneChargeClickListener != null) {
            phoneCharge.setOnClickListener { v -> mPhoneChargeClickListener!!.onClick(v) }
        }
        val transfer = view.findViewById<View>(R.id.transfer)
        if (transfer != null && mTransferClickListener != null) {
            transfer.setOnClickListener { v -> mTransferClickListener!!.onClick(v) }
        }
        val lifepayment = view.findViewById<View>(R.id.lifepayment)
        if (lifepayment != null && mLifePaymentClickListener != null) {
            lifepayment.setOnClickListener { v -> mLifePaymentClickListener!!.onClick(v) }
        }
        val play = view.findViewById<View>(R.id.play)
        if (play != null && mPlayClickListener != null) {
            play.setOnClickListener { v -> mPlayClickListener!!.onClick(v) }
        }
        val travel = view.findViewById<View>(R.id.travel)
        if (travel != null && mTravelClickListener != null) {
            travel.setOnClickListener { v -> mTravelClickListener!!.onClick(v) }
        }
        val creditCard = view.findViewById<View>(R.id.creditcard)
        if (creditCard != null && mCreditCardClickListener != null) {
            creditCard.setOnClickListener { v -> mCreditCardClickListener!!.onClick(v) }
        }
    }
}