package com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.recyclerview.template.header.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.header.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundProductAdapter
 * 基金产品适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:41
 */
class FundProductAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                FundProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_product, parent, false))
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
                    holder.tvSeeMore.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
            is FundProductHolder -> {
                if (dataModel is FundProductModel) {
                    holder.tvTitle.text = dataModel.title
                    holder.tvRiseFallRatio.text = dataModel.riseFallRatio
                    holder.tvCode.text = dataModel.code
                    holder.tvRisk.text = dataModel.risk
                    holder.tvClassify.text = dataModel.classify
                    holder.tvRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                    holder.tvFollowCount.text = dataModel.followCount
                    holder.tglBtnIsFollowed.text = if (dataModel.isFollowed) "已添加" else "＋自选"
                    holder.tglBtnIsFollowed.isChecked = dataModel.isFollowed
                    holder.tglBtnIsFollowed.setOnCheckedChangeListener { buttonView, isChecked ->
                        onItemClickListener?.onItemClick(holder, position, buttonView, dataModel)
                    }
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
