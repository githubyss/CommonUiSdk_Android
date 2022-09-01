package com.githubyss.mobile.common.ui.app.page.animate.property

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.view.View
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.animator.evaluator.coordinate.PointEvaluator
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentAnimatePropertyBinding


/**
 * PropertyAnimatorFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:10:20
 */
class PropertyAnimatorFragment : BaseReflectBindingViewModelToolbarFragment<ComuiFragmentAnimatePropertyBinding>() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = PropertyAnimatorFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private var textAnimator: (View, AnimatorEngine) -> Unit = { _, _ -> }

    /**  */
    private lateinit var animatorTranslation: ValueAnimator
    private lateinit var animatorLength: ValueAnimator
    private lateinit var animatorScaleX: ValueAnimator
    private lateinit var animatorRotation: ValueAnimator
    private lateinit var animatorRotationX: ValueAnimator
    private lateinit var animatorRotationY: ValueAnimator

    private val deltaTranslation = 100F
    private val deltaLength = 100
    private val deltaScale = 0.5F
    private val deltaRotation = 20F


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_property_animator_title)
    }

    override fun bindXmlData() {
        binding.page = this
    }

    /**  */
    override fun stopAnimator() {
        animatorTranslation.cancel()
        animatorLength.cancel()
        animatorScaleX.cancel()
        animatorRotation.cancel()
        animatorRotationX.cancel()
        animatorRotationY.cancel()
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun onTextValueAnimatorClick(view: View) {
        configAnimatorAll(view, AnimatorEngine.Value)
    }

    /**  */
    fun onTextObjectAnimatorClick(view: View) {
        configAnimatorAll(view, AnimatorEngine.Object)
    }

    /**  */
    fun onButtonTranslationClick() {
        refreshText("位移")
        animatorTranslation.start()
    }

    /**  */
    fun onButtonLengthXClick() {
        refreshText("长度 X")
        animatorLength.start()
    }

    /**  */
    fun onButtonScaleXClick() {
        refreshText("伸缩 X")
        animatorScaleX.start()
    }

    /**  */
    fun onButtonRotationClick() {
        refreshText("旋转")
        animatorRotation.start()
    }

    /**  */
    fun onButtonRotationXClick() {
        refreshText("旋转 X")
        animatorRotationX.start()
    }

    /**  */
    fun onButtonRotationYClick() {
        refreshText("旋转 Y")
        animatorRotationY.start()
    }

    /**  */
    fun onButtonPlaySequentiallyClick() {
        startAnimatorSequentially()
    }

    /**  */
    fun onButtonPlayTogetherClick() {
        startAnimatorTogether()
    }

    /**  */
    fun onButtonStopClick() {
        stopAnimator()
    }

    /**  */
    private fun startAnimatorSequentially() {
        // AnimatorSet().apply {
        //     playSequentially(animatorTranslation, animatorLength, animatorScaleX, animatorRotation, animatorRotationX, animatorRotationY)
        //     start()
        // }

        AnimatorSet().apply {
            play(animatorRotationX).with(animatorRotationY)
            play(animatorTranslation).after(3000)
            play(animatorTranslation).before(animatorLength)
            start()
        }
    }

    /**  */
    private fun startAnimatorTogether() {
        AnimatorSet().apply {
            playTogether(animatorTranslation, animatorLength, animatorScaleX, animatorRotation, animatorRotationX, animatorRotationY)
            start()
        }
    }

    /**  */
    private fun configAnimatorAll(view: View, engine: AnimatorEngine) {
        configAnimatorTranslation(view, engine)
        configAnimatorLengthX(view, engine)
        configAnimatorScaleX(view, engine)
        configAnimatorRotation(view, engine)
        configAnimatorRotationX(view, engine)
        configAnimatorRotationY(view, engine)
    }

    /**  */
    private fun configAnimatorTranslation(view: View, engine: AnimatorEngine) {
        val pointX = view.x.toInt()
        val pointY = view.y.toInt()
        val point0 = Point(pointX, pointY)
        val point1 = Point(pointX, pointY + deltaTranslation.toInt())
        val pointArray = arrayOf(point0, point1, point0)

        animatorTranslation = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofObject(PointEvaluator(), *pointArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.y = (animation.animatedValue as Point).y.toFloat()
                }
            }

            AnimatorEngine.Object -> ObjectAnimator.ofObject(view, "translation", PointEvaluator(), *pointArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun configAnimatorLengthX(view: View, engine: AnimatorEngine) {
        val width0 = view.layoutParams.width
        val width1 = width0 + deltaLength
        val widthArray = intArrayOf(width0, width1, width0)

        animatorLength = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofInt(*widthArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.layoutParams.width = animation.animatedValue as Int
                    view.requestLayout()
                }
            }
            AnimatorEngine.Object -> ObjectAnimator.ofInt(view, "width", *widthArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun configAnimatorScaleX(view: View, engine: AnimatorEngine) {
        val scale0 = 1.0F
        val scale1 = scale0 + deltaScale
        val scaleArray = floatArrayOf(scale0, scale1, scale0)

        animatorScaleX = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofFloat(*scaleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.scaleX = animation.animatedValue as Float
                }
            }
            AnimatorEngine.Object -> ObjectAnimator.ofFloat(view, "scaleX", *scaleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun configAnimatorRotation(view: View, engine: AnimatorEngine) {
        val angle0 = view.rotation
        val angle1 = angle0 + deltaRotation
        val angleArray = floatArrayOf(angle0, angle1, angle0)

        animatorRotation = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofFloat(*angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.rotation = animation.animatedValue as Float
                }
            }
            AnimatorEngine.Object -> ObjectAnimator.ofFloat(view, "rotation", *angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun configAnimatorRotationX(view: View, engine: AnimatorEngine) {
        val angle0 = view.rotationX
        val angle1 = angle0 + deltaRotation
        val angleArray = floatArrayOf(angle0, angle1, angle0)

        animatorRotationX = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofFloat(*angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.rotationX = animation.animatedValue as Float
                }
            }
            AnimatorEngine.Object -> ObjectAnimator.ofFloat(view, "rotationX", *angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun configAnimatorRotationY(view: View, engine: AnimatorEngine) {
        val angle0 = view.rotationY
        val angle1 = angle0 + deltaRotation
        val angleArray = floatArrayOf(angle0, angle1, angle0)

        animatorRotationY = when (engine) {
            AnimatorEngine.Value -> ValueAnimator.ofFloat(*angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener { animation ->
                    view.rotationY = animation.animatedValue as Float
                }
            }
            AnimatorEngine.Object -> ObjectAnimator.ofFloat(view, "rotationY", *angleArray).apply {
                defaultAnimatorConfig()
                addUpdateListener {
                }
            }
        }
    }

    /**  */
    private fun refreshText(text: String) {
        binding.textValueAnimator.text = "Value $text"
        binding.textObjectAnimator.text = "Object $text"
    }

    /**  */
    private fun logcatViewProperty(view: View) {
        logD(TAG, "{x:${view.x.toInt()}, y:${view.y.toInt()}} {translationX:${view.translationX.toInt()}, translationY:${view.translationY.toInt()}} {top:${view.top}, bottom:${view.bottom}, left:${view.left}, right:${view.right}} {width:${view.width}, height:${view.height}}")
    }

    /** ******************** Extension ******************** */

    /**  */
    private fun ValueAnimator.defaultAnimatorConfig() {
        startDelay = 0
        duration = 1000
        // repeatCount = 0
        // repeatMode = ValueAnimator.REVERSE
    }

    /**  */
    private fun Point.scale(scale: Int): Point {
        this.x *= scale
        this.y *= scale
        return Point(x, y)
    }


    /** ****************************** Class ****************************** */

    /**  */
    private sealed class AnimatorEngine {
        object Value : AnimatorEngine()
        object Object : AnimatorEngine()
    }
}
