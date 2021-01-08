package com.githubyss.mobile.common.ui.floatingview;


/**
 * BaseAutoShortedViewListener
 * <Description> 自动收起悬浮窗回调监听
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:51:25
 */
public interface BaseAutoShortedViewListener {
    
    void onShow();
    
    void onClose();
    
    void onLengthen();
    
    void onShorten();
}
