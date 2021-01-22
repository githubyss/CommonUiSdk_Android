package com.githubyss.mobile.common.ui.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * ComuiMonospaceTextView
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiMonospaceTextView : AppCompatTextView {
    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context ?: return, attrs, defStyleAttr) {
        typeface = Typeface.createFromAsset(context.assets, "font/source_code_pro_regular.ttf")
    }
}
