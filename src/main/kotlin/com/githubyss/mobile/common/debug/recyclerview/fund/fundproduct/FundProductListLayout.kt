package com.githubyss.mobile.common.debug.recyclerview.fund.fundproduct

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.text.TextHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.view.*


/**
 * FundProductListLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:45
 */
class FundProductListLayout : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = FundProductListLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var viewContext: Context? = null
    private var dataList = ArrayList<FundProductModel>()
    private var rvAdapter: FundProductAdapter? = null
    
    private val onItemClickListener = object : FundProductAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is TextHolder -> {
                    ToastUtils.showMessage(msgStr = "${holder.tvText.text} was selected")
                }
            }
        }
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataList: ArrayList<FundProductModel>, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        viewContext = context
        this.dataList = dataList
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
        LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_list_view, this, true)
        
        rvAdapter = FundProductAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(viewContext)
        recyclerView_container.adapter = rvAdapter
    }
}
