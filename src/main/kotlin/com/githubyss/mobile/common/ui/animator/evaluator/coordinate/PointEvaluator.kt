package com.githubyss.mobile.common.ui.animator.evaluator.coordinate

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * PointEvaluator
 *
 * @author Ace Yan
 * @github githubyss
 */
class PointEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        val x = startValue.x + fraction * (endValue.x - startValue.x)
        val y = startValue.y + fraction * (endValue.y - startValue.y)

        return Point(x.toInt(), y.toInt())
    }
}
