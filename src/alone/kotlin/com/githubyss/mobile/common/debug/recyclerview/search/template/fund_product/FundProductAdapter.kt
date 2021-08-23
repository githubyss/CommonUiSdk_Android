package com.githubyss.mobile.common.debug.recyclerview.search.template.fund_product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFundProductBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * FundProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:41
 */
class FundProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = FundProductAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFundProductBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFundProductBinding -> {
                    if (dataModel is FundProductModel) {
                        // binding.fundProduct = dataModel
                        // binding.executePendingBindings()
                        binding.textFundProductTitle.setText(dataModel.title, keyList)
                        FontManager.replaceFontFromAsset(binding.textFundProductRiseFallRatio, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                        try {
                            when {
                                dataModel.riseFallRatio.toFloat() == 0.0f -> {
                                    binding.textFundProductRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                    binding.textFundProductRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                                }
                                dataModel.riseFallRatio.toFloat() > 0.0f -> {
                                    binding.textFundProductRiseFallRatio.text = "+${dataModel.riseFallRatio}%"
                                    binding.textFundProductRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_ff5500))
                                }
                                dataModel.riseFallRatio.toFloat() < 0.0f -> {
                                    binding.textFundProductRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                    binding.textFundProductRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_00c29e))
                                }
                            }
                        } catch (e: NumberFormatException) {
                            binding.textFundProductRiseFallRatio.text = "-.--%"
                            binding.textFundProductRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                        }
                        binding.textFundProductCode.text = dataModel.code
                        binding.textFundProductRisk.text = dataModel.risk
                        binding.textFundProductClassify.text = dataModel.classify
                        binding.textFundProductRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                        // tvFollowCount.text = dataModel.followCount
                        when (dataModel.isFollowed) {
                            IsFollow.TRUE -> {
                                binding.buttonFundProductIsFollowed.text = "已添加"
                                binding.buttonFundProductIsFollowed.isEnabled = false
                            }
                            IsFollow.FALSE -> {
                                binding.buttonFundProductIsFollowed.text = "＋自选"
                                binding.buttonFundProductIsFollowed.isEnabled = true
                            }
                        }
                        binding.buttonFundProductIsFollowed.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                        binding.flexboxItemFundProduct.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
