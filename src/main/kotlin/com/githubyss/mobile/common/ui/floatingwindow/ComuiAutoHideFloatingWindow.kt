package com.githubyss.mobile.common.ui.floatingwindow

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresPermission
import android.view.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.processor.ComkitScreenProcessor
import com.githubyss.mobile.common.ui.R
import java.lang.Exception
import java.lang.ref.WeakReference


/**
 * ComuiAutoHideFloatingWindow
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiAutoHideFloatingWindow private constructor(context: Context) : View.OnClickListener, View.OnTouchListener, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    companion object {
        @Volatile
        var instance: ComuiAutoHideFloatingWindow? = null

        fun getInstance(context: Context): ComuiAutoHideFloatingWindow? {
            if (instance == null) {
                synchronized(ComuiAutoHideFloatingWindow::class) {
                    if (instance == null) {
                        instance = ComuiAutoHideFloatingWindow(context)
                    }
                }
            }
            return instance
        }
    }


    private val AUTO_HIDE_DELAY = 1000L
    private val ANIMATION_DURATION = 500L

    private val MSG_HIDE_FLOATING_WINDOW = 0x01

    private val FAULT_VALUE = -1

    //    private var windowView: ViewGroup? = null
    private var rootView: ViewGroup? = null

    private var btnCenter: Button? = null
    private var llTouchMove: LinearLayout? = null

    private var slideDownAnimator: ObjectAnimator? = null
    private var slideUpAnimator: ObjectAnimator? = null

    private var windowManager: WindowManager? = null
    private var windowLayoutParams: WindowManager.LayoutParams? = null
//    private var rootLayoutParams: FrameLayout.LayoutParams? = null

    private var beShown = false
    private var beAnimating = false
    private var beSlidingDown = false
    private var beSlidingUp = false

    private var rootViewMeasuredHeight = FAULT_VALUE
    private var rootViewCoordinateY = FAULT_VALUE
    private var moveBeginCoordinateY = FAULT_VALUE
    private var moveCurrentCoordinateY = FAULT_VALUE
    private var moveDeltaCoordinateY = FAULT_VALUE

    private var autoHideHandler: AutoHideHandler? = null
    private var autoHideRunnable: AutoHideRunnable? = null


    init {
        inflateView(context)
        initView(rootView)
        initLayoutParams()
    }


    private class AutoHideHandler constructor(val floatingWindowWeakRefComui: WeakReference<ComuiAutoHideFloatingWindow>) : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                floatingWindowWeakRefComui.get()?.MSG_HIDE_FLOATING_WINDOW -> {
                    floatingWindowWeakRefComui.get()?.hide()
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


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCenter -> hide()
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (beAnimating) {
            return true
        }

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                moveBeginCoordinateY = event.rawY.toInt()
                autoHideHandler?.removeCallbacks(autoHideRunnable)

                logcatViewProperty(rootView, "ACTION_DOWN rootView\t")
            }

            MotionEvent.ACTION_MOVE -> {
                moveCurrentCoordinateY = event.rawY.toInt()
                moveDeltaCoordinateY = moveCurrentCoordinateY - moveBeginCoordinateY

                if ((windowLayoutParams?.y ?: 0) >= 0) {
                    windowLayoutParams?.y = windowLayoutParams?.y?.plus(moveDeltaCoordinateY)
                    windowManager?.updateViewLayout(rootView, windowLayoutParams)
                } else {

                }

                moveBeginCoordinateY = moveCurrentCoordinateY

                logcatViewProperty(rootView, "ACTION_MOVE rootView\t")
            }

            MotionEvent.ACTION_UP -> {
                moveCurrentCoordinateY = event.rawY.toInt()
                moveDeltaCoordinateY = moveCurrentCoordinateY - moveBeginCoordinateY

                if ((windowLayoutParams?.y ?: 0) < 0) {
                    hide()
                } else {

                }

                logcatViewProperty(rootView, "ACTION_UP rootView\t\t")
            }

            MotionEvent.ACTION_CANCEL -> {
                slideDownAnimator?.cancel()
                slideUpAnimator?.cancel()
                autoHideHandler?.removeCallbacks(autoHideRunnable)
            }
        }

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

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        logcatViewProperty(rootView, "onAnimationUpdate rootView\t\t")
    }


    @RequiresPermission(android.Manifest.permission.SYSTEM_ALERT_WINDOW)
    fun show() {
        addView()
        slideDown()
    }

    fun hide() {
        slideUp()
    }

    private fun inflateView(context: Context) {
        val inflater = LayoutInflater.from(context.applicationContext)
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.comui_auto_hide_floating_window, null, false) as FrameLayout
        }

        rootView?.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        rootViewMeasuredHeight = rootView?.measuredHeight ?: 0
        logcatViewProperty(rootView, "After measure rootView\t\t\t")
    }

    private fun initView(view: View?) {
        btnCenter = view?.findViewById(R.id.btnCenter)
        llTouchMove = view?.findViewById(R.id.llTouchMove)
        btnCenter?.setOnClickListener(this@ComuiAutoHideFloatingWindow)
        llTouchMove?.setOnTouchListener(this@ComuiAutoHideFloatingWindow)
    }

    private fun initLayoutParams() {
        if (windowManager == null) {
            windowManager = ComkitScreenProcessor.windowManager()
        }

        if (windowLayoutParams == null) {
            windowLayoutParams = WindowManager.LayoutParams()
        }

        windowLayoutParams?.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        windowLayoutParams?.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        windowLayoutParams?.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
//        windowLayoutParams?.format = PixelFormat.TRANSLUCENT
        windowLayoutParams?.alpha = 0.9F
        windowLayoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        windowLayoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT

        /* The x here means the offset to the gravity, and will be ignored when the gravity is default. Details see in "https://developer.android.google.cn/reference/android/view/WindowManager.LayoutParams" */
        windowLayoutParams?.x = 0
        windowLayoutParams?.y = 0
    }

    private fun addView() {
        try {
            if (!beShown) {
                windowManager?.addView(rootView, windowLayoutParams)
                beShown = true
            }
        } catch (e: Exception) {
            ComkitLogcatUtils.e(msg = e.toString())
        }

        logcatViewProperty(rootView, "After addView rootView\t\t\t")
    }

    private fun removeView() {
        try {
            if (beShown) {
                windowManager?.removeView(rootView)
                beShown = false
            }
        } catch (e: Exception) {
            ComkitLogcatUtils.e(msg = e.toString())
        }

        logcatViewProperty(rootView, "After removeView rootView\t\t")
        instance = null
    }

    private fun slideDown() {
        initSlideDownAnimator(rootView)
        if (beShown && !beAnimating) {
            slideDownAnimator?.start()
            beSlidingDown = true
        }
    }

    private fun slideUp() {
        initSlideUpAnimator(rootView)
        if (beShown && !beAnimating) {
            slideUpAnimator?.start()
            beSlidingUp = true
        }
    }

    private fun initSlideDownAnimator(view: View?) {
        val beginY: Float
        val endY: Float
        rootView?.y = -rootViewMeasuredHeight.toFloat()
        when (rootViewCoordinateY) {
            FAULT_VALUE -> {
                beginY = -rootViewMeasuredHeight.toFloat()
                endY = 0F
            }

            else -> {
                beginY = -(rootViewCoordinateY + rootViewMeasuredHeight).toFloat()
                endY = 0F
            }
        }
        slideDownAnimator = ObjectAnimator.ofFloat(view, "translationY", -rootViewMeasuredHeight.toFloat(), 0F)
        slideDownAnimator?.duration = ANIMATION_DURATION
        slideDownAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
        slideDownAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)

        logcatViewProperty(rootView, "initSlideDownAnimator rootView\t")
    }

    private fun initSlideUpAnimator(view: View?) {
        val beginY: Float
        val endY: Float
        when (rootViewCoordinateY) {
            FAULT_VALUE -> {
                beginY = rootView?.y ?: 0F
                endY = -((rootView?.y ?: 0F) + (rootView?.measuredHeight ?: 0))
            }

            else -> {
                beginY = rootViewCoordinateY.toFloat()
//                endY =
            }
        }

        slideUpAnimator = ObjectAnimator.ofFloat(view, "translationY", 0F, -rootViewMeasuredHeight.toFloat())
        slideUpAnimator?.duration = ANIMATION_DURATION
        slideUpAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
        slideUpAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)

        logcatViewProperty(rootView, "initSlideUpAnimator rootView\t")
    }

    private fun autoHide(canAutoHide: Boolean) {
        if (canAutoHide) {
            if (autoHideHandler == null) autoHideHandler = AutoHideHandler(WeakReference(this@ComuiAutoHideFloatingWindow))
            if (autoHideRunnable == null) autoHideRunnable = AutoHideRunnable()
            autoHideHandler?.postDelayed(autoHideRunnable, AUTO_HIDE_DELAY)
        }
    }

    private fun logcatViewProperty(view: View?, location: String = "") {
        val coordinate = IntArray(2)
        view?.getLocationOnScreen(coordinate)
        ComkitLogcatUtils.d(
                msg = "$location: " +
                        "view-> " +
                        "{x:${view?.x?.toInt()}, y:${view?.y?.toInt()}}\t" +
                        "{cX:${coordinate[0]}, cY:${coordinate[1]}}\t" +
                        "{w:${view?.width}, h:${view?.height}}\t" +
                        "{mW:${view?.measuredWidth}, mH:${view?.measuredHeight}}\t" +
                        "{t:${view?.top}, b:${view?.bottom}, l:${view?.left}, r:${view?.right}}\t" +
                        "params-> " +
                        "{x:${windowLayoutParams?.x}, y:${windowLayoutParams?.y}}\t" +
                        "{w:${windowLayoutParams?.width}, h:${windowLayoutParams?.height}}\t"
        )
    }

