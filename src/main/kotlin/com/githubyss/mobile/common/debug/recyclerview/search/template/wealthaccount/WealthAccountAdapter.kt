package com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.recyclerview.inline.inflate
import com.githubyss.mobile.common.ui.databinding.ComuiListItemFundWealthAccountBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * WealthAccountAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:05:23
 */
class WealthAccountAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemFundWealthAccountBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemFundWealthAccountBinding -> {
                    if (dataModel is WealthAccountModel) {
                        GlideUtils.loadImage(dataModel.imageUrl, binding.imageWealthAccount)
                        binding.textWealthAccountTitle.setText(dataModel.title, keyList)
                        binding.textWealthAccountContent.setText(dataModel.content, keyList)
                        when (dataModel.isFollowed) {
                            IsFollow.TRUE -> {
                                binding.buttonWealthAccountIsFollowed.text = "已关注"
                                binding.buttonWealthAccountIsFollowed.isEnabled = false
                            }
                            IsFollow.FALSE -> {
                                binding.buttonWealthAccountIsFollowed.text = "＋ 关注"
                                binding.buttonWealthAccountIsFollowed.isEnabled = true
                            }
                        }
                        binding.buttonWealthAccountIsFollowed.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                        binding.flexboxItemWealthAccount.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
