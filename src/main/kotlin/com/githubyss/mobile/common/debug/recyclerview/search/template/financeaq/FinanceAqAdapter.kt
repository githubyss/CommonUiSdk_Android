package com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FinanceAqAdapter
 * 财顾问答适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:55:41
 */
class FinanceAqAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                FinanceAqHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_finance_aq, parent, false))
            }
            else -> {
                EmptyItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.comui_list_item_none, parent, false)
                )
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is FinanceAqHolder -> {
                if (dataModel is FinanceAqModel) {
                    holder.tvTitle.setText(dataModel.title, keyList)
                    holder.tvContent.setText(dataModel.content, keyList)
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
