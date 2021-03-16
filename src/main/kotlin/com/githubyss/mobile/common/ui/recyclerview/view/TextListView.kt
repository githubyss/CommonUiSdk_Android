package com.githubyss.mobile.common.ui.recyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.model.TextModel
import com.githubyss.mobile.common.ui.recyclerview.viewholder.TextHolder
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.adapter.TextAdapter
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.view.*


/**
 * TextListView
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 10:40:00
 */
class TextListView : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = TextListView::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
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
    
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        viewContext = context
        initData()
        initView()
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    fun initData() {
        dataList.add(TextModel("", false, MultiType.HEADER))
        (0 until 5).forEach {
            val dataModel = TextModel("喵$it", false, MultiType.TEXT)
            dataList.add(dataModel)
        }
        dataList.add(TextModel("", false, MultiType.FOOTER))
    }
    
    fun initView() {
        LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_list_view, this, true)
        
        rvAdapter = TextAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(viewContext)
        recyclerView_container.adapter = rvAdapter
    }
}