//    private fun logcatCoordinate(location: String = "") {
//        ComkitLogcatUtils.d(msg = "$location: {coordinateLocationRoot:{${coordinateLocationRoot[0]}, ${coordinateLocationRoot[1]}}}   {coordinateLocationWindow:{${coordinateLocationWindow[0]}, ${coordinateLocationWindow[1]}}}   {rootLayoutParams.topMargin:${rootLayoutParams.topMargin}}   {windowLayoutParams.y = ${windowLayoutParams.y}}")
//    }

//    private fun logcatViewProperty(view: View?, location: String = "") {
//        ComkitLogcatUtils.d(msg = "$location view: {x:${ComkitScreenProcessor.px2Dp(input = view?.x)}, y:${ComkitScreenProcessor.px2Dp(input = view?.y)}} {width:${ComkitScreenProcessor.px2Dp(input = view?.width?.toFloat())}, height:${ComkitScreenProcessor.px2Dp(input = view?.height?.toFloat())}} {measuredWidth:${ComkitScreenProcessor.px2Dp(input = view?.measuredWidth?.toFloat())}, measuredHeight:${ComkitScreenProcessor.px2Dp(input = view?.measuredHeight?.toFloat())}} {top:${ComkitScreenProcessor.px2Dp(input = view?.top?.toFloat())}, bottom:${ComkitScreenProcessor.px2Dp(input = view?.bottom?.toFloat())}, left:${ComkitScreenProcessor.px2Dp(input = view?.left?.toFloat())}, right:${ComkitScreenProcessor.px2Dp(input = view?.right?.toFloat())}}")
//    }
}
