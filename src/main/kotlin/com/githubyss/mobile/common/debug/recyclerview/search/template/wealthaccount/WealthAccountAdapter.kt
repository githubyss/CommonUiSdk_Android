package com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * WealthAccountAdapter
 * 财富号适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:05:23
 */
class WealthAccountAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                WealthAccountHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_fund_wealth_account, parent, false))
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
            is WealthAccountHolder -> {
                if (dataModel is WealthAccountModel) {
                    GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                    holder.tvTitle.setText(dataModel.title, keyList)
                    holder.tvContent.setText(dataModel.content, keyList)
                    when (dataModel.isFollowed) {
                        IsFollow.TRUE -> {
                            holder.tglBtnIsFollowed.text = "已关注"
                            holder.tglBtnIsFollowed.isEnabled = false
                        }
                        IsFollow.FALSE -> {
                            holder.tglBtnIsFollowed.text = "＋ 关注"
                            holder.tglBtnIsFollowed.isEnabled = true
                        }
                    }
                    holder.tglBtnIsFollowed.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
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
