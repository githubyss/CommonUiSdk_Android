package com.githubyss.mobile.common.ui.recyclerview.template.itemlist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import kotlinx.android.synthetic.main.comui_recycler_list_view.view.*


/**
 * ItemListLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 15:50:50
 */
class ItemListLayout : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ItemListLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    // private var layoutContext: Context? = null
    //
    // private var adapter: BaseItemAdapter? = null
    //
    // @RecyclerView.Orientation
    // private var orientation = RecyclerView.VERTICAL
    // var onItemClickListener: BaseItemAdapter.OnItemClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(adapter: BaseItemAdapter, @RecyclerView.Orientation orientation: Int, context: Context, listener: BaseItemAdapter.OnItemClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // this.layoutContext = context
        // this.adapter = adapter
        // this.orientation = orientation
        // this.onItemClickListener = listener
        
        // layoutView = View.inflate(layoutContext, R.layout.comui_recycler_list_view, this)
        // layoutView = LayoutInflater.from(layoutContext).inflate(R.layout.comui_recycler_list_view, this, true)
        // View.inflate(layoutContext, R.layout.comui_recycler_list_view, this)
        LayoutInflater.from(context).inflate(R.layout.comui_recycler_list_view, this)
        
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = orientation
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = layoutManager
        recyclerView_container.adapter = adapter
        adapter.onItemClickListener = listener
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
