package com.githubyss.mobile.common.debug.recyclerview.multi.template.text

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.databinding.ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding


/**
 * TextListLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 10:40:00
 */
class TextListLayout : FrameLayout {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = TextListLayout::class.simpleName ?: "simpleName is null"
    }
    
    private var binding: ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding
    
    private var viewContext: Context? = null
    private var dataList = ArrayList<TextModel>()
    private var rvAdapter: TextAdapter? = null
    
    private val onItemClickListener = object : TextAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is TextHolder -> {
                    ToastUtils.showMessage(msgStr = "${holder.tvText.text} was selected")
                }
            }
        }
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataList: ArrayList<TextModel>, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        viewContext = context
        this.dataList = dataList
        
        // LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_view_bg_white_corner_normal_margin_tiny, this)
        binding = ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding.inflate(LayoutInflater.from(context), this, true)
    
        rvAdapter = TextAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        binding.recyclerContainer.setHasFixedSize(true)
        binding.recyclerContainer.layoutManager = LinearLayoutManager(viewContext)
        binding.recyclerContainer.adapter = rvAdapter
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    fun initView() {
    }
}
