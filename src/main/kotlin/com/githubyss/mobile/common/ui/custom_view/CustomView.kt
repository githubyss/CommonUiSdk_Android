package com.githubyss.mobile.common.ui.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.githubyss.mobile.common.kit.util.dp2px


class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        canvas.drawCircle(width / 2f, height / 2f, 14.dp2px, paint)
    }
}
