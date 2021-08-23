package com.githubyss.mobile.common.ui.base.view_binding.page.inline

import com.githubyss.mobile.common.ui.base.view_binding.page.base.BaseActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * BaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 16:50:32
 */
abstract class BaseActivity : BaseActivity() {
    val binding by inflate<ComuiActivityBaseBinding>()
}
