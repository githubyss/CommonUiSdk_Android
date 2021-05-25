package com.githubyss.mobile.common.ui.recyclerview.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout


/**
 * BaseItemLayout
 * 基础布局
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 18:32:46
 */
open class BaseItemLayout : FrameLayout {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var onLayoutClickListener: OnLayoutClickListener? = null
    
    
    /** ********* ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnLayoutClickListener {
        fun onClick(position: Int, view: View?, data: BaseItemModel)
    }
}
