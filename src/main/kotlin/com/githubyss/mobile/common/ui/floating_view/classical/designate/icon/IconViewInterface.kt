package com.githubyss.mobile.common.ui.floating_view.classical.designate.icon

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes


/**
 * IconViewInterface
 * 图标 View 接口方法
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/10 16:31:35
 */
interface IconViewInterface {
    fun customIcon(@DrawableRes drawableId: Int)
    fun customIcon(url: String?)
    fun customIcon(drawable: Drawable?)
    fun customIcon(bitmap: Bitmap?)
    fun customView(view: IconViewMagnet?)
    fun customView(@LayoutRes layoutId: Int)
}
