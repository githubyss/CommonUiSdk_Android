package com.githubyss.mobile.common.ui.floatingwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.githubyss.mobile.common.kit.util.ImageUtils;
import com.githubyss.mobile.common.ui.R;


/**
 * ScreenshotAutoHideDialog
 * <功能> 响应截屏事件的可自动隐藏的对话框
 * <详细>
 *
 * @author yanss 16072015
 * @createdTime 2017/04/05 15:25:20
 */
public class ScreenshotAutoHideFloatingWindow {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    public static ScreenshotAutoHideFloatingWindow thisInstance;

    private ViewGroup containerView;
    private ViewGroup rootView;

    private ImageView ivScreenshot;
    private LinearLayout llTouchMoveBtn;

    private final long WAIT_BITMAP_DELAY_TIME = 100;
    private final long AUTO_HIDE_DELAY_TIME   = 5000;
    private final long ANIM_DURATION          = 500;

    private final int MSG_HIDE_FLOATING_WINDOW = 1;

    private Activity mActivity;
    private String filePath;
    private boolean isShown     = false;
    private boolean isAnimating = false;

    private int rootViewCoordinateY;
    private int moveBeginCoordinateY = 0;
    private int moveCurrentCoordinateY;
    private int moveDeltaCoordinateY;

    // private Timer     autoHideTimer;
    // private TimerTask autoHideTimerTask;

    private Runnable waitBitmapRunnable;
    private Runnable autoHideRunnable;
    private Runnable hidingRunnable;
    private Runnable showingRunnable;

    private Animation animSlideDownIn;
    private Animation animSlideUpOut;

    private WindowManager thisWindowManager;
    private WindowManager.LayoutParams thisLayoutParams;


    // ---------- ---------- ---------- Implementations ---------- ---------- ----------

