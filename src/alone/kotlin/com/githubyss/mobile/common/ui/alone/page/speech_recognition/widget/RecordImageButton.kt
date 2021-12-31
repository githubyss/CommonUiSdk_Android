package com.githubyss.mobile.common.ui.alone.page.speech_recognition.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.githubyss.mobile.common.ui.R


class RecordImageButton : RelativeLayout {
    private var voice_img: RecordImageView? = null
    private var voice_btn: View? = null
    private var voice_tip: TextView? = null
    private var mListener: Listener? = null
    private var voice_loading: ProgressBar? = null
    var progressImage = IntArray(11)

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (isInEditMode) {
            return
        }
        initView(context)
    }

    private fun initView(context: Context) {
        // 加载布局文件
        inflate(context, R.layout.view_record_image, this)
        progressImage[0] = R.drawable.voice_btn_bg1
        progressImage[1] = R.drawable.voice_btn_bg2
        progressImage[2] = R.drawable.voice_btn_bg3
        progressImage[3] = R.drawable.voice_btn_bg4
        progressImage[4] = R.drawable.voice_btn_bg5
        progressImage[5] = R.drawable.voice_btn_bg6
        progressImage[6] = R.drawable.voice_btn_bg7
        progressImage[7] = R.drawable.voice_btn_bg8
        progressImage[9] = R.drawable.voice_btn_bg9
        progressImage[9] = R.drawable.voice_btn_bg10
        progressImage[10] = R.drawable.voice_btn_bg11
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val view: View = this
        voice_btn = view.findViewById(R.id.voice_btn)
        voice_img = view.findViewById<View>(R.id.voice_img) as RecordImageView
        voice_img!!.imageButton!!.setOnTouchListener { view, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e("onTouchEvent action down action", action.toString() + "")
                    voice_tip!!.text = "松开完成"
                    voice_img!!.showAnimation()
                    voice_loading!!.visibility = GONE
                    if (mListener != null) {
                        mListener!!.onVoiceStart()
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    Log.e("onTouchEvent action up action", action.toString() + "")
                    voice_tip!!.text = "长按说话"
                    voice_img!!.dismissAnimation()
                    if (mListener != null) {
                        mListener!!.onVoiceEnd()
                    }
                }
            }
            true
        }
        voice_tip = view.findViewById<View>(R.id.voice_tip) as TextView
        voice_loading = view.findViewById<View>(R.id.voice_loading) as ProgressBar
    }

    fun setVolum(volum: Int) {
        var volum = volum
        if (volum > 30) volum = 30
        val volumDouble = Math.ceil((volum / 3).toDouble())
        voice_btn!!.setBackgroundResource(progressImage[volumDouble.toInt()])
    }

    fun showLoading() {
        if (voice_loading!!.visibility == GONE) {
            voice_loading!!.visibility = VISIBLE
        }
        voice_img!!.hideOutCircleAnim()
    }

    fun hideLoading() {
        if (voice_loading!!.visibility == VISIBLE) {
            voice_loading!!.visibility = GONE
        }
        voice_img!!.showOutCircleAnim()
    }

    fun setVoiceListener(listener: Listener?) {
        mListener = listener
    }

    interface Listener {
        fun onVoiceStart()
        fun onVoiceEnd()
    }
}