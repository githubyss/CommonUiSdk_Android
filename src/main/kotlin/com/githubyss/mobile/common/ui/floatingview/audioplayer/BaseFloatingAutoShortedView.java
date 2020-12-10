package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.ui.R;


/**
 * AudioPlayerFloatingWindow
 * <功能> 自动隐藏悬浮窗
 * <详细>
 *
 * @author yanss 16072015
 * @createdTime 2017/04/05 15:25:20
 */
public class BaseFloatingAutoShortedView extends FrameLayout {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    protected View rootView;

    private final long AUTO_HIDE_DELAY_TIME = 1000;
    private final long ANIM_DURATION = 500;

    private Context containerContext;
    private boolean isShown = false;
    private boolean isAnimating = false;

    private Runnable autoShortenRunnable;

    private Animator animatorSlideRightShow;
    private Animator animatorSlideLeftClose;
    private Animator animatorSlideRightLengthen;
    private Animator animatorSlideLeftShorten;

    private BaseFloatingAutoShortedViewListener baseFloatingAutoShortedViewListener;

    /**
     * 使用 Handler 进行延时
     * yanss 2017/04/11 11:46:39
     */
    private Handler delayHandler = new Handler();


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public BaseFloatingAutoShortedView(Context context) {
        this(context, null);
    }

    public BaseFloatingAutoShortedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFloatingAutoShortedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        containerContext = context;
    }


    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    protected void initBase() {
        setClickable(true);
        initAnimator();
    }

    protected void showFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (!isShown && !isAnimating) {
            animatorSlideRightShow.start();
        }
        startAutoShortenRunnable();
    }


    protected void closeFloatingWindow() {
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
        if (isShown && !isAnimating) {
            animatorSlideLeftShorten.start();
        }
    }


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initAnimator() {
        animatorSlideRightShow = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_right_show);
        animatorSlideLeftClose = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_left_close);
        animatorSlideRightLengthen = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_right_lengthen);
        animatorSlideLeftShorten = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_left_shorten);

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


    // ---------- ---------- ---------- Setter ---------- ---------- ----------

    public void setBaseFloatingAutoShortedViewListener(BaseFloatingAutoShortedViewListener baseFloatingAutoShortedViewListener) {
        this.baseFloatingAutoShortedViewListener = baseFloatingAutoShortedViewListener;
    }


    // ---------- ---------- ---------- Implementations ---------- ---------- ----------

    /**
     * 动画监听
     * yanss 2017/04/10 16:54:28
     */
    private AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            isAnimating = true;

            if (animatorSlideRightShow.equals(animation)) {
                isShown = true;
                return;
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            isAnimating = false;

            if (animatorSlideLeftClose.equals(animation)) {
                baseFloatingAutoShortedViewListener.onClose();
                return;
            }

            if (animatorSlideRightLengthen.equals(animation)) {
                baseFloatingAutoShortedViewListener.onSlide(true);
                return;
            }

            if (animatorSlideLeftShorten.equals(animation)) {
                baseFloatingAutoShortedViewListener.onSlide(false);
                return;
            }
        }
    };
}