    /**
     * 点击监听
     * yanss 2017/04/07 14:19:25
     */
    private View.OnClickListener thisOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.iv_feedback) {
                thisDelayHandler.removeCallbacks(autoHideRunnable);

                if (!isAnimating) {
                    clearView();
                }
            } else if (id == R.id.iv_customservice) {
                thisDelayHandler.removeCallbacks(autoHideRunnable);

                if (!isAnimating) {
                    clearView();
                }
            } else if (id == R.id.iv_share) {
                thisDelayHandler.removeCallbacks(autoHideRunnable);

                if (!isAnimating) {
                    clearView();
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeFile(filePath);
                    } catch (Exception e) {
                        Log.e("", e.getMessage());
                    }
                    String[] sharePath = new String[]{"WXChat", "WXFriends", "Weibo", "QQChat", "QQZone"};
                }
            }
        }
    };

    /**
     * 触摸事件
     * yanss 2017/04/11 15:27:32
     */
    private View.OnTouchListener thisOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int[] coordinateLocation = new int[2];

            if (isAnimating) {
                return true;
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    moveBeginCoordinateY = (int) event.getRawY();

                    rootView.getLocationOnScreen(coordinateLocation);
                    rootViewCoordinateY = coordinateLocation[1];

                    if (null != thisDelayHandler && null != autoHideRunnable) {
                        thisDelayHandler.removeCallbacks(autoHideRunnable);
                    }

                    // LogUtils.e("~~~yanss~~~ >>> ACTION_DOWN() >>> 1)Begin 2)Current 3)Delta 4)Params.y 5)Y 6)getY()", Integer.toString(moveBeginCoordinateY) + "~" + Integer.toString(moveCurrentCoordinateY) + "~" + Integer.toString(moveDeltaCoordinateY) + "~" + Integer.toString(thisLayoutParams.y) + "~" + Integer.toString(rootViewCoordinateY) + "~" + Integer.toString(coordinateLocation[1]));
                    break;

                case MotionEvent.ACTION_MOVE:
                    moveCurrentCoordinateY = (int) event.getRawY();
                    moveDeltaCoordinateY = moveCurrentCoordinateY - moveBeginCoordinateY;

                    if (moveDeltaCoordinateY < 0) {
                        FrameLayout.LayoutParams containerViewLayoutParams = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                        containerViewLayoutParams.topMargin += moveDeltaCoordinateY;
                        rootView.setLayoutParams(containerViewLayoutParams);
                    }

                    // if (moveDeltaCoordinateY < 0) {
                    //     int y = rootViewCoordinateY + moveDeltaCoordinateY;
                    //     rootView.setY(y);
                    // }

                    // FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                    // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onTouch() >>> marginTop", Integer.toString(layoutParams.topMargin));
                    // layoutParams.topMargin = 0 - SuningFunctionUtils.dip2px(context, 50);
                    // rootView.setLayoutParams(layoutParams);
                    // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onTouch() >>> marginTop 2", Integer.toString(layoutParams.topMargin));

                    moveBeginCoordinateY = moveCurrentCoordinateY;

                    // rootView.getLocationOnScreen(coordinateLocation);
                    // LogUtils.e("~~~yanss~~~ >>> ACTION_MOVE() >>> 1)Begin 2)Current 3)Delta 4)Params.y 5)Y 6)getY()", Integer.toString(moveBeginCoordinateY) + "~" + Integer.toString(moveCurrentCoordinateY) + "~" + Integer.toString(moveDeltaCoordinateY) + "~" + Integer.toString(thisLayoutParams.y) + "~" + Integer.toString(rootViewCoordinateY) + "~" + Integer.toString(coordinateLocation[1]));
                    break;

                case MotionEvent.ACTION_UP:
                    moveCurrentCoordinateY = (int) event.getRawY();
                    moveDeltaCoordinateY = moveCurrentCoordinateY - moveBeginCoordinateY;

                    if (Math.abs(((FrameLayout.LayoutParams) rootView.getLayoutParams()).topMargin) > 0) {
                        hideFloatingWindow();
                    } else {
                        if (null == thisDelayHandler) {
                            thisDelayHandler = new Handler();
                        }
                        thisDelayHandler.postDelayed(autoHideRunnable, AUTO_HIDE_DELAY_TIME);
                    }


                    // if (rootView.getY() > rootView.getHeight()) {
                    //     sendMessage(MSG_HIDE_FLOATING_WINDOW);
                    // } else {
                    //     if (null == thisDelayHandler) {
                    //         thisDelayHandler = new Handler();
                    //     }
                    //     thisDelayHandler.postDelayed(autoHideRunnable, AUTO_HIDE_DELAY_TIME);
                    // }


                    // rootView.getLocationOnScreen(coordinateLocation);
                    // LogUtils.e("~~~yanss~~~ >>> ACTION_UP() >>> 1)Begin 2)Current 3)Delta 4)Params.y 5)Y 6)getY()", Integer.toString(moveBeginCoordinateY) + "~" + Integer.toString(moveCurrentCoordinateY) + "~" + Integer.toString(moveDeltaCoordinateY) + "~" + Integer.toString(thisLayoutParams.y) + "~" + Integer.toString(rootViewCoordinateY) + "~" + Integer.toString(coordinateLocation[1]));
                    break;

                default:
                    break;
            }
            return true;
        }
    };

    /**
     * 动画监听
     * yanss 2017/04/10 16:54:28
     */
    private Animation.AnimationListener thisAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            isAnimating = true;

            if (animSlideDownIn.equals(animation)) {
                // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onAnimationStart() >>> " + "animSlideDownIn");
                isShown = true;
                return;
            }

            if (animSlideUpOut.equals(animation)) {
                // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onAnimationStart() >>> " + "animSlideUpOut");
                if (null == hidingRunnable) {
                    hidingRunnable = new Runnable() {
                        @Override
                        public void run() {
                            clearView();
                            if (isAnimating) {
                                isAnimating = false;
                            }
                        }
                    };
                }
                if (null == thisDelayHandler) {
                    thisDelayHandler = new Handler();
                }
                thisDelayHandler.postDelayed(hidingRunnable, ANIM_DURATION);
                return;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            isAnimating = false;

            if (animSlideUpOut.equals(animation)) {
                // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onAnimationEnd() >>> ", "animSlideUpOut");
                // clearView();
                return;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.onAnimationRepeat() >>> " + "");
        }
    };

    /**
     * 通过 Handler 发送消息，在主线程中进行 UI 更新
     * yanss 2017/04/11 10:26:27
     */
    private Handler thisMessageHandler = new Handler() {
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
    private Handler thisDelayHandler = new Handler();


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initViewFindById(View view) {
        ivScreenshot = (ImageView) view.findViewById(R.id.iv_screenshot);
        llTouchMoveBtn = (LinearLayout) view.findViewById(R.id.ll_touch_move_btn);
        view.findViewById(R.id.iv_feedback).setOnClickListener(thisOnClickListener);
        view.findViewById(R.id.iv_customservice).setOnClickListener(thisOnClickListener);
        view.findViewById(R.id.iv_share).setOnClickListener(thisOnClickListener);

        animSlideDownIn = AnimationUtils.loadAnimation(mActivity, R.anim.slide_down_in);
        animSlideUpOut = AnimationUtils.loadAnimation(mActivity, R.anim.slide_up_out);

        // LogUtils.e("initViewFindById", animSlideDownIn.toString() + "::" + animSlideUpOut.toString());
    }

    private void initViewContent() {
        animSlideDownIn.setDuration(ANIM_DURATION);
        animSlideUpOut.setDuration(ANIM_DURATION);

        animSlideDownIn.setFillAfter(true);
        animSlideUpOut.setFillAfter(true);
    }

    private void initListener() {
        llTouchMoveBtn.setOnTouchListener(thisOnTouchListener);
        animSlideDownIn.setAnimationListener(thisAnimationListener);
        animSlideUpOut.setAnimationListener(thisAnimationListener);
    }

    private void initLayoutParams() {
        if (null == thisWindowManager) {
            thisWindowManager = (WindowManager) mActivity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }
        if (null == thisLayoutParams) {
            thisLayoutParams = new WindowManager.LayoutParams();
        }
        if(Build.VERSION.SDK_INT >= 26) {
            thisLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else {
            thisLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        }
        thisLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        thisLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        thisLayoutParams.format = PixelFormat.TRANSLUCENT;
        thisLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        thisLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        thisLayoutParams.x = 0;
        thisLayoutParams.y = 0;
        // thisLayoutParams.windowAnimations = android.R.style.Animation_Translucent;
    }

    private Bitmap refreshViewContent() {
        // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.refreshViewContent() >>> filePath = ", filePath == null ? "null" : filePath);
        Bitmap bitmap = ImageUtils.INSTANCE.getBitmap(filePath, 300, 300);
        if (null == bitmap) {
            if (null == waitBitmapRunnable) {
                waitBitmapRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.run() >>> showFloatingWindow again");
                        showFloatingWindow(filePath);
                    }
                };
            }
            if (null == thisDelayHandler) {
                thisDelayHandler = new Handler();
            }
            thisDelayHandler.postDelayed(waitBitmapRunnable, WAIT_BITMAP_DELAY_TIME);
            return null;
        }

        int[] parameter = {bitmap.getWidth(), bitmap.getHeight()};
        ivScreenshot.setScaleType(ImageView.ScaleType.FIT_XY);
        ivScreenshot.setTag(parameter);
        ivScreenshot.setImageBitmap(bitmap);
        return bitmap;
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
        // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHide.clearView() Begin>>> isShown || isAnimating", isShown + "||" + isAnimating);
        try {
            isShown = false;

            rootView.clearAnimation();
            if (containerView.getChildCount() > 0) {
                containerView.removeView(rootView);
            }
            thisWindowManager.removeView(containerView);
            // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHide.clearView() End>>> isShown || isAnimating", isShown + "||" + isAnimating);
        } catch (Exception e) {
            // LogUtils.logException(e);
        }
    }


    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    public void showFloatingWindow(String filePath) {
        // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.showFloatingWindow() >>> show Time", Long.toString(SystemClock.elapsedRealtime()));

        this.filePath = filePath;
        if (null == refreshViewContent()) {
            return;
        }

        if (null != thisDelayHandler && null != autoHideRunnable) {
            thisDelayHandler.removeCallbacks(autoHideRunnable);
        }
        // clearView();

        if (!isShown) {
            try {
                thisWindowManager.addView(containerView, thisLayoutParams);
                FrameLayout.LayoutParams containerViewLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                containerViewLayoutParams.topMargin = 0;
                containerView.addView(rootView, containerViewLayoutParams);
                rootView.startAnimation(animSlideDownIn);
            }catch (WindowManager.BadTokenException e){
            }catch (Exception e){
            }
        } else {
            thisWindowManager.updateViewLayout(containerView, thisLayoutParams);
        }

        if (null == autoHideRunnable) {
            autoHideRunnable = new Runnable() {
                @Override
                public void run() {
                    // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.run() >>> isShown || isAnimating", isShown + "||" + isAnimating);
                    if (isShown && !isAnimating) {
                        sendMessage(MSG_HIDE_FLOATING_WINDOW);
                    }
                }
            };
        }
        if (null == thisDelayHandler) {
            thisDelayHandler = new Handler();
        }
        thisDelayHandler.postDelayed(autoHideRunnable, AUTO_HIDE_DELAY_TIME + ANIM_DURATION);

        // if (null == autoHideTimer) {
        //     autoHideTimer = new Timer();
        // }
        //
        // if (null != autoHideTimerTask) {
        //     autoHideTimerTask.cancel();
        // }
        //
        // /**
        //  * 自动隐藏悬浮窗
        //  * yanss 2017/04/07 14:20:16
        //  */
        // autoHideTimerTask = new TimerTask() {
        //     @Override
        //     public void run() {
        //         if (isShown) {
        //             hideFloatingWindow();
        //         }
        //     }
        // };
        // autoHideTimer.schedule(autoHideTimerTask, AUTO_HIDE_DELAY_TIME);
    }

    public void hideFloatingWindow() {
        // LogUtils.e("~~~yanss~~~ >>> ScreenshotAutoHideFloatingWindow.hideFloatingWindow() >>> ");
        rootView.startAnimation(animSlideUpOut);
    }

    public void sendMessage(int id) {
        if (thisMessageHandler != null) {
            Message message = Message.obtain(thisMessageHandler, id);
            thisMessageHandler.sendMessage(message);
        }
    }


    // ---------- ---------- ---------- Lifecycle ---------- ---------- ----------


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public ScreenshotAutoHideFloatingWindow(Activity activity) {
        mActivity = activity;

        if (null == rootView) {
            rootView = (LinearLayout) LayoutInflater.from(mActivity.getApplicationContext()).inflate(R.layout.floating_window_screenshot_auto_hide, null, false);
        }

        if (null == containerView) {
            containerView = new FrameLayout(mActivity);
        }

        initViewFindById(rootView);
        initViewContent();
        initListener();
        initLayoutParams();
    }

    public static ScreenshotAutoHideFloatingWindow newInstance(Activity activity) {
        if (null == thisInstance) {
            thisInstance = new ScreenshotAutoHideFloatingWindow(activity);
        }
        return thisInstance;
    }


    // ---------- ---------- ---------- Getters And Setters ---------- ---------- ----------
}
