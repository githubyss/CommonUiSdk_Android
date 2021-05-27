package com.githubyss.mobile.common.debug.recyclerview.multi.page

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.multi.template.multi.MultiAdapter
import com.githubyss.mobile.common.debug.recyclerview.multi.template.multi.MultiModel
import com.githubyss.mobile.common.debug.recyclerview.multi.template.text.TextHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarFragmentBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * RecyclerViewByMultiTypeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class RecyclerViewByMultiTypeFragment : BaseToolbarFragmentBindingInline(R.layout.comui_fragment_recycler_view) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = RecyclerViewByMultiTypeFragment::class.simpleName ?: "simpleName is null"
    }
    
    private val binding by bindView<ComuiFragmentRecyclerViewBinding>()
    
    private var dataList = ArrayList<MultiModel>()
    private var rvAdapter: MultiAdapter? = null
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        setToolbarTitle(R.string.comui_multi_type_title)
        
        rvAdapter = MultiAdapter(dataList)
        binding.recyclerContainer.setHasFixedSize(true)
        binding.recyclerContainer.layoutManager = LinearLayoutManager(activity)
        binding.recyclerContainer.adapter = rvAdapter
        rvAdapter?.setOnItemClickListener(onItemClickListener)
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
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onItemClickListener = object : MultiAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is TextHolder -> {
                    ToastUtils.showMessage(msgStr = "${holder.tvText.text} was selected")
                }
            }
        }
    }
}
