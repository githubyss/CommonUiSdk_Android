package com.githubyss.mobile.common.debug.recyclerview.fund.activityicon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.fund.header.HeaderSeeMoreHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.header.HeaderSeeMoreModel
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * ActivityIconAdapter
 * 活动图标适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:29:29
 */
class ActivityIconAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
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
                ActivityIconHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_activity_icon, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyNoneHolder -> {
            }
            is HeaderSeeMoreHolder -> {
                if (dataModel is HeaderSeeMoreModel) {
                    holder.tvTitle.text = dataModel.header
                    holder.tvSeeMore.visibility = View.GONE
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
            is ActivityIconHolder -> {
                if (dataModel is ActivityIconModel) {
                    GlideUtils.loadImage(dataModel.iconUrl, holder.ivIconImage)
                    holder.tvLabel.text = dataModel.label
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
