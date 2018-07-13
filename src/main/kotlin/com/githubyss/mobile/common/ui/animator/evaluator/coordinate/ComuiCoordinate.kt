package com.githubyss.mobile.common.ui.animator.evaluator.coordinate

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * ComuiCoordinate
 * <Description>
 * <Details> Element x and y are in pixels.
 *
 * @author Ace Yan
 * @github githubyss
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ComuiCoordinate constructor(var x: Float, var y: Float) : Parcelable {
    /**
     * ComuiCoordinate.scale(scale)
     * <Description>
     * <Details>
     *
     * @param scale Scale to scale.
     * @return Scaled coordinate.
     * @author Ace Yan
     * @github githubyss
     */
    fun scale(scale: Float): ComuiCoordinate {
        x *= scale
        y *= scale
        return ComuiCoordinate(x, y)
    }
}
