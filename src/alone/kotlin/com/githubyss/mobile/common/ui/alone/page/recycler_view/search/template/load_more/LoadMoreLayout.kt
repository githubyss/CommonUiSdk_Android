package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.load_more

import android.content.Context
import android.util.AttributeSet
import com.githubyss.mobile.common.ui.databinding.ComuiListItemLoadMoreBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemLayout


/**
 * LoadMoreLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/26 15:16:43
 */
class LoadMoreLayout : BaseItemLayout<ComuiListItemLoadMoreBinding> {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = LoadMoreLayout::class.java.simpleName
    }

    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, listener: OnLayoutClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onLayoutClickListener = listener
    }
}
