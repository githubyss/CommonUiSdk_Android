package com.githubyss.mobile.common.ui.floatingview.audioplayer;


/**
 * BaseAutoShortedFloatingViewListener
 * <Description> 自动收起悬浮窗对外回调
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/06 11:07:43
 */
public interface BaseAutoShortedFloatingViewListener {
    
    void onClose();
    
    void onRefreshPlayerStyle(boolean isMini);
}
