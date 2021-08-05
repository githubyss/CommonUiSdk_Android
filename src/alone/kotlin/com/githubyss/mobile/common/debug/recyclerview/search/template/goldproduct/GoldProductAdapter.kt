package com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemGoldProductBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType


/**
 * GoldProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:32
 */
class GoldProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = GoldProductAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemGoldProductBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemGoldProductBinding -> {
                    if (dataModel is GoldProductModel) {
                        binding.textGoldProductTitle.setText(dataModel.title, keyList)
                        binding.textGoldProductPrice.text = dataModel.price
                        FontManager.replaceFontFromAsset(binding.textGoldProductPrice, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                        binding.textGoldProductUnit.text = dataModel.unit
                        binding.textGoldProductCode.text = dataModel.code
                        binding.textGoldProductRisk.text = dataModel.risk
                        binding.textGoldProductClassify.text = dataModel.classify
                        binding.textGoldProductPriceTime.text = dataModel.priceTime
                        binding.flexboxItemGoldProduct.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
