package com.githubyss.mobile.common.ui.time

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import com.githubyss.mobile.common.kit.enumeration.TimeUnit
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.layout.BaseFrameLayoutBindingReflect
import com.githubyss.mobile.common.ui.databinding.ComuiTimeCountdownBinding


/**
 * TimeCountdownView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/23 10:02:00
 */
class TimeCountdownView : BaseFrameLayoutBindingReflect<ComuiTimeCountdownBinding> {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = TimeCountdownView::class.java.simpleName
    }
    
    var remainingMillisecond: Long = 0L
        set(value) {
            startCountdown(value)
        }
    
    var currentTimeStampMillisecond: Long = 0L
        set(value) {
            if (endTimeStampMillisecond > 0) startCountdown(value, endTimeStampMillisecond)
        }
    
    var endTimeStampMillisecond: Long = 0L
        set(value) {
            if (currentTimeStampMillisecond > 0) startCountdown(currentTimeStampMillisecond, value)
        }
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeCountdown)
        remainingMillisecond = typedArray.getString(R.styleable.TimeCountdown_remainingMillisecond)?.toLong() ?: 0L
        typedArray.recycle()
    }
    
    private fun startCountdown(remainingMs: Long) {
        var remainingMs = remainingMs
        val countDownTimer = object : CountDownTimer(remainingMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingMs -= 1000
                refreshTime(remainingMs)
            }
            
            override fun onFinish() {
            }
        }
        countDownTimer.start()
    }
    
    private fun startCountdown(currentTimeStampMs: Long, endTimeStampMs: Long) {
        startCountdown(endTimeStampMs - currentTimeStampMs)
    }
    
    private fun refreshTime(remainingMs: Long) {
        val hour = remainingMs / TimeUnit.HOUR
        val minute = (remainingMs - hour * TimeUnit.HOUR) / TimeUnit.MINUTE
        val second = (remainingMs - hour * TimeUnit.HOUR - minute * TimeUnit.MINUTE) / TimeUnit.SECOND
        binding.tvRemainingHour.text = hour.toString()
        binding.tvRemainingMinute.text = minute.toString()
        binding.tvRemainingSecond.text = second.toString()
    }
}
