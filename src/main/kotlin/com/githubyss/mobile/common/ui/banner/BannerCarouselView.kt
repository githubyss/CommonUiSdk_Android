package com.githubyss.mobile.common.ui.banner

import android.content.Context
import android.database.DataSetObserver
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.githubyss.mobile.common.kit.util.ScreenUtils
import java.lang.ref.WeakReference


/**
 * BannerCarouselView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 10:52:05
 */
class BannerCarouselView : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = BannerCarouselView::class.simpleName ?: "simpleName is null"
        private const val SWITCH_VIEW_PAGER = 1
        private const val TIME_PERIOD = 5000
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var viewContext: Context? = null
    
    private var indicatorWidth = 0f
    private var indicatorHeight = 0f
    private var indicatorMargin = 0
    private var handler: MyHandler? = null
    private var pageAdapter: StatisticsPagerAdapter? = null
    private var isInit = false
    private var actualCount = 0
    private var viewPager: BannerViewPager? = null
    private var indicator: BannerLineIndicator? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
        initView()
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun updateDataSource() {
        val viewPager = getChildAt(0) as ViewPager
        val dotIndicator = getChildAt(1) as BannerLineIndicator
        if (pageAdapter?.count == 1) {
            viewPager.setCurrentItem(0, false)
            dotIndicator.visibility = View.GONE
        } else {
            viewPager.setCurrentItem(1, false)
            dotIndicator.visibility = View.VISIBLE
            val params = dotIndicator.layoutParams
            params.width = (indicatorWidth * ((pageAdapter?.count ?: return) - 2) + ScreenUtils.dp2Px(3.0f, viewContext) * ((pageAdapter?.count ?: return) - 3) + indicatorWidth).toInt()
            dotIndicator.layoutParams = params
        }
        dotIndicator.removePageChangeListener()
        dotIndicator.updateViewPagerCount(viewPager)
        actualCount = if ((pageAdapter?.count ?: return) > 2) {
            pageAdapter?.count ?: return
        } else {
            1
        }
    }
    
    fun setAdapter(adapter: StatisticsPagerAdapter) {
        if (isInit) {
            return
        }
        
        isInit = true
        pageAdapter = adapter
        pageAdapter?.registerDataSetObserver(dataSetObserver)
        val viewPager = getChildAt(0) as ViewPager
        val dotIndicator = getChildAt(1) as BannerLineIndicator
        viewPager.adapter = pageAdapter
        if (pageAdapter?.count == 1) {
            viewPager.setCurrentItem(0, false)
            dotIndicator.visibility = View.GONE
        } else {
            viewPager.setCurrentItem(1, false)
            dotIndicator.visibility = View.VISIBLE
            val params = dotIndicator.layoutParams
            params.width = (indicatorWidth * ((pageAdapter?.count ?: return) - 2) + ScreenUtils.dp2Px(3.0f, viewContext) * ((pageAdapter?.count ?: return) - 3) + indicatorWidth).toInt()
            dotIndicator.layoutParams = params
        }
        dotIndicator.bindSlideWithViewPager(viewPager)
        actualCount = if ((pageAdapter?.count ?: return) > 2) {
            pageAdapter?.count ?: return
        } else {
            1
        }
    }
    
    fun startCarouse() {
        if (pageAdapter == null || (pageAdapter?.count ?: return) < 2) {
            // 数量不够轮播时不进行轮播操作
            return
        }
        handler?.removeCallbacks(autoCarouselRunnable)
        handler?.removeMessages(SWITCH_VIEW_PAGER)
        handler?.postDelayed(autoCarouselRunnable, TIME_PERIOD.toLong())
    }
    
    fun stopCarouse() {
        handler?.removeCallbacks(autoCarouselRunnable)
        handler?.removeMessages(SWITCH_VIEW_PAGER)
    }
    
    fun viewStatistic() {
        if (viewPager != null && pageAdapter != null) {
            viewStatistic(viewPager?.currentItem ?: return)
        }
    }
    
    private fun init(context: Context, attrs: AttributeSet?) {
        viewContext = context
        handler = MyHandler(this)
        indicatorWidth = ScreenUtils.dp2Px(9.0f, viewContext).toFloat()
        indicatorHeight = ScreenUtils.dp2Px(1.5f, viewContext).toFloat()
        indicatorMargin = ScreenUtils.dp2Px(5.0f, viewContext)
    }
    
    private fun initView() {
        if (isInit) {
            return
        }
        
        viewPager = BannerViewPager(context)
        addView(viewPager)
        val params1 = viewPager?.layoutParams as LayoutParams
        params1.width = ViewGroup.LayoutParams.MATCH_PARENT
        params1.height = ViewGroup.LayoutParams.MATCH_PARENT
        viewPager?.layoutParams = params1
        viewPager?.setVpTouchListener(onViewPagerTouchListener)
        
        indicator = BannerLineIndicator(viewContext)
        val params2 = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.bottomMargin = indicatorMargin
        params2.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        params2.height = indicatorHeight.toInt()
        indicator?.layoutParams = params2
        addView(indicator)
        
        viewPager?.addOnPageChangeListener(onPageChangeListener)
    }
    
    private fun viewStatistic(index: Int) {
        var actIndex = 0
        val total = pageAdapter?.count ?: return
        actIndex = if (total == 0) {
            return
        } else if (total == 1) {
            0
        } else {
            when (index) {
                0 -> {
                    total - 3
                }
                total - 1 -> {
                    0
                }
                else -> {
                    index - 1
                }
            }
        }
        if (pageAdapter != null) {
            pageAdapter?.viewStatistic(actIndex)
        }
    }
    
    
    /** ********** ********** ********** Classes ********** ********** **********  */
    
    internal class MyHandler(carouselView: BannerCarouselView) : Handler() {
        var weakReference: WeakReference<BannerCarouselView> = WeakReference(carouselView)
        
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                SWITCH_VIEW_PAGER -> {
                    var viewPager: ViewPager? = null
                    try {
                        viewPager = weakReference.get()?.getChildAt(0) as ViewPager
                    } catch (e: Exception) {
                        Log.e(TAG, "first child must be ViewPager!")
                    }
                    if (viewPager != null && viewPager.childCount > 1) {
                        if (viewPager.currentItem != 0 && viewPager.currentItem != (viewPager.adapter?.count ?: return) - 1) {
                            viewPager.currentItem = viewPager.currentItem + 1
                        }
                    }
                }
            }
        }
        
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
        override fun onPageSelected(position: Int) {}
        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                viewStatistic()
            }
        }
    }
    
    private val onViewPagerTouchListener: BannerViewPager.OnViewPagerTouchListener = object : BannerViewPager.OnViewPagerTouchListener {
        override fun onTouchStart() {
            stopCarouse()
        }
        
        override fun onTouchEnd() {
            startCarouse()
        }
    }
    
    private val dataSetObserver: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            updateDataSource()
        }
    }
    
    private val autoCarouselRunnable: Runnable = object : Runnable {
        override fun run() {
            handler?.sendEmptyMessage(SWITCH_VIEW_PAGER)
            handler?.postDelayed(this, TIME_PERIOD.toLong())
        }
    }
}
