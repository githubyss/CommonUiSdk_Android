package com.githubyss.mobile.common.ui.recyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recyclerview.model.ImageModel
import com.githubyss.mobile.common.ui.recyclerview.viewholder.TextHolder
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.adapter.ImageAdapter
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.view.*


/**
 * ImageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:15:17
 */
class ImageListView : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ImageListView::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var viewContext: Context? = null
    private var dataList = ArrayList<ImageModel>()
    private var rvAdapter: ImageAdapter? = null
    
    private val onItemClickListener = object : ImageAdapter.OnItemClickListener {
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
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initData() {
        dataList.add(ImageModel("", "", MultiType.HEADER))
        dataList.add(ImageModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", MultiType.IMAGE))
        dataList.add(ImageModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", MultiType.IMAGE))
        dataList.add(ImageModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", MultiType.IMAGE))
        dataList.add(ImageModel("", "", MultiType.FOOTER))
    }
    
    fun initView() {
        LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_list_view, this, true)
        
        rvAdapter = ImageAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(viewContext)
        recyclerView_container.adapter = rvAdapter
    }
}
