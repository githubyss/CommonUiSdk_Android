package com.githubyss.common.ui.app.page.homepage

import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseBinding


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseReflectBindingActivity<CombaseActivityBaseBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(HomepageComposeFragment(), HomepageComposeFragment.TAG, FRAGMENT_BASE_CONTAINER_ID, false)
    }
}