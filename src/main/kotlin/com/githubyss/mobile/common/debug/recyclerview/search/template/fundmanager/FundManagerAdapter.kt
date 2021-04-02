package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductModel
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundManagerAdapter
 * 热门经理人适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:43
 */
class FundManagerAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                FundManagerHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_manager, parent, false))
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
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                    // holder.tvSeeMore.setOnClickListener { v ->
                    //     onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    // }
                }
            }
            is FundManagerHolder -> {
                when (dataModel) {
                    is FundManagerModel -> {
                        holder.layoutFundManager.visibility = View.VISIBLE
                        holder.layoutFundProduct.visibility = View.GONE
                        GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                        holder.tvTitle.text = dataModel.title
                        holder.tvBestReturn.text = dataModel.bestReturn
                        holder.tvRiseFallRatio.text = dataModel.riseFallRatio
                        holder.tvDescription.text = dataModel.description
                        holder.layoutItem.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                    is FundProductModel -> {
                        holder.layoutFundManager.visibility = View.GONE
                        holder.layoutFundProduct.visibility = View.VISIBLE
                        holder.tvProductTitle.text = dataModel.title
                        holder.tvProductRiseFallRatio.text = dataModel.riseFallRatio
                        holder.tvProductCode.text = dataModel.code
                        holder.tvProductRisk.text = dataModel.risk
                        holder.tvProductClassify.text = dataModel.classify
                        holder.tvProductRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                        holder.tvProductFollowCount.text = dataModel.followCount
                        holder.tglBtnProductIsFollowed.text = if (dataModel.isFollowed) "已添加" else "＋自选"
                        holder.tglBtnProductIsFollowed.isChecked = dataModel.isFollowed
                        holder.tglBtnProductIsFollowed.setOnCheckedChangeListener { buttonView, isChecked ->
                            onItemClickListener?.onItemClick(holder, position, buttonView, dataModel)
                        }
                        holder.layoutItem.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
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
