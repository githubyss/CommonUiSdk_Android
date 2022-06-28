package com.githubyss.mobile.common.ui.text.classical

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView


/**
 * AutofitTextView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 17:37:19
 */
class AutofitTextView : AppCompatTextView {
    
    /** ****************************** Properties ****************************** */
    
    private var textPaint: Paint? = null
    private var textSizeF = 0F
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    
    
    /** ****************************** Override ****************************** */
    
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
