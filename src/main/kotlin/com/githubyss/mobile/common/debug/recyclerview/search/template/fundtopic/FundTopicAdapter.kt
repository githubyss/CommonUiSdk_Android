package com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic

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
 * FundTopicAdapter
 * 热门基金适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:07
 */
class FundTopicAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                FundTopicHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_topic, parent, false))
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
            is FundTopicHolder -> {
                if (dataModel is FundTopicModel) {
                    holder.tvTitle.text = dataModel.title
                    holder.tvRiseFallRatio.text = dataModel.riseFallRatio
                    holder.tvHint.text = dataModel.hint
                    holder.tvRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
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
