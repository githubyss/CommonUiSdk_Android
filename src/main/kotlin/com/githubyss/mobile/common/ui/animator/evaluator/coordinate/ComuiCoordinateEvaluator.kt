package com.githubyss.mobile.common.ui.animator.evaluator.coordinate

import android.animation.TypeEvaluator

/**
 * ComuiCoordinateEvaluator
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiCoordinateEvaluator : TypeEvaluator<ComuiCoordinate> {
    override fun evaluate(fraction: Float, startValue: ComuiCoordinate, endValue: ComuiCoordinate): ComuiCoordinate {
        val x = startValue.x + fraction * (endValue.x - startValue.x)
        val y = startValue.y + fraction * (endValue.y - startValue.y)

        return ComuiCoordinate(x, y)
    }
}
