package com.githubyss.mobile.common.debug.recyclerview.multi.page

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.multi.enumeration.MultiType
import com.githubyss.mobile.common.debug.recyclerview.multi.template.multi.MultiAdapter
import com.githubyss.mobile.common.debug.recyclerview.multi.template.multi.MultiModel
import com.githubyss.mobile.common.debug.recyclerview.multi.template.text.TextHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarFragment
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentMultiTypeBinding


/**
 * RecyclerViewByMultiTypeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class RecyclerViewByMultiTypeFragment : BaseToolbarFragment(R.layout.comui_fragment_multi_type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = RecyclerViewByMultiTypeFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentMultiTypeBinding>()
    
    private var dataList = ArrayList<MultiModel>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        initView()
        initData()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_multi_type)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        initMultiRecyclerView(dataList)
    }
    
    private fun initMultiRecyclerView(dataList: MutableList<MultiModel>) {
        val rvAdapter = MultiAdapter(dataList)
        rvAdapter.onItemClickListener = object : MultiAdapter.OnItemClickListener {
            override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
                when (holder) {
                    is TextHolder -> {
                        ToastUtils.showMessage(msgStr = "${holder.tvText.text} was selected")
                    }
                }
            }
        }
        
        binding.recyclerContainer.apply {
            layoutManager = object : LinearLayoutManager(activity) {}.apply {
                orientation = LinearLayoutManager.VERTICAL
                isSmoothScrollbarEnabled = true
                isAutoMeasureEnabled = true
            }
            adapter = rvAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }
    
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
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
}
