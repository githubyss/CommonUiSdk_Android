package com.githubyss.mobile.common.ui.floatingwindow;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.constant.Constant;
import com.githubyss.mobile.common.ui.utils.PermissionFloatUtils;


/**
 * AudioPlayerFloatingWindow
 * <功能> 音频播放器悬浮窗
 * <详细>
 *
 * @author yanss 16072015
 * @createdTime 2017/04/05 15:25:20
 */
public class AudioPlayerFloatingWindow {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private static AudioPlayerFloatingWindow INSTANCE;

    private ViewGroup containerView;
    private ViewGroup rootView;

    private TextView textView_title;
    private TextView textView_timePosition;
    private TextView textView_timeDuration;
    private SeekBar seekBar_audioPlayer;
    private ImageView imageView_playStartPauseController;
    private ImageView imageView_voiceSwitch;
    private ImageView imageView_close;
    private ImageView imageView_lengthen;

    private final long AUTO_HIDE_DELAY_TIME = 1000;
    private final long ANIM_DURATION = 500;

    private final int MSG_HIDE_FLOATING_WINDOW = 1;


    //8.0 type样式，不可修改，为适应低版本编译，自己定义
    public static final int TYPE_APPLICATION_OVERLAY = 2038;

    //小米8.0,8.1的坑，正常规则走不通，只能走系统漏洞。
    public static final int TYPE_PRESENTATION = 2037;

    private Context containerContext;
    private boolean isShown = false;
    private boolean isAnimating = false;

    private int rootViewCoordinateY;
    private int moveBeginCoordinateY = 0;
    private int moveCurrentCoordinateY;
    private int moveDeltaCoordinateY;

    private Runnable autoShortenRunnable;
    private Runnable hideRunnable;

    private Animator animatorSlideRightShow;
    private Animator animatorSlideLeftHide;
    private Animator animatorSlideRightLengthen;
    private Animator animatorSlideLeftShorten;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public AudioPlayerFloatingWindow(Context context) {
        containerContext = context;

        LayoutInflater inflater = LayoutInflater.from(containerContext);
        if (null == rootView) {
            rootView = (LinearLayout) inflater.inflate(R.layout.comui_floating_audio_player_view, null, false);
        }

        if (null == containerView) {
            containerView = new FrameLayout(containerContext);
        }

        initViewFindById(rootView);
        initListener();
        initAnimator();
        initLayoutParams();
    }

    public static AudioPlayerFloatingWindow getInstance(Activity activity) {
        if (null == INSTANCE) {
            INSTANCE = new AudioPlayerFloatingWindow(activity);
        }
        return INSTANCE;
    }


    // ---------- ---------- ---------- Implementations ---------- ---------- ----------

