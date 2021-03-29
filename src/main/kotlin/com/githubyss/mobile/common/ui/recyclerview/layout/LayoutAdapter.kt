package com.githubyss.mobile.common.ui.recyclerview.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * LayoutAdapter
 * 区块的适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 17:05:43
 */
class LayoutAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = LayoutAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.EMPTY -> {
                EmptyHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty, parent, false))
            }
            // ItemType.HEADER -> {
            //     HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            // }
            else -> {
                LayoutHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_layout, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyNoneHolder -> {
            }
            // is HeaderSeeMoreHolder -> {
            //     if (dataModel is HeaderSeeMoreModel) {
            //         holder.tvTitle.text = dataModel.header
            //         holder.layoutItem.setOnClickListener { v ->
            //             onItemClickListener?.onItemClick(holder, position, v, dataModel)
            //         }
            //         holder.tvSeeMore.setOnClickListener { v ->
            //             onItemClickListener?.onItemClick(holder, position, v, dataModel)
            //         }
            //     }
            // }
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
