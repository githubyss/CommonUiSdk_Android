package com.githubyss.mobile.common.ui.recyclerview.template.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * BaseItemAdapter
 * 基础适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 14:41:04
 */
abstract class BaseItemAdapter constructor(private val dataList: List<BaseItemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var onItemClickListener: OnItemClickListener? = null
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun getItemCount(): Int {
        return dataList.size
    }
    
    override fun getItemViewType(position: Int): Int {
        return when {
            dataList.isEmpty() -> {
                ItemType.EMPTY
            }
            else -> {
                dataList[position].type
            }
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View, data: BaseItemModel)
    }
}
