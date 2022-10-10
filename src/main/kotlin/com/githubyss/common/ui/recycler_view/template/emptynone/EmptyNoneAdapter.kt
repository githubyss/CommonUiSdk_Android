package com.githubyss.common.ui.recycler_view.template.emptynone

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.common.base.recycler_view.binding_inline_root.bindView
import com.githubyss.common.ui.databinding.ComuiListItemEmptyNoneBinding
import com.githubyss.common.ui.recycler_view.base.classical.BaseItemAdapter
import com.githubyss.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.common.ui.recycler_view.enumeration.ItemType


/**
 * EmptyNoneAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/09 16:11:44
 */
class EmptyNoneAdapter constructor(private val dataList: List<BaseItemModel>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = EmptyNoneAdapter::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return bindView<ComuiListItemEmptyNoneBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}
