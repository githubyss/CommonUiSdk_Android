package com.githubyss.mobile.common.ui.banner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.R


/**
 * BannerLineIndicator
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 10:53:03
 */
class BannerLineIndicator : View {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = BannerLineIndicator::class.simpleName ?: "simpleName is null"
    }
    
    private var count: Int = 0
    private var actualCount: Int = 0
    private var bannerViewPager: ViewPager? = null
    private var lineWidth: Float = 0f
    private var lineHeight: Float = 0f
    private var paint: Paint = Paint()
    
    private var indicatedPosition: Int = 0
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0
    private var isFirstMeasure: Boolean = true
    
    private var lineColorOn: Int = ResourceUtils.getColor(R.color.comres_color_ffffff)
    private var lineColorOff: Int = ResourceUtils.getColor(R.color.comres_color_white60pct)
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init()
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (isFirstMeasure) {
            isFirstMeasure = false
            indicatedPosition = 1
        }
    }
    
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackgroundLines(canvas)
        drawIndicatedLine(canvas)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /**
     * 配置位置指示器的颜色
     *
     * @param onColor  选中的颜色
     * @param offColor 未选中的颜色
     * @return
     */
    fun configColor(@ColorInt onColor: Int, @ColorInt offColor: Int) {
        lineColorOn = onColor
        lineColorOff = offColor
    }
    
    /**
     * 绑定 Indicator 与 ViewPager 的滑动
     *
     * @param viewPager 待绑定的 ViewPager
     * @return
     */
    fun bindSlideWithViewPager(viewPager: ViewPager?) {
        bannerViewPager = viewPager
        viewPager?.let {
            if ((viewPager.adapter?.count ?: return) > 2) {
                count = (viewPager.adapter?.count ?: return) - 2
                actualCount = viewPager.adapter?.count ?: return
            } else {
                count = 1
                actualCount = 1
            }
            viewPager.addOnPageChangeListener(onPageChangeListener)
        }
    }
    
    /**
     * 更新 ViewPager 数据
     *
     * @param viewPager 待绑定的 ViewPager
     * @return
     */
    fun updateViewPagerCount(viewPager: ViewPager?) {
        viewPager?.let {
            if ((viewPager.adapter?.count ?: return) > 2) {
                count = (viewPager.adapter?.count ?: return) - 2
                actualCount = viewPager.adapter?.count ?: return
            } else {
                count = 1
                actualCount = 1
            }
            viewPager.addOnPageChangeListener(onPageChangeListener)
        }
    }
    
    /**
     * 移除 ViewPager 页面切换监听
     *
     * @param
     * @return
     */
    fun removePageChangeListener() {
        if (bannerViewPager != null) {
            bannerViewPager?.removeOnPageChangeListener(onPageChangeListener)
        }
    }
    
    
    private fun init() {
        paint = Paint()
        lineWidth = ScreenUtils.dp2Px(9.0f).toFloat()
        lineHeight = ScreenUtils.dp2Px(1.5f).toFloat()
    }
    
    /**
     * 设置选中的点
     *
     * @param position 选中的位置
     * @return
     */
    private fun setDot(position: Int) {
        indicatedPosition = position
        invalidate()
    }
    
    /**
     * 绘制已选定的指示器
     *
     * @param canvas 画布
     * @return
     */
    private fun drawIndicatedLine(canvas: Canvas?) {
        paint.color = lineColorOn
        val cx: Float
        val cy = 0f
        if (count > 1) {
            cx = (indicatedPosition - 1) * (viewWidth - 2 * lineWidth) / (count - 1)
            canvas?.drawRect(cx, cy, cx + 2 * lineWidth, cy + lineHeight, paint)
        } else if (count == 0) {
            cx = 0f
            canvas?.drawRect(cx, cy, cx + lineWidth, cy + lineHeight, paint)
        }
    }
    
    /**
     * 绘制未选定的指示器
     *
     * @param canvas 画布
     * @return
     */
    private fun drawBackgroundLines(canvas: Canvas?) {
        paint.color = lineColorOff
        var cx: Float
        val cy = 0f
        for (i in 0 until count) {
            if (i == indicatedPosition - 1) continue
            if (count > 1) {
                cx = if (i < indicatedPosition - 1) {
                    i * (viewWidth - 2 * lineWidth) / (count - 1)
                } else {
                    i * (viewWidth - 2 * lineWidth) / (count - 1) + lineWidth
                }
                canvas?.drawRect(cx, cy, cx + lineWidth, cy + lineHeight, paint)
            }
        }
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    /** 页面切换 */
    private val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
        
        override fun onPageSelected(position: Int) {
            if (position == 0 || position == actualCount - 1) {
                return
            }
            setDot(position)
        }
        
        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (bannerViewPager?.currentItem == 0) {
                    if (actualCount >= 2 && (bannerViewPager?.adapter?.count ?: return) > actualCount - 2) {
                        bannerViewPager?.setCurrentItem(actualCount - 2, false)
                    }
                }
                if (bannerViewPager?.currentItem == actualCount - 1) {
                    if ((bannerViewPager?.adapter?.count ?: return) > 2) {
                        bannerViewPager?.setCurrentItem(1, false)
                    }
                }
            }
        }
    }
}
