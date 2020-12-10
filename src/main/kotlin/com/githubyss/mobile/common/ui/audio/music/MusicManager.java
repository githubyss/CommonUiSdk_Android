package com.githubyss.mobile.common.ui.audio.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import com.githubyss.mobile.common.ui.audio.model.AudioInfo;
import com.githubyss.mobile.common.ui.audio.model.MusicModel;
import com.githubyss.mobile.common.ui.audio.util.MusicInterface;

import java.util.List;

import static com.githubyss.mobile.common.ui.audio.music.AudioState.*;


/**
 * 88396251
 * 2018-5-22
 * 音乐播放器
 */

public class MusicManager {
    private static final String TAG = "MusicManager";
    public AudioState audioState;
    private MediaPlayer mediaPlayer;
    private AudioInfo musicInfo;
    private MusicInterface musicInterface;
    private static final int WHAT_REFRESH = 0x268;
    private static final int MAX_PROGRESS = 100;
    public int MaxProgress = MAX_PROGRESS;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener myFocusListener;
    //  private AudioFocusRequest mFocusRequest;//8.0新方法
    private int mOriginalVol;//音量
    private int mUpdateProgress = 0;//缓存进度
    private boolean isHandStop = false;//是否是手动停止
    public boolean loop = false;//是否循环播放
    private boolean playFromStart = false;//是否重新开始
    public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;

    private MusicManager() {
        audioState = STOP;
    }

    private static volatile MusicManager instance = null;

    public static MusicManager getInstance() {
        if (instance == null) {
            synchronized (MusicManager.class) {
                if (instance == null) {
                    instance = new MusicManager();
                }
            }
        }
        return instance;
    }

