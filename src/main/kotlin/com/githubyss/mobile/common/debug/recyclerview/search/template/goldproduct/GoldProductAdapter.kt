package com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.kit.manager.font.FontConfig
import com.githubyss.mobile.common.kit.manager.font.FontManager
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * GoldProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:32
 */
class GoldProductAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.ITEM -> {
                GoldProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_list_item_gold_product, parent, false))
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
            is GoldProductHolder -> {
                if (dataModel is GoldProductModel) {
                    holder.tvTitle.setText(dataModel.title, keyList)
                    holder.tvPrice.text = dataModel.price
                    FontManager.replaceFontFromAsset(holder.tvPrice, FontConfig.FontPath.DIN_NEXT_LT_PRO_MEDIUM)
                    holder.tvUnit.text = dataModel.unit
                    holder.tvCode.text = dataModel.code
                    holder.tvRisk.text = dataModel.risk
                    holder.tvClassify.text = dataModel.classify
                    holder.tvPriceTime.text = dataModel.priceTime
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
