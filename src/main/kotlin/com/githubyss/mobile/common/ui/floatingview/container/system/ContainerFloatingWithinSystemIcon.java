package com.githubyss.mobile.common.ui.floatingview.container.system;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.githubyss.mobile.common.kit.constant.Constants;
import com.githubyss.mobile.common.kit.enumeration.VersionCode;
import com.githubyss.mobile.common.kit.enumeration.WindowManagerLayoutParamsType;
import com.githubyss.mobile.common.kit.util.ScreenUtils;
import com.githubyss.mobile.common.kit.manager.audio.constant.Constant;
import com.githubyss.mobile.common.ui.floatingview.designate.icon.DesignatedIconView;
import com.githubyss.mobile.common.ui.floatingview.designate.icon.DesignatedIconViewListener;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetView;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetViewToDesignateViewListener;
import com.githubyss.mobile.common.ui.utils.PermissionOverlayUtils;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


/**
 * ContainerFloatingWithinSystemIcon
 * <Description> 系统级别悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 19:48:13
 */
public class ContainerFloatingWithinSystemIcon implements ContainerFloatingWithinSystemInterface<ContainerFloatingWithinSystemIcon, DesignatedIconView, FeatureMagnetView> {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static ContainerFloatingWithinSystemIcon instance;
    
    private Context containerContext;
    
    private WindowManager              windowManager;
    private ViewGroup                  containerView;
    private WindowManager.LayoutParams containerLayoutParams;
    
    private DesignatedIconView     designatedView;
    private ViewGroup.LayoutParams designatedLayoutParams;
    
    /** 是否跳转过悬浮窗权限设置页 */
    private boolean isJumpToOverlayPermission = true;
    
    private ContainerFloatingWithinSystemListener containerFloatingWithinSystemListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ContainerFloatingWithinSystemIcon(@NonNull Context context) {
        initInContainer(context);
    }
    
