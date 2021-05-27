package com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.base.viewbinding.inflateBinding
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
class WealthAccountAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        return WealthAccountHolder(inflateBinding(parent))
        // return inflate(parent)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        when (holder) {
            is WealthAccountHolder -> {
                if (dataModel is WealthAccountModel) {
                    GlideUtils.loadImage(dataModel.imageUrl, holder.binding.imageWealthAccount)
                    holder.binding.textWealthAccountTitle.setText(dataModel.title, keyList)
                    holder.binding.textWealthAccountContent.setText(dataModel.content, keyList)
                    when (dataModel.isFollowed) {
                        IsFollow.TRUE -> {
                            holder.binding.buttonWealthAccountIsFollowed.text = "已关注"
                            holder.binding.buttonWealthAccountIsFollowed.isEnabled = false
                        }
                        IsFollow.FALSE -> {
                            holder.binding.buttonWealthAccountIsFollowed.text = "＋ 关注"
                            holder.binding.buttonWealthAccountIsFollowed.isEnabled = true
                        }
                    }
                    holder.binding.buttonWealthAccountIsFollowed.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                    holder.binding.flexboxItemWealthAccount.setOnClickListener { v ->
                        onItemClickListener?.onItemClick(holder, position, v, dataModel)
                    }
                }
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
