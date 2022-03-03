package com.githubyss.mobile.common.ui.banner.classical

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.viewpager.widget.ViewPager


/**
 * BannerViewPager
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 14:25:26
 */
class BannerViewPager : ViewPager {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = BannerViewPager::class.java.simpleName
    }
    
    var onViewPagerTouchListener: OnViewPagerTouchListener? = null
    
    private var xDistance = 0f
    private var yDistance = 0f
    private var xLast = 0f
    private var yLast = 0f
    private var touchSlop = 0
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        init(context)
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                onViewPagerTouchListener?.onTouchStart()
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                onViewPagerTouchListener?.onTouchEnd()
            }
        }
        return super.dispatchTouchEvent(ev)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    private fun init(context: Context) {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
    
    
    /** ****************************** Interface ****************************** */
    
    interface OnViewPagerTouchListener {
        fun onTouchStart()
        fun onTouchEnd()
    }
}
