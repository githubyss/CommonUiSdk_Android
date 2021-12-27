package com.githubyss.mobile.common.ui.text_view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/**
 * KeyWordHighlightTextView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/28 10:01:50
 */
class KeyWordHighlightTextView : AppCompatTextView {
    
    /** ****************************** Properties ****************************** */
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    
    
    /** ****************************** Functions ****************************** */
    
    fun setText(text: String?, vararg keywords: String?, color: Int? = null, vague: Boolean = false) {
        setText(KeyWordHighlightUtils.matcherText(text, keywords = *keywords, color, vague))
    }
    
    fun setText(text: String?, keywords: List<String?>?, color: Int? = null, vague: Boolean = false) {
        setText(KeyWordHighlightUtils.matcherText(text, keywords, color, vague))
    }
    
    fun setText(text: String?, keywordColorMap: Map<String?, Int?>?, vague: Boolean = false) {
        setText(KeyWordHighlightUtils.matcherText(text, keywordColorMap, vague))
    }
}