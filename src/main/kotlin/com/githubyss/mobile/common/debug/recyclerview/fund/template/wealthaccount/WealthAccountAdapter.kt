package com.githubyss.mobile.common.debug.recyclerview.fund.template.wealthaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreModel
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * WealthAccountAdapter
 * 财富号适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:05:23
 */
class WealthAccountAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                WealthAccountHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_wealth_account, parent, false))
            }
            else -> {
                EmptyNoneHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty_none, parent, false))
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
            is WealthAccountHolder -> {
                if (dataModel is WealthAccountModel) {
                    GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                    holder.tvTitle.text = dataModel.title
                    holder.tvContent.text = dataModel.content
                    holder.tglBtnIsFollowed.text = if (dataModel.isFollowed) "已关注" else "＋ 关注"
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
