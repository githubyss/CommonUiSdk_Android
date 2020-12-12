package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.enumeration.AudioState;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.audio.player.AudioPlayInterface;
import com.githubyss.mobile.common.ui.audio.util.ProgressTextUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.PLAYING;
import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.READY;
import static com.githubyss.mobile.common.ui.audio.enumeration.AudioState.START;


/**
 * DesignatedFloatingAudioPlayerView
 * <Description> 特定的音频播放器悬浮窗
 * <Details> 针对特此的布局，实现布局元素的显示和操作响应。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:30:01
 */
public class DesignatedAudioPlayerFloatingView extends BaseAutoShortedFloatingView {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private TextView textView_title;
    private TextView textView_timePosition;
    private TextView textView_timeDuration;
    private SeekBar seekBar_audioPlayer;
    private ImageView imageView_playStartPauseController;
    private ImageView imageView_voiceSwitch;
    private ImageView imageView_close;
    private ImageView imageView_lengthen;

    private DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener;
    private AudioPlayInterface audioPlayInterface;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public DesignatedAudioPlayerFloatingView(@NonNull Context context) {
        this(context, R.layout.comui_floating_audio_player_view);
    }

    public DesignatedAudioPlayerFloatingView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        if (rootView == null) {
            rootView = inflate(context, resource, this);
            initBase();
            initDesignated();
        }
    }


    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    /**
     * 当前已经在播放
     * 重新进入初始化当前数据
     */
    public void initData() {
        if (seekBar_audioPlayer != null) {
            stateChanged(START);
            // 先把一些漏掉的东西初始化下
            stateChanged(READY);
            stateChanged(AudioPlayManager.getInstance().getAudioState());
            seekBar_audioPlayer.setProgress(AudioPlayManager.getInstance().getCurrentPosition());
            seekBar_audioPlayer.setSecondaryProgress(AudioPlayManager.getInstance().getUpdateProgress());
        }
    }

    protected void playProgress(int CurrentPosition) {
        if (!seekBar_audioPlayer.isPressed()) {
            seekBar_audioPlayer.setProgress(CurrentPosition);
        }
    }

    public void bufferingUpdateProgress(int percent) {
        seekBar_audioPlayer.setSecondaryProgress(percent);
    }

    protected void stateChanged(AudioState audioState) {
        switch (audioState) {
            case START:
                if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {
                    break;
                }
                textView_title.setText(AudioPlayManager.getInstance().getAudioList().get(AudioPlayManager.getInstance().getPosition()).getTitle());
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
            case PLAYING:
                imageView_playStartPauseController.setImageResource(R.drawable.icon_audio_player_pause);
                break;
            case PREPARE:
                break;
            case READY:
                seekBar_audioPlayer.setMax(AudioPlayManager.getInstance().MaxProgress);
                textView_timePosition.setText(AudioPlayManager.getInstance().getCurrentTime());
                textView_timeDuration.setText(AudioPlayManager.getInstance().getDurationTime());
                break;
            case PAUSE:
                imageView_playStartPauseController.setImageResource(R.drawable.icon_audio_player_start);
                break;
            case STOP:
                imageView_playStartPauseController.setImageResource(R.drawable.icon_audio_player_start);
                seekBar_audioPlayer.setProgress(0);
                seekBar_audioPlayer.setSecondaryProgress(0);
                textView_timePosition.setText("");
                textView_timeDuration.setText("");
                break;
            default:
                break;
        }
    }


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initDesignated() {
        initViewFindById(rootView);
        initListener();
    }

    private void initViewFindById(View view) {
        textView_title = view.findViewById(R.id.textView_title);
        textView_timePosition = view.findViewById(R.id.textView_timePosition);
        textView_timeDuration = view.findViewById(R.id.textView_timeDuration);
        seekBar_audioPlayer = view.findViewById(R.id.seekBar_audioPlayer);
        imageView_playStartPauseController = view.findViewById(R.id.imageView_playStartPauseController);
        imageView_voiceSwitch = view.findViewById(R.id.imageView_voiceSwitch);
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_lengthen = view.findViewById(R.id.imageView_lengthen);
    }

    private void initListener() {
        imageView_playStartPauseController.setOnClickListener(onClickListener);
        imageView_voiceSwitch.setOnClickListener(onClickListener);
        imageView_close.setOnClickListener(onClickListener);
        imageView_lengthen.setOnClickListener(onClickListener);

        seekBar_audioPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
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

        this.setBaseAutoShortedFloatingViewListener(new BaseAutoShortedFloatingViewListener() {
            @Override
            public void onSlide(boolean isShow) {
                refreshCloseButton(isShow);
            }

            @Override
            public void onClose() {
                designatedAudioPlayerFloatingViewListener.onClose();
            }
        });

        AudioPlayManager.getInstance().setMusicListener(new AudioPlayInterface() {
            @Override
            public void onStateChanged(AudioState audioState) {
                stateChanged(audioState);
                if (audioPlayInterface != null) {
                    audioPlayInterface.onStateChanged(audioState);
                }
            }

            @Override
            public void onPlayProgress(int currentPosition) {
                playProgress(currentPosition);
                if (audioPlayInterface != null) {
                    audioPlayInterface.onPlayProgress(currentPosition);
                }
            }

            @Override
            public void onBufferingUpdateProgress(int percent) {
                bufferingUpdateProgress(percent);
                if (audioPlayInterface != null) {
                    audioPlayInterface.onBufferingUpdateProgress(percent);
                }
            }
        });
    }

    private void refreshCloseButton(boolean isShow) {
        if (isShow) {
            imageView_close.setVisibility(View.VISIBLE);
            imageView_lengthen.setVisibility(View.GONE);
        } else {
            imageView_close.setVisibility(View.GONE);
            imageView_lengthen.setVisibility(View.VISIBLE);
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
            if (AudioPlayManager.getInstance().getAudioList() == null || AudioPlayManager.getInstance().getAudioList().size() == 0) {
                return;
            }
            int id = v.getId();
            if (id == R.id.imageView_playStartPauseController) {
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
            } else if (id == R.id.imageView_lengthen) {
                lengthenFloatingWindow();
            }

            // MusicManager.getInstance().previous();
            // MusicManager.getInstance().next();
        }
    };


    // ---------- ---------- ---------- Setter ---------- ---------- ----------

    public void setDesignatedAudioPlayerFloatingViewListener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener) {
        this.designatedAudioPlayerFloatingViewListener = designatedAudioPlayerFloatingViewListener;
    }

    public void setAudioPlayInterface(AudioPlayInterface audioPlayInterface) {
        this.audioPlayInterface = audioPlayInterface;
    }
}
