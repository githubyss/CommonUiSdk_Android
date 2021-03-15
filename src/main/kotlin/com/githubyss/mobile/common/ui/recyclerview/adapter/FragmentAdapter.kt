package com.githubyss.mobile.common.ui.recyclerview.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.enumeration.VersionCode
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.fragment.TextFragment
import com.githubyss.mobile.common.ui.recyclerview.model.FragmentModel
import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiType
import com.githubyss.mobile.common.ui.recyclerview.viewholder.EmptyHolder
import com.githubyss.mobile.common.ui.recyclerview.viewholder.FooterHolder
import com.githubyss.mobile.common.ui.recyclerview.viewholder.FragmentHolder
import com.githubyss.mobile.common.ui.recyclerview.viewholder.HeaderHolder


/**
 * FragmentAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:44:10
 */
class FragmentAdapter constructor(private val dataList: List<FragmentModel>, private var fragment: Fragment?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
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
                FragmentHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fragment, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyHolder -> {
            }
            is HeaderHolder -> {
                holder.tvTitle.text = "FRAGMENT HEADER"
            }
            is FooterHolder -> {
                holder.tvTitle.text = "FRAGMENT FOOTER"
            }
            is FragmentHolder -> {
                val fragmentManager = fragment?.childFragmentManager
                val newContainerId = View.generateViewId()
                holder.layoutFrameLayout.id = newContainerId
                fragmentManager?.beginTransaction()?.replace(holder.layoutFrameLayout.id, dataModel.fragment ?: return)?.commit()
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
