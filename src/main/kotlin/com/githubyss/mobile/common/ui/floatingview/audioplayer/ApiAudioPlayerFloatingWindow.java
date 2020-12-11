package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.content.Context;
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
import android.widget.Toast;

import com.githubyss.mobile.common.kit.hint.ComkitToastUtils;
import com.githubyss.mobile.common.kit.resource.ComkitResUtils;
import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.music.AudioState;
import com.githubyss.mobile.common.ui.audio.music.MusicManager;
import com.githubyss.mobile.common.ui.audio.util.MusicInterface;
import com.githubyss.mobile.common.ui.constant.Constant;
import com.githubyss.mobile.common.ui.utils.PermissionFloatUtils;

import java.util.List;

import androidx.annotation.LayoutRes;


/**
 * AudioPlayerFloatingWindow
 * <功能> 音频播放器悬浮窗
 * <详细>
 *
 * @author yanss 16072015
 * @createdTime 2017/04/05 15:25:20
 */
public class ApiAudioPlayerFloatingWindow implements ApiAudioPlayerFloatingWindowInterface {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private static ApiAudioPlayerFloatingWindow INSTANCE;

    private Context containerContext;
    private ViewGroup containerView;
    private DesignatedAudioPlayerFloatingView designatedFloatingView;
    private ViewGroup.LayoutParams designatedLayoutParams;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowLayoutParams;
    @LayoutRes
    private int layoutId = R.layout.comui_floating_audio_player_view;

    private ViewGroup rootView;

    //8.0 type样式，不可修改，为适应低版本编译，自己定义
    public static final int TYPE_APPLICATION_OVERLAY = 2038;

    //小米8.0,8.1的坑，正常规则走不通，只能走系统漏洞。
    public static final int TYPE_PRESENTATION = 2037;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public ApiAudioPlayerFloatingWindow(Context context) {
        containerContext = context;
        initLayoutParams();
    }

    public static ApiAudioPlayerFloatingWindow getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ApiAudioPlayerFloatingWindow.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiAudioPlayerFloatingWindow(context);
                }
            }
        }

        return INSTANCE;
    }


    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------

    @Override
    public ApiAudioPlayerFloatingWindow show() {
        ensureFloatingView();
        return this;
    }

    @Override
    public ApiAudioPlayerFloatingWindow close() {
        MusicManager.getInstance().destory();
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
    public ApiAudioPlayerFloatingWindow initData(List<AudioModel> audioList) {
        if (designatedFloatingView != null) {
            if (MusicManager.getInstance().getAudioList() == null || MusicManager.getInstance().getAudioList().size() == 0) {

                // Fake data
                // List<AudioModel> audioList1 = new ArrayList<>();
                // audioList1.add(new AudioModel("0", "ReviewMyKisses", "Amiena", "", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3", AudioState.STOP));
                AudioListModel audioListModel = new AudioListModel();
                audioListModel.setAudioList(audioList);
                audioListModel.setCurrentIndex(0);

                if (audioListModel != null) {
                    MusicManager.getInstance().setInfo(audioListModel);
                    MusicManager.getInstance().play(containerContext);
                } else {
                    ComkitToastUtils.INSTANCE.showMessage(containerContext, ComkitResUtils.INSTANCE.getString(containerContext, R.string.music_play_no_list), Toast.LENGTH_SHORT, false);
                }
            } else {
                designatedFloatingView.initData();
            }

            designatedFloatingView.setMusicInterface(new MusicInterface() {
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

    private void initLayoutParams() {
        if (Build.VERSION.SDK_INT >= Constant.VERSION_CODES_O) {
            boolean MiUiO = (Build.VERSION.SDK_INT == Constant.VERSION_CODES_O || Build.VERSION.SDK_INT == Constant.VERSION_CODES_O_MR1) && PermissionFloatUtils.isMiui();
            if (MiUiO) {
                getWindowLayoutParams().type = TYPE_PRESENTATION;
            } else {
                getWindowLayoutParams().type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }
        } else {
            getWindowLayoutParams().type = WindowManager.LayoutParams.TYPE_PHONE;
        }

        // if (Build.VERSION.SDK_INT >= 26) {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        // } else {
        //     layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        // }

        getWindowLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        getWindowLayoutParams().gravity = Gravity.LEFT | Gravity.BOTTOM;
        getWindowLayoutParams().format = PixelFormat.TRANSLUCENT;
        getWindowLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindowLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindowLayoutParams().x = 0;
        getWindowLayoutParams().y = 0;
        // getLayoutParams().windowAnimations = android.R.style.Animation_Translucent;
    }

    private WindowManager.LayoutParams getWindowLayoutParams() {
        if (windowLayoutParams == null) {
            windowLayoutParams = new WindowManager.LayoutParams();
        }
        return (WindowManager.LayoutParams) windowLayoutParams;
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
            });
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
                if (designatedFloatingView == null) {
                    return;
                }
                try {
                    if (getContainerView().getChildCount() > 0) {
                        getContainerView().removeView(designatedFloatingView);
                    }
                    getWindowManager().removeView(getContainerView());
                } catch (Exception e) {
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
        try {
            getWindowManager().addView(getContainerView(), getWindowLayoutParams());
        } catch (WindowManager.BadTokenException e) {
        } catch (Exception e) {
        }
    }

    private FrameLayout getContainerView() {
        if (containerView == null) {
            containerView = new FrameLayout(containerContext);
        }
        return (FrameLayout) containerView;
    }

    private WindowManager getWindowManager() {
        if (windowManager == null && containerContext != null) {
            windowManager = (WindowManager) containerContext.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }
}
