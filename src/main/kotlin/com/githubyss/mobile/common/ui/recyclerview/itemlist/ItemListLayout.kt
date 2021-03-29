package com.githubyss.mobile.common.ui.recyclerview.itemlist

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
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
    
    private var layoutContext: Context? = null
    
    // private var layoutView: View? = null
    private var dataList = ArrayList<BaseItemModel>()
    private var adapter: BaseItemAdapter? = null
    
    @RecyclerView.Orientation
    private var orientation = RecyclerView.VERTICAL
    var onItemClickListener: BaseItemAdapter.OnItemClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataList: ArrayList<BaseItemModel>, adapter: BaseItemAdapter, @RecyclerView.Orientation orientation: Int, context: Context, listener: BaseItemAdapter.OnItemClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        this.layoutContext = context
        this.dataList = dataList
        this.adapter = adapter
        this.orientation = orientation
        this.onItemClickListener = listener
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
        // layoutView = View.inflate(layoutContext, R.layout.comui_recycler_list_view, this)
        // layoutView = LayoutInflater.from(layoutContext).inflate(R.layout.comui_recycler_list_view, this, true)
        View.inflate(layoutContext, R.layout.comui_recycler_list_view, this)
        // LayoutInflater.from(layoutContext).inflate(R.layout.comui_recycler_list_view, this, true)
        
        adapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(layoutContext)
        layoutManager.orientation = orientation
        recyclerView_container.layoutManager = layoutManager
        recyclerView_container.adapter = adapter
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
}
