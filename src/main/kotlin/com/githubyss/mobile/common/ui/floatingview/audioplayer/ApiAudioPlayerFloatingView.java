package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.githubyss.mobile.common.kit.indicator.ToastIndicator;
import com.githubyss.mobile.common.kit.resource.ComkitResUtils;
import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.enumeration.AudioState;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayListener;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.core.view.ViewCompat;


/**
 * ApiFloatingAudioPlayerView
 * <Description> 悬浮窗管理器
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:32:57
 */
public class ApiAudioPlayerFloatingView implements ApiAudioPlayerFloatingViewInterface {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private static volatile ApiAudioPlayerFloatingView instance;

    private Context containerContext;
    private WeakReference<FrameLayout> containerView;
    private DesignatedAudioPlayerFloatingView designatedFloatingView;
    private ViewGroup.LayoutParams designatedLayoutParams;
    @LayoutRes
    private int layoutId = R.layout.comui_floating_audio_player_view;
    private ApiAudioPlayerFloatingWindowListener apiAudioPlayerFloatingWindowListener;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    private ApiAudioPlayerFloatingView(Context context) {
        containerContext = context;
        initLayoutParams();
    }

    public static ApiAudioPlayerFloatingView getInstance(Context context) {
        if (instance == null) {
            synchronized (ApiAudioPlayerFloatingView.class) {
                if (instance == null) {
                    instance = new ApiAudioPlayerFloatingView(context);
                }
            }
        }

        return instance;
    }


    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------

    @Override
    public ApiAudioPlayerFloatingView show() {
        ensureFloatingView();
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView close() {
        AudioPlayManager.getInstance().destroy();
        if (designatedFloatingView != null) {
            designatedFloatingView.closeFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView lengthen() {
        if (designatedFloatingView != null) {
            designatedFloatingView.lengthenFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView shorten() {
        if (designatedFloatingView != null) {
            designatedFloatingView.shortenFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView attach(FrameLayout container) {
        if (container == null || designatedFloatingView == null) {
            containerView = new WeakReference<>(container);
            return this;
        }
        if (designatedFloatingView.getParent() == container) {
            return this;
        }
        if (designatedFloatingView.getParent() != null) {
            ((ViewGroup) designatedFloatingView.getParent()).removeView(designatedFloatingView);
        }
        containerView = new WeakReference<>(container);
        container.addView(designatedFloatingView);
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView detach(FrameLayout container) {
        if (designatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(designatedFloatingView)) {
            container.removeView(designatedFloatingView);
        }
        if (getContainerView() == container) {
            containerView = null;
        }
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView initData(List<AudioModel> audioList) {
        if (designatedFloatingView != null) {
            if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {

                // Fake data
                // List<AudioModel> audioList1 = new ArrayList<>();
                // audioList1.add(new AudioModel("0", "ReviewMyKisses", "Amiena", "", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", AudioState.STOP));
                AudioListModel audioListModel = new AudioListModel();
                audioListModel.setAudioList(audioList);
                audioListModel.setCurrentIndex(0);

                if (audioListModel != null) {
                    AudioPlayManager.getInstance().setInfo(audioListModel);
                    AudioPlayManager.getInstance().play(containerContext);
                    designatedFloatingView.refreshVoiceSwitch();
                } else {
                    ToastIndicator.INSTANCE.showMessage(containerContext, ComkitResUtils.INSTANCE.getString(containerContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
                }
            } else {
                designatedFloatingView.refreshData();
            }

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
    public BaseAutoShortedFloatingView getAutoShortedView() {
        return designatedFloatingView;
    }

    @Override
    public ApiAudioPlayerFloatingView customView(DesignatedAudioPlayerFloatingView viewGroup) {
        designatedFloatingView = viewGroup;
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView customView(@LayoutRes int resource) {
        layoutId = resource;
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedFloatingView != null) {
            designatedFloatingView.setLayoutParams(params);
        }
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingView listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener) {
        if (designatedFloatingView != null) {
            designatedFloatingView.setDesignatedAudioPlayerFloatingViewListener(designatedAudioPlayerFloatingViewListener);
        }
        return this;
    }


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(0, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 0);
    }

    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
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

            listener(new DesignatedAudioPlayerFloatingViewListener() {
                @Override
                public void onClose() {
                    removeFloatingView();
                }

                @Override
                public void onUpdateAudioInfo(AudioModel audioModel) {
                    apiAudioPlayerFloatingWindowListener.onUpdateAudioInfo(audioModel);
                }
            });
        }
    }

    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(designatedFloatingView) && getContainerView() != null) {
                    getContainerView().removeView(designatedFloatingView);
                }
                designatedFloatingView = null;
            }
        });
    }

    private void addViewToWindow(final View view) {
        if (getContainerView() == null) {
            return;
        }
        getContainerView().addView(view);
    }

    private FrameLayout getContainerView() {
        if (containerView == null) {
            return null;
        }
        return containerView.get();
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

    public ApiAudioPlayerFloatingView setContainerContext(Context containerContext) {
        this.containerContext = containerContext;
        return this;
    }

    public void setApiAudioPlayerFloatingWindowListener(ApiAudioPlayerFloatingWindowListener apiAudioPlayerFloatingWindowListener) {
        this.apiAudioPlayerFloatingWindowListener = apiAudioPlayerFloatingWindowListener;
    }
}
