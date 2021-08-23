package com.githubyss.mobile.common.ui.floating_view.container.app

import android.app.Activity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes


/**
 * AppFloatingInterface
 * 应用级 Floating 接口方法
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/20 17:04:49
 */
interface AppFloatingInterface<C, D> {
    fun show(): C?
    fun close(): C
    fun layoutParams(params: ViewGroup.LayoutParams?): C
    fun setMovable(isMovable: Boolean): C
    fun customView(view: D?): C
    fun customView(@LayoutRes layoutId: Int): C
    fun attach(activity: Activity?)
    fun attach(container: FrameLayout?)
    fun detach(activity: Activity?)
    fun detach(container: FrameLayout?)
}
