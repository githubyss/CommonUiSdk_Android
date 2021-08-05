package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFundManagerBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType


/**
 * FundManagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:43
 */
class FundManagerAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = FundManagerAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFundManagerBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFundManagerBinding -> {
                    when (dataModel) {
                        is FundManagerModel -> {
                            GlideUtils.loadCircleImage(binding.imageFundManager, binding.flexboxItemFundManager, dataModel.imageUrl)
                            binding.textFundManagerTitle.setText(dataModel.title, keyList)
                            binding.textFundManagerBestReturn.text = dataModel.bestReturn
                            FontManager.replaceFontFromAsset(binding.textFundManagerRiseFallRatio, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                            try {
                                when {
                                    dataModel.riseFallRatio.toFloat() == 0.0f -> {
                                        binding.textFundManagerRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                        binding.textFundManagerRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                                    }
                                    dataModel.riseFallRatio.toFloat() > 0.0f -> {
                                        binding.textFundManagerRiseFallRatio.text = "+${dataModel.riseFallRatio}%"
                                        binding.textFundManagerRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_ff5500))
                                    }
                                    dataModel.riseFallRatio.toFloat() < 0.0f -> {
                                        binding.textFundManagerRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                        binding.textFundManagerRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_00c29e))
                                    }
                                }
                            } catch (e: NumberFormatException) {
                                binding.textFundManagerRiseFallRatio.text = "-.--%"
                                binding.textFundManagerRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                            }
                            binding.textFundManagerDescription.setText(dataModel.description, keyList)
                            binding.flexboxItemFundManager.setOnClickListener { v ->
                                onItemClickListener?.onItemClick(holder, position, v, dataModel)
                            }
                        }
                    }
                }
            }
        }
    }
}
