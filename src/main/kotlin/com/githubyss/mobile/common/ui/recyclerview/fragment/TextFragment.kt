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
import com.githubyss.mobile.common.ui.recyclerview.model.TextModel
import com.githubyss.mobile.common.ui.recyclerview.viewholder.TextHolder
import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiType
import com.githubyss.mobile.common.ui.recyclerview.adapter.TextAdapter
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * TextFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:35:13
 */
class TextFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = TextFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var dataList = ArrayList<TextModel>()
    private var rvAdapter: TextAdapter? = null
    
    private val onItemClickListener = object : TextAdapter.OnItemClickListener {
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
        // dataList.add(TextModel("", false, MultiType.HEADER))
        (0 until 10).forEach {
            val dataModel = TextModel("喵$it", false, MultiType.TEXT)
            dataList.add(dataModel)
        }
        // dataList.add(TextModel("", false, MultiType.FOOTER))
    }
    
    override fun initView() {
        rvAdapter = TextAdapter(dataList)
        rvAdapter?.onItemClickListener = onItemClickListener
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
