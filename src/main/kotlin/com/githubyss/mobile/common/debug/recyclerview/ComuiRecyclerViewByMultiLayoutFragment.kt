package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.debug.recyclerview.image.ImageListLayout
import com.githubyss.mobile.common.debug.recyclerview.image.ImageModel
import com.githubyss.mobile.common.debug.recyclerview.text.TextListLayout
import com.githubyss.mobile.common.debug.recyclerview.text.TextModel
import com.githubyss.mobile.common.debug.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recyclerview.layout.LayoutModel
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiRecyclerViewByMultiLayoutFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class ComuiRecyclerViewByMultiLayoutFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiRecyclerViewByMultiLayoutFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<LayoutModel>()
    private var rvAdapter: LayoutAdapter? = null
    
    private val onItemClickListener = object : LayoutAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int) {
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
        val imageList = ArrayList<ImageModel>()
        imageList.add(ImageModel("", "", MultiType.HEADER))
        imageList.add(ImageModel("狗狗", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", MultiType.IMAGE))
        imageList.add(ImageModel("变色龙", "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", MultiType.IMAGE))
        imageList.add(ImageModel("猫猫", "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", MultiType.IMAGE))
        imageList.add(ImageModel("", "", MultiType.FOOTER))
        
        val textList = ArrayList<TextModel>()
        textList.add(TextModel("", false, MultiType.HEADER))
        (0 until 5).forEach {
            val textModel = TextModel("喵$it", false, MultiType.TEXT)
            textList.add(textModel)
        }
        textList.add(TextModel("", false, MultiType.FOOTER))
        
        dataList.add(LayoutModel(ImageListLayout(imageList, activity?.baseContext ?: ComuiApplication.instance), MultiType.VIEW))
        dataList.add(LayoutModel(TextListLayout(textList, activity?.baseContext ?: ComuiApplication.instance), MultiType.VIEW))
        dataList.add(LayoutModel(ImageListLayout(imageList, activity?.baseContext ?: ComuiApplication.instance), MultiType.VIEW))
    }
    
    override fun initView() {
        rvAdapter = LayoutAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