    private boolean audioInit() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    MaxProgress = mediaPlayer.getDuration();
                    setAudioState(READY);
                    startSelf();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    next();
                }
            });
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    if (musicInterface != null) {
                        mUpdateProgress = MaxProgress * percent / 100;
                        musicInterface.onBufferingUpdateProgress(mUpdateProgress);
                    }
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    switch (extra) {
                        case -1003://初始化的时候连接异常。错误码未公开
                            stop();
                            playFromStart = true;
                            break;
                    }

                    // LogUtils.e(TAG, "OnError what=" + what + " extra=" + extra);
                    return true;//返回false会继续回调onCompletion，true不会。
                }
            });
            return true;
        }
        return false;
    }

    /**
     * 获取焦点
     * 屏蔽8.0方法，升级后打开
     */
    private void requestFocus() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (mFocusRequest != null && mAudioManager != null) {
//                AudioFocusChange(mAudioManager.requestAudioFocus(mFocusRequest), false);
//            }
//        } else {
        if (myFocusListener != null && mAudioManager != null) {
            AudioFocusChange(mAudioManager.requestAudioFocus(myFocusListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN), false);
        }
        //       }
    }

    /**
     * 释放焦点
     * 屏蔽8.0方法，升级后打开
     */
    private void abandonFocus() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            if (mFocusRequest != null && mAudioManager != null) {
//                mAudioManager.abandonAudioFocusRequest(mFocusRequest);
//            }
//        } else {
        if (myFocusListener != null && mAudioManager != null) {
            mAudioManager.abandonAudioFocus(myFocusListener);
        }
//        }
    }

    /**
     * 初始化焦点获取
     */
    private void audioFocusInit(Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        myFocusListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                AudioFocusChange(focusChange, true);
            }
        };
        //请求焦点
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            AudioAttributes mPlaybackAttributes = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_GAME)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .build();
//            mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
//                    .setAudioAttributes(mPlaybackAttributes)
//                    .setAcceptsDelayedFocusGain(true)
//                    .setOnAudioFocusChangeListener(myFocusListener, handler)
//                    .build();
//        }
        requestFocus();
    }

    /**
     * 音频焦点监听返回
     */
    public void AudioFocusChange(int focusChange, boolean asynchronous) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT://暂时失去Audio Focus，并会很快再次获得。
                // LogUtils.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                if (pause()) {
                    isHandStop = false;
                }
                break;
            case AudioManager.AUDIOFOCUS_GAIN://获得了Audio Focus,重新获取焦点的时候回调
                //AudioManager.AUDIOFOCUS_REQUEST_GRANTED://申请成功，处理相同
                // LogUtils.e(TAG, "AUDIOFOCUS_GAIN");
                //手动暂停或停止的，不需要重启。
                if (asynchronous && isHandStop && (audioState == PAUSE || audioState == STOP)) {
                    return;
                }
                startSelf();
                if (mOriginalVol != 0 && mAudioManager != null) {
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mOriginalVol, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                    mOriginalVol = 0;
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS://失去了Audio Focus，并将会持续很长的时间。
                // LogUtils.e(TAG, "AUDIOFOCUS_LOSS");
                if (pause()) {//如果本来就在暂停状态，说明本身已经停止，所以还是手动停止的
                    isHandStop = false;
                }
                break;
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK://暂时失去AudioFocus，但是可以继续播放，不过要在降低音量
                // LogUtils.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                if (mAudioManager != null) {
                    mOriginalVol = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mOriginalVol / 2, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                }
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED://申请失败
                // LogUtils.e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                // if (EPApp.mContext != null) {
                //     ToastUtil.showMessage(EPApp.mContext, "其他应用正在占用音频资源");
                // }
                break;
            case AUDIOFOCUS_REQUEST_DELAYED:
                // LogUtils.e(TAG, "AUDIOFOCUS_REQUEST_DELAYED");
                //申请未获取到焦点，但是setAcceptsDelayedFocusGain为true,所以没有返回申请失败，只是在等待，8.0特性
                // if (EPApp.mContext != null) {
                //     ToastUtil.showMessage(EPApp.mContext, "其他应用正在占用音频资源，请稍等");
                // }
                break;
            default:
                break;
        }
    }

    /**
     * 外部调用，第一次开始播放
     */
    public void play(Context context) {
        //CookieHandlerUtil.getInstance().syncCookie();
        playFromStart = true;
        audioFocusInit(context);
    }

    /**
     * 当前歌曲重新开始播放
     */
    private void startFromBegin() {
        if (audioState != PREPARE) {
            audioPrepare();
        }
    }

    /**
     * 上一首歌
     */
    public int previous() {
        if (!loop && musicInfo != null && musicInfo.currentIndex <= 0) {
            return -1;
        }
        if (mediaPlayer == null) {
            startFromBegin();
            return 1;
        }
        if (audioState == PLAYING) {
            stop();
        }
        musicInfo.currentIndex--;
        if (musicInfo.currentIndex < 0) {
            musicInfo.currentIndex = musicInfo.getAudioInfoList().size() - 1;
        }
        audioPrepare();
        return 0;
    }

    /**
     * 暂停
     */
    public boolean pause() {
        if (musicInfo == null) {
            destory();
            return false;
        }
        if (audioState == PLAYING && mediaPlayer != null) {
            isHandStop = true;
            mediaPlayer.pause();
            handler.removeMessages(WHAT_REFRESH);
            setAudioState(PAUSE);
            return true;
        }
        return false;
    }

    /**
     * 开始播放，当暂停或准备好后才行
     */
    public void start() {
        if (musicInfo == null) {
            destory();
            return;
        }
        requestFocus();
    }

    private void startSelf() {
        if (musicInfo == null) {
            destory();
            return;
        }
        if (mediaPlayer == null || playFromStart) {
            startFromBegin();
            playFromStart = false;
            return;
        }
        if (audioState == PAUSE || audioState == READY) {
            mediaPlayer.start();
            handler.sendEmptyMessage(WHAT_REFRESH);
            setAudioState(PLAYING);
        }
    }

    /**
     * 下一首歌
     */
    public void next() {
        if (!loop && musicInfo != null && musicInfo.currentIndex >= musicInfo.getAudioInfoList().size() - 1) {
            return;
        }
        if (mediaPlayer == null) {
            startFromBegin();
            return;
        }
        if (audioState == PLAYING) {
            stop();
        }
        musicInfo.currentIndex++;
        if (musicInfo.currentIndex >= musicInfo.getAudioInfoList().size()) {
            musicInfo.currentIndex = 0;
        }
        audioPrepare();
    }

    /**
     * 停止
     * 注意停止后得重新Prepare，无法直接start
     */
    public boolean stop() {
        if (audioState != STOP && mediaPlayer != null) {
            isHandStop = true;
            mediaPlayer.stop();
            handler.removeMessages(WHAT_REFRESH);
            setAudioState(STOP);
            return true;
        }
        return false;
    }

    /**
     * 每一首歌开始前的准备工作
     */
    private void audioPrepare() {
        if (musicInfo == null || musicInfo.getAudioInfoList().size() == 0) {
            return;
        }
        if (!audioInit()) {
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(musicInfo.getAudioInfoList().get(musicInfo.currentIndex).getUrl());
        } catch (Exception e) {
            // LogUtils.e(TAG, e.getStackTrace().toString() + "");
            return;
        }

        setAudioState(START);
        mediaPlayer.prepareAsync();
        setAudioState(PREPARE);
    }

    /**
     * 当前状态
     */
    private void setAudioState(AudioState audioState) {
        if (this.audioState == audioState) {
            return;
        }
        this.audioState = audioState;
        if (musicInterface != null) {
            musicInterface.onStateChanged(audioState);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_REFRESH:
                    handler.sendEmptyMessageDelayed(WHAT_REFRESH, 200);
                    if (mediaPlayer != null && mediaPlayer.isPlaying() && musicInterface != null) {
                        musicInterface.onPlayProgress(mediaPlayer.getCurrentPosition());
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void setInfo(AudioInfo info) {
        stop();
        this.musicInfo = info;
    }

    public List<MusicModel> getPlayList() {
        if (musicInfo == null) {
            return null;
        }
        return musicInfo.getAudioInfoList();
    }

    public int getPosition() {
        if (musicInfo == null) {
            return 0;
        }
        return musicInfo.currentIndex;
    }

    public void setMusicListener(MusicInterface musicInterface) {
        this.musicInterface = musicInterface;
    }

    public void seekTo(int progress) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(progress);
        }
    }

    public String getDurationTime() {
        // if (mediaPlayer != null) {
        //     return ProgressTextUtils.getProgressText(mediaPlayer.getDuration());
        // }
        return "";
    }

    public String getCurrentTime() {
        // if (mediaPlayer != null) {
        //     return ProgressTextUtils.getProgressText(mediaPlayer.getCurrentPosition());
        // }
        return "";
    }

    public int getCurrentPosition() {
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public int getUpdateProgress() {
        return mUpdateProgress;
    }

    private void end() {
        if (mediaPlayer != null) {
            abandonFocus();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            musicInfo = null;
            setAudioState(AudioState.END);
        }
    }

    public void destory() {
        end();
        MaxProgress = MAX_PROGRESS;
        mAudioManager = null;
        myFocusListener = null;
        mUpdateProgress = 0;
        mOriginalVol = 0;
        isHandStop = false;
        loop = false;
        playFromStart = false;
        if (handler != null) {
            handler.removeMessages(WHAT_REFRESH);
        }
    }
}
