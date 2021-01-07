package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;


/**
 * ApiMagnetFloatingViewInterface
 * <Description> 磁力吸附悬浮窗管理器对外接口
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiMagnetFloatingViewInterface {
    
    ApiMagnetFloatingView init(Context context);
    
    ApiMagnetFloatingView show();
    
    void close();
    
    BaseMagnetFloatingView getMagnetView();
    
    void icon(@DrawableRes int resId);
    
    void customView(DesignatedMagnetFloatingView viewGroup);
    
    void customView(@LayoutRes int resource);
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    void listener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener);
    
    void attach(Activity activity);
    
    void attach(FrameLayout container);
    
    void detach(Activity activity);
    
    void detach(FrameLayout container);
}