    /**
     * 点击监听
     * yanss 2017/04/07 14:19:25
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.imageView_playStartPauseController) {
            } else if (id == R.id.imageView_voiceSwitch) {
            } else if (id == R.id.imageView_close) {
                // hideFloatingWindow();
                shortenFloatingWindow();
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

            if (animatorSlideLeftHide.equals(animation)) {
                clearView();
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
     * 通过 Handler 发送消息，在主线程中进行 UI 更新
     * yanss 2017/04/11 10:26:27
     */
    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HIDE_FLOATING_WINDOW:
                    hideFloatingWindow();
                    break;
                default:
                    break;
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
        textView_timePosition = view.findViewById(R.id.textView_timePosition);
        textView_timeDuration = view.findViewById(R.id.textView_timeDuration);
        seekBar_audioPlayer = view.findViewById(R.id.seekBar_audioPlayer);
        imageView_playStartPauseController = view.findViewById(R.id.imageView_playStartPauseController);
        imageView_voiceSwitch = view.findViewById(R.id.imageView_voiceSwitch);
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_lengthen = view.findViewById(R.id.imageView_lengthen);
    }

    private void initListener() {
        imageView_playStartPauseController.setOnClickListener(onClickListener);
        imageView_voiceSwitch.setOnClickListener(onClickListener);
        imageView_close.setOnClickListener(onClickListener);
        imageView_lengthen.setOnClickListener(onClickListener);
    }

    private void initAnimator() {
        animatorSlideRightShow = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_right_show);
        animatorSlideRightLengthen = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_right_lengthen);
        animatorSlideLeftHide = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_left_close);
        animatorSlideLeftShorten = AnimatorInflater.loadAnimator(containerContext, R.animator.slide_left_shorten);

        animatorSlideRightShow.setTarget(rootView);
        animatorSlideRightLengthen.setTarget(rootView);
        animatorSlideLeftHide.setTarget(rootView);
        animatorSlideLeftShorten.setTarget(rootView);

        animatorSlideRightShow.setDuration(ANIM_DURATION);
        animatorSlideRightLengthen.setDuration(ANIM_DURATION);
        animatorSlideLeftHide.setDuration(ANIM_DURATION);
        animatorSlideLeftShorten.setDuration(ANIM_DURATION);

        animatorSlideRightShow.addListener(animatorListenerAdapter);
        animatorSlideRightLengthen.addListener(animatorListenerAdapter);
        animatorSlideLeftHide.addListener(animatorListenerAdapter);
        animatorSlideLeftShorten.addListener(animatorListenerAdapter);
    }

    private void initLayoutParams() {
        if (Build.VERSION.SDK_INT >= Constant.VERSION_CODES_O) {
            boolean MiUiO = (Build.VERSION.SDK_INT == Constant.VERSION_CODES_O || Build.VERSION.SDK_INT == Constant.VERSION_CODES_O_MR1) && PermissionFloatUtils.isMiui();
            if (MiUiO) {
                getLayoutParams().type = TYPE_PRESENTATION;
            } else {
                getLayoutParams().type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }
        } else {
            getLayoutParams().type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        // if(Build.VERSION.SDK_INT >= 26) {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        // }else {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        // }

        getLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        getLayoutParams().gravity = Gravity.LEFT | Gravity.BOTTOM;
        getLayoutParams().format = PixelFormat.TRANSLUCENT;
        getLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getLayoutParams().x = 0;
        getLayoutParams().y = 0;
        // thisLayoutParams.windowAnimations = android.R.style.Animation_Translucent;
    }

    private WindowManager getWindowManager() {
        if (windowManager == null && containerContext != null) {
            windowManager = (WindowManager) containerContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    private WindowManager.LayoutParams getLayoutParams() {
        if (layoutParams == null) {
            layoutParams = new WindowManager.LayoutParams();
            initLayoutParams();
        }
        return layoutParams;
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

    /**
     * ScreenshotAutoHideFloatingWindow.clearView([])
     * <功能> 清除 rootView 和 containerView，同时结束 View 的动画，并重置 isShown 状态
     * <详细>
     *
     * @param []
     * @return void
     * @author yanss 16072015
     * @createdTime 2017/04/13 18:58:00
     */
    private void clearView() {
        try {
            isShown = false;
            rootView.clearAnimation();
            if (containerView.getChildCount() > 0) {
                containerView.removeView(rootView);
            }
            getWindowManager().removeView(containerView);
        } catch (Exception e) {
            // LogUtils.logException(e);
        }
    }

    private void removeAutoHideHandleCallbacks() {
        if (delayHandler != null && autoShortenRunnable != null) {
            delayHandler.removeCallbacks(autoShortenRunnable);
        }
    }


    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    public void showFloatingWindow() {
        removeAutoHideHandleCallbacks();

        if (!isShown) {
            try {
                getWindowManager().addView(containerView, getLayoutParams());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = 0;
                containerView.addView(rootView, layoutParams);
                animatorSlideRightShow.start();
                // rootView.startAnimation(animSlideRightShow);
            } catch (WindowManager.BadTokenException e) {
            } catch (Exception e) {
            }
        } else {
            getWindowManager().updateViewLayout(containerView, layoutParams);
        }

        if (autoShortenRunnable == null) {
            autoShortenRunnable = new Runnable() {
                @Override
                public void run() {
                    shortenFloatingWindow();
                    // sendMessage(MSG_HIDE_FLOATING_WINDOW);
                }
            };
        }
        // getDelayHandler().postDelayed(autoShortenRunnable, AUTO_HIDE_DELAY_TIME + ANIM_DURATION);
    }

    public void hideFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideLeftHide.start();
            // rootView.startAnimation(animSlideLeftHide);
        }
    }

    public void lengthenFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideRightLengthen.start();
            // rootView.startAnimation(animSlideRightLengthen);
        }
    }

    public void shortenFloatingWindow() {
        if (isShown && !isAnimating) {
            animatorSlideLeftShorten.start();
            // rootView.startAnimation(animSlideLeftShorten);
        }
    }

    public void sendMessage(int id) {
        if (messageHandler != null) {
            Message message = Message.obtain(messageHandler, id);
            messageHandler.sendMessage(message);
        }
    }


    // ---------- ---------- ---------- Lifecycle ---------- ---------- ----------

}
