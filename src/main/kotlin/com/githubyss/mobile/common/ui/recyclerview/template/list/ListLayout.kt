package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubyss.mobile.common.ui.databinding.ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter


/**
 * ListLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 15:50:50
 */
class ListLayout : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ListLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var binding: ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding
    
    // private var layoutContext: Context? = null
    //
    // private var adapter: BaseItemAdapter? = null
    //
    // @RecyclerView.Orientation
    // private var orientation = RecyclerView.VERTICAL
    // var onItemClickListener: BaseItemAdapter.OnItemClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(adapter: BaseItemAdapter, orientation: Int, context: Context, listener: BaseItemAdapter.OnItemClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // this.layoutContext = context
        // this.adapter = adapter
        // this.orientation = orientation
        // this.onItemClickListener = listener
        
        // layoutView = View.inflate(layoutContext, R.layout.comui_recycler_view_bg_white_corner_normal_margin_tiny, this)
        // layoutView = LayoutInflater.from(layoutContext).inflate(R.layout.comui_recycler_view_bg_white_corner_normal_margin_tiny, this, true)
        // View.inflate(layoutContext, R.layout.comui_recycler_view_bg_white_corner_normal_margin_tiny, this)
    
        // val view = LayoutInflater.from(context).inflate(R.layout.comui_recycler_view_bg_white_corner_normal_margin_tiny, this)
        // binding = ComuiRecyclerViewBinding.bind(view)
        binding = ComuiRecyclerViewBgWhiteCornerNormalMarginTinyBinding.inflate(LayoutInflater.from(context), this, true)
        
        adapter.onItemClickListener = listener
        
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = orientation
        binding.recyclerViewContainer.setHasFixedSize(true)
        binding.recyclerViewContainer.layoutManager = layoutManager
        binding.recyclerViewContainer.adapter = adapter
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
