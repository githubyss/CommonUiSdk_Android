package com.githubyss.mobile.common.ui.floatingview.container.app;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;


/**
 * ContainerFloatingWithinAppInterface
 * <Description> 应用级别悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:04:04
 */
public interface ContainerFloatingWithinAppInterface<C, D, F> {
    
    C show();
    
    void close();
    
    C layoutParams(ViewGroup.LayoutParams params);
    
    C setMovable(boolean isMovable);
    
    C customIcon(Drawable drawable);
    
    C customIcon(Bitmap bitmap);
    
    C customIcon(@DrawableRes int drawableId);
    
    C customView(D viewGroup);
    
    C customView(@LayoutRes int layoutId);
    
    D getDesignatedView();
    
    F getFeatureView();
    
    void attach(Activity activity);
    
    void attach(FrameLayout container);
    
    void detach(Activity activity);
    
    void detach(FrameLayout container);
}