    public static ContainerFloatingWithinSystemIcon getInstance(Context context) {
        if (instance == null) {
            synchronized (ContainerFloatingWithinSystemIcon.class) {
                if (instance == null) {
                    instance = new ContainerFloatingWithinSystemIcon(context);
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ContainerFloatingWithinSystemIcon show() {
        if (!checkPermission(true)) {
            return null;
        }
        
        initLayoutParams();
        ensureFloatingView();
        return this;
    }
    
    @Override
    public void close() {
        if (containerContext != null && voiceReceiver != null) {
            LocalBroadcastManager.getInstance(containerContext).unregisterReceiver(voiceReceiver);
        }
        
        removeFloatingView();
    }
    
    @Override
    public void layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedView != null) {
            designatedView.setLayoutParams(params);
        }
    }
    
    @Override
    public ContainerFloatingWithinSystemIcon setMovable(boolean isMovable) {
        if (designatedView != null) {
            designatedView.setMovable(isMovable);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinSystemIcon customIcon(Drawable drawable) {
        if (designatedView != null) {
            designatedView.customIcon(drawable);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinSystemIcon customIcon(int drawableId) {
        if (designatedView != null) {
            designatedView.customIcon(drawableId);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinSystemIcon customView(DesignatedIconView viewGroup) {
        this.designatedView = viewGroup;
        if (designatedView != null) {
            designatedView.customView(viewGroup);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinSystemIcon customView(int layoutId) {
        if (designatedView != null) {
            designatedView.customView(layoutId);
        }
        return this;
    }
    
    @Override
    public DesignatedIconView getDesignatedView() {
        return designatedView;
    }
    
    @Override
    public FeatureMagnetView getFeatureView() {
        return designatedView;
    }
    
    @Override
    public void refreshViewWhenAppForeground() {
        if (containerView != null) {
            containerView.setVisibility(View.VISIBLE);
        }
    }
    
    @Override
    public void refreshViewWhenAppBackground() {
        if (containerView != null) {
            containerView.setVisibility(View.GONE);
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initInContainer(Context context) {
        this.containerContext = context;
        initLocalBroadcastReceiver();
    }
    
    private void initLocalBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.INTENT_ACTION_IS_FOREGROUND);
        intentFilter.addAction(Constants.INTENT_ACTION_CLOSE_FLOAT);
        if (containerContext != null) {
            // 注册广播接收器
            LocalBroadcastManager.getInstance(containerContext).registerReceiver(voiceReceiver, intentFilter);
        }
    }
    
    private void initLayoutParams() {
        if (Build.VERSION.SDK_INT >= VersionCode.O) {
            if (PermissionOverlayUtils.isMiUiO()) {
                getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_PRESENTATION;
            } else {
                getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_APPLICATION_OVERLAY;
            }
        } else {
            getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_PHONE;
        }
        
        // if (Build.VERSION.SDK_INT >= VersionCode.O) {
        //     layoutParams.type = WindowManagerLayoutParamsType.TYPE_APPLICATION_OVERLAY;
        // } else {
        //     layoutParams.type = WindowManagerLayoutParamsType.TYPE_TOAST;
        // }
        
        getContainerLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH) | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        // getContainerLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        getContainerLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getContainerLayoutParams().format = PixelFormat.TRANSLUCENT;
        getContainerLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().x = 0;
        getContainerLayoutParams().y = ScreenUtils.INSTANCE.dp2Px(0.0f);
        // getContainerLayoutParams().windowAnimations = android.R.style.Animation_Translucent;
        
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(0, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 0);
    }
    
    private WindowManager.LayoutParams getContainerLayoutParams() {
        if (containerLayoutParams == null) {
            containerLayoutParams = new WindowManager.LayoutParams();
        }
        return (WindowManager.LayoutParams) containerLayoutParams;
    }
    
    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
    }
    
    private void ensureFloatingView() {
        synchronized (this) {
            if (designatedView == null) {
                designatedView = new DesignatedIconView(containerContext);
                designatedView.setLayoutParams(getDesignatedLayoutParams());
                designatedView.setBackgroundColor(0x000000FF);
                initListener();
                addViewToWindow(designatedView);
            }
        }
        
        // if (!isShown) {
        // } else {
        //     getWindowManager().updateViewLayout(containerView, layoutParams);
        // }
    }
    
    private void initListener() {
        if (designatedView != null) {
            designatedView.setDesignatedIconViewListener(new DesignatedIconViewListener() {
                @Override
                public void onClose() {
                    removeFloatingView();
                }
                
                @Override
                public void onClick() {
                }
            });
            
            designatedView.setFeatureMagnetViewToDesignateViewListener(new FeatureMagnetViewToDesignateViewListener() {
                @Override
                public void onRemove(FeatureMagnetView magnetView) {
                    removeFloatingView();
                }
                
                @Override
                public void onClick(FeatureMagnetView magnetView) {
                }
            });
        }
    }
    
    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedView != null) {
                    try {
                        if (getContainerView() != null && getContainerView().getChildCount() > 0) {
                            getContainerView().removeView(designatedView);
                        }
                        getWindowManager().removeView(getContainerView());
                    } catch (Exception e) {
                    }
                    designatedView = null;
                }
            }
        });
    }
    
    private void addViewToWindow(final View designatedView) {
        if (getContainerView() == null) {
            return;
        }
        try {
            getContainerView().addView(designatedView);
            getWindowManager().addView(getContainerView(), getContainerLayoutParams());
        } catch (Exception e) {
        }
    }
    
    private FrameLayout getContainerView() {
        if (containerView == null) {
            containerView = new FrameLayout(containerContext);
            containerView.setBackgroundColor(0x00FFFF33);
        }
        return (FrameLayout) containerView;
    }
    
    private WindowManager getWindowManager() {
        if (windowManager == null && containerContext != null) {
            windowManager = (WindowManager) containerContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }
    
    private boolean checkPermission(boolean isNeedJumpToOverlayPermission) {
        // 获取悬浮窗权限状态
        boolean isHasOverlayPermission = PermissionOverlayUtils.hasPermission(containerContext);
        // 没有悬浮窗权限，并且需要去开启权限，跳转悬浮窗权限设置页
        if (!isHasOverlayPermission && isNeedJumpToOverlayPermission) {
            // 跳转悬浮窗权限设置页
            PermissionOverlayUtils.jumpToOverlayPermission(containerContext);
        }
        return isHasOverlayPermission;
    }
    
    
    // ---------- ---------- ---------- Implementations ---------- ---------- ----------
    
    /** 监听前后台切换 */
    private BroadcastReceiver voiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 前后台事件
            if (action.equals(Constant.INTENT_ACTION_IS_FOREGROUND)) {
                // 回到前台
                if (intent.getBooleanExtra("isForeground", true)) {
                    refreshViewWhenAppForeground();
                    
                    if (isJumpToOverlayPermission) {
                        // 跳转状态重置为假
                        isJumpToOverlayPermission = false;
                        
                        // 悬浮窗权限是关的，拦截后续逻辑，等待用户重新点击
                        if (!checkPermission(false)) {
                            return;
                        }
                        
                        show();
                    }
                }
                // 进入后台
                else {
                    refreshViewWhenAppBackground();
                }
            }
            // 关闭浮窗事件
            else if (action.equals(Constant.INTENT_ACTION_CLOSE_FLOAT)) {
                close();
            }
        }
    };
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ContainerFloatingWithinSystemIcon setContainerFloatingWithinSystemListener(ContainerFloatingWithinSystemListener containerFloatingWithinSystemListener) {
        this.containerFloatingWithinSystemListener = containerFloatingWithinSystemListener;
        return this;
    }
}
