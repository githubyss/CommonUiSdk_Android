package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.common.base.recycler_view.binding.BindingAdapterDoubleLayerItem
import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.base.recycler_view.binding.BindingRecyclerViewAdapter
import com.githubyss.common.base.recycler_view.binding.BindingRecyclerViewDoubleLayerAdapter
import com.githubyss.mobile.common.kit.util.showToast
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.dialog.hint.HintDialog


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
        recyclerViewVm.updateArticleData()
        when (recyclerViewVm.isEditing.value) {
            true -> bindingRecyclerViewAdapter.updateDataList(recyclerViewVm.itemsCombineEditing)
            false -> bindingRecyclerViewAdapter.updateDataList(recyclerViewVm.itemsCombine)
        }
        bindingRecyclerViewAdapter.onItemClickListener = object : BindingRecyclerViewDoubleLayerAdapter.OnItemClickListener {
            override fun onItemClick(data: BindingAdapterDoubleLayerItem) {
                recyclerViewVm.refreshCheckState(data)
                recyclerViewVm.refreshIds()
            }
        }
        bindingRecyclerViewAdapter.onInnerItemClickListener = object : BindingRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(data: BindingAdapterItem) {
                recyclerViewVm.refreshCheckState(data)
                recyclerViewVm.refreshIds()
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
        // recyclerViewVm.isEditing.value = recyclerViewVm.isEditing.value?.not()
        recyclerViewVm.refreshEditingState(true)
        bindingRecyclerViewAdapter.updateDataList(recyclerViewVm.itemsCombineEditing)
    }

    /**  */
    fun onButtonEditFinishClick() {
        recyclerViewVm.refreshEditingState(false)
        bindingRecyclerViewAdapter.updateDataList(recyclerViewVm.itemsCombine)
    }

    /**  */
    fun onButtonSelectAllClick() {
        recyclerViewVm.refreshSelectAllState()
        recyclerViewVm.selectAllItems()
        recyclerViewVm.refreshIds()
    }

    /**  */
    fun onButtonDeleteClick() {
        HintDialog.instance.showDialog(
            parentFragmentManager,
            "确认删除", "${recyclerViewVm.ids.value?.size} 篇内容将从您的听单列表中删除，是否确认删除？",
            "确认", "取消",
            {
                showToast("删除")
                recyclerViewVm.deleteItemTitle()
                bindingRecyclerViewAdapter.updateDataList(recyclerViewVm.itemsCombineEditing)
            },
            {
                showToast("取消")
            })
    }
}
