package com.githubyss.mobile.common.ui.recyclerview.layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.FooterHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderHolder
import com.githubyss.mobile.common.ui.R


/**
 * LayoutAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 17:05:43
 */
class LayoutAdapter constructor(private val dataList: List<LayoutModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
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
                LayoutType.EMPTY
            }
            else -> {
                dataList[position].type
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, @LayoutType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LayoutType.EMPTY -> {
                EmptyHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty, parent, false))
            }
            LayoutType.HEADER -> {
                HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            LayoutType.FOOTER -> {
                FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_footer, parent, false))
            }
            else -> {
                LayoutHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_layout, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is HeaderHolder -> {
                holder.tvTitle.text = "FRAGMENT HEADER"
            }
            is FooterHolder -> {
                holder.tvTitle.text = "FRAGMENT FOOTER"
            }
            is LayoutHolder -> {
                holder.layoutItem.addView(dataModel.view)
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
