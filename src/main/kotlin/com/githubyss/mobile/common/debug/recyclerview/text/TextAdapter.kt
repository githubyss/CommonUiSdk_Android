package com.githubyss.mobile.common.debug.recyclerview.text

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.template.emptypage.EmptyPageHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.FooterHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderHolder
import com.githubyss.mobile.common.ui.R


/**
 * TextAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:41:07
 */
class TextAdapter constructor(private val dataList: List<TextModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
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
                TextHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_text, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyPageHolder -> {
            }
            is HeaderHolder -> {
                holder.tvTitle.text = "~~~TEXT HEADER ~~~"
            }
            is FooterHolder -> {
                holder.tvTitle.text = "~~~ TEXT FOOTER ~~~"
            }
            is TextHolder -> {
                holder.tvText.text = dataModel.text
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
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initData() {
        dataList.indices.asSequence().filter { dataList[it].selectStatus }.forEach { selectedPosition = it }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(holder: RecyclerView.ViewHolder, position: Int)
    }
}
