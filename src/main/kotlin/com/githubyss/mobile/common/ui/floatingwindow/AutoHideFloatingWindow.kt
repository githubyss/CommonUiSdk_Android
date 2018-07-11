package com.githubyss.mobile.common.ui.floatingwindow

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresPermission
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.processor.ComkitScreenProcessor
import com.githubyss.mobile.common.ui.R
import java.lang.ref.WeakReference


/**
 * AutoHideFloatingWindow
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class AutoHideFloatingWindow private constructor(context: Context) : View.OnTouchListener, Animator.AnimatorListener {
    companion object {
        @Volatile
        var instance: AutoHideFloatingWindow? = null

        fun getInstance(context: Context): AutoHideFloatingWindow? {
            if (instance == null) {
                synchronized(AutoHideFloatingWindow::class) {
                    if (instance == null) {
                        instance = AutoHideFloatingWindow(context)
                    }
                }
            }
            return instance
        }
    }


    private val AUTO_HIDE_DELAY = 1000L
    private val ANIMATION_DURATION = 500L

    private val MSG_HIDE_FLOATING_WINDOW = 0x01

    private var rootView: ViewGroup? = null

    private var btnCenter: Button? = null
    private var llTouchMove: LinearLayout? = null

    private var slideDownAnimator: ObjectAnimator? = null
    private var slideUpAnimator: ObjectAnimator? = null

    private var windowManager: WindowManager? = null
    private var layoutParams: WindowManager.LayoutParams? = null

    private var beShown = false
    private var beAnimating = false
    private var beSlidingDown = false
    private var beSlidingUp = false

    private var rootViewCoordinateY = 0
    private var moveBeginCoordinateY = 0
    private var moveCurrentCoordinateY = 0
    private var moveDeltaCoordinateY = 0

    private var autoHideHandler: AutoHideHandler? = null
    private var autoHideRunnable: AutoHideRunnable? = null


    init {
        inflateRootView(context)
        initView(rootView)
        initLayoutParams()
        initAnimator(rootView)
    }


    private class AutoHideHandler constructor(val floatingWindowWeakRef: WeakReference<AutoHideFloatingWindow>) : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                floatingWindowWeakRef.get()?.MSG_HIDE_FLOATING_WINDOW -> {
                    floatingWindowWeakRef.get()?.hide()
                }
            }
        }
    }

    private inner class AutoHideRunnable : Runnable {
        override fun run() {
            if (beShown && !beAnimating) {
                autoHideHandler?.sendMessage(autoHideHandler?.obtainMessage(MSG_HIDE_FLOATING_WINDOW))
            }
        }
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true
    }

    override fun onAnimationStart(animator: Animator?) {
        beAnimating = true
    }

    override fun onAnimationRepeat(animator: Animator?) {
        beAnimating = true
    }

    override fun onAnimationEnd(animator: Animator?) {
        beAnimating = false

        when {
            beSlidingDown -> {
                beSlidingDown = false
                autoHide(true)
            }

            beSlidingUp -> {
                beSlidingUp = false
                removeView()
            }
        }
    }

    override fun onAnimationCancel(animator: Animator?) {
        beAnimating = false
    }


    @RequiresPermission(android.Manifest.permission.SYSTEM_ALERT_WINDOW)
    fun show() {
        addView()
        slideDown()
    }

    fun hide() {
        slideUp()
    }

    private fun inflateRootView(context: Context) {
        val inflater = LayoutInflater.from(context.applicationContext)
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.comui_auto_hide_floating_window, null, false) as LinearLayout
        }
        rootView?.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        logcatViewProperty(rootView, "After measure\t\t")
    }

    private fun initView(view: View?) {
        btnCenter = view?.findViewById(R.id.btnCenter)
        llTouchMove = view?.findViewById(R.id.llTouchMove)
        llTouchMove?.setOnTouchListener(this@AutoHideFloatingWindow)
    }

    private fun initLayoutParams() {
        if (windowManager == null) {
            windowManager = ComkitScreenProcessor.windowManager()
        }

        if (layoutParams == null) {
            layoutParams = WindowManager.LayoutParams()
        }

        layoutParams?.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        layoutParams?.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        layoutParams?.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        layoutParams?.format = PixelFormat.TRANSLUCENT
        layoutParams?.alpha = 0.9F
        layoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams?.x = 0
        layoutParams?.y = 0 - (rootView?.measuredHeight ?: 0)
    }

    private fun initAnimator(view: View?) {
        slideDownAnimator = ObjectAnimator.ofFloat(view, "translationY", layoutParams?.y?.toFloat() ?: 0F, 0F)
        slideDownAnimator?.duration = ANIMATION_DURATION
        slideDownAnimator?.addListener(this@AutoHideFloatingWindow)

        slideUpAnimator = ObjectAnimator.ofFloat(view, "translationY", 0F, layoutParams?.y?.toFloat() ?: 0F)
        slideUpAnimator?.duration = ANIMATION_DURATION
        slideUpAnimator?.addListener(this@AutoHideFloatingWindow)
    }

    private fun addView() {
        ComkitLogcatUtils.d(msg = "beShown = $beShown")
        logcatViewProperty(rootView, "Before addView\t\t")
        if (!beShown) {
            windowManager?.addView(rootView, layoutParams)
            beShown = true
        }
        logcatViewProperty(rootView, "After addView\t\t")
    }

    private fun removeView() {
        ComkitLogcatUtils.d(msg = "beShown = $beShown")
        logcatViewProperty(rootView, "Before removeView\t")
        if (beShown) {
            windowManager?.removeView(rootView)
            beShown = false
        }
        logcatViewProperty(rootView, "After removeView\t")
    }

    private fun slideDown() {
        if (beShown && !beAnimating) {
            slideDownAnimator?.start()
            beSlidingDown = true
        }
    }

    private fun slideUp() {
        if (beShown && !beAnimating) {
            slideUpAnimator?.start()
            beSlidingUp = true
        }
    }

    private fun autoHide(canAutoHide: Boolean) {
        if (canAutoHide) {
            if (autoHideHandler == null) autoHideHandler = AutoHideHandler(WeakReference(this@AutoHideFloatingWindow))
            if (autoHideRunnable == null) autoHideRunnable = AutoHideRunnable()
            autoHideHandler?.postDelayed(autoHideRunnable, AUTO_HIDE_DELAY)
        }
    }

    private fun logcatViewProperty(view: View?, location: String = "") {
        ComkitLogcatUtils.d(msg = "$location view: {x:${view?.x?.toInt()}, y:${view?.y?.toInt()}} {width:${view?.width}, height:${view?.height}} {measuredWidth:${view?.measuredWidth}, measuredHeight:${view?.measuredHeight}} {top:${view?.top}, bottom:${view?.bottom}, left:${view?.left}, right:${view?.right}}")
    }

//    private fun logcatViewProperty(view: View?, location: String = "") {
//        ComkitLogcatUtils.d(msg = "$location view: {x:${ComkitScreenProcessor.px2Dp(input = view?.x)}, y:${ComkitScreenProcessor.px2Dp(input = view?.y)}} {width:${ComkitScreenProcessor.px2Dp(input = view?.width?.toFloat())}, height:${ComkitScreenProcessor.px2Dp(input = view?.height?.toFloat())}} {measuredWidth:${ComkitScreenProcessor.px2Dp(input = view?.measuredWidth?.toFloat())}, measuredHeight:${ComkitScreenProcessor.px2Dp(input = view?.measuredHeight?.toFloat())}} {top:${ComkitScreenProcessor.px2Dp(input = view?.top?.toFloat())}, bottom:${ComkitScreenProcessor.px2Dp(input = view?.bottom?.toFloat())}, left:${ComkitScreenProcessor.px2Dp(input = view?.left?.toFloat())}, right:${ComkitScreenProcessor.px2Dp(input = view?.right?.toFloat())}}")
//    }
}
