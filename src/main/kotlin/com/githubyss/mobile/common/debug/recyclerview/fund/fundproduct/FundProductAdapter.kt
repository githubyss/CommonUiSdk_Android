package com.githubyss.mobile.common.debug.recyclerview.fund.fundproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.debug.recyclerview.viewholder.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.R


/**
 * FundProductAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:41
 */
class FundProductAdapter constructor(private val dataList: List<FundProductModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var selectedPosition = 0
    var onItemClickListener: OnItemClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        initData()
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun getItemCount(): Int {
        return dataList.size
    }
    
    override fun getItemViewType(position: Int): Int {
        return when {
            dataList.isEmpty() -> {
                FundProductType.EMPTY
            }
            else -> {
                dataList[position].type
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, @FundProductType viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FundProductType.EMPTY -> {
                EmptyNoneHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_empty_none, parent, false))
            }
            FundProductType.HEADER -> {
                HeaderSeeMoreHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_header_see_more, parent, false))
            }
            else -> {
                FundProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_recycler_item_fund_product, parent, false))
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is EmptyNoneHolder -> {
            }
            is HeaderSeeMoreHolder -> {
                holder.tvTitle.text = dataModel.title
            }
            is FundProductHolder -> {
                holder.tvTitle.text = dataModel.title
                holder.tvRiseFallRatio.text = dataModel.riseFallRatio
                holder.tvCode.text = dataModel.code
                holder.tvRisk.text = dataModel.risk
                holder.tvClassify.text = dataModel.classify
                holder.tvRiseFallTimeSpan.text = dataModel.riseFallTimeSpan
                holder.tvFollowCount.text = dataModel.followCount
                holder.tglBtnIsFollowed.text = if (dataModel.isFollowed) "已添加" else "＋自选"
                holder.tglBtnIsFollowed.isChecked = dataModel.isFollowed
                holder.layoutItem.setOnClickListener {
                    onItemClickListener?.onItemClick(holder, position)
                }
            }
            else -> {
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initData() {
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(holder: RecyclerView.ViewHolder, position: Int)
    }
}
