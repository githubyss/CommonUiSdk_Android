package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R


/**
 * SingleSelectionAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 16:24:27
 */
class MultiTypeAdapter constructor(private val dataList: List<MultiTypeModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
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
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MultiType.EMPTY -> {
                return MultiEmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_empty, parent, false))
            }
            MultiType.HEADER -> {
                return MultiHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_header, parent, false))
            }
            MultiType.FOOTER -> {
                return MultiFooterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_footer, parent, false))
            }
            MultiType.TEXT -> {
                return MultiTextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_text, parent, false))
            }
            MultiType.IMAGE -> {
                return MultiImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_image, parent, false))
            }
            else -> {
                return MultiEmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_multi_empty, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is MultiEmptyViewHolder -> {
            }
            is MultiHeaderViewHolder -> {
                holder.tvTitle.text = "~~~ HEADER ~~~"
            }
            is MultiFooterViewHolder -> {
                holder.tvTitle.text = "~~~ FOOTER ~~~"
            }
            is MultiTextViewHolder -> {
                holder.tvTitle.text = dataModel.title
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
            is MultiImageViewHolder -> {
                holder.tvImageTitle.text = dataModel.title
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
