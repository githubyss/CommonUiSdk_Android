package com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFundTopicBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundTopicAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:07
 */
class FundTopicAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = FundTopicAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFundTopicBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFundTopicBinding -> {
                    if (dataModel is FundTopicModel) {
                        binding.textFundTopicTitle.setText(dataModel.title, keyList)
                        FontManager.replaceFontFromAsset(binding.textFundTopicRiseFallRatio, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                        try {
                            when {
                                dataModel.riseFallRatio.toFloat() == 0.0f -> {
                                    binding.textFundTopicRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                    binding.textFundTopicRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                                }
                                dataModel.riseFallRatio.toFloat() > 0.0f -> {
                                    binding.textFundTopicRiseFallRatio.text = "+${dataModel.riseFallRatio}%"
                                    binding.textFundTopicRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_ff5500))
                                }
                                dataModel.riseFallRatio.toFloat() < 0.0f -> {
                                    binding.textFundTopicRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                    binding.textFundTopicRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_00c29e))
                                }
                            }
                        } catch (e: NumberFormatException) {
                            binding.textFundTopicRiseFallRatio.text = "-.--%"
                            binding.textFundTopicRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                        }
                        binding.textFundTopicHint.text = dataModel.hint
                        binding.textFundTopicRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                        binding.flexboxItemFundTopic.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
