package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.faq

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFaqBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * FaqAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:27:19
 */
class FaqAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = FaqAdapter::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFaqBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFaqBinding -> {
                    if (dataModel is FaqModel) {
                        binding.textFaqContent.setText(dataModel.content, keyList)
                        binding.flexboxItemFaq.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
