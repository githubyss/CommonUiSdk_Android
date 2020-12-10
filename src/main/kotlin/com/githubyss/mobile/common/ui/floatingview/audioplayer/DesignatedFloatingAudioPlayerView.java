package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.audio.music.AudioState;
import com.githubyss.mobile.common.ui.audio.music.MusicManager;
import com.githubyss.mobile.common.ui.audio.util.MusicInterface;
import com.githubyss.mobile.common.ui.audio.util.ProgressTextUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import static com.githubyss.mobile.common.ui.audio.music.AudioState.PLAYING;
import static com.githubyss.mobile.common.ui.audio.music.AudioState.READY;
import static com.githubyss.mobile.common.ui.audio.music.AudioState.START;


/**
 * DesignatedFloatingAudioPlayerView
 * <Description> 特定的音频播放器悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:30:01
 */
public class DesignatedFloatingAudioPlayerView extends BaseFloatingAutoShortedView {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private TextView textView_title;
    private TextView textView_timePosition;
    private TextView textView_timeDuration;
    private SeekBar seekBar_audioPlayer;
    private ImageView imageView_playStartPauseController;
    private ImageView imageView_voiceSwitch;
    private ImageView imageView_close;
    private ImageView imageView_lengthen;

    private DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener;
    private MusicInterface musicInterface;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public DesignatedFloatingAudioPlayerView(@NonNull Context context) {
        this(context, R.layout.comui_floating_audio_player_view);
    }

    public DesignatedFloatingAudioPlayerView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        rootView = inflate(context, resource, this);
        initBase();
        initDesignated();
    }


    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------

    /**
     * 当前已经在播放
     * 重新进入初始化当前数据
     */
    public void initData() {
        if (seekBar_audioPlayer != null) {
            StateChanged(START);
            // 先把一些漏掉的东西初始化下
            StateChanged(READY);
            StateChanged(MusicManager.getInstance().audioState);
            seekBar_audioPlayer.setProgress(MusicManager.getInstance().getCurrentPosition());
            seekBar_audioPlayer.setSecondaryProgress(MusicManager.getInstance().getUpdateProgress());
        }
    }

    protected void PlayProgress(int CurrentPosition) {
        if (!seekBar_audioPlayer.isPressed()) {
            seekBar_audioPlayer.setProgress(CurrentPosition);
        }
    }

    public void BufferingUpdateProgress(int percent) {
        seekBar_audioPlayer.setSecondaryProgress(percent);
    }

    protected void StateChanged(AudioState audioState) {
        switch (audioState) {
            case START:
                if (MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0) {
                    break;
                }
                textView_title.setText(MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getTitle());
                // mMusicPlayAuthorTv.setText(mContext.getString(R.string.music_play_author) + MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
                // mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_click_icon);
                // mMusicNextIv.setImageResource(R.drawable.music_play_next_click_icon);
                // mMusicPreviousIv.setEnabled(true);
                // mMusicNextIv.setEnabled(true);
                seekBar_audioPlayer.setProgress(0);
                seekBar_audioPlayer.setSecondaryProgress(0);
                textView_timePosition.setText("");
                textView_timeDuration.setText("");
                if (MusicManager.getInstance().loop) {
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
                seekBar_audioPlayer.setMax(MusicManager.getInstance().MaxProgress);
                textView_timePosition.setText(MusicManager.getInstance().getCurrentTime());
                textView_timeDuration.setText(MusicManager.getInstance().getDurationTime());
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
                MusicManager.getInstance().seekTo(seekBar.getProgress());
            }
        });

        this.setBaseFloatingAutoShortedViewListener(new BaseFloatingAutoShortedViewListener() {
            @Override
            public void onSlide(boolean isShow) {
                refreshCloseButton(isShow);
            }

            @Override
            public void onClose() {
                designatedFloatingAudioPlayerViewListener.onClose();
            }
        });

        MusicManager.getInstance().setMusicListener(new MusicInterface() {
            @Override
            public void onStateChanged(AudioState audioState) {
                StateChanged(audioState);
                if (musicInterface != null) {
                    musicInterface.onStateChanged(audioState);
                }
            }

            @Override
            public void onPlayProgress(int currentPosition) {
                PlayProgress(currentPosition);
                if (musicInterface != null) {
                    musicInterface.onPlayProgress(currentPosition);
                }
            }

            @Override
            public void onBufferingUpdateProgress(int percent) {
                BufferingUpdateProgress(percent);
                if (musicInterface != null) {
                    musicInterface.onBufferingUpdateProgress(percent);
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
            if (MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0) {
                return;
            }
            int id = v.getId();
            if (id == R.id.imageView_playStartPauseController) {
                if (MusicManager.getInstance().audioState == PLAYING) {
                    MusicManager.getInstance().pause();
                } else {
                    MusicManager.getInstance().start();
                }
            } else if (id == R.id.imageView_voiceSwitch) {

            } else if (id == R.id.imageView_close) {
                // hideFloatingWindow();
                shortenFloatingWindow();
            } else if (id == R.id.imageView_lengthen) {
                lengthenFloatingWindow();
            }

            // MusicManager.getInstance().previous();
            // MusicManager.getInstance().next();
        }
    };


    // ---------- ---------- ---------- Setter ---------- ---------- ----------

    public void setDesignatedFloatingAudioPlayerViewListener(DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener) {
        this.designatedFloatingAudioPlayerViewListener = designatedFloatingAudioPlayerViewListener;
    }

    public void setMusicInterface(MusicInterface musicInterface) {
        this.musicInterface = musicInterface;
    }
}
