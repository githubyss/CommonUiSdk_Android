package com.githubyss.mobile.common.ui.floatingview.container.system;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
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
import com.githubyss.mobile.common.kit.info.ScreenInfo;
import com.githubyss.mobile.common.ui.audio.constant.Constant;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.DesignatedAudioPlayerView;
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.DesignatedAudioPlayerViewListener;
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.FeatureAutoShortenViewToContainerViewListener;
import com.githubyss.mobile.common.ui.utils.PermissionOverlayUtils;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


/**
 * ContainerFloatingWithinSystemAudioPlayer
 * <Description> 系统级别悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 14:52:42
 */
public class ContainerFloatingWithinSystemAudioPlayer implements ContainerFloatingWithinSystemInterface<ContainerFloatingWithinSystemAudioPlayer> {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static ContainerFloatingWithinSystemAudioPlayer instance;
    
    private Context containerContext;
    
    private WindowManager              windowManager;
    private ViewGroup                  containerView;
    private WindowManager.LayoutParams containerLayoutParams;
    
    private DesignatedAudioPlayerView designatedView;
    private ViewGroup.LayoutParams    designatedLayoutParams;
    
    /** 是否跳转过悬浮窗权限设置页 */
    private boolean isJumpToOverlayPermission = true;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ContainerFloatingWithinSystemAudioPlayer(@NonNull Context context) {
        init(context);
    }
    
    public static ContainerFloatingWithinSystemAudioPlayer getInstance(Context context) {
        if (instance == null) {
            synchronized (ContainerFloatingWithinSystemAudioPlayer.class) {
                if (instance == null) {
                    instance = new ContainerFloatingWithinSystemAudioPlayer(context);
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ContainerFloatingWithinSystemAudioPlayer show() {
        if (!checkPermission(true)) {
            return null;
        }
        
        initLayoutParams();
        ensureFloatingView();
        return this;
    }
    
    @Override
    public void close() {
        AudioPlayManager.getInstance().destroy();
        
        if (containerContext != null && voiceReceiver != null) {
            LocalBroadcastManager.getInstance(containerContext).unregisterReceiver(voiceReceiver);
        }
        
        if (designatedView != null) {
            designatedView.closeFloatingWindow();
        }
    }
    
    @Override
    public void layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedView != null) {
            designatedView.setLayoutParams(params);
        }
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
    
    private void init(Context context) {
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
        getContainerLayoutParams().gravity = Gravity.LEFT | Gravity.BOTTOM;
        getContainerLayoutParams().format = PixelFormat.TRANSLUCENT;
        getContainerLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().x = 0;
        getContainerLayoutParams().y = ScreenInfo.INSTANCE.dp2Px(70.0f);
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
                designatedView = new DesignatedAudioPlayerView(containerContext);
                designatedView.setLayoutParams(getDesignatedLayoutParams());
                designatedView.setBackgroundColor(0x000000FF);
                designatedView.setFeatureAutoShortenViewToContainerViewListener(new FeatureAutoShortenViewToContainerViewListener() {
                    @Override
                    public void onShow() {
                    }
                    
                    @Override
                    public void onClose() {
                        removeFloatingView();
                    }
                });
                addViewToWindow(designatedView);
                designatedView.showFloatingWindow();
            }
        }
        
        // if (!isShown) {
        // } else {
        //     getWindowManager().updateViewLayout(containerView, layoutParams);
        // }
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
                        
                        if (show() != null) {
                            designatedView.play(AudioPlayManager.getInstance().getAudioList());
                        }
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
    
    public DesignatedAudioPlayerView getDesignatedView() {
        return designatedView;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ContainerFloatingWithinSystemAudioPlayer setForNativeDesignatedViewListener(DesignatedAudioPlayerViewListener forNativeDesignatedViewListener) {
        designatedView.setForNativeDesignatedAudioPlayerViewListener(forNativeDesignatedViewListener);
        return this;
    }
    
    public ContainerFloatingWithinSystemAudioPlayer setForWebDesignatedViewListener(DesignatedAudioPlayerViewListener forWebDesignatedViewListener) {
        designatedView.setForNativeDesignatedAudioPlayerViewListener(forWebDesignatedViewListener);
        return this;
    }
}
