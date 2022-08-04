package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.*
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingRecyclerViewAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingRecyclerViewDoubleLayerAdapter


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
    private val bindingRecyclerViewAdapter by lazy { BindingRecyclerViewDoubleLayerAdapter() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_title)
    }

    override fun setupData() {
        when (recyclerViewVm.inEdit.value) {
            true -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombineEditing)
            false -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombine)
            else -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombine)
        }
        bindingRecyclerViewAdapter.onItemClickListener = object : BindingRecyclerViewDoubleLayerAdapter.OnItemClickListener {
            override fun onItemClick(data: BindingAdapterDoubleLayerItem) {
                recyclerViewVm.refreshCheckState(data)
            }
        }
        bindingRecyclerViewAdapter.onInnerItemClickListener = object : BindingRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(data: BindingAdapterItem) {
                recyclerViewVm.refreshCheckState(data)
            }
        }
    }

    /**  */
    override fun bindXmlData() {
        binding.recyclerViewVm = recyclerViewVm
        binding.recyclerViewPage = this
        binding.bindingRecyclerViewAdapter = bindingRecyclerViewAdapter
    }

    /**  */
    override fun observeViewModelData() {
    }

    /**  */
    override fun removeViewModelObserver() {
    }

    /**  */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun onButtonEditClick() {
        recyclerViewVm.inEdit.value = recyclerViewVm.inEdit.value?.not()
        when (recyclerViewVm.inEdit.value) {
            true -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombineEditing)
            false -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombine)
            else -> bindingRecyclerViewAdapter.updateDataList(ArticleListDataCenter.itemsCombine)
        }
    }
}
