package com.githubyss.mobile.common.ui.floatingview.feature.magnet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.kit.info.ScreenInfo;
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.FeatureAutoShortenViewToContainerViewListener;
import com.githubyss.mobile.common.ui.utils.SystemUtils;


/**
 * FeatureMagnetView
 * <Description> 磁力吸附悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:13:32
 */
public class FeatureMagnetView extends FrameLayout {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    protected View    rootView;
    private   Context containerContext;
    
    private final int MARGIN_EDGE          = ScreenInfo.INSTANCE.dp2Px(14.0f);
    private final int TOUCH_TIME_THRESHOLD = 150;
    
    private   float   originalRawX;
    private   float   originalRawY;
    private   float   originalX;
    private   float   originalY;
    private   int     screenWidth;
    private   int     screenHeight;
    private   int     statusBarHeight;
    private   float   portraitY;
    private   long    lastTouchDownTime;
    private   boolean isNearestLeft = true;
    protected boolean isMovable     = true;
    
    private FeatureMagnetViewToDesignateViewListener featureMagnetViewToDesignateViewListener;
    
    private MoveAnimatorRunnable moveAnimatorRunnable;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public FeatureMagnetView(Context context) {
        this(context, null);
    }
    
    public FeatureMagnetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public FeatureMagnetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        containerContext = context;
        // initInBase();
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event == null) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                changeOriginalTouchParams(event);
                updateSize();
                moveAnimatorRunnable.stop();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isMovable) {
                    updateViewPosition(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                clearPortraitY();
                moveToEdge();
                if (isOnClickEvent()) {
                    onClick();
                }
                break;
            default:
                break;
        }
        return true;
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
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    protected void initInBase() {
        moveAnimatorRunnable = new MoveAnimatorRunnable();
        statusBarHeight = SystemUtils.getStatusBarHeight(getContext());
        setClickable(true);
        // updateSize();
    }
    
    protected void moveToEdge() {
        moveToEdge(isNearestLeft(), false);
    }
    
    protected void moveToEdge(boolean isLeft, boolean isLandscape) {
        float moveDistance = isLeft ? MARGIN_EDGE : screenWidth - MARGIN_EDGE;
        float y            = getY();
        if (!isLandscape && portraitY != 0) {
            y = portraitY;
            clearPortraitY();
        }
        moveAnimatorRunnable.start(moveDistance, Math.min(Math.max(0, y), screenHeight - getHeight()));
    }
    
    protected boolean isNearestLeft() {
        int middle = screenWidth / 2;
        isNearestLeft = getX() < middle;
        return isNearestLeft;
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private boolean isOnClickEvent() {
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
    
    private void updateSize() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            screenWidth = viewGroup.getWidth() - getWidth();
            screenHeight = viewGroup.getHeight();
        }
        // screenWidth = (SystemUtils.getScreenWidth(getContext()) - this.getWidth());
        // screenHeight = SystemUtils.getScreenHeight(getContext());
    }
    
    private void clearPortraitY() {
        portraitY = 0;
    }
    
    private void move(float deltaX, float deltaY) {
        setX(getX() + deltaX);
        setY(getY() + deltaY);
    }
    
    private void markPortraitY(boolean isLandscape) {
        if (isLandscape) {
            portraitY = getY();
        }
    }
    
    private void onClick() {
        if (featureMagnetViewToDesignateViewListener != null) {
            featureMagnetViewToDesignateViewListener.onClick(this);
        }
    }
    
    private void onRemove() {
        if (featureMagnetViewToDesignateViewListener != null) {
            featureMagnetViewToDesignateViewListener.onRemove(this);
        }
    }
    
    
    // ---------- ---------- ---------- Implementations ---------- ---------- ----------
    
    /** 动画 */
    protected class MoveAnimatorRunnable implements Runnable {
        private Handler handler = new Handler(Looper.getMainLooper());
        private float   destinationX;
        private float   destinationY;
        private long    startingTime;
        
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
            float deltaX   = (destinationX - getX()) * progress;
            float deltaY   = (destinationY - getY()) * progress;
            move(deltaX, deltaY);
            if (progress < 1) {
                handler.post(this);
            }
        }
        
        private void stop() {
            handler.removeCallbacks(this);
        }
    }
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public void setFeatureMagnetViewToDesignateViewListener(FeatureMagnetViewToDesignateViewListener featureMagnetViewToDesignateViewListener) {
        this.featureMagnetViewToDesignateViewListener = featureMagnetViewToDesignateViewListener;
    }
}
