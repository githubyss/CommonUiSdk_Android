package com.githubyss.mobile.common.ui.app.page.animate.property

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.view.View
import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.animator.evaluator.coordinate.PointEvaluator
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentAnimatePropertyBinding
import com.githubyss.mobile.common.ui.floatingwindow.ComuiAutoHideFloatingWindow


/**
 * PropertyAnimatorFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:10:20
 */
class PropertyAnimatorFragment : BaseReflectBindingToolbarFragment<ComuiFragmentAnimatePropertyBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = PropertyAnimatorFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private var moveValueAnimator: ValueAnimator? = null
    private var scaleValueAnimator: ValueAnimator? = null
    private var moveObjectAnimator: ObjectAnimator? = null
    private var scaleObjectAnimator: ObjectAnimator? = null


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        binding.btnMove.setOnClickListener(onClickListener)
        binding.btnScale.setOnClickListener(onClickListener)
        binding.btnShow.setOnClickListener(onClickListener)
        binding.btnHide.setOnClickListener(onClickListener)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_property_animator_title)
    }

    /**  */
    override fun onDestroy() {
        super.onDestroy()
        stopAnimator()
        ComuiAutoHideFloatingWindow.instance.hide()
    }

    /**  */
    override fun stopAnimator() {
        moveValueAnimator?.cancel()
        scaleValueAnimator?.cancel()
        moveObjectAnimator?.cancel()
        scaleObjectAnimator?.cancel()
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun Point.scale(scale: Int): Point {
        this.x *= scale
        this.y *= scale
        return Point(x, y)
    }

    /**  */
    private fun startMoveValueAnimator(view: View) {
        val originX = view.x.toInt()
        val originY = view.y.toInt()
        val originPoint = Point(originX, originY)
        // val targetedPoint = Point(originX, originY + 500)
        val targetedPoint = originPoint.scale(2)
        moveValueAnimator = ValueAnimator.ofObject(PointEvaluator(), originPoint, targetedPoint)
        moveValueAnimator?.duration = 1000
        moveValueAnimator?.startDelay = 0
        moveValueAnimator?.repeatCount = 1
        moveValueAnimator?.repeatMode = ValueAnimator.REVERSE
        moveValueAnimator?.addUpdateListener { animation ->
            val currentValue = animation.animatedValue as Point
            view.y = currentValue.y.toFloat()
            view.requestLayout()

            logcatViewProperty(view)
        }
        moveValueAnimator?.start()
    }

    /**  */
    private fun startScaleValueAnimator(view: View) {
        val originalWidth = view.layoutParams.width
        val targetedWidth = originalWidth * 2
        scaleValueAnimator = ValueAnimator.ofInt(originalWidth, targetedWidth)
        scaleValueAnimator?.startDelay = 0
        scaleValueAnimator?.duration = 1000
        scaleValueAnimator?.repeatCount = 1
        scaleValueAnimator?.repeatMode = ValueAnimator.REVERSE
        scaleValueAnimator?.addUpdateListener { animation ->
            val currentValue = animation.animatedValue as Int
            view.layoutParams.width = currentValue
            view.requestLayout()

            logcatViewProperty(view)
        }
        scaleValueAnimator?.start()
    }

    /**  */
    private fun startMoveObjectAnimator(view: View?) {
        moveObjectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0F, 300F, 0F)
        // moveObjectAnimator = ObjectAnimator.ofFloat(view, "x", 0F, 300F)
        moveObjectAnimator?.startDelay = 0
        moveObjectAnimator?.duration = 1000
        moveObjectAnimator?.repeatCount = ObjectAnimator.INFINITE
        moveObjectAnimator?.repeatMode = ObjectAnimator.REVERSE
        moveObjectAnimator?.start()
    }

    /**  */
    private fun startScaleObjectAnimator(view: View?) {
        val originalWidth = view?.layoutParams?.width ?: return
        val targetedWidth = originalWidth * 2
        scaleObjectAnimator = ObjectAnimator.ofFloat(view, "rotation", 0F, 360F)
        scaleObjectAnimator?.startDelay = 0
        scaleObjectAnimator?.duration = 1000
        scaleObjectAnimator?.repeatCount = ObjectAnimator.INFINITE
        scaleObjectAnimator?.repeatMode = ObjectAnimator.REVERSE
        scaleObjectAnimator?.start()
    }

    /**  */
    private fun logcatViewProperty(view: View) {
        logD(TAG, "{x:${view.x.toInt()}, y:${view.y.toInt()}} {translationX:${view.translationX.toInt()}, translationY:${view.translationY.toInt()}} {top:${view.top}, bottom:${view.bottom}, left:${view.left}, right:${view.right}} {width:${view.width}, height:${view.height}}")
    }


    /** ****************************** Implementations ****************************** */

    /**  */
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btnMove -> {
                startMoveObjectAnimator(binding?.btnAnimator)
            }

            R.id.btnScale -> {
                startScaleObjectAnimator(binding?.btnAnimator)
            }

            R.id.btnShow -> {
                ComuiAutoHideFloatingWindow.instance.show(BaseApplicationHolder.getApp())
            }

            R.id.btnHide -> {
                ComuiAutoHideFloatingWindow.instance.hide()
            }
        }
    }
}
