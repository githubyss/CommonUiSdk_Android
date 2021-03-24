package com.githubyss.mobile.common.ui.recyclerview.itemlist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.view.*


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
    
    private var viewContext: Context? = null
    private var dataList = ArrayList<BaseItemModel>()
    private var adapter: BaseItemAdapter? = null
    
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
            }
        }
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataList: ArrayList<BaseItemModel>, adapter: BaseItemAdapter, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        this.viewContext = context
        this.dataList = dataList
        this.adapter = adapter
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
        LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_list_view, this, true)
        
        adapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(viewContext)
        recyclerView_container.adapter = adapter
    }
}
