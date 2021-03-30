package com.githubyss.mobile.common.debug.recyclerview.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.template.emptypage.EmptyPageHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.FooterHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R


/**
 * ImageAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:16:04
 */
class ImageAdapter constructor(private val dataList: List<ImageModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var selectedPosition = 0
    var onItemClickListener: OnItemClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        initData()
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun getItemCount(): Int {
        return dataList.size
    }
    
    override fun getItemViewType(position: Int): Int {
        return when {
            dataList.isEmpty() -> {
                MultiType.EMPTY
            }
            else -> {
                dataList[position].type
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, @MultiType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MultiType.EMPTY -> {
                EmptyPageHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.comui_recycler_item_empty, parent, false)
                )
            }
            MultiType.HEADER -> {
                HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header, parent, false))
            }
            MultiType.FOOTER -> {
                FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_footer, parent, false))
            }
            else -> {
                ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_image, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyPageHolder -> {
            }
            is HeaderHolder -> {
                holder.tvTitle.text = "~~~ IMAGE HEADER ~~~"
            }
            is FooterHolder -> {
                holder.tvTitle.text = "~~~ IMAGE FOOTER ~~~"
            }
            is ImageHolder -> {
                holder.tvImageDescription.text = dataModel.description
                GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
                holder.layoutItem.setOnClickListener {
                    onItemClickListener?.onItemClick(holder, position)
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initData() {
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(holder: RecyclerView.ViewHolder, position: Int)
    }
}
