package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.kit.util.showToast
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ArticleListDataCenter
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemArticleTitle
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemDatetime
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingRecyclerViewAdapter


/**
 * RecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 17:36:32
 */
class RecyclerViewFragment : BaseReflectBindingViewModelToolbarFragment<ComuiFragmentRecyclerViewBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = RecyclerViewFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val recyclerViewVm: RecyclerViewViewModel by viewModels()
    private val bindingRecyclerViewAdapter by lazy { BindingRecyclerViewAdapter() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_title)
    }

    override fun setupData() {
        bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.items)
        bindingRecyclerViewAdapter.onItemClickListener = object : BindingRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(data: BindingAdapterItem) {
                val itemInfo = when (data) {
                    is ItemDatetime -> data.datetime
                    is ItemArticleTitle -> data.title
                    else -> ""
                }
                logD(TAG, "点击了项目 $itemInfo")
                showToast("点击了项目 $itemInfo")
            }
        }
    }

    /**  */
    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    /**  */
    override fun bindXmlData() {
        binding.recyclerViewVm = recyclerViewVm
        binding.bindingRecyclerViewAdapter = bindingRecyclerViewAdapter
    }

    /**  */
    override fun observeViewModelData() {
        this.recyclerViewVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    /**  */
    override fun removeViewModelObserver() {
        this.recyclerViewVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    /**  */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
        }
    }


    /** ****************************** Functions ****************************** */


    /** ****************************** Implementations ****************************** */

    /**  */
    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_recycler_view_multi_type -> {}
            R.id.button_recycler_view_multi_view -> {}
            R.id.button_recycler_view_three_layer -> {}
        }
    }
}
