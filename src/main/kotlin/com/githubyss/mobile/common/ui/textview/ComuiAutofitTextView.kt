package com.githubyss.mobile.common.ui.textview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView

/**
 * ComuiAutofitTextView
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiAutofitTextView : TextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    private var textPaint: Paint? = null
    private var textSizeF = 0F


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        refitTextContinuously(text.toString(), width)
    }


    private fun refitTextContinuously(tvText: String, tvWidth: Int) {
        if (TextUtils.isEmpty(tvText) || tvWidth <= 0) return

        textSizeF = textSize

        textPaint = Paint()
        textPaint?.set(paint)

        val availableTvWidth = tvWidth - paddingLeft - paddingRight
        val charWidthArray = FloatArray(tvText.length)

        val rectBounds = Rect()
        textPaint?.getTextBounds(tvText, 0, tvText.length, rectBounds)

        var textWidth = rectBounds.width() + 50
        while (textWidth > availableTvWidth) {
            textSizeF--
            textPaint?.textSize = textSizeF
            textWidth = textPaint?.getTextWidths(tvText, charWidthArray) ?: textWidth
        }

        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeF)
    }
}
