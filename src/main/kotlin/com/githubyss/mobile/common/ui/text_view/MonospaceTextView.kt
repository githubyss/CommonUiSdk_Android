package com.githubyss.mobile.common.ui.text_view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/**
 * MonospaceTextView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 17:38:20
 */
class MonospaceTextView : AppCompatTextView {
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        typeface = Typeface.createFromAsset(context.assets, "font/source_code_pro_regular.ttf")
    }
}
