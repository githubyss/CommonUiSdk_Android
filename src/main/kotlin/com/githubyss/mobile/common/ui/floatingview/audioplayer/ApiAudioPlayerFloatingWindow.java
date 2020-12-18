package com.githubyss.mobile.common.ui.floatingview.audioplayer;

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

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.constant.Constant;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.enumeration.AudioState;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayListener;
import com.githubyss.mobile.common.ui.utils.PermissionOverlayUtils;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


/**
 * ApiAudioPlayerFloatingWindow
 * <Description> 音频播放器悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/14 17:53:33
 */
public class ApiAudioPlayerFloatingWindow implements ApiAudioPlayerFloatingWindowInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static ApiAudioPlayerFloatingWindow instance;
    
    private Context containerContext;
    private WindowManager windowManager;
    private ViewGroup containerView;
    private WindowManager.LayoutParams containerLayoutParams;
    private DesignatedAudioPlayerFloatingView designatedFloatingView;
    private ViewGroup.LayoutParams designatedLayoutParams;
    @LayoutRes
    private int layoutId = R.layout.comui_floating_audio_player_view;
    /** 原生 listener */
    private ApiAudioPlayerFloatingWindowListener nativeAudioListener;
    /** Web 端 listener */
    private ApiAudioPlayerFloatingWindowListener webAudioListener;
    
    /** 是否跳转过悬浮窗权限设置页 */
    private boolean isJumpToOverlayPermission = false;
    
    //8.0 type样式，不可修改，为适应低版本编译，自己定义
    public static final int TYPE_APPLICATION_OVERLAY = 2038;
    
    //小米8.0,8.1的坑，正常规则走不通，只能走系统漏洞。
    public static final int TYPE_PRESENTATION = 2037;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ApiAudioPlayerFloatingWindow() {
    }
    
    public static ApiAudioPlayerFloatingWindow getInstance() {
        if (instance == null) {
            synchronized (ApiAudioPlayerFloatingWindow.class) {
                if (instance == null) {
                    instance = new ApiAudioPlayerFloatingWindow();
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ApiAudioPlayerFloatingWindow init(Context context) {
        this.containerContext = context;
        initLocalBroadcastReceiver();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow show() {
        initLayoutParams();
        ensureFloatingView();
        listener(new DesignatedAudioPlayerFloatingViewListener() {
            @Override
            public void onClose() {
                removeFloatingView();
            }
            
            @Override
            public void onUpdateAudioInfo(AudioModel audioModel) {
                if (nativeAudioListener != null) {
                    nativeAudioListener.onUpdateAudioInfo(audioModel);
                }
                if (webAudioListener != null) {
                    webAudioListener.onUpdateAudioInfo(audioModel);
                }
            }
        });
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow close() {
        AudioPlayManager.getInstance().destroy();
        
        if (containerContext != null && voiceReceiver != null) {
            LocalBroadcastManager.getInstance(containerContext).unregisterReceiver(voiceReceiver);
        }
        
        if (designatedFloatingView != null) {
            designatedFloatingView.closeFloatingWindow();
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow lengthen() {
        if (designatedFloatingView != null) {
            designatedFloatingView.lengthenFloatingWindow();
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow shorten() {
        if (designatedFloatingView != null) {
            designatedFloatingView.shortenFloatingWindow();
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow play(List<AudioModel> audioList) {
        // 默认对外开放的方法，需要去开启悬浮窗权限
        play(audioList, true);
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow play(List<AudioModel> audioList, boolean isNeedJumpToOverlayPermission) {
        if (audioList == null) {
            return this;
        }
        
        AudioListModel audioListModel = new AudioListModel();
        audioListModel.setAudioList(audioList);
        audioListModel.setCurrentIndex(0);
        
        AudioModel currentAudio = AudioPlayManager.getInstance().getCurrentAudio();
        AudioModel newListFirstAudio = audioListModel.getAudioList().get(0);
        AudioPlayManager.getInstance().setInfo(audioListModel);
        
        // 悬浮窗权限是关的，拦截后续逻辑
        if (!checkPermission(isNeedJumpToOverlayPermission)) {
            // 需要跳转开启权限
            if (isNeedJumpToOverlayPermission) {
                // 跳转状态置为真
                isJumpToOverlayPermission = true;
            }
            return this;
        }
        
        boolean isContinue = currentAudio != null && newListFirstAudio != null && currentAudio.getId().equals(newListFirstAudio.getId());
        
        if (isContinue) {
            AudioPlayManager.getInstance().start();
        } else {
            AudioPlayManager.getInstance().stop();
            AudioPlayManager.getInstance().play(containerContext);
        }
        
        if (designatedFloatingView != null) {
            // if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {
            //     AudioListModel audioListModel = new AudioListModel();
            //     audioListModel.setAudioList(audioList);
            //     audioListModel.setCurrentIndex(0);
            //
            //     if (audioListModel != null) {
            //         AudioPlayManager.getInstance().setInfo(audioListModel);
            //         AudioPlayManager.getInstance().play(containerContext);
            //         designatedFloatingView.refreshVoiceSwitch();
            //     } else {
            //         ComkitToastUtils.INSTANCE.showMessage(containerContext, ComkitResUtils.INSTANCE.getString(containerContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
            //     }
            // } else {
            //     designatedFloatingView.refreshData();
            // }
            
            designatedFloatingView.refreshVoiceSwitch();
            
            designatedFloatingView.setAudioPlayListener(new AudioPlayListener() {
                @Override
                public void onStateChanged(AudioState audioState) {
                    // if (mMusicNotification != null && !misShow && !mfinished && !isFinishing()) {
                    //     mMusicNotification.showNotify();
                    // }
                }
                
                @Override
                public void onPlayProgress(int currentPosition) {
                
                }
                
                @Override
                public void onBufferingUpdateProgress(int percent) {
                
                }
            });
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow start() {
        AudioPlayManager.getInstance().start();
        return this;
    }
    
    
    @Override
    public ApiAudioPlayerFloatingWindow pause() {
        AudioPlayManager.getInstance().pause();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow previous() {
        AudioPlayManager.getInstance().previous();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow next() {
        AudioPlayManager.getInstance().next();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow switchVoice() {
        AudioPlayManager.getInstance().switchVoice();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow stop() {
        AudioPlayManager.getInstance().stop();
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow displayWhenAppForeground() {
        if (containerView != null) {
            containerView.setVisibility(View.VISIBLE);
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow hideWhenAppBackground() {
        if (containerView != null) {
            containerView.setVisibility(View.GONE);
        }
        return this;
    }
    
    @Override
    public AudioModel getCurrentAudio() {
        return AudioPlayManager.getInstance().getCurrentAudio();
    }
    
    @Override
    public boolean isFloatingShow() {
        if (designatedFloatingView != null) {
            return designatedFloatingView.isShown();
        }
        return false;
    }
    
    @Override
    public BaseAutoShortedFloatingView getAutoShortedView() {
        return designatedFloatingView;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow customView(DesignatedAudioPlayerFloatingView viewGroup) {
        designatedFloatingView = viewGroup;
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow customView(int resource) {
        layoutId = resource;
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedFloatingView != null) {
            designatedFloatingView.setLayoutParams(params);
        }
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingWindow listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener) {
        if (designatedFloatingView != null) {
            designatedFloatingView.setDesignatedAudioPlayerFloatingViewListener(designatedAudioPlayerFloatingViewListener);
        }
        return this;
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initLocalBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.INTENT_ACTION_IS_FOREGROUND);
        intentFilter.addAction(Constant.INTENT_ACTION_CLOSE_FLOAT);
        if (containerContext != null) {
            // 注册广播接收器
            LocalBroadcastManager.getInstance(containerContext).registerReceiver(voiceReceiver, intentFilter);
        }
    }
    
    private void initLayoutParams() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (PermissionOverlayUtils.isMiUiO()) {
                getContainerLayoutParams().type = TYPE_PRESENTATION;
            } else {
                getContainerLayoutParams().type = TYPE_APPLICATION_OVERLAY;
            }
        } else {
            getContainerLayoutParams().type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        
        // if (Build.VERSION.SDK_INT >= 26) {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        // } else {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        // }
        
        getContainerLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH) | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        // getWindowLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        getContainerLayoutParams().gravity = Gravity.LEFT | Gravity.BOTTOM;
        getContainerLayoutParams().format = PixelFormat.TRANSLUCENT;
        getContainerLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getContainerLayoutParams().x = 0;
        getContainerLayoutParams().y = 0;
        // getLayoutParams().windowAnimations = android.R.style.Animation_Translucent;
    }
    
    private void ensureFloatingView() {
        synchronized (this) {
            if (designatedFloatingView == null) {
                designatedFloatingView = new DesignatedAudioPlayerFloatingView(containerContext, layoutId);
                designatedFloatingView.setLayoutParams(getDesignatedLayoutParams());
                // designatedFloatingView.setBackgroundColor(0xFF0000FF);
                addViewToWindow(designatedFloatingView);
                designatedFloatingView.showFloatingWindow();
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
                if (designatedFloatingView != null) {
                    try {
                        if (getContainerView().getChildCount() > 0) {
                            getContainerView().removeView(designatedFloatingView);
                        }
                        getWindowManager().removeView(getContainerView());
                    } catch (Exception e) {
                    }
                    designatedFloatingView = null;
                }
            }
        });
    }
    
    private void addViewToWindow(final View view) {
        getContainerView().addView(view);
        try {
            getWindowManager().addView(getContainerView(), getContainerLayoutParams());
        } catch (WindowManager.BadTokenException e) {
        } catch (Exception e) {
        }
    }
    
    private WindowManager getWindowManager() {
        if (windowManager == null && containerContext != null) {
            windowManager = (WindowManager) containerContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }
    
    private FrameLayout getContainerView() {
        if (containerView == null) {
            containerView = new FrameLayout(containerContext);
            // containerView.setBackgroundColor(0xFFFFFF33);
        }
        return (FrameLayout) containerView;
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
                    displayWhenAppForeground();
                    
                    if (isJumpToOverlayPermission) {
                        // 跳转状态重置为假
                        isJumpToOverlayPermission = false;
                        play(AudioPlayManager.getInstance().getAudioList(), false);
                    }
                }
                // 进入后台
                else {
                    hideWhenAppBackground();
                }
            }
            // 关闭浮窗事件
            else if (action.equals(Constant.INTENT_ACTION_CLOSE_FLOAT)) {
                close();
            }
        }
    };
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ApiAudioPlayerFloatingWindow setNativeAudioListener(ApiAudioPlayerFloatingWindowListener nativeAudioListener) {
        this.nativeAudioListener = nativeAudioListener;
        return this;
    }
    
    public ApiAudioPlayerFloatingWindow setWebAudioListener(ApiAudioPlayerFloatingWindowListener webAudioListener) {
        this.webAudioListener = webAudioListener;
        return this;
    }
}
