package com.githubyss.common.ui.time_view.classical

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import com.githubyss.common.base.layout.frame_layout.binding_reflect.BaseReflectBindingFrameLayout
import com.githubyss.common.kit.enumeration.TimeUnit
import com.githubyss.common.ui.databinding.ComuiTimeCountdownBinding


/**
 * TimeCountdownView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/23 10:02:00
 */
class TimeCountdownView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BaseReflectBindingFrameLayout<ComuiTimeCountdownBinding>(context, attrs, defStyleAttr) {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = TimeCountdownView::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /** 剩余时间 */
    var remainingMillisecond = 0L
        set(value) = startCountdown(value)

    /** 开始时间戳 */
    var startTimeStampMillisecond = 0L
        set(value) {
            if (endTimeStampMillisecond > 0) {
                startCountdown(value, endTimeStampMillisecond)
            }
        }

    /** 停止时间戳 */
    var endTimeStampMillisecond = 0L
        set(value) {
            if (startTimeStampMillisecond > 0) {
                startCountdown(startTimeStampMillisecond, value)
            }
        }


    /** ****************************** Constructor ****************************** */

    /**  */
    init {
        // initAttrs(context, attrs)
    }


    /** ****************************** Functions ****************************** */

    /** 使用 xml 配置控件参数，不优雅，弃用 */
    // private fun initAttrs(context: Context, attrs: AttributeSet?) {
    //     val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ComuiTimeCountdown)
    //     remainingMillisecond = typedArray.getString(R.styleable.ComuiTimeCountdown_remainingMillisecond)?.toLong() ?: 0L
    //     typedArray.recycle()
    // }

    /**  */
    private fun startCountdown(remainingMs: Long) {
        var remaining = remainingMs
        val countDownTimer = object : CountDownTimer(remaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remaining -= 1000
                refreshTime(remaining)
            }

            override fun onFinish() {
            }
        }
        countDownTimer.start()
    }

    /**  */
    private fun startCountdown(startTimeStampMs: Long, endTimeStampMs: Long) {
        startCountdown(endTimeStampMs - startTimeStampMs)
    }

    /**  */
    private fun refreshTime(remainingMs: Long) {
        val hour = remainingMs / TimeUnit.HOUR
        val minute = (remainingMs - hour * TimeUnit.HOUR) / TimeUnit.MINUTE
        val second = (remainingMs - hour * TimeUnit.HOUR - minute * TimeUnit.MINUTE) / TimeUnit.SECOND
        binding.tvRemainingHour.text = hour.toString()
        binding.tvRemainingMinute.text = minute.toString()
        binding.tvRemainingSecond.text = second.toString()
    }
}
