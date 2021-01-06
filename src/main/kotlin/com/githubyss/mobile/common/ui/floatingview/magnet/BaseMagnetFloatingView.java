package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.ui.utils.SystemUtils;


/**
 * BaseFloatingMagnetView
 * <Description> 磁力吸附悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:13:32
 */
public class BaseMagnetFloatingView extends FrameLayout {

    public static final int MARGIN_EDGE = 13;
    private static final int TOUCH_TIME_THRESHOLD = 150;

    private float   originalRawX;
    private float   originalRawY;
    private float   originalX;
    private float   originalY;
    protected int   screenWidth;
    private int     screenHeight;
    private int     statusBarHeight;
    private float   portraitY;
    private long    lastTouchDownTime;
    private boolean isNearestLeft = true;

    private   DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener;
    protected MoveAnimator                         moveAnimator;

    public void setMagnetViewListener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener) {
        this.designatedMagnetFloatingViewListener = designatedMagnetFloatingViewListener;
    }

    public BaseMagnetFloatingView(Context context) {
        this(context, null);
    }

    public BaseMagnetFloatingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseMagnetFloatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        moveAnimator = new MoveAnimator();
        statusBarHeight = SystemUtils.getStatusBarHeight(getContext());
        setClickable(true);
        // updateSize();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event == null) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changeOriginalTouchParams(event);
                updateSize();
                moveAnimator.stop();
                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition(event);
                break;
            case MotionEvent.ACTION_UP:
                clearPortraitY();
                moveToEdge();
                if (isOnClickEvent()) {
                    dealClickEvent();
                }
                break;
        }
        return true;
    }

    protected void dealClickEvent() {
        if (designatedMagnetFloatingViewListener != null) {
            designatedMagnetFloatingViewListener.onClick(this);
        }
    }

    protected boolean isOnClickEvent() {
        return System.currentTimeMillis() - lastTouchDownTime < TOUCH_TIME_THRESHOLD;
    }

    private void updateViewPosition(MotionEvent event) {
        setX(originalX + event.getRawX() - originalRawX);
        // 限制不可超出屏幕高度
        float desY = originalY + event.getRawY() - originalRawY;
        if (desY < statusBarHeight) {
            desY = statusBarHeight;
        }
        if (desY > screenHeight - getHeight()) {
            desY = screenHeight - getHeight();
        }
        setY(desY);
    }

    private void changeOriginalTouchParams(MotionEvent event) {
        originalX = getX();
        originalY = getY();
        originalRawX = event.getRawX();
        originalRawY = event.getRawY();
        lastTouchDownTime = System.currentTimeMillis();
    }

    protected void updateSize() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            screenWidth = viewGroup.getWidth() - getWidth();
            screenHeight = viewGroup.getHeight();
        }
        // mScreenWidth = (SystemUtils.getScreenWidth(getContext()) - this.getWidth());
        // mScreenHeight = SystemUtils.getScreenHeight(getContext());
    }

    public void moveToEdge() {
        moveToEdge(isNearestLeft(), false);
    }

    public void moveToEdge(boolean isLeft, boolean isLandscape) {
        float moveDistance = isLeft ? MARGIN_EDGE : screenWidth - MARGIN_EDGE;
        float y = getY();
        if (!isLandscape && portraitY != 0) {
            y = portraitY;
            clearPortraitY();
        }
        moveAnimator.start(moveDistance, Math.min(Math.max(0, y), screenHeight - getHeight()));
    }

    private void clearPortraitY() {
        portraitY = 0;
    }

    protected boolean isNearestLeft() {
        int middle = screenWidth / 2;
        isNearestLeft = getX() < middle;
        return isNearestLeft;
    }

    public void onRemove() {
        if (designatedMagnetFloatingViewListener != null) {
            designatedMagnetFloatingViewListener.onRemove(this);
        }
    }

    protected class MoveAnimator implements Runnable {

        private Handler handler = new Handler(Looper.getMainLooper());
        private float destinationX;
        private float destinationY;
        private long startingTime;

        void start(float x, float y) {
            this.destinationX = x;
            this.destinationY = y;
            startingTime = System.currentTimeMillis();
            handler.post(this);
        }

        @Override
        public void run() {
            if (getRootView() == null || getRootView().getParent() == null) {
                return;
            }
            float progress = Math.min(1, (System.currentTimeMillis() - startingTime) / 400f);
            float deltaX = (destinationX - getX()) * progress;
            float deltaY = (destinationY - getY()) * progress;
            move(deltaX, deltaY);
            if (progress < 1) {
                handler.post(this);
            }
        }

        private void stop() {
            handler.removeCallbacks(this);
        }
    }

    private void move(float deltaX, float deltaY) {
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getParent() != null) {
            final boolean isLandscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE;
            markPortraitY(isLandscape);
            ((ViewGroup) getParent()).post(new Runnable() {
                @Override
                public void run() {
                    updateSize();
                    moveToEdge(isNearestLeft, isLandscape);
                }
            });
        }
    }

    private void markPortraitY(boolean isLandscape) {
        if (isLandscape) {
            portraitY = getY();
        }
    }
}
