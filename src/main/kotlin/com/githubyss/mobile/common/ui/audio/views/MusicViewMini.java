package com.githubyss.mobile.common.ui.audio.views;// package com.ace.accessibility.automatic.audio.views;
//
// import android.content.Context;
// import android.content.Intent;
// import android.util.AttributeSet;
// import android.view.View;
//
// import com.suning.mobile.epa.R;
// import com.suning.mobile.epa.audio.music.AudioState;
// import com.suning.mobile.epa.audio.ui.MusicPlayActivity;
// import com.suning.mobile.epa.audio.util.CustomStatisticsUtils;
//
//
// /**
//  * 88396251
//  * 2018-5-22
//  * 音乐播放迷你界面
//  */
//
// public class MusicViewMini extends BaseMusicLayout {
//
//     public MusicViewMini(Context context) {
//         super(context);
//     }
//
//     public MusicViewMini(Context context, AttributeSet attrs) {
//         super(context, attrs);
//     }
//
//     @Override
//     protected int getContentViewId() {
//         return R.layout.music_play_mini_layout;
//     }
//
//     @Override
//     protected void initView() {
//         setOnClickListener(this);//子布局太大，覆盖了父布局，截获了父布局的OnTouch事件
//     }
//
//     @Override
//     protected void StateChanged(AudioState audioState) {
//     }
//
//     @Override
//     protected void PlayProgress(int CurrentPosition) {
//         super.PlayProgress(CurrentPosition);
//     }
//
//     @Override
//     public void onClick(View view) {
//         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_FRAME);
//         Intent intentMian = new Intent(mContext, MusicPlayActivity.class);
//         intentMian.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//         mContext.startActivity(intentMian);
//     }
// }