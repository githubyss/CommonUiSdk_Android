package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.adapter.FragmentAdapter
import com.githubyss.mobile.common.ui.recyclerview.fragment.ImageFragment
import com.githubyss.mobile.common.ui.recyclerview.fragment.TextFragment
import com.githubyss.mobile.common.ui.recyclerview.model.FragmentModel
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiMultiFragmentRecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:22:07
 */
class ComuiMultiFragmentRecyclerViewFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiMultiFragmentRecyclerViewFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<FragmentModel>()
    private var rvAdapter: FragmentAdapter? = null
    
    private val onItemClickListener = object : FragmentAdapter.OnItemClickListener {
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
        // dataList.add(FragmentModel(null, MultiType.HEADER))
        dataList.add(FragmentModel(TextFragment(), MultiType.FRAGMENT))
        dataList.add(FragmentModel(ImageFragment(), MultiType.FRAGMENT))
        dataList.add(FragmentModel(null, MultiType.FOOTER))
    }
    
    override fun initView() {
        rvAdapter = FragmentAdapter(dataList, this)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
