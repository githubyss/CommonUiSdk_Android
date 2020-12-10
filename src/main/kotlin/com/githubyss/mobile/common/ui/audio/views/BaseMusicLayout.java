package com.githubyss.mobile.common.ui.audio.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.githubyss.mobile.common.ui.audio.music.AudioState;
import com.githubyss.mobile.common.ui.audio.music.MusicManager;
import com.githubyss.mobile.common.ui.audio.util.MusicInterface;

// import android.support.annotation.LayoutRes;
// import com.suning.mobile.epa.audio.music.AudioState;
// import com.suning.mobile.epa.audio.music.MusicManager;
// import com.suning.mobile.epa.audio.util.MusicInterface;


/**
 * 88396251
 * 2018-5-22
 * 音乐播放基类界面
 */

public abstract class BaseMusicLayout extends LinearLayout implements View.OnClickListener {
    private MusicInterface mMusicInterface;
    protected Context mContext;

    public BaseMusicLayout(Context context) {
        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(getContentViewId(), this);
        initView();
        onMusicListener();
    }

    public BaseMusicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(getContentViewId(), this);
        initView();
        onMusicListener();
    }

    /**
     * 设置容器id
     */
    // @LayoutRes
    protected abstract int getContentViewId();

    /**
     * 初始化View的显示
     */
    protected abstract void initView();

    /**
     * 当前进度
     */
    protected void PlayProgress(int CurrentPosition) {
    }

    /**
     * 缓存进度
     */
    public void BufferingUpdateProgress(int percent) {
    }

    /**
     * 当前播放状态
     */
    protected abstract void StateChanged(AudioState audioState);

    private void onMusicListener() {
        MusicManager.getInstance().setMusicListener(new MusicInterface() {
            @Override
            public void onStateChanged(AudioState audioState) {
                StateChanged(audioState);
                if (mMusicInterface != null) {
                    mMusicInterface.onStateChanged(audioState);
                }
            }

            @Override
            public void onPlayProgress(int CurrentPosition) {
                PlayProgress(CurrentPosition);
                if (mMusicInterface != null) {
                    mMusicInterface.onPlayProgress(CurrentPosition);
                }
            }

            @Override
            public void onBufferingUpdateProgress(int percent) {
                BufferingUpdateProgress(percent);
                if (mMusicInterface != null) {
                    mMusicInterface.onBufferingUpdateProgress(percent);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    /**
     * 回调传递到下一级
     */
    public void setMusicInterface(MusicInterface musicInterface) {
        mMusicInterface = musicInterface;
    }
}
