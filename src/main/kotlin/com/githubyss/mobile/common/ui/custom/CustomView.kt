package com.githubyss.mobile.common.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.githubyss.mobile.common.kit.util.dp2px


val RADIUS: Float = 100.dp2px

class CustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        // canvas.drawLine(100f, 100f, 200f, 200f, paint)
        // canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        canvas.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)
        path.addRect(width / 2f - RADIUS, height / 2f,
                     width / 2f + RADIUS, height / 2f + RADIUS * 2,
                     Path.Direction.CCW)
        path.addCircle(width / 2f, height / 2f, RADIUS * 2, Path.Direction.CCW)
        path.fillType = Path.FillType.EVEN_ODD
    }
}
