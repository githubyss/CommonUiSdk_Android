package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding


/**
 * RecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 17:36:32
 */
class RecyclerViewFragment : BaseReflectBindingToolbarFragment<ComuiFragmentRecyclerViewBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = RecyclerViewFragment::class.java.simpleName
    }

    private val recyclerViewVm: RecyclerViewViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    override fun setupData() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_title)
    }

    override fun setupViewModel() {
        binding?.recyclerViewVm = recyclerViewVm
    }

    override fun observeViewModel() {
        this.recyclerViewVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.recyclerViewVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
    }


    /** ****************************** Functions ****************************** */


    /** ****************************** Implementations ****************************** */

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_recycler_view_multi_type -> {}
            R.id.button_recycler_view_multi_view ->{}
            R.id.button_recycler_view_three_layer -> {}
        }
    }
}
