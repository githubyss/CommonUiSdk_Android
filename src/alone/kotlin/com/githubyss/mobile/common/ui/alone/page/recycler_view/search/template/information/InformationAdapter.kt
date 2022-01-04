package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.information

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemInformationBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * InformationAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:43:33
 */
class InformationAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = InformationAdapter::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemInformationBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemInformationBinding -> {
                    if (dataModel is InformationModel) {
                        when {
                            StringUtils.isEmpty(dataModel.imageUrl) -> {
                                binding.imageInformation.visibility = View.GONE
                            }
                            else -> {
                                binding.imageInformation.visibility = View.VISIBLE
                            }
                        }
                        GlideUtils.loadImage(binding.imageInformation, binding.root, dataModel.imageUrl)
                        binding.textInformationTitle.setText(dataModel.title, keyList)
                        binding.textInformationContent.setText(dataModel.content, keyList)
                        binding.textInformationTime.text = dataModel.time
                        binding.textInformationAuthor.text = dataModel.author
                        binding.flexboxItemInformation.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
