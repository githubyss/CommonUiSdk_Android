package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.wealth_account

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.databinding.ComuiListItemWealthAccountBinding
import com.githubyss.mobile.common.ui.recycler_view.base.binding_inline.BaseInlineBindingViewHolder
import com.githubyss.mobile.common.ui.recycler_view.base.binding_inline.inflate
import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * WealthAccountAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:05:23
 */
class WealthAccountAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ****************************** Override ****************************** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return inflate<ComuiListItemWealthAccountBinding>(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseInlineBindingViewHolder<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemWealthAccountBinding -> {
                    if (dataModel is WealthAccountModel) {
                        GlideUtils.loadImage(binding.imageWealthAccount, binding.root, dataModel.imageUrl)
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
