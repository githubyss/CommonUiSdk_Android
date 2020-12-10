package com.githubyss.mobile.common.ui.audio.views;// package com.ace.accessibility.automatic.audio.views;
//
// import android.animation.TypeEvaluator;
// import android.animation.ValueAnimator;
// import android.content.Context;
// import android.graphics.PixelFormat;
// import android.graphics.Point;
// import android.os.Build;
// import android.os.Handler;
// import android.os.Message;
// import android.util.DisplayMetrics;
// import android.view.Gravity;
// import android.view.MotionEvent;
// import android.view.View;
// import android.view.WindowManager;
//
// import com.suning.mobile.epa.audio.util.Constant;
// import com.suning.mobile.epa.audio.util.PermissionFloatUtils;
// import com.suning.mobile.epa.collectmoney.refreshview.utils.RefreshUtil;
// import com.suning.mobile.epa.utils.DeviceInfoUtil;
//
// /**
//  * 88396251
//  * 2018-5-17
//  * 悬浮框界面
//  */
//
// public class FloatView {
//     //8.0 type样式，不可修改，为适应低版本编译，自己定义
//     public static final int TYPE_APPLICATION_OVERLAY = 2038;
//     //小米8.0,8.1的坑，正常规则走不通，只能走系统漏洞。
//     public static final int TYPE_PRESENTATION = 2037;
//     private WindowManager.LayoutParams wmParams;
//     // 创建浮动窗口设置布局参数的对象
//     private WindowManager mWindowManager;
//     private Context mContext;
//     private static final int WHAT_HIDE = 0x275;
//     private static final int WHAT_HIDE_TIME = 3500;//高亮显示时间
//     private boolean moveable = true;//是否可以移动
//     private float downX, downY;
//     private float offsetX, offsetY, mBarHeight;
//     private long lastTouchTimeMillis;
//     private final float DISTANCE = 15.0f;  //  点击偏移量   在上、下、左、右这个范围之内都会触发点击事件
//     private DisplayMetrics mDisplayMetrics;
//     private View mContentView;
//     private final int initX = 0;
//     private int initY = 0;
//     private final int bottomHeight = 126;//距离底部的差值
//
//     public FloatView(Context mContext) {
//         this.mContext = mContext;
//     }
//
//     /**
//      * 不重复添加view，实例化一次后一直存在
//      */
//     public void addContentView(View ContentView) {
//         if (mContentView == null)
//             addView(ContentView);
//     }
//
//     /**
//      * 显示的view换新的view显示
//      */
//     public void addView(View ContentView) {
//         if (mContext == null) {
//             return;
//         }
//         if (mContentView != null) {
//             getWindowManager().removeView(mContentView);
//         }
//         initY = RefreshUtil.getScreenHeight(mContext) - DeviceInfoUtil.dip2px(mContext, bottomHeight);
//         mContentView = ContentView;
//         getWindowManager().addView(mContentView, getLayoutParams());// 添加mFloatLayout
//         mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//         mBarHeight = getStatusBarHeight(mContext);
//         mContentView.setOnTouchListener(new WindowTouchListener());
//         lastTouchTimeMillis = System.currentTimeMillis();
//         handler.sendEmptyMessage(WHAT_HIDE);
//     }
//
//     /**
//      * 获取状态栏的高度
//      */
//     public int getStatusBarHeight(Context context) {
//         int height = 0;
//         int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//         if (resId > 0) {
//             height = context.getResources().getDimensionPixelSize(resId);
//         }
//         return height;
//     }
//
//     /**
//      * 获得显示信息
//      */
//     public DisplayMetrics getDisplayMetrics() {
//         if (mDisplayMetrics == null && mContext != null) {
//             mDisplayMetrics = mContext.getResources().getDisplayMetrics();
//         }
//         return mDisplayMetrics;
//     }
//
//     private void initParams() {
//         // 设置window type
//         if (Build.VERSION.SDK_INT >= Constant.VERSION_CODES_O) {
//             boolean MiUiO=(Build.VERSION.SDK_INT == Constant.VERSION_CODES_O || Build.VERSION.SDK_INT == Constant.VERSION_CODES_O_MR1)
//                     && PermissionFloatUtils.isMiui();
//             if (MiUiO) {
//                 getLayoutParams().type = TYPE_PRESENTATION;
//             } else {
//                 getLayoutParams().type = TYPE_APPLICATION_OVERLAY;
//             }
//         } else {
//             getLayoutParams().type = WindowManager.LayoutParams.TYPE_PHONE;
//         }
//         // 设置图片格式，效果为背景透明
//         getLayoutParams().format = PixelFormat.RGBA_8888;
//         // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
//         getLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//         // 调整悬浮窗显示的停靠位置为左侧置顶
//         getLayoutParams().gravity = Gravity.LEFT | Gravity.TOP;
//         //设置x、y初始值，相对于gravity,
//         getLayoutParams().x = initX;
//         getLayoutParams().y = initY;
//         // 设置悬浮窗口长宽数据
//         getLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT;
//         getLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT;
//     }
//
//     /**
//      * 获得WindowManager.LayoutParams参数
//      */
//     public WindowManager.LayoutParams getLayoutParams() {
//         if (wmParams == null) {
//             wmParams = new WindowManager.LayoutParams();
//             initParams();
//         }
//         return wmParams;
//     }
//
//     public WindowManager getWindowManager() {
//         if (mWindowManager == null && mContext != null) {
//             mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//         }
//         return mWindowManager;
//     }
//
//
//     class WindowTouchListener implements View.OnTouchListener {
//         @Override
//         public boolean onTouch(View v, MotionEvent event) {
//             if (moveable) {
//                 switch (event.getAction()) {
//                     case MotionEvent.ACTION_DOWN:
//                         downX = event.getRawX();
//                         downY = event.getRawY();
//                         offsetX = event.getX();
//                         offsetY = event.getY();
//                         getLayoutParams().alpha = 1.0f;
//                         lastTouchTimeMillis = System.currentTimeMillis();
//                         getWindowManager().updateViewLayout(v, getLayoutParams());
//                         break;
//                     case MotionEvent.ACTION_MOVE:
//                         lastTouchTimeMillis = System.currentTimeMillis();
//                         updateLocation(v, event.getRawX() - offsetX, event.getRawY() - offsetY, true);
//                         break;
//                     case MotionEvent.ACTION_UP:
//                         //截获点击事件，长按事件已被屏蔽，要实现要在ACTION_MOVE重新写逻辑
//                         if (event.getRawX() >= downX - DISTANCE && event.getRawX() <= downX + DISTANCE &&
//                                 event.getRawY() >= downY - DISTANCE && event.getRawY() <= downY + DISTANCE) {
//                             v.performClick();
//                         } else {
//                             ValueAnimator animator = alignAnimator(v, event.getRawX(), event.getRawY());
//                             animator.start();
//                         }
//                         lastTouchTimeMillis = System.currentTimeMillis();
//                         handler.sendEmptyMessage(WHAT_HIDE);
//                         break;
//                     case MotionEvent.ACTION_OUTSIDE:
//                         break;
//                     default:
//                         break;
//                 }
//                 return true;
//             }
//             return false;
//         }
//
//     }
//
//     /**
//      * 更新窗口的位置
//      */
//     private void updateLocation(View v, float x, float y, boolean offset) {
//         if (this != null && mContext != null) {
//             if (offset) {
//                 getLayoutParams().x = (int) x;
//                 getLayoutParams().y = (int) (y - mBarHeight);
//             } else {
//                 getLayoutParams().x = (int) x;
//                 getLayoutParams().y = (int) y;
//             }
//             getWindowManager().updateViewLayout(v, getLayoutParams());
//         }
//     }
//
//     /**
//      * 自动对齐的一个小动画（自定义属性动画），使自动贴边的时候显得不那么生硬
//      */
//     private ValueAnimator alignAnimator(final View v, float x, float y) {
//         ValueAnimator animator;
//         if (x <= getDisplayMetrics().widthPixels / 2) {
//             animator = ValueAnimator.ofObject(new PointEvaluator(), new Point((int) x, (int) y), new Point(0, (int) y));
//         } else {
//             animator = ValueAnimator.ofObject(new PointEvaluator(), new Point((int) x, (int) y), new Point(getDisplayMetrics().widthPixels, (int) y));
//         }
//         animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//             @Override
//             public void onAnimationUpdate(ValueAnimator animation) {
//                 Point point = (Point) animation.getAnimatedValue();
//                 updateLocation(v, point.x, point.y, true);
//             }
//         });
//         animator.setDuration(160);
//         return animator;
//     }
//
//     public class PointEvaluator implements TypeEvaluator {
//
//         @Override
//         public Object evaluate(float fraction, Object from, Object to) {
//             Point startPoint = (Point) from;
//             Point endPoint = (Point) to;
//             float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
//             float y = startPoint.y + fraction * (endPoint.y - startPoint.y);
//             Point point = new Point((int) x, (int) y);
//             return point;
//         }
//     }
//
//     private Handler handler = new Handler() {
//
//         @Override
//         public void handleMessage(Message msg) {
//             super.handleMessage(msg);
//             switch (msg.what) {
//                 case WHAT_HIDE:
//                     if (System.currentTimeMillis() - lastTouchTimeMillis >= WHAT_HIDE_TIME) {
//                         if (mContext != null && mContentView != null) {
//                             getLayoutParams().alpha = 0.4f;
//                             getWindowManager().updateViewLayout(mContentView, getLayoutParams());
//                         }
//                     } else {
//                         handler.sendEmptyMessageDelayed(WHAT_HIDE, 200);
//                     }
//                     break;
//             }
//         }
//     };
//
//     public void onClickChild() {
//         if (mContentView == null) {
//             return;
//         }
//         getLayoutParams().alpha = 1.0f;
//         lastTouchTimeMillis = System.currentTimeMillis();
//         getWindowManager().updateViewLayout(mContentView, getLayoutParams());
//         handler.sendEmptyMessage(WHAT_HIDE);
//     }
//
//     public void setMoveable(boolean moveable) {
//         this.moveable = moveable;
//     }
//
//     public boolean isShowing() {
//         if (mContentView != null) {
//             return mContentView.getVisibility() == View.VISIBLE ? true : false;
//         }
//         return false;
//     }
//
//     public void show() {
//         if (handler != null) {
//             handler.sendEmptyMessage(WHAT_HIDE);
//         }
//         if (mContext != null && mContentView != null) {
//             mContentView.setVisibility(View.VISIBLE);
//         }
//     }
//
//     public void hide() {
//         if (handler != null) {
//             handler.removeMessages(WHAT_HIDE);
//         }
//         if (mContext != null && mContentView != null) {
//             mContentView.setVisibility(View.GONE);
//         }
//     }
//
//     public void remove() {
//         if (handler != null) {
//             handler.removeMessages(WHAT_HIDE);
//         }
//         if (mWindowManager != null && mContentView != null) {
//             // 移除悬浮窗口
//             mWindowManager.removeView(mContentView);
//         }
//     }
//
//     public boolean isExistence() {
//         return mContentView == null ? false : true;
//     }
// }
