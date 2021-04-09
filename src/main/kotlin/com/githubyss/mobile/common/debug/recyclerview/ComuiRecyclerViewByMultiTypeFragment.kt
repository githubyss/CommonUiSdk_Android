package com.githubyss.mobile.common.debug.recyclerview

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.text.TextHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.basemvp.BaseToolbarFragment
import com.githubyss.mobile.common.ui.databinding.ComuiDebugFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recyclerview.template.multi.MultiAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.multi.MultiModel
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * ComuiRecyclerViewByMultiTypeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class ComuiRecyclerViewByMultiTypeFragment : BaseToolbarFragment<ComuiDebugFragmentRecyclerViewBinding>() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiRecyclerViewByMultiTypeFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<MultiModel>()
    private var rvAdapter: MultiAdapter? = null
    
    private val onItemClickListener = object : MultiAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is TextHolder -> {
                    ToastUtils.showMessage(msgStr = "${holder.tvText.text} was selected")
                }
            }
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    // override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //     rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
    //     return rootView
    // }
    
    // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //     super.onViewCreated(view, savedInstanceState)
    // }
    
    override fun init() {
        initData()
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initData() {
        dataList.add(MultiModel("Header", "", false, MultiType.HEADER))
        
        (0 until 10).forEach {
            val dataModel = MultiModel("Row $it", "", false, MultiType.TEXT)
            dataList.add(dataModel)
        }
        
        dataList.add(MultiModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "小傻狗", false, MultiType.TEXT))
        dataList.add(MultiModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "小傻狗", false, MultiType.TEXT))
        dataList.add(MultiModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", false, MultiType.IMAGE))
        
        (11 until 20).forEach {
            val dataModel = MultiModel("Row $it", "", false, MultiType.TEXT)
            dataList.add(dataModel)
        }
        
        dataList.add(MultiModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "小傻狗", false, MultiType.TEXT))
        dataList.add(MultiModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("狗狗", "小傻狗", false, MultiType.TEXT))
        dataList.add(MultiModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", false, MultiType.IMAGE))
        dataList.add(MultiModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", false, MultiType.IMAGE))
        
        dataList.add(MultiModel("Footer", "", false, MultiType.FOOTER))
    }
    
    private fun initView() {
        rvAdapter = MultiAdapter(dataList)
        
        binding.recyclerViewContainer.setHasFixedSize(true)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewContainer.adapter = rvAdapter
        rvAdapter?.setOnItemClickListener(onItemClickListener)
    }
}