package com.githubyss.mobile.common.ui.audio.views;// package com.ace.accessibility.automatic.audio.views;
//
// import android.app.Notification;
// import android.app.NotificationChannel;
// import android.app.NotificationManager;
// import android.app.PendingIntent;
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.Intent;
// import android.content.IntentFilter;
// import android.graphics.Color;
// import android.os.Build;
// import android.support.v4.app.NotificationCompat;
// import android.view.View;
// import android.widget.RemoteViews;
//
// import com.suning.mobile.epa.R;
// import com.suning.mobile.epa.audio.music.MusicManager;
// import com.suning.mobile.epa.audio.ui.MusicPlayActivity;
// import com.suning.mobile.epa.audio.util.Constant;
// import com.suning.mobile.epa.audio.util.CustomStatisticsUtils;
//
// import static android.content.Context.NOTIFICATION_SERVICE;
// import static com.suning.mobile.epa.audio.music.AudioState.STATE_PAUSE;
// import static com.suning.mobile.epa.audio.music.AudioState.STATE_PLAYING;
// import static com.suning.mobile.epa.audio.music.AudioState.STATE_STOP;
// import static com.suning.mobile.epa.common.ConstantsEPA.NOTIFY_CHANNEL_ID_MUSIC;
// import static com.suning.mobile.epa.common.ConstantsEPA.NOTIFY_CHANNEL_NAME_MUSIC;
//
//
// /**
//  * Created by 88396251 on 2018/5/30.
//  * 通知栏消息框
//  */
//
// public class MusicNotification {
//     private final Context mContext;
//     // 初始化广播接收器，只接受本地广播
//     private MyBroadcastReceiver receviver = null;
//     private RemoteViews mRemoteViews;
//     private NotificationManager mNotificationManager;
//     // 标识
//     private final String INTENT_BUTTONID_TAG = "ButtonId";
//     /**
//      * 上一首 按钮点击 ID
//      */
//     private final int BUTTON_PREV_ID = 1;
//     /**
//      * 播放/暂停 按钮点击 ID
//      */
//     private final int BUTTON_PALY_ID = 2;
//     /**
//      * 下一首 按钮点击 ID
//      */
//     private final int BUTTON_NEXT_ID = 3;
//     /**
//      * 返回详情界面 ID
//      */
//     private final int BUTTON_MAIN_ID = 4;
//     //    private final String CHANNEL_NAME = "Primary Channel";
// //    private final String PRIMARY_CHANNEL = "default";//8.0新特性。
//     private final int notifyID = 200;
//
//     public MusicNotification(Context context) {
//         this.mContext = context;
//     }
//
//     public void initBroadcastReceiver() {
//         if (mContext == null)
//             return;
//         IntentFilter intentFilter = new IntentFilter();
//         intentFilter.addAction(Constant.INTENT_ACTION_NOTIFICATION);
//         receviver = new MyBroadcastReceiver();
//         // 注册广播接收器, LocalBroadcastManager.getInstance(getBaseContext())接不到通知栏的广播
//         mContext.registerReceiver(receviver, intentFilter);
//     }
//
//     private class MyBroadcastReceiver extends BroadcastReceiver {
//
//         @Override
//         public void onReceive(Context context, Intent intent) {
//             String action = intent.getAction();
//             if (action.equals(Constant.INTENT_ACTION_NOTIFICATION)) {
//                 // 通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
//                 switch (intent.getIntExtra(INTENT_BUTTONID_TAG, 0)) {
//                     case BUTTON_PREV_ID:
//                         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_LAST);
//                         MusicManager.getInstance().previous();
//                         break;
//                     case BUTTON_PALY_ID:
//                         if (MusicManager.getInstance().audioState == STATE_PLAYING) {
//                             CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_PAUSE);
//                             MusicManager.getInstance().pause();
//                         } else {
//                             CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_PALY);
//                             MusicManager.getInstance().start();
//                         }
//                         break;
//                     case BUTTON_NEXT_ID:
//                         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_NEXT);
//                         MusicManager.getInstance().next();
//                         break;
//                     default:
//                         break;
//                 }
//             }
//         }
//     }
//
//     public void initRemoteView() {
//         if (MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0)
//             return;
//         if (mRemoteViews == null)
//             mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.music_play_notification_layout);
//         mRemoteViews.setImageViewResource(R.id.music_play_custom_song_icon, R.drawable.notification_logo);
//         mRemoteViews.setTextViewText(R.id.music_play_custom_song_singer_tv, MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getTitle());
//         mRemoteViews.setTextViewText(R.id.music_play_custom_song_name_tv, MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition()).getAuthor());
//
//         // API3.0 以上的时候显示按钮，否则消失
//         // 如果版本号低于（3。0），那么不显示按钮
//         if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) {
//             mRemoteViews.setViewVisibility(R.id.music_play_custom_button_ll, View.GONE);
//         } else {
//             mRemoteViews.setViewVisibility(R.id.music_play_custom_button_ll, View.VISIBLE);
//             if (MusicManager.getInstance().audioState == STATE_PLAYING) {
//                 mRemoteViews.setImageViewResource(R.id.music_play_player_play, R.drawable.music_player_btn_pause_normal);
//             } else if (MusicManager.getInstance().audioState == STATE_PAUSE || MusicManager.getInstance().audioState == STATE_STOP) {
//                 mRemoteViews.setImageViewResource(R.id.music_play_player_play, R.drawable.music_player_btn_play_press);
//             }
//         }
//     }
//
//     /**
//      * 带按钮的通知栏
//      */
//     public void showNotify() {
//         if (mContext == null) {
//             return;
//         }
//         initRemoteView();
//         if (mRemoteViews == null){
//             return;
//         }
//
//         mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//         // 点击的事件处理
//         Intent buttonIntent = new Intent(Constant.INTENT_ACTION_NOTIFICATION);
//         /* 上一首按钮 */
//         buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PREV_ID);
//         // 这里加了广播，所及INTENT的必须用getBroadcast方法
//         PendingIntent intent_prev = PendingIntent.getBroadcast(mContext, BUTTON_PREV_ID, buttonIntent,
//                 PendingIntent.FLAG_UPDATE_CURRENT);
//         mRemoteViews.setOnClickPendingIntent(R.id.music_play_player_previous, intent_prev);
//         /* 播放/暂停 按钮 */
//         buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
//         PendingIntent intent_paly = PendingIntent.getBroadcast(mContext, BUTTON_PALY_ID, buttonIntent,
//                 PendingIntent.FLAG_UPDATE_CURRENT);
//         mRemoteViews.setOnClickPendingIntent(R.id.music_play_player_play, intent_paly);
//         /* 下一首 按钮 */
//         buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
//         PendingIntent intent_next = PendingIntent.getBroadcast(mContext, BUTTON_NEXT_ID, buttonIntent,
//                 PendingIntent.FLAG_UPDATE_CURRENT);
//         mRemoteViews.setOnClickPendingIntent(R.id.music_play_player_next, intent_next);
//         /* 返回详情页 按钮 */
//         Intent resultIntent = new Intent(mContext, MusicPlayActivity.class);//改动这个class，或者换签名后，请重启手机才能实现
//         resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//         resultIntent.putExtra("isNotification", true);
//         PendingIntent pIntent = PendingIntent.getActivity(mContext, BUTTON_MAIN_ID,
//                 resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//         //判断是否是8.0Android.O
//         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//             NotificationChannel chan = new NotificationChannel(NOTIFY_CHANNEL_ID_MUSIC,
//                     NOTIFY_CHANNEL_NAME_MUSIC, NotificationManager.IMPORTANCE_DEFAULT);
//             chan.setLightColor(Color.GREEN);
//             chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//             mNotificationManager.createNotificationChannel(chan);
//             Notification.Builder mBuilder = new Notification.Builder(mContext, NOTIFY_CHANNEL_ID_MUSIC);
//             mBuilder.setContentIntent(pIntent);
//             // 设置点击一次后消失（如果没有点击事件，则该方法无效。）
//             mBuilder.setAutoCancel(true);
//             mBuilder.setContent(mRemoteViews).setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
//                     .setTicker(mContext.getString(R.string.music_play_notificition_ticker)).setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
//                     .setOngoing(true).setSmallIcon(R.drawable.notification_logo);
//             Notification notify = mBuilder.build();
//             notify.flags = Notification.FLAG_ONGOING_EVENT;
//             mNotificationManager.notify(notifyID, notify);
//         } else {
//             NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
//             mBuilder.setContentIntent(pIntent);
//             // 设置点击一次后消失（如果没有点击事件，则该方法无效。）
//             mBuilder.setAutoCancel(true);
//             mBuilder.setContent(mRemoteViews).setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
//                     .setTicker(mContext.getString(R.string.music_play_notificition_ticker)).setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
//                     .setOngoing(true).setSmallIcon(R.drawable.notification_logo);
//             Notification notify = mBuilder.build();
//             notify.flags = Notification.FLAG_ONGOING_EVENT;
//             mNotificationManager.notify(notifyID, notify);
//         }
//     }
//
//     public void cancel() {
//         if (mNotificationManager != null)
//             mNotificationManager.cancelAll();
//     }
//
//     public void destroy() {
//         if (receviver != null && mContext != null) {
//             mContext.unregisterReceiver(receviver);
//             receviver = null;
//         }
//         if (mNotificationManager != null)
//             mNotificationManager.cancelAll();
//     }
// }
