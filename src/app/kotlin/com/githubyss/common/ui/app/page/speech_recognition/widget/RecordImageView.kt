package com.githubyss.common.ui.app.page.speech_recognition.widget

import android.animation.Animator
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.view.View
import android.view.LayoutInflater
import com.githubyss.common.ui.R
import android.view.Gravity
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.animation.LinearInterpolator

class RecordImageView(context: Context?, attrs: AttributeSet?) : FrameLayout(context!!, attrs) {
    /**
     * 按钮
     */
    private var iv_btn: ImageView? = null

    /**
     * 循环外圈
     */
    private var iv_out_circle: ImageView? = null

    /**
     * 装波纹的容器
     */
    private var fl_move_circle: FrameLayout? = null

    /**
     * 按钮点击监听
     */
    private var onBtnPressListener: OnBtnPressListener? = null
    fun setOnBtnPressListener(onBtnPressListener: OnBtnPressListener?) {
        this.onBtnPressListener = onBtnPressListener
    }

    private val thisHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> showAnimation()
                2 -> this.removeMessages(1)
            }
        }
    }

    /**
     * 初始化控件个监听按钮
     */
    private fun initView() {
        val v = LayoutInflater.from(context).inflate(R.layout.view_imageview_record, null, false)
        addView(v, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        iv_btn = findViewById<View>(R.id.iv_btn) as ImageView
        iv_out_circle = findViewById<View>(R.id.iv_out_circle) as ImageView
        fl_move_circle = findViewById<View>(R.id.fl_move_circle) as FrameLayout
        //        iv_btn.setOnTouchListener(new OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                switch (event.getAction()) {
        //                    case MotionEvent.ACTION_DOWN://手指按钮
        //                        //手指又按下，取消掉显示循环波纹的消息
        //                        thisHandler.removeMessages(1);
        //                        pressDown();
        //                        break;
        //                    case MotionEvent.ACTION_UP://手指抬起
        //                        //取消掉循环的波纹
        //
        //                        break;
        //                }
        //                return true;
        //            }
        //        });
        startOutCircleAnim()
    }

    val imageButton: View?
        get() = iv_btn

    fun showAnimation() {
        iv_out_circle!!.visibility = GONE
        pressUp()
        addMoveCircle()
        //发送延时消息，3秒后继续显示循环波纹
        thisHandler.sendEmptyMessageDelayed(1, 1000)
        if (onBtnPressListener != null) {
            onBtnPressListener!!.btnClick()
        }
    }

    fun dismissAnimation() {
        thisHandler.sendEmptyMessage(2)
    }

    /**
     * 发散波纹
     */
    private fun addMoveCircle() {
        val imageView = ImageView(context)
        val lp = LayoutParams(dip2px(context, 100f), dip2px(context, 100f))
        lp.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        imageView.layoutParams = lp
        imageView.setImageResource(R.drawable.record_outside_circle)
        fl_move_circle!!.addView(imageView)
        val outCircleAnimX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 5f)
        val outCircleAnimY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 5f)
        val alphaAnim = ObjectAnimator.ofFloat(imageView, "alpha", 0.6f, 0f)
        outCircleAnimX.duration = 1500
        outCircleAnimY.duration = 1500
        alphaAnim.duration = 1500
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(outCircleAnimX, outCircleAnimY, alphaAnim)
        animatorSet.start()
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                //移除掉刚才添加的波纹
                fl_move_circle!!.removeView(imageView)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    fun showOutCircleAnim() {
        iv_out_circle!!.visibility = VISIBLE
        startOutCircleAnim()
    }

    fun hideOutCircleAnim() {
        iv_out_circle!!.visibility = GONE
    }

    /**
     * 开始循环的放大缩小波纹
     */
    private fun startOutCircleAnim() {
        val outCircleAlpha = ObjectAnimator.ofFloat(iv_out_circle, "alpha", 0.2f, 0.6f)
        outCircleAlpha.duration = 1000
        val outCircleAnimX = ObjectAnimator.ofFloat(iv_out_circle, "scaleX", 1f, 1.18f, 1f)
        val outCircleAnimY = ObjectAnimator.ofFloat(iv_out_circle, "scaleY", 1f, 1.18f, 1f)
        outCircleAnimX.duration = 2000
        outCircleAnimY.duration = 2000
        outCircleAnimX.repeatCount = ValueAnimator.INFINITE
        outCircleAnimY.repeatCount = ValueAnimator.INFINITE
        outCircleAnimX.interpolator = LinearInterpolator()
        outCircleAnimY.interpolator = LinearInterpolator()
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(outCircleAnimX, outCircleAnimY, outCircleAlpha)
        animatorSet.start()
    }

    /**
     * 按下按钮
     */
    private fun pressDown() {
        val animatorSet = AnimatorSet()
        val scaleXIn = ObjectAnimator.ofFloat(iv_btn, "scaleX", 1f, 0.94f)
        scaleXIn.duration = 400
        scaleXIn.interpolator = LinearInterpolator()
        val scaleYIn = ObjectAnimator.ofFloat(iv_btn, "scaleY", 1f, 0.94f)
        scaleYIn.duration = 400
        scaleYIn.interpolator = LinearInterpolator()
        animatorSet.playTogether(scaleXIn, scaleYIn)
        animatorSet.start()
    }

    /**
     * 抬起按钮
     */
    private fun pressUp() {
        val animatorSet1 = AnimatorSet()
        val scaleXOut = ObjectAnimator.ofFloat(iv_btn, "scaleX", 0.94f, 1.06f, 1f)
        scaleXOut.duration = 500
        scaleXOut.interpolator = LinearInterpolator()
        val scaleYOut = ObjectAnimator.ofFloat(iv_btn, "scaleY", 0.94f, 1.06f, 1f)
        scaleYOut.duration = 500
        scaleYOut.interpolator = LinearInterpolator()
        animatorSet1.playTogether(scaleXOut, scaleYOut)
        animatorSet1.start()
    }

    /**
     * 按钮点击监听
     */
    interface OnBtnPressListener {
        fun btnClick()
    }

    /**
     * 模拟点击
     */
    fun onceClick() {
        //取消掉循环的波纹
        iv_out_circle!!.visibility = GONE
        pressDown()
        pressUp()
        addMoveCircle()
    }

    companion object {
        /**
         * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }

    init {
        initView()
    }
}