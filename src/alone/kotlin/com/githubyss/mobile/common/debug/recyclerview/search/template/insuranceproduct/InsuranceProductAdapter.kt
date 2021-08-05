package com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemInsuranceProductBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType


/**
 * InsuranceProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 16:54:34
 */
class InsuranceProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = InsuranceProductAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemInsuranceProductBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemInsuranceProductBinding -> {
                    if (dataModel is InsuranceProductModel) {
                        GlideUtils.loadImage(binding.imageInsuranceProduct, binding.root, dataModel.imageUrl)
                        binding.textInsuranceProductTitle.setText(dataModel.title, keyList)
                        binding.textInsuranceProductHint.text = dataModel.hint
                        binding.textInsuranceProductPrice.text = dataModel.price
                        binding.flexboxItemInsuranceProduct.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
