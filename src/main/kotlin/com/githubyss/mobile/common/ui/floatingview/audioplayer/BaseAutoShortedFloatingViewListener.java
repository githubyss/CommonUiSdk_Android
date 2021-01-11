package com.githubyss.mobile.common.ui.floatingview.audioplayer;


/**
 * BaseAutoShortedFloatingViewListener
 * <Description> 自动收起悬浮窗回调监听
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/06 11:07:43
 */
public interface BaseAutoShortedFloatingViewListener {
    
    void onShow();
    
    void onClose();
    
    void onLengthen();
    
    void onShorten();
}
