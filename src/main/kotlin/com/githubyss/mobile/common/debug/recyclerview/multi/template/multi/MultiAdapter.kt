package com.githubyss.mobile.common.debug.recyclerview.multi.template.multi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.multi.template.image.ImageHolder
import com.githubyss.mobile.common.debug.recyclerview.multi.template.text.TextHolder
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.debug.recyclerview.multi.template.emptypage.EmptyPageHolder
import com.githubyss.mobile.common.debug.recyclerview.multi.template.footer.FooterHolder
import com.githubyss.mobile.common.debug.recyclerview.multi.template.header.HeaderHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R


/**
 * SingleSelectionAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 16:24:27
 */
class MultiAdapter constructor(private val dataList: List<MultiModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var selectedPosition = 0
    private var onItemClickListener: OnItemClickListener? = null
    
    
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
                        .inflate(R.layout.comui_list_item_empty, parent, false)
                )
            }
            MultiType.HEADER -> {
                HeaderHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.comui_list_item_header, parent, false))
            }
            MultiType.FOOTER -> {
                FooterHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.comui_list_item_footer, parent, false))
            }
            MultiType.TEXT -> {
                TextHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_text, parent, false))
            }
            MultiType.IMAGE -> {
                ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_image, parent, false))
            }
            else -> {
                EmptyPageHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.comui_list_item_empty, parent, false)
                )
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyPageHolder -> {
            }
            is HeaderHolder -> {
                holder.tvTitle.text = "~~~ HEADER ~~~"
            }
            is FooterHolder -> {
                holder.tvTitle.text = "~~~ FOOTER ~~~"
            }
            is TextHolder -> {
                holder.tvText.text = dataModel.title
                holder.ivSelect.isSelected = dataModel.selectStatus
                holder.ivSelect.visibility = if (dataModel.selectStatus) View.VISIBLE else View.GONE
                holder.layoutItem.setOnClickListener {
                    if (selectedPosition != position) {
                        dataList[selectedPosition].selectStatus = false
                        notifyItemChanged(selectedPosition)
                        
                        dataList[position].selectStatus = true
                        notifyItemChanged(position)
                        
                        onItemClickListener?.onItemClick(holder, position)
                        selectedPosition = position
                    }
                }
            }
            is ImageHolder -> {
                holder.tvImageDescription.text = dataModel.title
                GlideUtils.loadImage(dataModel.imageUrl, holder.ivImage)
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    
    private fun initData() {
        dataList.indices.asSequence().filter { dataList[it].selectStatus }.forEach { selectedPosition = it }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(holder: RecyclerView.ViewHolder, position: Int)
    }
}
