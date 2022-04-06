package com.githubyss.mobile.common.ui.app.page.homepage

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseBinding


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseReflectBindingActivity<ComkitActivityBaseBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        switchFragment(HomepageComposeFragment(), HomepageComposeFragment.TAG, FRAGMENT_BASE_CONTAINER_ID, false)
    }
}