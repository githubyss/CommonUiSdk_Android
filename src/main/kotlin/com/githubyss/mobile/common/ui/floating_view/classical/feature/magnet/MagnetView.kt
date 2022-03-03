package com.githubyss.mobile.common.ui.floating_view.classical.feature.magnet

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.githubyss.mobile.common.kit.util.BarUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import kotlin.math.max
import kotlin.math.min

/**
 * MagnetView
 * 磁力吸附 View
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/10 15:10:05
 */
open class MagnetView : FrameLayout {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = MagnetView::class.java.simpleName
    }
    
    private val MARGIN_EDGE: Int = ScreenUtils.dp2Px(14.0f) ?: 0
    private val TOUCH_TIME_THRESHOLD: Int = 150
    
    private var originalRawX: Float = 0f
    private var originalRawY: Float = 0f
    private var originalX: Float = 0f
    private var originalY: Float = 0f
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private var statusBarHeight: Int = 0
    private var portraitY: Float = 0f
    private var lastTouchDownTime: Long = 0
    private var isNearestLeft = true
    
    private var moveAnimatorRunnable: MoveAnimatorRunnable? = null
    
    private var featureContext: Context? = null
    protected var featureView: View? = null
    
    open var isMovable = true
    
    var featureViewListener: MagnetViewListener? = null
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        featureContext = context
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                changeOriginalTouchParams(event)
                updateSize()
                moveAnimatorRunnable?.stop()
            }
            MotionEvent.ACTION_MOVE -> if (isMovable) {
                updateViewPosition(event)
            }
            MotionEvent.ACTION_UP -> {
                clearPortraitY()
                moveToEdge()
                if (isOnClickEvent()) {
                    onIconClick()
                }
            }
            else -> {
            }
        }
        return true
    }
    
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (parent != null) {
            val isLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
            markPortraitY(isLandscape)
            (parent as ViewGroup).post {
                updateSize()
                moveToEdge(isNearestLeft, isLandscape)
            }
        }
    }
    
    
    /** ****************************** Functions ****************************** */
    
    protected fun initInBase() {
        moveAnimatorRunnable = MoveAnimatorRunnable(this)
        statusBarHeight = BarUtils.getStatusBarHeight(context)
        isClickable = true
        // updateSize();
    }
    
    protected fun moveToEdge(isLeft: Boolean = isNearestLeft(), isLandscape: Boolean = false) {
        val moveDistance = if (isLeft) MARGIN_EDGE else (screenWidth - MARGIN_EDGE)
        var y = y
        if (!isLandscape && portraitY != 0f) {
            y = portraitY
            clearPortraitY()
        }
        moveAnimatorRunnable?.start(moveDistance.toFloat(), min(max(0f, y), screenHeight - height.toFloat()))
    }
    
    protected fun isNearestLeft(): Boolean {
        val middle = screenWidth / 2
        isNearestLeft = x < middle
        return isNearestLeft
    }
    
    
    /** ****************************** Private ****************************** */
    
    private fun isOnClickEvent(): Boolean {
        return System.currentTimeMillis() - lastTouchDownTime < TOUCH_TIME_THRESHOLD
    }
    
    private fun updateViewPosition(event: MotionEvent) {
        x = originalX + event.rawX - originalRawX
        // 限制不可超出屏幕高度
        var desY = originalY + event.rawY - originalRawY
        if (desY < statusBarHeight) {
            desY = statusBarHeight.toFloat()
        }
        if (desY > screenHeight - height) {
            desY = screenHeight - height.toFloat()
        }
        y = desY
    }
    
    private fun changeOriginalTouchParams(event: MotionEvent) {
        originalX = x
        originalY = y
        originalRawX = event.rawX
        originalRawY = event.rawY
        lastTouchDownTime = System.currentTimeMillis()
    }
    
    private fun updateSize() {
        val viewGroup = parent as ViewGroup
        if (viewGroup != null) {
            screenWidth = viewGroup.width - width
            screenHeight = viewGroup.height
        }
    }
    
    private fun clearPortraitY() {
        portraitY = 0f
    }
    
    private fun move(deltaX: Float, deltaY: Float) {
        x += deltaX
        y += deltaY
    }
    
    private fun markPortraitY(isLandscape: Boolean) {
        if (isLandscape) {
            portraitY = y
        }
    }
    
    private fun onIconClick() {
        featureViewListener?.onIconClick()
    }
    
    private fun onRemove() {
        featureViewListener?.onRemove()
    }
    
    
    /** ****************************** Implementations ****************************** */
    
    /** 动画  */
    private class MoveAnimatorRunnable(var view: MagnetView) : Runnable {
        private val handler = Handler(Looper.getMainLooper())
        private var destinationX: Float = 0f
        private var destinationY: Float = 0f
        private var startingTime: Long = 0
        
        fun start(x: Float, y: Float) {
            destinationX = x
            destinationY = y
            startingTime = System.currentTimeMillis()
            handler.post(this)
        }
        
        override fun run() {
            if (view.rootView == null || view.rootView.parent == null) {
                return
            }
            val progress = min(1f, (System.currentTimeMillis() - startingTime) / 400f)
            val deltaX: Float = (destinationX - view.x) * progress
            val deltaY: Float = (destinationY - view.y) * progress
            view.move(deltaX, deltaY)
            if (progress < 1) {
                handler.post(this)
            }
        }
        
        fun stop() {
            handler.removeCallbacks(this)
        }
    }
}
