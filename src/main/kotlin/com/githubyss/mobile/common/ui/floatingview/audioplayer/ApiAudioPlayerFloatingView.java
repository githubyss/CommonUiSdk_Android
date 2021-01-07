package com.githubyss.mobile.common.ui.floatingview.audioplayer;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.githubyss.mobile.common.kit.indicator.ToastIndicator;
import com.githubyss.mobile.common.kit.resource.ComkitResUtils;
import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.constant.Constant;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.enumeration.AudioState;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayListener;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.core.view.ViewCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


/**
 * ApiAudioPlayerFloatingView
 * <Description> 音频播放器悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:32:57
 */
public class ApiAudioPlayerFloatingView implements ApiAudioPlayerFloatingViewInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static volatile ApiAudioPlayerFloatingView instance;
    
    private Context                           containerContext;
    private WeakReference<FrameLayout>        containerView;
    private DesignatedAudioPlayerFloatingView designatedFloatingView;
    private ViewGroup.LayoutParams            designatedLayoutParams;
    @LayoutRes
    private int                               layoutId = R.layout.comui_floating_audio_player;
    private ApiAudioPlayerFloatingListener    apiAudioPlayerFloatingListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ApiAudioPlayerFloatingView() {
    }
    
    public static ApiAudioPlayerFloatingView getInstance() {
        if (instance == null) {
            synchronized (ApiAudioPlayerFloatingView.class) {
                if (instance == null) {
                    instance = new ApiAudioPlayerFloatingView();
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ApiAudioPlayerFloatingView init(Context context) {
        this.containerContext = context;
        return this;
    }
    
    @Override
    public ApiAudioPlayerFloatingView show() {
        initLayoutParams();
        ensureFloatingView();
        listener(new DesignatedAudioPlayerFloatingViewListener() {
            @Override
            public void onClose() {
                removeFloatingView();
            }
            
            @Override
            public void onUpdateAudioInfo(AudioModel audioModel) {
                apiAudioPlayerFloatingListener.onUpdateAudioInfo(audioModel);
            }
        });
        return this;
    }
    
    @Override
    public void close() {
        AudioPlayManager.getInstance().destroy();
        if (designatedFloatingView != null) {
            designatedFloatingView.closeFloatingWindow();
        }
    }
    
    @Override
    public void lengthen() {
        if (designatedFloatingView != null) {
            designatedFloatingView.lengthenFloatingWindow();
        }
    }
    
    @Override
    public void shorten() {
        if (designatedFloatingView != null) {
            designatedFloatingView.shortenFloatingWindow();
        }
    }
    
    @Override
    public void play(List<AudioModel> audioList) {
        // 默认对外开放的方法，需要去开启悬浮窗权限
        play(audioList, true);
    }
    
    @Override
    public void play(List<AudioModel> audioList, boolean isNeedJumpToOverlayPermission) {
        if (audioList == null) {
            return;
        }
        
        AudioListModel audioListModel = new AudioListModel();
        audioListModel.setAudioList(audioList);
        audioListModel.setCurrentIndex(0);
        
        AudioModel currentAudio      = AudioPlayManager.getInstance().getCurrentAudio();
        AudioModel newListFirstAudio = audioListModel.getAudioList().get(0);
        AudioPlayManager.getInstance().setInfo(audioListModel);
        
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
    }
    
    @Override
    public void start() {
        AudioPlayManager.getInstance().start();
    }
    
    @Override
    public void pause() {
        AudioPlayManager.getInstance().pause();
    }
    
    @Override
    public void previous() {
        AudioPlayManager.getInstance().previous();
    }
    
    @Override
    public void next() {
        AudioPlayManager.getInstance().next();
    }
    
    @Override
    public void switchVoice() {
        AudioPlayManager.getInstance().switchVoice();
    }
    
    @Override
    public void stop() {
        AudioPlayManager.getInstance().stop();
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
    public void customView(DesignatedAudioPlayerFloatingView viewGroup) {
        designatedFloatingView = viewGroup;
    }
    
    @Override
    public void customView(@LayoutRes int resource) {
        layoutId = resource;
    }
    
    @Override
    public void layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedFloatingView != null) {
            designatedFloatingView.setLayoutParams(params);
        }
    }
    
    @Override
    public void listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener) {
        if (designatedFloatingView != null) {
            designatedFloatingView.setDesignatedAudioPlayerFloatingViewListener(designatedAudioPlayerFloatingViewListener);
        }
    }
    
    @Override
    public void attach(Activity activity) {
        attach(getActivityRoot(activity));
    }
    
    @Override
    public void attach(FrameLayout container) {
        if (container == null || designatedFloatingView == null) {
            containerView = new WeakReference<>(container);
            return;
        }
        if (designatedFloatingView.getParent() == container) {
            return;
        }
        if (designatedFloatingView.getParent() != null) {
            ((ViewGroup) designatedFloatingView.getParent()).removeView(designatedFloatingView);
        }
        containerView = new WeakReference<>(container);
        container.addView(designatedFloatingView);
    }
    
    @Override
    public void detach(Activity activity) {
        detach(getActivityRoot(activity));
    }
    
    @Override
    public void detach(FrameLayout container) {
        if (designatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(designatedFloatingView)) {
            container.removeView(designatedFloatingView);
        }
        if (getContainerView() == container) {
            containerView = null;
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(0, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 0);
    }
    
    private void ensureFloatingView() {
        synchronized (this) {
            if (designatedFloatingView != null) {
                return;
            }
            designatedFloatingView = new DesignatedAudioPlayerFloatingView(containerContext, layoutId);
            designatedFloatingView.setLayoutParams(getDesignatedLayoutParams());
            addViewToWindow(designatedFloatingView);
            designatedFloatingView.showFloatingWindow();
        }
    }
    
    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedFloatingView != null) {
                    try {
                        if (ViewCompat.isAttachedToWindow(designatedFloatingView) && getContainerView() != null) {
                            getContainerView().removeView(designatedFloatingView);
                        }
                    } catch (Exception e) {
                    }
                    designatedFloatingView = null;
                }
            }
        });
    }
    
    private void addViewToWindow(final View view) {
        if (getContainerView() == null) {
            return;
        }
        try {
            getContainerView().addView(view);
        } catch (Exception e) {
        }
    }
    
    private FrameLayout getContainerView() {
        if (containerView == null) {
            return null;
        }
        return containerView.get();
    }
    
    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
    }
    
    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ApiAudioPlayerFloatingView setApiAudioPlayerFloatingListener(ApiAudioPlayerFloatingListener apiAudioPlayerFloatingListener) {
        this.apiAudioPlayerFloatingListener = apiAudioPlayerFloatingListener;
        return this;
    }
}
