package com.githubyss.mobile.common.ui.floating_view.classical.container.system

import android.view.ViewGroup
import androidx.annotation.LayoutRes


/**
 * SystemFloatingInterface
 * 系统级 Floating 接口方法
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/23 16:45:51
 */
interface SystemFloatingInterface<C, D> {
    fun show(): C?
    fun close(): C
    fun layoutParams(params: ViewGroup.LayoutParams?): C
    fun setMovable(isMovable: Boolean): C
    fun customView(view: D?): C
    fun customView(@LayoutRes layoutId: Int): C
    fun refreshViewWhenAppForeground()
    fun refreshViewWhenAppBackground()
}
