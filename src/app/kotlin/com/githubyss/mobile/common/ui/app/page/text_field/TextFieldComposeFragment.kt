package com.githubyss.mobile.common.ui.app.page.text_field

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.SpaceNormal
import com.githubyss.mobile.common.ui.page.compose.PageSidePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * TextFieldComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/29 00:12:24
 */
class TextFieldComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = TextFieldComposeFragment::class.java.simpleName
    }

    private val textFieldComposeVm: TextFieldComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(textFieldComposeVm.title) { activity?.onBackPressed() }
    }

    @Composable
    override fun Content() {
        PageSidePadding(paddingVertical = Dp.SpaceNormal)
        {
            TextFields()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun TextFields() {
    }
}
