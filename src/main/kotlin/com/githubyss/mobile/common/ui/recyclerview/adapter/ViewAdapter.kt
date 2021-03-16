package com.githubyss.mobile.common.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.model.ViewModel
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.viewholder.*


/**
 * ViewAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 17:05:43
 */
class ViewAdapter constructor(private val dataList: List<ViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
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
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MultiType.EMPTY -> {
                EmptyHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty, parent, false))
            }
            MultiType.HEADER -> {
                HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header, parent, false))
            }
            MultiType.FOOTER -> {
                FooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_footer, parent, false))
            }
            else -> {
                ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_frame_layout, parent, false))
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
            is ViewHolder -> {
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
