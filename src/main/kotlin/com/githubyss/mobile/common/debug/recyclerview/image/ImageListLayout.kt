package com.githubyss.mobile.common.debug.recyclerview.image

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.text.TextHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.databinding.ComuiRecyclerViewBinding


/**
 * ImageListLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 14:35:16
 */
class ImageListLayout : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ImageListLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var binding: ComuiRecyclerViewBinding
    
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
    
    constructor(dataList: ArrayList<ImageModel>, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        viewContext = context
        this.dataList = dataList
        
        // LayoutInflater.from(viewContext).inflate(R.layout.comui_recycler_view, this)
        binding = ComuiRecyclerViewBinding.inflate(LayoutInflater.from(context), this, true)
        
        rvAdapter = ImageAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        binding.recyclerViewContainer.setHasFixedSize(true)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(viewContext)
        binding.recyclerViewContainer.adapter = rvAdapter
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun initView() {
    }
}
