package com.githubyss.mobile.common.ui.alone.recyclerview.search.template.insurance_product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemInsuranceProductBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


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
        val TAG: String = InsuranceProductAdapter::class.java.simpleName
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
