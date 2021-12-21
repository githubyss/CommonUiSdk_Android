package com.githubyss.mobile.common.ui.floating_view.feature.autoshorten

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.R


/**
 * AutoShortenView
 * 自动收起 View
 * 仅实现悬浮窗整体的显示、关闭、弹出、收起以及自动收起的逻辑。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/10 16:06:37
 */
open class AutoShortenView : FrameLayout {
    
    companion object {
        private val TAG: String = AutoShortenView::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val AUTO_HIDE_DELAY_TIME: Long = 500
    private val ANIM_DURATION: Long = 500
    
    private var isShowing: Boolean = false
    private var isShorten: Boolean = true
    private var isAnimating: Boolean = false
    
    private var animatorSlideRightShow: Animator? = null
    private var animatorSlideLeftRemove: Animator? = null
    private var animatorSlideRightLengthen: Animator? = null
    private var animatorSlideLeftShorten: Animator? = null
    
    private var autoShortenRunnable: Runnable? = null
    
    private var delayHandler: Handler? = null
    
    private var featureContext: Context? = null
    protected var featureView: View? = null
    
    open var isMovable = true
    
    var featureViewListener: AutoShortenViewListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        featureContext = context
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    protected fun initInBase() {
        isClickable = true
        initAnimator()
    }
    
    fun showAnimator() {
        removeAutoShortenHandleCallbacks()
        if (!isShowing && !isAnimating) {
            animatorSlideRightShow?.start()
        }
        startAutoShortenRunnable()
    }
    
    fun removeAnimator() {
        if (isShowing && !isAnimating) {
            animatorSlideLeftRemove?.start()
        }
    }
    
    fun lengthenAnimator() {
        removeAutoShortenHandleCallbacks()
        if (isShowing && !isAnimating) {
            animatorSlideRightLengthen?.start()
        }
        startAutoShortenRunnable()
    }
    
    fun shortenAnimator() {
        removeAutoShortenHandleCallbacks()
        if (isShowing && !isAnimating && !isShorten) {
            animatorSlideLeftShorten?.start()
        }
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun initAnimator() {
        animatorSlideRightShow = AnimatorInflater.loadAnimator(featureContext, R.animator.comui_floating_audio_player_slide_right_show)
        animatorSlideLeftRemove = AnimatorInflater.loadAnimator(featureContext, R.animator.comui_floating_audio_player_slide_left_close)
        animatorSlideRightLengthen = AnimatorInflater.loadAnimator(featureContext, R.animator.comui_floating_audio_player_slide_right_lengthen)
        animatorSlideLeftShorten = AnimatorInflater.loadAnimator(featureContext, R.animator.comui_floating_audio_player_slide_left_shorten)
        
        animatorSlideRightShow?.setTarget(featureView)
        animatorSlideLeftRemove?.setTarget(featureView)
        animatorSlideRightLengthen?.setTarget(featureView)
        animatorSlideLeftShorten?.setTarget(featureView)
        
        animatorSlideRightShow?.duration = ANIM_DURATION
        animatorSlideLeftRemove?.duration = ANIM_DURATION
        animatorSlideRightLengthen?.duration = ANIM_DURATION
        animatorSlideLeftShorten?.duration = ANIM_DURATION
        
        animatorSlideRightShow?.addListener(animatorListenerAdapter)
        animatorSlideLeftRemove?.addListener(animatorListenerAdapter)
        animatorSlideRightLengthen?.addListener(animatorListenerAdapter)
        animatorSlideLeftShorten?.addListener(animatorListenerAdapter)
    }
    
    private fun getDelayHandler(): Handler? {
        if (delayHandler == null) {
            delayHandler = Handler()
        }
        return delayHandler
    }
    
    private fun startAutoShortenRunnable() {
        if (autoShortenRunnable == null) {
            autoShortenRunnable = Runnable { shortenAnimator() }
        }
        getDelayHandler()?.postDelayed(autoShortenRunnable ?: return, AUTO_HIDE_DELAY_TIME + ANIM_DURATION)
    }
    
    private fun removeAutoShortenHandleCallbacks() {
        if (autoShortenRunnable != null) {
            getDelayHandler()?.removeCallbacks(autoShortenRunnable)
        }
    }
    
    private fun onShow() {
        featureViewListener?.onShow()
    }
    
    private fun onRemove() {
        featureViewListener?.onRemove()
    }
    
    private fun onLengthen() {
        featureViewListener?.onLengthen()
    }
    
    private fun onShorten() {
        featureViewListener?.onShorten()
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    /** 动画监听  */
    private val animatorListenerAdapter: AnimatorListenerAdapter = object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
            isAnimating = true
            
            if (animatorSlideRightShow == animation) {
                isShowing = true
                onShow()
                return
            }
            
            if (animatorSlideRightLengthen == animation) {
                if (featureView != null) {
                    featureView?.translationX = -(ScreenUtils.dp2Px(230.0f).toFloat())
                    onLengthen()
                }
                return
            }
        }
        
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            isAnimating = false
            
            if (animatorSlideRightShow == animation) {
                isShorten = false
                return
            }
            
            if (animatorSlideLeftRemove == animation) {
                isShowing = false
                isShorten = true
                onRemove()
                return
            }
            
            if (animatorSlideRightLengthen == animation) {
                isShorten = false
                return
            }
            
            if (animatorSlideLeftShorten == animation) {
                isShorten = true
                if (featureView != null) {
                    onShorten()
                    featureView?.translationX = 0.0f
                }
            }
        }
    }
}
