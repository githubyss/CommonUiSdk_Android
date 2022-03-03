package com.githubyss.mobile.common.ui.recycler_view.three_layer

import android.os.SystemClock
import android.view.View


/**
 * 防止重复点击，一段时间内只能点击一次，默认1秒
 */
abstract class OnGapClickListener : View.OnClickListener {
    private var mGapTime = DEFAULT_GAP_TIME
    private var lastClickTime: Long = 0
    override fun onClick(v: View) {
        val curClickTime = SystemClock.elapsedRealtime()
        if (lastClickTime == 0L || curClickTime - lastClickTime >= mGapTime) {
            lastClickTime = curClickTime
            onGapClick(v)
        }
    }

    /**
     * 点击事件
     * @param v
     */
    abstract fun onGapClick(v: View?)

    /**
     * 自定义间隔时间，单位毫秒
     * @param gapTime
     */
    fun setGapTime(gapTime: Int) {
        if (gapTime < 0) {
            return
        }
        mGapTime = gapTime
    }

    companion object {
        private const val DEFAULT_GAP_TIME = 1000
    }
}