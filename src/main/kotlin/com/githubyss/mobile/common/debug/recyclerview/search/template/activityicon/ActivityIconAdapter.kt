package com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * ActivityIconAdapter
 * 活动图标适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:29:29
 */
class ActivityIconAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.comui_list_item_activity_icon, parent, false)
                view.layoutParams.width = (ScreenUtils.getScreenWidthPx(parent.context) - ScreenUtils.dp2Px(28.0f)) / 4
                ActivityIconHolder(view)
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
            is ActivityIconHolder -> {
                if (dataModel is ActivityIconModel) {
                    val label = dataModel.label
                    val url = dataModel.iconUrl
                    if (StringUtils.isEmpty(label) && StringUtils.isEmpty(url)) {
                        holder.layoutItem.visibility = View.INVISIBLE
                    }
                    
                    GlideUtils.loadImage(dataModel.iconUrl, holder.ivIconImage)
                    // val formatLabel = if (label.length > 4) {
                    //     "${label.substring(0, 4)}..."
                    // } else {
                    //     label
                    // }
                    holder.tvLabel.setText(label, keyList)
                    
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
