package com.githubyss.mobile.common.ui.base.view_binding.page.inline

import com.githubyss.mobile.common.ui.base.view_binding.page.base.BaseActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * BaseInlineActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 16:50:32
 */
abstract class BaseInlineActivity : BaseActivity() {

    /** ****************************** Properties ****************************** */

    val binding by inflate<ComuiActivityBaseBinding>()
}
