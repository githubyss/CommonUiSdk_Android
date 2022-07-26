package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentRecyclerViewBinding


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


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_title)
    }

    /**  */
    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    /**  */
    override fun bindViewModelXml() {
        binding.recyclerViewVm = recyclerViewVm
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
