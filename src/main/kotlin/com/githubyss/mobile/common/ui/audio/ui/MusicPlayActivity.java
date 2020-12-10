// package com.githubyss.mobile.common.ui.audio.ui;// package com.ace.accessibility.automatic.audio.ui;
//
// import android.content.ActivityNotFoundException;
// import android.content.Intent;
// import android.graphics.Bitmap;
// import android.graphics.BitmapFactory;
// import android.net.Uri;
// import android.os.Build;
// import android.os.Bundle;
// import android.os.Handler;
// import android.provider.Settings;
// import android.view.KeyEvent;
// import android.view.View;
//
// import com.githubyss.mobile.common.ui.R;
// import com.githubyss.mobile.common.ui.audio.views.MusicView;
// import org.json.JSONException;
// import org.json.JSONObject;
//
// /**
//  * 88396251
//  * 2018/5/28
//  * 音频播放主界面
//  */
//
// public class MusicPlayActivity extends BaseActivity {
//
//     private static final String PACKAGE = "package:";
//     private MusicView mMusicPlayMusicView;
//     private boolean mfinished;
//     private boolean misShow;
//     private MusicNotification mMusicNotification;
//     private QQShareCallback qqShareCallback = new QQShareCallback();
//
//     @Override
//     protected void onCreate(Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);
//         setContentView(R.layout.music_play_activity_layout);
//         initView();
//         initData();
//     }
//
//     private void initView() {
//         mMusicPlayMusicView = (MusicView) findViewById(R.id.music_play_musicview);
//         mMusicNotification = new MusicNotification(getBaseContext());
//     }
//
//     private void initData() {
//         if (getIntent().getBooleanExtra("isNotification", false) && EpaActivityLifecycleUtil.getInstance().getActivityNum() <= 1) {
//             //通知栏在程序杀死后才启动进入。直接回到首页
//             Intent intent = new Intent(getBaseContext(), SplashActivity.class);
//             startActivity(intent);
//             finish();
//             return;
//         }
//         mfinished = false;
//         misShow = false;
//         hideFloat();
//         mMusicNotification.initBroadcastReceiver();
//         EventBus.getDefault().register(this);
//         if (getIntent().getBooleanExtra("startNew", false) ||
//                 MusicManager.getInstance().getPlayList() == null || MusicManager.getInstance().getPlayList().size() == 0) {
//             AudioInfo info = (AudioInfo) getIntent().getSerializableExtra("AudioInfo");
//             if (info != null) {
//                 MusicManager.getInstance().setInfo(info);
//                 MusicManager.getInstance().play(getBaseContext());
//             } else {
//                 ToastUtil.showMessage(getBaseContext(), getString(R.string.music_play_no_list));
//             }
//         } else {
//             mMusicPlayMusicView.initData();
//         }
//
//
//         mMusicPlayMusicView.setMusicInterface(new MusicInterface() {
//             @Override
//             public void onStateChanged(AudioState audioState) {
//                 if (mMusicNotification != null && !misShow && !mfinished && !isFinishing()) {
//                     mMusicNotification.showNotify();
//                 }
//             }
//
//             @Override
//             public void onPlayProgress(int CurrentPosition) {
//
//             }
//
//             @Override
//             public void onBufferingUpdateProgress(int percent) {
//
//             }
//         });
//     }
//
//     private void hideFloat() {
//         LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Constant.INTENT_ACTION_CLOSE_FLOAT));
//     }
//
//     public void musicBack(View view) {
//         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_BACK);
//         CustomAlertDialog.showNoTitleTwoBtn(getFragmentManager(), getString(R.string.music_play_login_out), getString(R.string.music_play_login_out_confirm), new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 hasPermission(true);
//             }
//         }, getString(R.string.music_play_login_out_cancel), new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 mfinished = true;
//                 CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_QUIT);
//                 MusicManager.getInstance().destory();
//                 finish();
//             }
//         }, false);
//     }
//
//     public void musicShare(View view) {
//         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_SHARE);
//         share();
//     }
//
//     @Override
//     public boolean onKeyDown(int keyCode, KeyEvent event) {
//         if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//             musicBack(null);
//         }
//         return false;
//     }
//
//     /**
//      * openRequest 是否打开验证页
//      */
//     private void hasPermission(boolean openRequest) {
//         if (PermissionFloatUtils.hasPermission(getBaseContext())) {
//             onSuccess();
//         } else if (openRequest) {
//             try {
//                 Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                 intent.setData(Uri.parse(PACKAGE + getPackageName()));
//                 startActivityForResult(intent, ActivityRequestCode.MUSIC_PERMISSION_REQUEST);
//             } catch (ActivityNotFoundException e) {
//                 Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                 intent.setData(Uri.fromParts("package", getPackageName(), null));
//                 startActivityForResult(intent, 0);
//             }
//         } else {
//             onFail();
//         }
//     }
//
//     /**
//      * 8.0bug,canDrawOverlays返回有延迟
//      */
//     private void delayed() {
//         new Handler().postDelayed(new Runnable() {
//             @Override
//             public void run() {
//                 hasPermission(false);
//             }
//         }, 500);
//     }
//
//     @Override
//     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//         super.onActivityResult(requestCode, resultCode, data);
//         if (requestCode == ActivityRequestCode.MUSIC_PERMISSION_REQUEST) {
//             //8.0特性
//             if (Build.VERSION.SDK_INT == Constant.VERSION_CODES_O) {
//                 delayed();
//             } else {
//                 hasPermission(false);
//             }
//         }
//         if (requestCode == Constants.REQUEST_QQ_SHARE ||
//                 requestCode == Constants.REQUEST_QZONE_SHARE ||
//                 requestCode == Constants.REQUEST_OLD_SHARE) {
//             Tencent.handleResultData(data, qqShareCallback);
//         }
//     }
//
//     private void onSuccess() {
//         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_BACKGROUND);
//         startService(new Intent(MusicPlayActivity.this, MusicService.class));
//         mfinished = true;
//         finish();
//     }
//
//     private void onFail() {
//         CustomStatisticsUtils.setCustomEvent(CustomStatisticsUtils.ELEID_QUIT);
//         ToastUtil.showMessage(getBaseContext(), getString(R.string.music_play_float_fail));
//         MusicManager.getInstance().destory();
//         mfinished = true;
//         finish();
//     }
//
//     /**
//      * 分享
//      */
//     private void share() {
//         if (MusicManager.getInstance().getPlayList() == null ||
//                 (MusicManager.getInstance().getPlayList().size() <= MusicManager.getInstance().getPosition())) {
//             return;
//         }
//         MusicModel item = MusicManager.getInstance().getPlayList().get(MusicManager.getInstance().getPosition());
//         if (item == null) {
//             return;
//         }
//         ShareBean shareBean = new ShareBean();
//         shareBean.setImageUrl(item.getIcon() + "");
//         shareBean.setShareTitle(item.getTitle() + "");
//         shareBean.setShareContent(item.getAuthor() + "");
//         shareBean.setShareUrl(item.getUrl() + "");
//         final Bundle shareBundle = new Bundle();
//         shareBundle.putSerializable("shareBean", shareBean);
//         shareDialog(shareBundle, BitmapFactory.decodeResource(getResources(), R.drawable.audio_defult_icon));
//     }
//
//     private void shareDialog(Bundle shareBundle, Bitmap bitmap) {
//         CommonShareDialog show = CommonShareDialog.show(this, getSupportFragmentManager(), true, shareBundle, bitmap, true);
//         show.setQQShareCallback(qqShareCallback);
//     }
//
//     @Subscribe(threadMode = ThreadMode.MAIN)
//     public void invokeShareCallback(ShareResult shareResult) {
//         if (!TextUtils.isEmpty(shareResult.jsonStr)) {
//             try {
//                 JSONObject jsonObject = new JSONObject(shareResult.jsonStr);
//                 if (jsonObject.getInt("result") == 0) {
//                     ToastUtil.showMessage(getBaseContext(), getString(R.string.music_play_layout_share_success));
//                 }
//             } catch (JSONException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
//
//     @Override
//     protected void onStop() {
//         super.onStop();
//         misShow = false;
//         if (!mfinished && mMusicNotification != null) {
//             mMusicNotification.showNotify();
//         }
//     }
//
//     @Override
//     protected void onPause() {
//         super.onPause();
//         CustomStatisticsProxy.onPause(this);
//     }
//
//     @Override
//     protected void onResume() {
//         super.onResume();
//         CustomStatisticsProxy.onResume(this, ResUtil.getString(R.string.music_play_activity_text));
//         misShow = true;
//         if (mMusicNotification != null) {
//             mMusicNotification.cancel();
//         }
//     }
//
//     @Override
//     protected void onDestroy() {
//         super.onDestroy();
//         EventBus.getDefault().unregister(this);
//         if (mMusicNotification != null) {
//             mMusicNotification.destroy();
//         }
//     }
// }
