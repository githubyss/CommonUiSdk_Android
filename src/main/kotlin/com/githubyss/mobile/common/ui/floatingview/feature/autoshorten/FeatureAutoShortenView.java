package com.githubyss.mobile.common.ui.floatingview.feature.autoshorten;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.kit.util.ScreenUtils;
import com.githubyss.mobile.common.ui.R;


/**
 * FeatureAutoShortenView
 * <Description> 自动收起悬浮窗
 * <Details> 仅实现悬浮窗整体的显示、关闭、弹出、收起以及自动收起的逻辑。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:51:02
 */
public class FeatureAutoShortenView extends FrameLayout {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    protected View    rootView;
    protected Context containerContext;
    
    private final long AUTO_HIDE_DELAY_TIME = 500;
    private final long ANIM_DURATION        = 500;
    
    protected boolean isMovable = true;
    
    private boolean isShown     = false;
    private boolean isShorten   = true;
    private boolean isAnimating = false;
    
    private Animator animatorSlideRightShow;
    private Animator animatorSlideLeftClose;
    private Animator animatorSlideRightLengthen;
    private Animator animatorSlideLeftShorten;
    
    private FeatureAutoShortenViewToContainerViewListener featureAutoShortenViewToContainerViewListener;
    private FeatureAutoShortenViewToDesignateViewListener featureAutoShortenViewToDesignateViewListener;
    
    private Runnable autoShortenRunnable;
    
    private Handler delayHandler = new Handler();
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public FeatureAutoShortenView(Context context) {
        this(context, null);
    }
    
    public FeatureAutoShortenView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public FeatureAutoShortenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        containerContext = context;
        // initBase();
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    protected void initInBase() {
        setClickable(true);
        initAnimator();
    }
    
    public void showFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (!isShown && !isAnimating) {
            animatorSlideRightShow.start();
        }
        startAutoShortenRunnable();
    }
    
    public void closeFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideLeftClose.start();
        }
    }
    
    protected void lengthenFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (isShown && !isAnimating) {
            animatorSlideRightLengthen.start();
        }
        startAutoShortenRunnable();
    }
    
    protected void shortenFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (isShown && !isAnimating && !isShorten) {
            animatorSlideLeftShorten.start();
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initAnimator() {
        animatorSlideRightShow = AnimatorInflater.loadAnimator(containerContext, R.animator.comui_floating_audio_player_slide_right_show);
        animatorSlideLeftClose = AnimatorInflater.loadAnimator(containerContext, R.animator.comui_floating_audio_player_slide_left_close);
        animatorSlideRightLengthen = AnimatorInflater.loadAnimator(containerContext, R.animator.comui_floating_audio_player_slide_right_lengthen);
        animatorSlideLeftShorten = AnimatorInflater.loadAnimator(containerContext, R.animator.comui_floating_audio_player_slide_left_shorten);
        
        animatorSlideRightShow.setTarget(rootView);
        animatorSlideLeftClose.setTarget(rootView);
        animatorSlideRightLengthen.setTarget(rootView);
        animatorSlideLeftShorten.setTarget(rootView);
        
        animatorSlideRightShow.setDuration(ANIM_DURATION);
        animatorSlideLeftClose.setDuration(ANIM_DURATION);
        animatorSlideRightLengthen.setDuration(ANIM_DURATION);
        animatorSlideLeftShorten.setDuration(ANIM_DURATION);
        
        animatorSlideRightShow.addListener(animatorListenerAdapter);
        animatorSlideLeftClose.addListener(animatorListenerAdapter);
        animatorSlideRightLengthen.addListener(animatorListenerAdapter);
        animatorSlideLeftShorten.addListener(animatorListenerAdapter);
    }
    
    private Handler getDelayHandler() {
        if (delayHandler == null) {
            delayHandler = new Handler();
        }
        return delayHandler;
    }
    
    private void startAutoShortenRunnable() {
        if (autoShortenRunnable == null) {
            autoShortenRunnable = new Runnable() {
                @Override
                public void run() {
                    shortenFloatingWindow();
                }
            };
        }
        getDelayHandler().postDelayed(autoShortenRunnable, AUTO_HIDE_DELAY_TIME + ANIM_DURATION);
    }
    
    private void removeAutoShortenHandleCallbacks() {
        if (delayHandler != null && autoShortenRunnable != null) {
            delayHandler.removeCallbacks(autoShortenRunnable);
        }
    }
    
    private void onShow() {
        if (featureAutoShortenViewToContainerViewListener != null) {
            featureAutoShortenViewToContainerViewListener.onShow();
        }
    }
    
    private void onClose() {
        if (featureAutoShortenViewToContainerViewListener != null) {
            featureAutoShortenViewToContainerViewListener.onClose();
        }
    }
    
    private void onLengthen() {
        if (featureAutoShortenViewToDesignateViewListener != null) {
            featureAutoShortenViewToDesignateViewListener.onLengthen();
        }
    }
    
    private void onShorten() {
        if (featureAutoShortenViewToDesignateViewListener != null) {
            featureAutoShortenViewToDesignateViewListener.onShorten();
        }
    }
    
    
    // ---------- ---------- ---------- Implementations ---------- ---------- ----------
    
    /** 动画监听 */
    private AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            isAnimating = true;
            
            if (animatorSlideRightShow.equals(animation)) {
                isShown = true;
                onShow();
                return;
            }
            
            if (animatorSlideRightLengthen.equals(animation)) {
                if (rootView != null) {
                    rootView.setTranslationX(-ScreenUtils.INSTANCE.dp2Px(230.0f));
                    onLengthen();
                }
                return;
            }
        }
        
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            isAnimating = false;
            
            if (animatorSlideRightShow.equals(animation)) {
                isShorten = false;
                return;
            }
            
            if (animatorSlideLeftClose.equals(animation)) {
                isShown = false;
                isShorten = true;
                onClose();
                return;
            }
            
            if (animatorSlideRightLengthen.equals(animation)) {
                isShorten = false;
                return;
            }
            
            if (animatorSlideLeftShorten.equals(animation)) {
                isShorten = true;
                if (rootView != null) {
                    onShorten();
                    rootView.setTranslationX(0.0f);
                }
            }
        }
    };
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    @Override
    public boolean isShown() {
        return isShown;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public void setFeatureAutoShortenViewToContainerViewListener(FeatureAutoShortenViewToContainerViewListener featureAutoShortenViewToContainerViewListener) {
        this.featureAutoShortenViewToContainerViewListener = featureAutoShortenViewToContainerViewListener;
    }
    
    public void setFeatureAutoShortenViewToDesignateViewListener(FeatureAutoShortenViewToDesignateViewListener featureAutoShortenViewToDesignateViewListener) {
        this.featureAutoShortenViewToDesignateViewListener = featureAutoShortenViewToDesignateViewListener;
    }
}
