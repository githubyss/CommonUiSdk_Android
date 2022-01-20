package com.githubyss.mobile.common.ui.app.page.speech_recognition.util

import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import com.githubyss.mobile.common.ui.R
import java.util.*

class GuideUtil(guideView: View?) {
    private var handler: Handler? = null
    private var guideView: View? = null
    private var guide1: TextView? = null
    private var guide2: TextView? = null
    private var guide3: TextView? = null
    private var guide4: TextView? = null
    private var guide5: TextView? = null
    private val timeInterval = 3000
    private var isStop = true
    var currentIndex = 0
    var animation: ScaleAnimation
    var guideStrList: MutableList<Array<String>> = ArrayList()
    var guideStrints1 = arrayOf("我要转账", "缴电费", "查看收益", "还信用卡", "给妈妈充话费")
    var guideStrints2 = arrayOf("我要理财", "看电影", "今天天气怎么样", "缴学费", "看新闻")
    var guideStrints3 = arrayOf("买火车票", "查看零钱宝收益", "附近的财富中心", "停车缴费", "视频充值")
    var guideStrints4 = arrayOf("股市资讯", "缴水费", "电影推荐", "充流量", "我要借钱")
    fun startGuide() {
        if (isStop) {
            isStop = false
            handler?.post(timerRunnable)
        }
    }

    fun cancel() {
        handler?.removeCallbacks(timerRunnable)
        isStop = true
    }

    var timerRunnable: Runnable = object : Runnable {
        override fun run() {
            if (isStop) {
                return
            }
            updateView()
            handler?.postDelayed(this, timeInterval.toLong())
        }
    }

    private fun updateView() {
        if (currentIndex == 3) {
            //控制数组越界
            currentIndex = 0
        }
        guideView!!.startAnimation(animation)
        if (guide1 != null && guide2 != null && guide3 != null && guide4 != null && guide5 != null) {
            val tempStr = guideStrList[currentIndex]
            guide1?.text = tempStr[0]
            guide2?.text = tempStr[1]
            guide3?.text = tempStr[2]
            guide4?.text = tempStr[3]
            guide5?.text = tempStr[4]
        }
        currentIndex++
    }

    init {
        handler = Handler(guideView!!.context.mainLooper)
        this.guideView = guideView
        if (guideView != null) {
            guide1 = guideView.findViewById<View>(R.id.guide1) as TextView
            guide2 = guideView.findViewById<View>(R.id.guide2) as TextView
            guide3 = guideView.findViewById<View>(R.id.guide3) as TextView
            guide4 = guideView.findViewById<View>(R.id.guide4) as TextView
            guide5 = guideView.findViewById<View>(R.id.guide5) as TextView
        }
        guideStrList = ArrayList()
        guideStrList.add(guideStrints1)
        guideStrList.add(guideStrints2)
        guideStrList.add(guideStrints3)
        guideStrList.add(guideStrints4)
        animation = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                                   Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.duration = 1000 //设置动画持续时间
    }
}
