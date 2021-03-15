package com.githubyss.mobile.common.ui.recyclerview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.model.ImageModel
import com.githubyss.mobile.common.ui.recyclerview.viewholder.TextHolder
import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiType
import com.githubyss.mobile.common.ui.recyclerview.adapter.ImageAdapter
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ImageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:15:17
 */
class ImageFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ImageFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
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
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
        return rootView
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    
    override fun initData() {
        dataList.add(ImageModel("", "", MultiType.HEADER))
        dataList.add(ImageModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", MultiType.IMAGE))
        dataList.add(ImageModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", MultiType.IMAGE))
        dataList.add(ImageModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", MultiType.IMAGE))
        dataList.add(ImageModel("", "", MultiType.FOOTER))
    }
    
    override fun initView() {
        rvAdapter = ImageAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
