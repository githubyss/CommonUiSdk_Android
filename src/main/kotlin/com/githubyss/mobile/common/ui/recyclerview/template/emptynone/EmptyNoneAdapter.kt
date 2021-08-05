package com.githubyss.mobile.common.ui.recyclerview.template.emptynone

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemEmptyNoneBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType


/**
 * EmptyNoneAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/09 16:11:44
 */
class EmptyNoneAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = EmptyNoneAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemEmptyNoneBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}
