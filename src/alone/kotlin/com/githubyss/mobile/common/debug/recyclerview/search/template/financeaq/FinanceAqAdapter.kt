package com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFinanceAqBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType


/**
 * FinanceAqAdapter
 * 财顾问答适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:55:41
 */
class FinanceAqAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = FinanceAqAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFinanceAqBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFinanceAqBinding -> {
                    if (dataModel is FinanceAqModel) {
                        binding.textFinanceAqTitle.setText(dataModel.title, keyList)
                        binding.textFinanceAqContent.setText(dataModel.content, keyList)
                        binding.flexboxItemFinanceAq.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
