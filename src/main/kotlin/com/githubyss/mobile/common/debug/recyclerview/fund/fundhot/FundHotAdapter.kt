package com.githubyss.mobile.common.debug.recyclerview.fund.fundhot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundHotAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:07
 */
class FundHotAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.EMPTY -> {
                EmptyNoneHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty_none, parent, false))
            }
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            else -> {
                FundHotHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_hot, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyNoneHolder -> {
            }
            is HeaderSeeMoreHolder -> {
                holder.tvTitle.text = dataModel.header
                holder.layoutItem.setOnClickListener { v ->
                    onItemClickListener?.onItemClick(holder, position, v)
                }
                holder.tvSeeMore.setOnClickListener { v ->
                    onItemClickListener?.onItemClick(holder, position, v)
                }
            }
            is FundHotHolder -> {
                if (dataModel is FundHotModel) {
                    holder.tvTitle.text = dataModel.title
                    holder.tvRiseFallRatio.text = dataModel.riseFallRatio
                    holder.tvHint.text = dataModel.hint
                    holder.tvRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                }
                holder.layoutItem.setOnClickListener { v ->
                    onItemClickListener?.onItemClick(holder, position, v)
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
