package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;


/**
 * AudioPlayerFloatingWindow
 * <功能> 音频播放器悬浮窗
 * <详细>
 *
 * @author yanss 16072015
 * @createdTime 2017/04/05 15:25:20
 */
public class BaseFloatingAudioPlayerView extends FrameLayout {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    public View rootView;

    private TextView textView_title;
    private TextView textView_timeFlow;
    private ImageView imageView_playController;
    private ImageView imageView_switchVoice;
    private ImageView imageView_close;
    private ImageView imageView_lengthen;

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

    private BaseFloatingAudioPlayerViewListener baseFloatingAudioPlayerViewListener;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public BaseFloatingAudioPlayerView(Context context) {
        this(context, null);
    }

    public BaseFloatingAudioPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFloatingAudioPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        containerContext = context;
    }

    public void init() {
        setClickable(true);
        initViewFindById(rootView);
        initAnimator();
        initListener();
    }


    // ---------- ---------- ---------- Implementations ---------- ---------- ----------

    /**
     * 点击监听
     * yanss 2017/04/07 14:19:25
     */
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.textView_title) {
            } else if (id == R.id.textView_timeFlow) {
            } else if (id == R.id.imageView_playController) {
            } else if (id == R.id.imageView_switchVoice) {
            } else if (id == R.id.imageView_close) {
                closeFloatingWindow();
            } else if (id == R.id.imageView_lengthen) {
                lengthenFloatingWindow();
            }
        }
    };

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
                baseFloatingAudioPlayerViewListener.onClose();
                return;
            }

            if (animatorSlideRightLengthen.equals(animation)) {
                refreshCloseButton(true);
                return;
            }

            if (animatorSlideLeftShorten.equals(animation)) {
                refreshCloseButton(false);
                return;
            }
        }
    };

    /**
     * 使用 Handler 进行延时
     * yanss 2017/04/11 11:46:39
     */
    private Handler delayHandler = new Handler();


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initViewFindById(View view) {
        textView_title = view.findViewById(R.id.textView_title);
        textView_timeFlow = view.findViewById(R.id.textView_timeFlow);
        imageView_playController = view.findViewById(R.id.imageView_playController);
        imageView_switchVoice = view.findViewById(R.id.imageView_switchVoice);
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_lengthen = view.findViewById(R.id.imageView_lengthen);
    }

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
    }

    private void initListener() {
        textView_title.setOnClickListener(onClickListener);
        textView_timeFlow.setOnClickListener(onClickListener);
        imageView_playController.setOnClickListener(onClickListener);
        imageView_switchVoice.setOnClickListener(onClickListener);
        imageView_close.setOnClickListener(onClickListener);
        imageView_lengthen.setOnClickListener(onClickListener);

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

    private void refreshCloseButton(boolean isShow) {
        if (isShow) {
            imageView_close.setVisibility(View.VISIBLE);
            imageView_lengthen.setVisibility(View.GONE);
        } else {
            imageView_close.setVisibility(View.GONE);
            imageView_lengthen.setVisibility(View.VISIBLE);
        }
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

    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    public void setAudioPlayerViewListener(BaseFloatingAudioPlayerViewListener baseFloatingAudioPlayerViewListener) {
        this.baseFloatingAudioPlayerViewListener = baseFloatingAudioPlayerViewListener;
    }

    public void showFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (!isShown && !isAnimating) {
            animatorSlideRightShow.start();
        }
        startAutoShortenRunnable();
    }


    public void closeFloatingWindow() {
        removeAutoShortenHandleCallbacks();
        if (isShown && !isAnimating) {
            animatorSlideLeftClose.start();
        }
        startAutoShortenRunnable();
    }

    public void lengthenFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideRightLengthen.start();
        }
    }

    public void shortenFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideLeftShorten.start();
        }
    }
}
