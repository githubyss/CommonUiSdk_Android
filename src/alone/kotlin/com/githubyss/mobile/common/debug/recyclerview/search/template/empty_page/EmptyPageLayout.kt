package com.githubyss.mobile.common.debug.recyclerview.search.template.empty_page

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.githubyss.mobile.common.debug.recyclerview.search.bean.HotWordMapBean
import com.githubyss.mobile.common.ui.databinding.ComuiListItemEmptyPageBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.google.android.flexbox.FlexboxLayout


/**
 * EmptyPageLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 23:20:41
 */
class EmptyPageLayout : BaseItemLayout<ComuiListItemEmptyPageBinding> {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = EmptyPageLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataModel: BaseItemModel?, keyWord: String, context: Context, listener: OnLayoutClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onLayoutClickListener = listener
        
        var formatKeyWord = ""
        formatKeyWord = if (keyWord.length > 4) {
            "${keyWord.substring(0, 4)}..."
        } else {
            keyWord
        }
        binding.textEmptyHint.text = "找不到“${formatKeyWord}”相关内容，尝试更换关键词"
        
        val layoutParams = (binding.layoutEmptyWithHotWord.layoutParams ?: return) as FlexboxLayout.LayoutParams
        
        if (dataModel == null) {
            // layoutParams.topMargin = ScreenUtils.dp2Px(123.0f)
            // layoutEmptyWithHotWord?.layoutParams = layoutParams
            binding.flexboxHotWord.visibility = View.GONE
        } else {
            // layoutParams.topMargin = ScreenUtils.dp2Px(20.0f)
            // layoutEmptyWithHotWord?.layoutParams = layoutParams
            binding.flexboxHotWord.visibility = View.VISIBLE
            if (dataModel is HotWordMapBean) {
                val hotWordList = ArrayList<String>()
                for (aWord in dataModel.itemList) {
                    hotWordList.add(aWord.title)
                }
                // binding.flowLayoutHotWord.setFlowData(hotWordList, object : SearchOldFlowLayout.OnSelectItemListener {
                //     override fun onSelect(str: String?, postion: Int) {
                //         onLayoutClickListener?.onClick(postion, binding.flowLayoutHotWord, dataModel)
                //     }
                // })
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
}
