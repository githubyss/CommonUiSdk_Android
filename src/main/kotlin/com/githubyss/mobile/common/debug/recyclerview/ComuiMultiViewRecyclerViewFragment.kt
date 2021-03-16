package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.adapter.ViewAdapter
import com.githubyss.mobile.common.ui.recyclerview.model.ViewModel
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import com.githubyss.mobile.common.ui.recyclerview.view.ImageListView
import com.githubyss.mobile.common.ui.recyclerview.view.TextListView
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiMultiViewRecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class ComuiMultiViewRecyclerViewFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiMultiViewRecyclerViewFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<ViewModel>()
    private var rvAdapter: ViewAdapter? = null
    
    private val onItemClickListener = object : ViewAdapter.OnItemClickListener {
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
        dataList.add(ViewModel(ImageListView(activity?.baseContext ?: ComuiApplication.instance), MultiType.VIEW))
        dataList.add(ViewModel(TextListView(activity?.baseContext ?: ComuiApplication.instance), MultiType.VIEW))
    }
    
    override fun initView() {
        rvAdapter = ViewAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
