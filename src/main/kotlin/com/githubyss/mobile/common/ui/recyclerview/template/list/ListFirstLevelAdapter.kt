package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * ListFirstLevelAdapter
 * 一级列表的适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/29 18:29:50
 */
class ListFirstLevelAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ListFirstLevelAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                ListFirstLevelHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_list, parent, false))
            }
            else -> {
                EmptyItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty_none, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is HeaderSeeMoreHolder -> {
                if (dataModel is HeaderSeeMoreModel) {
                    holder.tvTitle.text = dataModel.header
                    holder.tvSeeMore.visibility = if (dataModel.isSeeMore) View.VISIBLE else View.GONE
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
            is ListFirstLevelHolder -> {
                if (dataModel is ListModel) {
                    val layoutManager = LinearLayoutManager(dataModel.context)
                    layoutManager.orientation = dataModel.orientation
                    holder.listItem.setHasFixedSize(true)
                    holder.listItem.layoutManager = layoutManager
                    holder.listItem.adapter = dataModel.adapter
                    dataModel.adapter.onItemClickListener = dataModel.listener
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
