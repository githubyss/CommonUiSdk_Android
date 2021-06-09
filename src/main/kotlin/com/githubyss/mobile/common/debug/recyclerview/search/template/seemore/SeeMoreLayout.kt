package com.githubyss.mobile.common.debug.recyclerview.search.template.seemore

import android.content.Context
import android.util.AttributeSet
import com.githubyss.mobile.common.ui.databinding.ComuiListItemSeeMoreBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel


/**
 * SeeMoreLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 18:06:22
 */
class SeeMoreLayout : BaseItemLayout<ComuiListItemSeeMoreBinding> {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = SeeMoreLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataModel: BaseItemModel, keyWord: String, context: Context, listener: OnLayoutClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onLayoutClickListener = listener
        
        var formatKeyWord = ""
        formatKeyWord = if (keyWord.length > 4) {
            "${keyWord.substring(0, 4)}..."
        } else {
            keyWord
        }
        if (dataModel is SeeMoreModel) {
            binding.textSearchWordHint.setText("“${formatKeyWord}”相关的理财内容聚合", formatKeyWord)
            binding.textSeeMore.setOnClickListener { v ->
                onLayoutClickListener?.onClick(0, v, dataModel)
            }
        }
    }
}
