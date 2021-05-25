package com.githubyss.mobile.common.debug.recyclerview.search.template.loadmore

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.githubyss.mobile.common.ui.databinding.ComuiListItemLoadMoreBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemLayout


/**
 * LoadMoreLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/26 15:16:43
 */
class LoadMoreLayout : BaseItemLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = LoadMoreLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */

    private var binding: ComuiListItemLoadMoreBinding
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, listener: OnLayoutClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onLayoutClickListener = listener
        binding = ComuiListItemLoadMoreBinding.inflate(LayoutInflater.from(context), this, true)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
}
