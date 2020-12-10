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

import com.githubyss.mobile.common.kit.hint.ComkitToastUtils;
import com.githubyss.mobile.common.kit.resource.ComkitResUtils;
import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.music.AudioState;
import com.githubyss.mobile.common.ui.audio.music.MusicManager;
import com.githubyss.mobile.common.ui.audio.util.MusicInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
public class ApiFloatingAudioPlayerView implements ApiFloatingAudioPlayerViewInterface {

    private static volatile ApiFloatingAudioPlayerView mInstance;
    private Context mContext;
    private WeakReference<FrameLayout> mContainer;
    private DesignatedFloatingAudioPlayerView mDesignatedFloatingView;
    private ViewGroup.LayoutParams mLayoutParams = getLayoutParams();
    @LayoutRes
    private int mLayoutId = R.layout.comui_floating_audio_player_view;

    private ApiFloatingAudioPlayerView(Context context) {
        mContext = context;
    }

    public static ApiFloatingAudioPlayerView getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ApiFloatingAudioPlayerView.class) {
                if (mInstance == null) {
                    mInstance = new ApiFloatingAudioPlayerView(context);
                }
            }
        }

        return mInstance;
    }

    @Override
    public ApiFloatingAudioPlayerView show() {
        ensureFloatingView();
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView close() {
        MusicManager.getInstance().destory();
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.closeFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView lengthen() {
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.lengthenFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView shorten() {
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.shortenFloatingWindow();
        }
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView attach(FrameLayout container) {
        if (container == null || mDesignatedFloatingView == null) {
            mContainer = new WeakReference<>(container);
            return this;
        }
        if (mDesignatedFloatingView.getParent() == container) {
            return this;
        }
        if (mDesignatedFloatingView.getParent() != null) {
            ((ViewGroup) mDesignatedFloatingView.getParent()).removeView(mDesignatedFloatingView);
        }
        mContainer = new WeakReference<>(container);
        container.addView(mDesignatedFloatingView);
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView detach(FrameLayout container) {
        if (mDesignatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(mDesignatedFloatingView)) {
            container.removeView(mDesignatedFloatingView);
        }
        if (getContainer() == container) {
            mContainer = null;
        }
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView initData(List<AudioModel> audioList) {
        if (mDesignatedFloatingView != null) {
            if (MusicManager.getInstance().getAudioList() == null || MusicManager.getInstance().getAudioList().size() == 0) {

                // Fake data
                // List<AudioModel> audioList1 = new ArrayList<>();
                // audioList1.add(new AudioModel("0", "ReviewMyKisses", "Amiena", "", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", AudioState.STOP));
                AudioListModel audioListModel = new AudioListModel();
                audioListModel.setAudioList(audioList);
                audioListModel.setCurrentIndex(1);

                if (audioListModel != null) {
                    MusicManager.getInstance().setInfo(audioListModel);
                    MusicManager.getInstance().play(mContext);
                } else {
                    ComkitToastUtils.INSTANCE.showMessage(mContext, ComkitResUtils.INSTANCE.getString(mContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
                }
            } else {
                mDesignatedFloatingView.initData();
            }

            mDesignatedFloatingView.setMusicInterface(new MusicInterface() {
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
    public BaseFloatingAutoShortedView getAutoShortedView() {
        return mDesignatedFloatingView;
    }

    @Override
    public ApiFloatingAudioPlayerView customView(DesignatedFloatingAudioPlayerView viewGroup) {
        mDesignatedFloatingView = viewGroup;
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView customView(@LayoutRes int resource) {
        mLayoutId = resource;
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView layoutParams(ViewGroup.LayoutParams params) {
        mLayoutParams = params;
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.setLayoutParams(params);
        }
        return this;
    }

    @Override
    public ApiFloatingAudioPlayerView listener(DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener) {
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.setDesignatedFloatingAudioPlayerViewListener(designatedFloatingAudioPlayerViewListener);
        }
        return this;
    }


    private void ensureFloatingView() {
        synchronized (this) {
            if (mDesignatedFloatingView != null) {
                return;
            }
            mDesignatedFloatingView = new DesignatedFloatingAudioPlayerView(mContext, mLayoutId);
            mDesignatedFloatingView.setLayoutParams(mLayoutParams);
            addViewToWindow(mDesignatedFloatingView);
            mDesignatedFloatingView.showFloatingWindow();

            listener(new DesignatedFloatingAudioPlayerViewListener() {
                @Override
                public void onClose() {
                    removeFloatingView();
                }
            });
        }
    }

    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mDesignatedFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(mDesignatedFloatingView) && getContainer() != null) {
                    getContainer().removeView(mDesignatedFloatingView);
                }
                mDesignatedFloatingView = null;
            }
        });
    }

    private void addViewToWindow(final View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    private FrameLayout getContainer() {
        if (mContainer == null) {
            return null;
        }
        return mContainer.get();
    }

    private FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
        layoutParams.setMargins(0, layoutParams.topMargin, layoutParams.rightMargin, 0);
        return layoutParams;
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
}
