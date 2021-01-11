package com.githubyss.mobile.common.ui.floatingview.container.app;


import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 * ContainerFloatingWithinAppInterface
 * <Description> 应用级别悬浮窗接口方法
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:04:04
 */
public interface ContainerFloatingWithinAppInterface<T> {
    
    T show();
    
    void close();
    
    void layoutParams(ViewGroup.LayoutParams params);
    
    void attach(Activity activity);
    
    void attach(FrameLayout container);
    
    void detach(Activity activity);
    
    void detach(FrameLayout container);
}
