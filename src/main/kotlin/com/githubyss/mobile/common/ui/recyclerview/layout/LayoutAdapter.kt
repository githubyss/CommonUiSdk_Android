package com.githubyss.mobile.common.ui.recyclerview.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.FooterHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderHolder
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * LayoutAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 17:05:43
 */
class LayoutAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.EMPTY -> {
                EmptyHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty, parent, false))
            }
            ItemType.HEADER -> {
                HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header, parent, false))
            }
            ItemType.FOOTER -> {
                FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_footer, parent, false))
            }
            else -> {
                LayoutHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_layout, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is HeaderHolder -> {
                holder.tvTitle.text = dataModel.header
            }
            is FooterHolder -> {
                holder.tvTitle.text = dataModel.footer
            }
            is LayoutHolder -> {
                if (dataModel is LayoutModel) {
                    holder.layoutItem.addView(dataModel.view)
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
