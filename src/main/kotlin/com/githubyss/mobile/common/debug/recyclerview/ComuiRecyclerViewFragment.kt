package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.dialog.CommonDialog
import com.githubyss.mobile.common.ui.recyclerview.singleselection.ComuiSingleSelectionAdapter
import com.githubyss.mobile.common.ui.recyclerview.singleselection.ComuiSingleSelectionModel
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiRecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class ComuiRecyclerViewFragment : BaseFragment() {

    /** ********** ********** ********** Companion ********** ********** ********** */

    companion object {
        val TAG = ComuiRecyclerViewFragment::class.simpleName ?: "simpleName is null"
    }


    /** ********** ********** ********** Properties ********** ********** ********** */

    private var rootView: View? = null
    private var dataList = ArrayList<ComuiSingleSelectionModel>()
    private var rvAdapter: ComuiSingleSelectionAdapter? = null

    private val onItemClickListener = object : ComuiSingleSelectionAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            ToastUtils.showMessage(msgStr = "Row $position was selected")
            CommonDialog.showByMsg(
                manager = fragmentManager,
                titleStr = "Test title",
                btnLeftStr = "Cancel",
                btnRightStr = "Confirm",
                onBtnLeftClickListener = View.OnClickListener { ToastUtils.showMessage(msgStr = "Cancel clicked.") },
                onBtnRightClickListener = View.OnClickListener { ToastUtils.showMessage(msgStr = "Confirm clicked.") })
        }
    }


    /** ********* ********** ********** Override ********** ********** ********** */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    override fun initData() {
        (0 until 20).forEach {
            val dataModel = ComuiSingleSelectionModel("Row $it", "content in row $it", false)
            dataList.add(dataModel)
        }

        dataList[0].selectStatus = true
    }

    override fun initView() {
        rvAdapter = ComuiSingleSelectionAdapter(dataList)
        rvAdapter?.setOnItemClickListener(onItemClickListener)

        rvSingleSelection.setHasFixedSize(true)
        rvSingleSelection.layoutManager = LinearLayoutManager(activity)
        rvSingleSelection.adapter = rvAdapter
    }
}