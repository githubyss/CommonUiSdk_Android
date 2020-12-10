// package com.githubyss.mobile.common.ui.audio.views;// package com.ace.accessibility.automatic.audio.views;
//
// import android.content.Context;
// import android.util.AttributeSet;
// import android.view.View;
// import android.widget.ImageView;
// import android.widget.SeekBar;
// import android.widget.TextView;
//
// import com.githubyss.mobile.common.ui.R;
// import com.githubyss.mobile.common.ui.audio.music.AudioState;
// import com.githubyss.mobile.common.ui.audio.music.MusicManager;
// import com.githubyss.mobile.common.ui.audio.util.ProgressTextUtils;
//
// import static com.githubyss.mobile.common.ui.audio.music.AudioState.*;
//
//
// /**
//  * MusicView
//  * <Description> 音乐播放界面
//  * <Details>
//  *
//  * @author Ace Yan
//  * @github githubyss
//  * @createdTime 2020/12/10 11:21:42
//  */
// public class MusicView extends BaseMusicLayout {
//     private TextView mMusicPlayTitleTv, mMusicPlayAuthorTv, mMusicPlayPositionTimeTv, mMusicPlayDurationTimeTv;
//     private SeekBar mMusicPlaySeek;
//     private ImageView mMusicPlayStartIv, mMusicPreviousIv, mMusicNextIv;
//
//     public MusicView(Context context) {
//         super(context);
//     }
//
//     public MusicView(Context context, AttributeSet attrs) {
//         super(context, attrs);
//     }
//
//     @Override
//     protected int getContentViewId() {
//         return R.layout.music_play_view_layout;
//     }
//
//
//     @Override
//     protected void initView() {
//         mMusicPlayTitleTv = (TextView) findViewById(R.id.music_play_activity_title_tv);
//         mMusicPlayAuthorTv = (TextView) findViewById(R.id.music_play_activity_author_tv);
//         mMusicPlayPositionTimeTv = (TextView) findViewById(R.id.music_play_activity_position_time_tv);
//         mMusicPlayDurationTimeTv = (TextView) findViewById(R.id.music_play_activity_duration_time_tv);
//         mMusicPlayStartIv = (ImageView) findViewById(R.id.music_play_start_iv);
//         mMusicPlaySeek = (SeekBar) findViewById(R.id.music_play_seek);
//         mMusicPreviousIv = (ImageView) findViewById(R.id.music_play_previous_iv);
//         mMusicNextIv = (ImageView) findViewById(R.id.music_play_next_iv);
//         mMusicPreviousIv.setOnClickListener(this);
//         mMusicPlayStartIv.setOnClickListener(this);
//         mMusicNextIv.setOnClickListener(this);
//         SeekBarChange();
//     }
//
//     /**
//      * 当前已经在播放
//      * 重新进入初始化当前数据
//      */
//     public void initData() {
//         if (mMusicPlaySeek != null) {
//             StateChanged(START);
//             StateChanged(READY);//先把一些漏掉的东西初始化下
//             StateChanged(MusicManager.getInstance().audioState);
//             mMusicPlaySeek.setProgress(MusicManager.getInstance().getCurrentPosition());
//             mMusicPlaySeek.setSecondaryProgress(MusicManager.getInstance().getUpdateProgress());
//         }
//     }
//
//     private void SeekBarChange() {
//         mMusicPlaySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//             @Override
//             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                 mMusicPlayPositionTimeTv.setText(ProgressTextUtils.getProgressText(progress));
//             }
//
//             @Override
//             public void onStartTrackingTouch(SeekBar seekBar) {
//             }
//
//             @Override
//             public void onStopTrackingTouch(SeekBar seekBar) {
//                 MusicManager.getInstance().seekTo(seekBar.getProgress());
//             }
//         });
//     }
//
//     @Override
//     protected void PlayProgress(int CurrentPosition) {
//         super.PlayProgress(CurrentPosition);
//         if (!mMusicPlaySeek.isPressed()) {
//             mMusicPlaySeek.setProgress(CurrentPosition);
//         }
//     }
//
//     @Override
//     public void BufferingUpdateProgress(int percent) {
//         super.BufferingUpdateProgress(percent);
//         mMusicPlaySeek.setSecondaryProgress(percent);
//     }
//
//     @Override
//     protected void StateChanged(AudioState audioState) {
//         switch (audioState) {
//             case START:
//                 if (MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0) {
//                     break;
//                 }
//                 mMusicPlayTitleTv.setText(MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getTitle());
//                 mMusicPlayAuthorTv.setText(mContext.getString(R.string.music_play_author) + MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
//                 mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_click_icon);
//                 mMusicNextIv.setImageResource(R.drawable.music_play_next_click_icon);
//                 mMusicPreviousIv.setEnabled(true);
//                 mMusicNextIv.setEnabled(true);
//                 mMusicPlaySeek.setSecondaryProgress(0);
//                 mMusicPlaySeek.setProgress(0);
//                 mMusicPlayDurationTimeTv.setText("");
//                 mMusicPlayPositionTimeTv.setText("");
//                 if (MusicManager.getInstance().loop) {
//                     break;
//                 }
//                 if (MusicManager.getInstance().getPosition() == 0) {
//                     mMusicPreviousIv.setImageResource(R.drawable.music_play_previous_press_icon);
//                     mMusicPreviousIv.setEnabled(false);
//                 }
//                 if (MusicManager.getInstance().getPosition() == MusicManager.getInstance().getPlayList().size() - 1) {
//                     mMusicNextIv.setImageResource(R.drawable.music_play_next_press_icon);
//                     mMusicNextIv.setEnabled(false);
//                 }
//                 break;
//             case PLAYING:
//                 mMusicPlayStartIv.setImageResource(R.drawable.music_play_btn_pause);
//                 break;
//             case PREPARE:
//                 break;
//             case READY:
//                 mMusicPlaySeek.setMax(MusicManager.getInstance().MaxProgress);
//                 mMusicPlayDurationTimeTv.setText(MusicManager.getInstance().getDurationTime());
//                 mMusicPlayPositionTimeTv.setText(MusicManager.getInstance().getCurrentTime());
//                 break;
//             case PAUSE:
//                 mMusicPlayStartIv.setImageResource(R.drawable.music_play_btn_play);
//                 break;
//             case STOP:
//                 mMusicPlayStartIv.setImageResource(R.drawable.music_play_btn_play);
//                 mMusicPlaySeek.setSecondaryProgress(0);
//                 mMusicPlaySeek.setProgress(0);
//                 mMusicPlayDurationTimeTv.setText("");
//                 mMusicPlayPositionTimeTv.setText("");
//                 break;
//         }
//     }
//
//     @Override
//     public void onClick(View view) {
//         super.onClick(view);
//         if (MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0) {
//             return;
//         }
//         int id = view.getId();
//         if (id == R.id.music_play_previous_iv) {
//             MusicManager.getInstance().previous();
//         } else if (id == R.id.music_play_start_iv) {
//             if (MusicManager.getInstance().audioState == PLAYING) {
//                 MusicManager.getInstance().pause();
//             } else {
//                 MusicManager.getInstance().start();
//             }
//         } else if (id == R.id.music_play_next_iv) {
//             MusicManager.getInstance().next();
//         }
//     }
// }
