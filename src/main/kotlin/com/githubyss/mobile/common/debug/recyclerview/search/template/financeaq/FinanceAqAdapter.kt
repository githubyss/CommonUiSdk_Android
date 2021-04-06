package com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FinanceAqAdapter
 * 财顾问答适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:55:41
 */
class FinanceAqAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                FinanceAqHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_finance_aq, parent, false))
            }
            else -> {
                EmptyItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.comui_recycler_item_empty_none, parent, false)
                )
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is HeaderSeeMoreHolder -> {
                if (dataModel is HeaderSeeMoreModel) {
                    holder.tvTitle.text = dataModel.header
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                    // holder.tvSeeMore.setOnClickListener { v ->
                    //     onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    // }
                }
            }
            is FinanceAqHolder -> {
                if (dataModel is FinanceAqModel) {
                    holder.tvTitle.text = dataModel.title
                    holder.tvContent.text = dataModel.content
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
