package com.githubyss.mobile.common.ui.recyclerview.template.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.emptypage.EmptyPageHolder
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * LayoutAdapter
 * 区块的适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 17:05:43
 */
class LayoutAdapter constructor(private val dataList: List<BaseItemModel>, @LayoutRes private var layoutId: Int = R.layout.comui_recycler_item_layout_bg_transparent_corner_none_margin_none) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = LayoutAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                LayoutHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
            }
            else -> {
                EmptyPageHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is LayoutHolder -> {
                if (dataModel is LayoutModel) {
                    val view = dataModel.view ?: return
                    if (view.parent != null) {
                        (view.parent as ViewGroup).removeAllViews()
                    }
                    holder.layoutItem.removeAllViews()
                    holder.layoutItem.addView(view)
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
