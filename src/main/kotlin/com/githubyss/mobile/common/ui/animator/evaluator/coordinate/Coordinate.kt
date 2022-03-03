package com.githubyss.mobile.common.ui.animator.evaluator.coordinate

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Coordinate
 * <Description>
 * <Details> Element x and y are in pixels.
 *
 * @author Ace Yan
 * @github githubyss
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Coordinate constructor(var x: Float, var y: Float) : Parcelable {
    /**
     * Coordinate.scale(scale)
     * <Description>
     * <Details>
     *
     * @param scale Scale to scale.
     * @return Scaled coordinate.
     * @author Ace Yan
     * @github githubyss
     */
    fun scale(scale: Float): Coordinate {
        x *= scale
        y *= scale
        return Coordinate(x, y)
    }
}
