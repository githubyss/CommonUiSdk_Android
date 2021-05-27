package com.githubyss.mobile.common.debug.recyclerview.search.template.information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * InformationAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:43:33
 */
class InformationAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                InformationHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.comui_list_item_information, parent, false))
            }
            else -> {
                EmptyItemHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.comui_list_item_none, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is InformationHolder -> {
                if (dataModel is InformationModel) {
                    when {
                        StringUtils.isEmpty(dataModel.imageUrl) -> {
                            holder.ivImage.visibility = View.GONE
                        }
                        else -> {
                            holder.ivImage.visibility = View.VISIBLE
                        }
                    }
                    GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                    holder.tvTitle.setText(dataModel.title, keyList)
                    holder.tvContent.setText(dataModel.content, keyList)
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
