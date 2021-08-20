package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemEmptyNoneBinding
import com.githubyss.mobile.common.ui.databinding.ComuiListItemHeaderSeeMoreBinding
import com.githubyss.mobile.common.ui.databinding.ComuiRecyclerViewBgTransparentCornerNoneMarginNoneBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.HasMore
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import com.githubyss.mobile.common.ui.recyclerview.template.headerhasmore.HeaderHasMoreBean


/**
 * ListAdapter
 * 一级列表的适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/29 18:29:50
 */
class ListAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = ListAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.HEADER -> {
                inflate<ComuiListItemHeaderSeeMoreBinding>(parent)
            }
            ItemType.ITEM -> {
                inflate<ComuiRecyclerViewBgTransparentCornerNoneMarginNoneBinding>(parent)
            }
            else -> {
                inflate<ComuiListItemEmptyNoneBinding>(parent)
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemHeaderSeeMoreBinding -> {
                    if (dataModel is HeaderHasMoreBean) {
                        binding.textHeaderHasMoreTitle.text = dataModel.title
                        binding.textHeaderHasMoreSeeMore.visibility = if (dataModel.hasMore == HasMore.TRUE) View.VISIBLE else View.GONE
                        binding.flexboxItemHeaderHasMore.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
                is ComuiRecyclerViewBgTransparentCornerNoneMarginNoneBinding -> {
                    if (dataModel is ListModel) {
                        val layoutManager = LinearLayoutManager(dataModel.context)
                        layoutManager.orientation = dataModel.orientation
                        binding.recyclerContainer.setHasFixedSize(true)
                        binding.recyclerContainer.layoutManager = layoutManager
                        binding.recyclerContainer.adapter = dataModel.adapter
                        dataModel.adapter.onItemClickListener = dataModel.listener
                    }
                }
                is ComuiListItemEmptyNoneBinding -> {
                }
            }
        }
    }
}
