package com.githubyss.mobile.common.debug.recyclerview.search.template.app_icon

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.base.view_binding.inflateBinding
import com.githubyss.mobile.common.ui.base.view_binding.recycler_view.inline.BaseViewHolderBindingInline
import com.githubyss.mobile.common.ui.databinding.ComuiListItemAppIconBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType


/**
 * AppIconAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:17:59
 */
class AppIconAdapter constructor(private val dataList: List<BaseItemModel>, private val keyList: ArrayList<String>) : BaseItemAdapter<RecyclerView.ViewHolder>(dataList) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = AppIconAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateViewHolder(parent: ViewGroup, @ItemType viewType: Int): RecyclerView.ViewHolder {
        val binding = inflateBinding<ComuiListItemAppIconBinding>(parent)
        val view = binding.root
        view.layoutParams.width = (ScreenUtils.getScreenWidthPx(parent.context) - ScreenUtils.dp2Px(28.0f)) / 4
        return BaseViewHolderBindingInline(binding)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataModel = dataList[position]
        if (holder is BaseViewHolderBindingInline<*>) {
            when (val binding = holder.binding) {
                is ComuiListItemAppIconBinding -> {
                    if (dataModel is AppIconModel) {
                        val label = dataModel.label
                        val url = dataModel.iconUrl
                        if (StringUtils.isEmpty(label) && StringUtils.isEmpty(url)) {
                            binding.flexboxItemAppIcon.visibility = View.INVISIBLE
                        }
                        
                        GlideUtils.loadImage(binding.imageAppIcon, binding.root, dataModel.iconUrl)
                        val formatLabel = if (label.length > 4) {
                            "${label.substring(0, 4)}..."
                        } else {
                            label
                        }
                        binding.textAppIconLabel.setText(formatLabel, keyList)
                        
                        binding.flexboxItemAppIcon.setOnClickListener { v ->
                            onItemClickListener?.onItemClick(holder, position, v, dataModel)
                        }
                    }
                }
            }
        }
    }
}
