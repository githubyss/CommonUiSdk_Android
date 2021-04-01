package com.githubyss.mobile.common.debug.recyclerview.search.template.information

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * InformationAdapter
 * 资讯适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:43:33
 */
class InformationAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            ItemType.ITEM -> {
                InformationHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_information, parent, false))
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
            is InformationHolder -> {
                if (dataModel is InformationModel) {
                    GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                    holder.tvTitle.text = dataModel.title
                    holder.tvContent.text = dataModel.content
                    holder.tvTime.text = dataModel.time
                    holder.tvAuthor.text = dataModel.author
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
