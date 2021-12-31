package com.githubyss.mobile.common.ui.floatingwindow

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Application
import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Message
import android.view.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresPermission
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.kit.util.SystemUtils
import com.githubyss.mobile.common.ui.R
import java.lang.ref.WeakReference


/**
 * ComuiAutoHideFloatingWindow
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiAutoHideFloatingWindow private constructor() : View.OnClickListener, View.OnTouchListener, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    // companion object {
    //     @Volatile
    //     var instance: ComuiAutoHideFloatingWindow? = null
    //
    //     fun instance(application: Application): ComuiAutoHideFloatingWindow? {
    //         if (instance == null) {
    //             synchronized(ComuiAutoHideFloatingWindow::class) {
    //                 if (instance == null) {
    //                     instance = ComuiAutoHideFloatingWindow(application)
    //                 }
    //             }
    //         }
    //         return instance
    //     }
    // }

    companion object {
        var instance = Holder.INSTANCE

        private val TAG: String = ComuiAutoHideFloatingWindow::class.java.simpleName
    }

    private object Holder {
        val INSTANCE = ComuiAutoHideFloatingWindow()
    }


    private val AUTO_HIDE_DELAY = 1000L
    private val ANIMATION_DURATION = 500L

    private val MSG_HIDE_FLOATING_WINDOW = 0x01

    private val FAULT_VALUE = -1

    private var rootView: ViewGroup? = null

    private var viewHolder: ViewHolder? = null

    // private var slideDownAnimator: ObjectAnimator? = null
    // private var slideUpAnimator: ObjectAnimator? = null

    private var slideDownAnimator: ValueAnimator? = null
    private var slideUpAnimator: ValueAnimator? = null

    private var windowManager: WindowManager? = null
    private var windowLayoutParams: WindowManager.LayoutParams? = null

    private var beShown = false
    private var beAnimating = false
    private var beSlidingDown = false
    private var beSlidingUp = false

    private var rootViewMeasuredHeight = FAULT_VALUE
    private var moveBeginY = FAULT_VALUE
    private var moveCurrentY = FAULT_VALUE
    private var moveDeltaY = FAULT_VALUE

    private var autoHideHandler: AutoHideHandler? = null
    private var autoHideRunnable: AutoHideRunnable? = null


    init {
        initLayoutParams()
    }


    private class AutoHideHandler constructor(private val autoHideFloatingWindowWeakRef: WeakReference<ComuiAutoHideFloatingWindow>) : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                autoHideFloatingWindowWeakRef.get()?.MSG_HIDE_FLOATING_WINDOW -> {
                    autoHideFloatingWindowWeakRef.get()
                        ?.hide()
                }
            }
        }
    }

    private inner class AutoHideRunnable : Runnable {
        override fun run() {
            if (beShown && !beAnimating) {
                /**
                 * It is not necessary to send message because there is no child thread running at this condition.
                 * But for specification, Here is sending message to use handler to hide the window.
                 * by Ace Yan
                 */
                autoHideHandler?.sendMessage(autoHideHandler?.obtainMessage(MSG_HIDE_FLOATING_WINDOW))
                // hide()
            }
        }
    }

    private inner class ViewHolder {
        var ivCenter: ImageView? = null
        var btnCenter: Button? = null
        var llTouchMove: LinearLayout? = null
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
                moveBeginY = event.rawY.toInt()
                autoHideHandler?.removeCallbacks(autoHideRunnable)

                logcatViewProperty(rootView, "ACTION_DOWN rootView\t")
            }

            MotionEvent.ACTION_MOVE -> {
                moveCurrentY = event.rawY.toInt()
                moveDeltaY = moveCurrentY - moveBeginY

                if ((windowLayoutParams?.y ?: 0) >= 0) {
                    windowLayoutParams?.y = windowLayoutParams?.y?.plus(moveDeltaY)
                    windowManager?.updateViewLayout(rootView, windowLayoutParams)
                }
                else {

                }

                moveBeginY = moveCurrentY

                logcatViewProperty(rootView, "ACTION_MOVE rootView\t")
            }

            MotionEvent.ACTION_UP -> {
                moveCurrentY = event.rawY.toInt()
                moveDeltaY = moveCurrentY - moveBeginY

                if ((windowLayoutParams?.y ?: 0) < 0) {
                    hide()
                }
                else {
                    autoHide(true)
                }

                logcatViewProperty(rootView, "ACTION_UP rootView\t\t")
            }

            MotionEvent.ACTION_CANCEL -> {
                slideDownAnimator?.cancel()
                slideUpAnimator?.cancel()
                autoHideHandler?.removeCallbacks(autoHideRunnable)

                logcatViewProperty(rootView, "ACTION_UP rootView\t\t")
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
                releaseView()
            }
        }
    }

    override fun onAnimationCancel(animator: Animator?) {
        beAnimating = false
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        val currentValue = animation?.animatedValue as Float
        if ((windowLayoutParams?.y ?: FAULT_VALUE) >= 0) rootView?.y = 0F else rootView?.y = currentValue
        windowLayoutParams?.y = currentValue.toInt()
        windowManager?.updateViewLayout(rootView, windowLayoutParams)

        logcatViewProperty(rootView, "onAnimationUpdate rootView\t\t")
    }


    @RequiresPermission(android.Manifest.permission.SYSTEM_ALERT_WINDOW)
    fun show(application: Application?): ComuiAutoHideFloatingWindow {
        inflateView(application)
        addView()
        slideDown()
        return this@ComuiAutoHideFloatingWindow
    }

    fun hide(): ComuiAutoHideFloatingWindow {
        slideUp()
        return this@ComuiAutoHideFloatingWindow
    }

    fun setImage(path: String): ComuiAutoHideFloatingWindow {
        viewHolder?.ivCenter ?: return this@ComuiAutoHideFloatingWindow
        GlideUtils.loadImage(viewHolder?.ivCenter, this, path)
        return this@ComuiAutoHideFloatingWindow
    }

    private fun initLayoutParams() {
        if (windowManager == null) {
            windowManager = SystemUtils.getWindowManager()
        }

        if (windowLayoutParams == null) {
            windowLayoutParams = WindowManager.LayoutParams()
        }

        windowLayoutParams?.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        windowLayoutParams?.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        windowLayoutParams?.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL

        /** You can comment configuration of format to display the black window manager. by Ace Yan */
        windowLayoutParams?.format = PixelFormat.TRANSLUCENT
        /** Config alpha of window manager. by Ace Yan */
        // windowLayoutParams?.alpha = 0.9F
        windowLayoutParams?.width = WindowManager.LayoutParams.MATCH_PARENT
        windowLayoutParams?.height = WindowManager.LayoutParams.WRAP_CONTENT

        /** The x here means the offset to the gravity, and will be ignored when the gravity is default. Details see in "https://developer.android.google.cn/reference/android/view/WindowManager.LayoutParams". by Ace Yan */
        windowLayoutParams?.x = 0
        windowLayoutParams?.y = 0
    }

    private fun inflateView(context: Context?) {
        if (rootView == null) {
            rootView = LayoutInflater.from(context)
                .inflate(R.layout.comui_auto_hide_floating_window, null, false) as FrameLayout
            initView(rootView)
        }

        rootView?.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        rootViewMeasuredHeight = rootView?.measuredHeight ?: FAULT_VALUE
        logcatViewProperty(rootView, "After measure rootView\t\t\t")
    }

    private fun initView(view: View?) {
        viewHolder = ViewHolder()
        viewHolder?.ivCenter = view?.findViewById(R.id.ivCenter)
        viewHolder?.btnCenter = view?.findViewById(R.id.btnCenter)
        viewHolder?.llTouchMove = view?.findViewById(R.id.llTouchMove)

        viewHolder?.btnCenter?.setOnClickListener(this@ComuiAutoHideFloatingWindow)
        viewHolder?.llTouchMove?.setOnTouchListener(this@ComuiAutoHideFloatingWindow)
    }

    private fun addView() {
        try {
            if (!beShown) {
                windowManager?.addView(rootView, windowLayoutParams)
                beShown = true
            }
        }
        catch (e: Exception) {
            LogUtils.e(TAG, e.toString())
        }

        logcatViewProperty(rootView, "After addView rootView\t\t\t")
    }

    private fun removeView() {
        try {
            if (beShown) {
                windowManager?.removeView(rootView)
                beShown = false
            }
        }
        catch (e: Exception) {
            LogUtils.e(TAG, e.toString())
        }

        logcatViewProperty(rootView, "After removeView rootView\t\t")
    }

    private fun releaseView() {
        viewHolder?.ivCenter?.setImageResource(0)
    }

    private fun slideDown() {
        if (beShown && !beAnimating && initSlideDownAnimator(rootView)) {
            slideDownAnimator?.start()
            beSlidingDown = true
        }
    }

    private fun slideUp() {
        if (beShown && !beAnimating && initSlideUpAnimator(rootView)) {
            slideUpAnimator?.start()
            beSlidingUp = true
        }
    }

    private fun floating() {
    }

    // private fun initSlideDownAnimator(view: View?) {
    //     slideDownAnimator = ObjectAnimator.ofFloat(view, "y", -rootViewMeasuredHeight.toFloat(), 0F)
    //     slideDownAnimator?.duration = ANIMATION_DURATION
    //     slideDownAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
    //     slideDownAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)
    //
    //     logcatViewProperty(rootView, "initSlideDownAnimator rootView\t")
    // }
    //
    // private fun initSlideUpAnimator(view: View?) {
    //     slideUpAnimator = ObjectAnimator.ofFloat(view, "y", 0F, -rootViewMeasuredHeight.toFloat())
    //     slideUpAnimator?.duration = ANIMATION_DURATION
    //     slideUpAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
    //     slideUpAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)
    //
    //     logcatViewProperty(rootView, "initSlideUpAnimator rootView\t")
    // }

    private fun initSlideDownAnimator(view: View?): Boolean {
        if (rootViewMeasuredHeight == FAULT_VALUE) return false

        val beginY = -rootViewMeasuredHeight.toFloat()
        val endY = 0F
        slideDownAnimator = ValueAnimator.ofFloat(beginY, endY)
        slideDownAnimator?.duration = ANIMATION_DURATION
        slideDownAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
        slideDownAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)

        logcatViewProperty(rootView, "initSlideDownAnimator rootView\t")
        return true
    }

    private fun initSlideUpAnimator(view: View?): Boolean {
        if (rootViewMeasuredHeight == FAULT_VALUE) return false

        val coordinate = IntArray(2)
        view?.getLocationOnScreen(coordinate)
        val beginY = coordinate[1].toFloat()
        val endY = -rootViewMeasuredHeight.toFloat()
        slideUpAnimator = ValueAnimator.ofFloat(beginY, endY)
        slideUpAnimator?.duration = ANIMATION_DURATION
        slideUpAnimator?.addListener(this@ComuiAutoHideFloatingWindow)
        slideUpAnimator?.addUpdateListener(this@ComuiAutoHideFloatingWindow)

        logcatViewProperty(rootView, "initSlideUpAnimator rootView\t")
        return true
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
        LogUtils.d(TAG, "$location: " + "view-> " + "{x:${view?.x?.toInt()}, y:${view?.y?.toInt()}}\t" + "{cX:${coordinate[0]}, cY:${coordinate[1]}}\t" + "{w:${view?.width}, h:${view?.height}}\t" + "{mW:${view?.measuredWidth}, mH:${view?.measuredHeight}}\t" + "{t:${view?.top}, b:${view?.bottom}, l:${view?.left}, r:${view?.right}}\t" + "params-> " + "{x:${windowLayoutParams?.x}, y:${windowLayoutParams?.y}}\t" + "{w:${windowLayoutParams?.width}, h:${windowLayoutParams?.height}}\t")
    }

    // private fun logcatCoordinate(location: String = "") {
    //     ComkitLogcatUtils.d(msg = "$location: {coordinateLocationRoot:{${coordinateLocationRoot[0]}, ${coordinateLocationRoot[1]}}}   {coordinateLocationWindow:{${coordinateLocationWindow[0]}, ${coordinateLocationWindow[1]}}}   {rootLayoutParams.topMargin:${rootLayoutParams.topMargin}}   {windowLayoutParams.y = ${windowLayoutParams.y}}")
    // }

    // private fun logcatViewProperty(view: View?, location: String = "") {
    //     ComkitLogcatUtils.d(msg = "$location view: {x:${ComkitScreenProcessor.px2Dp(input = view?.x)}, y:${ComkitScreenProcessor.px2Dp(input = view?.y)}} {width:${ComkitScreenProcessor.px2Dp(input = view?.width?.toFloat())}, height:${ComkitScreenProcessor.px2Dp(input = view?.height?.toFloat())}} {measuredWidth:${ComkitScreenProcessor.px2Dp(input = view?.measuredWidth?.toFloat())}, measuredHeight:${ComkitScreenProcessor.px2Dp(input = view?.measuredHeight?.toFloat())}} {top:${ComkitScreenProcessor.px2Dp(input = view?.top?.toFloat())}, bottom:${ComkitScreenProcessor.px2Dp(input = view?.bottom?.toFloat())}, left:${ComkitScreenProcessor.px2Dp(input = view?.left?.toFloat())}, right:${ComkitScreenProcessor.px2Dp(input = view?.right?.toFloat())}}")
    // }
}
