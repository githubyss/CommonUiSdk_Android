package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundManagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:43
 */
class FundManagerAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                FundManagerHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_fund_manager, parent, false))
            }
            else -> {
                EmptyItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_none, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is FundManagerHolder -> {
                when (dataModel) {
                    is FundManagerModel -> {
                        holder.layoutFundManager.visibility = View.VISIBLE
                        GlideUtils.loadCircleImage(dataModel.imageUrl, holder.ivImage)
                        holder.tvTitle.setText(dataModel.title, keyList)
                        holder.tvBestReturn.text = dataModel.bestReturn
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
                        holder.tvDescription.setText(dataModel.description, keyList)
                        holder.layoutItem.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
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
