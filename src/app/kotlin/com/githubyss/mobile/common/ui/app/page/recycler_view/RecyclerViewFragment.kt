package com.githubyss.mobile.common.ui.app.page.recycler_view

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.recycler_view.multi.page.MultiTypeRecyclerViewFragment
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.page.SearchResultFragment
import com.githubyss.mobile.common.ui.app.page.recycler_view.three_layer.page.ThreeLayerRecyclerViewFragment
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingIcon


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

    private val recyclerViewVm: RecyclerViewViewModel by lazy { ViewModelProvider(requireActivity()).get(RecyclerViewViewModel::class.java) }


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

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        super.onStop()
        detachView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
    }


    /** ****************************** Functions ****************************** */

    private fun attachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .attach(binding?.layoutPage)
    }

    private fun detachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .detach(binding?.layoutPage)
    }

    /** ****************************** Implementations ****************************** */

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_recycler_view_multi_type -> switchFragment(MultiTypeRecyclerViewFragment(), MultiTypeRecyclerViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_recycler_view_multi_view -> switchFragment(SearchResultFragment(), SearchResultFragment.TAG, this, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_recycler_view_three_layer -> switchFragment(ThreeLayerRecyclerViewFragment(), ThreeLayerRecyclerViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
        }
    }
}
