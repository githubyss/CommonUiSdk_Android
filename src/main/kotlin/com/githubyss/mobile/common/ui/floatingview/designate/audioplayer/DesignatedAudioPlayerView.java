package com.githubyss.mobile.common.ui.floatingview.designate.audioplayer;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.enumeration.AudioState;
import com.githubyss.mobile.common.ui.audio.model.AudioListModel;
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayListener;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.FeatureAutoShortenView;
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.FeatureAutoShortenViewToDesignateViewListener;
import com.githubyss.mobile.common.ui.utils.ProgressTextUtils;

import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.PLAYING;
import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.READY;
import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.START;


/**
 * DesignatedAudioPlayerView
 * <Description> 特定的音频播放器悬浮窗
 * <Details> 针对特此的布局，实现布局元素的显示和操作响应。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:10:13
 */
public class DesignatedAudioPlayerView extends FeatureAutoShortenView implements DesignatedAudioPlayerViewInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private TextView  textView_title;
    private TextView  textView_timePosition;
    private TextView  textView_timeDuration;
    private SeekBar   seekBar_audioPlayer;
    private ImageView imageView_playPauseController;
    private ImageView imageView_voiceSwitch;
    private ImageView imageView_close;
    private View      frameLayout_normalPlayerContainer;
    private View      frameLayout_miniPlayerContainer;
    
    @LayoutRes
    private static int designatedLayoutId = R.layout.comui_floating_audio_player;
    @DrawableRes
    private        int iconId             = R.drawable.comui_lucky_money;
    
    private DesignatedAudioPlayerView designatedView;
    private Drawable                  iconDrawable;
    private Bitmap                    iconBitmap;
    
    /** 原生 Listener */
    protected DesignatedAudioPlayerViewListener forNativeDesignatedAudioPlayerViewListener;
    /** Web 端 Listener */
    protected DesignatedAudioPlayerViewListener forWebDesignatedAudioPlayerViewListener;
    
    private AudioPlayListener audioPlayListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public DesignatedAudioPlayerView(@NonNull Context context) {
        this(context, designatedLayoutId);
    }
    
    public DesignatedAudioPlayerView(@NonNull Context context, @LayoutRes int resource) {
        super(context);
        if (rootView == null) {
            rootView = inflate(context, resource, this);
            designatedView = this;
            initInBase();
            initInDesignated();
        }
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public void lengthen() {
        if (designatedView != null) {
            designatedView.lengthenFloatingWindow();
        }
    }
    
    @Override
    public void shorten() {
        if (designatedView != null) {
            designatedView.shortenFloatingWindow();
        }
    }
    
    @Override
    public void play(List<AudioModel> audioList) {
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
        
        if (designatedView != null) {
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
            
            designatedView.refreshVoiceSwitch();
            
            designatedView.setAudioPlayListener(new AudioPlayListener() {
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
        if (designatedView != null) {
            return designatedView.isShown();
        }
        return false;
    }
    
    @Override
    public void customIcon(Drawable drawable) {
        iconDrawable = drawable;
        if (drawable != null) {
        }
    }
    
    @Override
    public void customIcon(Bitmap bitmap) {
        iconBitmap = bitmap;
        if (bitmap != null) {
        }
    }
    
    @Override
    public void customIcon(int drawableId) {
        iconId = drawableId;
    }
    
    @Override
    public void customView(DesignatedAudioPlayerView viewGroup) {
        designatedView = viewGroup;
    }
    
    @Override
    public void customView(int layoutId) {
        designatedLayoutId = layoutId;
        
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initInDesignated() {
        initViewFindById(rootView);
        initListener();
    }
    
    private void initViewFindById(View view) {
        textView_title = view.findViewById(R.id.textView_title);
        textView_timePosition = view.findViewById(R.id.textView_timePosition);
        textView_timeDuration = view.findViewById(R.id.textView_timeDuration);
        seekBar_audioPlayer = view.findViewById(R.id.seekBar_audioPlayer);
        imageView_playPauseController = view.findViewById(R.id.imageView_playPauseController);
        imageView_voiceSwitch = view.findViewById(R.id.imageView_voiceSwitch);
        imageView_close = view.findViewById(R.id.imageView_close);
        frameLayout_normalPlayerContainer = view.findViewById(R.id.frameLayout_normalPlayerContainer);
        frameLayout_miniPlayerContainer = view.findViewById(R.id.frameLayout_miniPlayerContainer);
    }
    
    private void initListener() {
        imageView_playPauseController.setOnClickListener(onClickListener);
        imageView_voiceSwitch.setOnClickListener(onClickListener);
        imageView_close.setOnClickListener(onClickListener);
        frameLayout_miniPlayerContainer.setOnClickListener(onClickListener);
        
        seekBar_audioPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // LogcatUtils.INSTANCE.d("seekBar_audioPlayer onProgressChanged", "progress: " + progress);
                textView_timePosition.setText(ProgressTextUtils.getProgressText(progress));
            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                AudioPlayManager.getInstance().seekTo(seekBar.getProgress());
            }
        });
        
        designatedView.setFeatureAutoShortenViewToDesignateViewListener(new FeatureAutoShortenViewToDesignateViewListener() {
            @Override
            public void onLengthen() {
                refreshContainerVisibility(false);
            }
            
            @Override
            public void onShorten() {
                refreshContainerVisibility(true);
            }
        });
        
        AudioPlayManager.getInstance().setMusicListener(new AudioPlayListener() {
            @Override
            public void onStateChanged(AudioState audioState) {
                stateChanged(audioState);
                if (audioPlayListener != null) {
                    audioPlayListener.onStateChanged(audioState);
                }
            }
            
            @Override
            public void onPlayProgress(int currentPosition) {
                playProgress(currentPosition);
                if (audioPlayListener != null) {
                    audioPlayListener.onPlayProgress(currentPosition);
                }
            }
            
            @Override
            public void onBufferingUpdateProgress(int percent) {
                bufferingUpdateProgress(percent);
                if (audioPlayListener != null) {
                    audioPlayListener.onBufferingUpdateProgress(percent);
                }
            }
        });
    }
    
    private void refreshContainerVisibility(boolean isMini) {
        if (isMini) {
            frameLayout_normalPlayerContainer.setVisibility(View.GONE);
            frameLayout_miniPlayerContainer.setVisibility(View.VISIBLE);
        } else {
            frameLayout_normalPlayerContainer.setVisibility(View.VISIBLE);
            frameLayout_miniPlayerContainer.setVisibility(View.GONE);
        }
    }
    
    /**
     * 当前已经在播放
     * 重新进入初始化当前数据
     */
    private void refreshData() {
        if (seekBar_audioPlayer != null) {
            stateChanged(START);
            // 先把一些漏掉的东西初始化下
            stateChanged(READY);
            stateChanged(AudioPlayManager.getInstance().getAudioState());
            seekBar_audioPlayer.setProgress(AudioPlayManager.getInstance().getCurrentPosition());
            seekBar_audioPlayer.setSecondaryProgress(AudioPlayManager.getInstance().getUpdateProgress());
        }
    }
    
    private void refreshVoiceSwitch() {
        if (AudioPlayManager.getInstance().getCurrentAudio() == null) {
            return;
        }
        
        switch (AudioPlayManager.getInstance().getCurrentAudio().getVoiceType()) {
            case MALE:
                imageView_voiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_male);
                break;
            case FEMALE:
                imageView_voiceSwitch.setImageResource(R.drawable.comui_audio_player_voice_female);
                break;
            default:
                break;
        }
    }
    
    private void playProgress(int CurrentPosition) {
        if (!seekBar_audioPlayer.isPressed()) {
            seekBar_audioPlayer.setProgress(CurrentPosition);
        }
    }
    
    private void bufferingUpdateProgress(int percent) {
        seekBar_audioPlayer.setSecondaryProgress(percent);
    }
    
    private void stateChanged(AudioState audioState) {
        switch (audioState) {
            case START:
                if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {
                    break;
                }
                textView_title.setText(AudioPlayManager.getInstance().getAudioList().get(AudioPlayManager.getInstance().getCurrentIndex()).getTitle());
                // mMusicPlayAuthorTv.setText(mContext.getString(R.string.music_play_author) + MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
                // mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_click_icon);
                // mMusicNextIv.setImageResource(R.drawable.music_play_next_click_icon);
                // mMusicPreviousIv.setEnabled(true);
                // mMusicNextIv.setEnabled(true);
                
                seekBar_audioPlayer.setProgress(0);
                seekBar_audioPlayer.setSecondaryProgress(0);
                textView_timePosition.setText("");
                textView_timeDuration.setText("");
                if (AudioPlayManager.getInstance().isLoop) {
                    break;
                }
                // if (MusicManager.getInstance().getPosition() == 0) {
                //     mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_press_icon);
                //     mMusicPreviousIv.setEnabled(false);
                // }
                // if (MusicManager.getInstance().getPosition() == MusicManager.getInstance().getPlayList().size() - 1) {
                //     mMusicNextIv.setImageResource(R.drawable.music_play_next_press_icon);
                //     mMusicNextIv.setEnabled(false);
                // }
                break;
            case PREPARE:
                break;
            case PLAYING:
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_pause);
                updateAudioInfo(true);
                break;
            case STOP:
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_start);
                // 停止时进度就保留在最后
                // seekBar_audioPlayer.setProgress(0);
                // seekBar_audioPlayer.setSecondaryProgress(0);
                // textView_timePosition.setText("");
                // textView_timeDuration.setText("");
                updateAudioInfo(false);
                break;
            case END:
                updateAudioInfo(false);
                break;
            case PAUSE:
                imageView_playPauseController.setImageResource(R.drawable.comui_audio_player_start);
                updateAudioInfo(false);
                break;
            case READY:
                seekBar_audioPlayer.setMax(AudioPlayManager.getInstance().MaxProgress);
                textView_timePosition.setText(AudioPlayManager.getInstance().getCurrentTime());
                textView_timeDuration.setText(AudioPlayManager.getInstance().getDurationTime());
                break;
            case SWITCH:
                refreshVoiceSwitch();
                break;
            default:
                break;
        }
    }
    
    private void updateAudioInfo(boolean isPlaying) {
        AudioModel audioModel = AudioPlayManager.getInstance().getCurrentAudio();
        if (audioModel != null) {
            audioModel.setPlaying(isPlaying);
        }
        
        if (forNativeDesignatedAudioPlayerViewListener != null) {
            forNativeDesignatedAudioPlayerViewListener.onUpdateAudioInfo(audioModel);
        }
        
        if (forWebDesignatedAudioPlayerViewListener != null) {
            forWebDesignatedAudioPlayerViewListener.onUpdateAudioInfo(audioModel);
        }
    }
    
    
    // ---------- ---------- ---------- Implementations ---------- ---------- ----------
    
    /**
     * 点击监听
     * yanss 2017/04/07 14:19:25
     */
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.imageView_playPauseController) {
                if (AudioPlayManager.getInstance().getAudioState() == PLAYING) {
                    AudioPlayManager.getInstance().pause();
                } else {
                    AudioPlayManager.getInstance().start();
                }
            } else if (id == R.id.imageView_voiceSwitch) {
                AudioPlayManager.getInstance().switchVoice();
            } else if (id == R.id.imageView_close) {
                AudioPlayManager.getInstance().destroy();
                closeFloatingWindow();
            } else if (id == R.id.frameLayout_miniPlayerContainer) {
                lengthenFloatingWindow();
            }
            
            // MusicManager.getInstance().previous();
            // MusicManager.getInstance().next();
        }
    };
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public void setForNativeDesignatedAudioPlayerViewListener(DesignatedAudioPlayerViewListener forNativeDesignatedAudioPlayerViewListener) {
        this.forNativeDesignatedAudioPlayerViewListener = forNativeDesignatedAudioPlayerViewListener;
    }
    
    public void setForWebDesignatedAudioPlayerViewListener(DesignatedAudioPlayerViewListener forWebDesignatedAudioPlayerViewListener) {
        this.forWebDesignatedAudioPlayerViewListener = forWebDesignatedAudioPlayerViewListener;
    }
    
    public void setAudioPlayListener(AudioPlayListener audioPlayListener) {
        this.audioPlayListener = audioPlayListener;
    }
}
