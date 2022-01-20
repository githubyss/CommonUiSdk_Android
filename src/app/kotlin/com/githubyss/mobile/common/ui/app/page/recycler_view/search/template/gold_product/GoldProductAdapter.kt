package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.gold_product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.base.view_binding.recycler_view.inline.BaseInlineBindingViewHolder
import com.githubyss.mobile.common.kit.base.view_binding.recycler_view.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemGoldProductBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * GoldProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:32
 */
class GoldProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = GoldProductAdapter::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemGoldProductBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseInlineBindingViewHolder<*>) {
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
