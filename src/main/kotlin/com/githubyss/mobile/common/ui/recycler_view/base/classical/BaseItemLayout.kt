package com.githubyss.mobile.common.ui.recycler_view.base.classical

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.frame_layout.binding_reflect.BaseReflectBindingFrameLayout


/**
 * BaseItemLayout
 * 基础布局
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 18:32:46
 */
abstract class BaseItemLayout<B : ViewBinding> : BaseReflectBindingFrameLayout<B> {
    
    /** ****************************** Properties ****************************** */
    
    var onLayoutClickListener: OnLayoutClickListener? = null
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)
    
    
    /** ****************************** Interface ****************************** */
    
    interface OnLayoutClickListener {
        fun onClick(position: Int, view: View?, data: BaseItemModel)
    }
}