package com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundProductAdapter
 * 基金产品适配器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:41
 */
class FundProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                FundProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_fund_product, parent, false))
            }
            else -> {
                EmptyItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.comui_list_item_none, parent, false)
                )
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is FundProductHolder -> {
                if (dataModel is FundProductModel) {
                    holder.tvTitle.setText(dataModel.title, keyList)
                    FontManager.replaceFontFromAsset(holder.tvRiseFallRatio, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                    try {
                        when {
                            dataModel.riseFallRatio.toFloat() == 0.0f -> {
                                holder.tvRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                holder.tvRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                            }
                            dataModel.riseFallRatio.toFloat() > 0.0f -> {
                                holder.tvRiseFallRatio.text = "+${dataModel.riseFallRatio}%"
                                holder.tvRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_ff5500))
                            }
                            dataModel.riseFallRatio.toFloat() < 0.0f -> {
                                holder.tvRiseFallRatio.text = "${dataModel.riseFallRatio}%"
                                holder.tvRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_00c29e))
                            }
                        }
                    } catch (e: NumberFormatException) {
                        holder.tvRiseFallRatio.text = "-.--%"
                        holder.tvRiseFallRatio.setTextColor(ResourceUtils.getColor(R.color.comres_color_999999))
                    }
                    holder.tvCode.text = dataModel.code
                    holder.tvRisk.text = dataModel.risk
                    holder.tvClassify.text = dataModel.classify
                    holder.tvRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                    // holder.tvFollowCount.text = dataModel.followCount
                    when (dataModel.isFollowed) {
                        IsFollow.TRUE -> {
                            holder.tglBtnIsFollowed.text = "已添加"
                            holder.tglBtnIsFollowed.isEnabled = false
                        }
                        IsFollow.FALSE -> {
                            holder.tglBtnIsFollowed.text = "＋自选"
                            holder.tglBtnIsFollowed.isEnabled = true
                        }
                    }
                    holder.tglBtnIsFollowed.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                    holder.layoutItem.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
