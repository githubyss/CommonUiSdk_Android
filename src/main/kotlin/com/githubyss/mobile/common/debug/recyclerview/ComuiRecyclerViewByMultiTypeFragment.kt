package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.debug.recyclerview.text.TextHolder
import com.githubyss.mobile.common.debug.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiTypeAdapter
import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiTypeModel
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiRecyclerViewByMultiTypeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class ComuiRecyclerViewByMultiTypeFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiRecyclerViewByMultiTypeFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<MultiTypeModel>()
    private var rvAdapter: MultiTypeAdapter? = null
    
    private val onItemClickListener = object : MultiTypeAdapter.OnItemClickListener {
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
        dataList.add(MultiTypeModel("Header", "", false, MultiType.HEADER)
        )
        
        (0 until 10).forEach {
            val dataModel = MultiTypeModel("Row $it", "", false, MultiType.TEXT)
            dataList.add(dataModel)
        }
        
        dataList.add(MultiTypeModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", false, MultiType.IMAGE)
        )
        dataList.add(MultiTypeModel("狗狗", "小傻狗", false, MultiType.TEXT)
        )
        dataList.add(MultiTypeModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", false, MultiType.IMAGE)
        )
        dataList.add(MultiTypeModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", false, MultiType.IMAGE)
        )
        
        dataList.add(MultiTypeModel("Footer", "", false, MultiType.FOOTER)
        )
    }
    
    override fun initView() {
        rvAdapter = MultiTypeAdapter(dataList)
        rvAdapter?.setOnItemClickListener(onItemClickListener)
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}