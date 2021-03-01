package com.githubyss.mobile.common.ui.basemvp

import android.widget.Button
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.ui.R
import kotlinx.android.synthetic.main.comui_toolbar_base.*

/**
 * BaseFragment.ktDescription>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class BaseFragment : Fragment() {
    /** Bind Presenter. by Ace Yan*/
    open fun bindPresenter() {}

    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    open fun initView() {}

    /** Init default value of Top-level variables, and so on. by Ace Yan */
    open fun initData() {}

    /** Refresh Views. by Ace Yan */
    open fun refreshView() {}


    /** Change button status. by Ace Yan */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }

    /** Setup Toolbar title by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
        activity?.let { it.toolbarBase.setTitle(titleResId) }
    }

    /** Setup Toolbar title by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        activity?.let { it.toolbarBase.title = titleString }
    }

    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }

            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(R.id.flFragmentContainer, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }

            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.flFragmentContainer, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
