package com.githubyss.common.ui.app.page.spinner

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.common.kit.util.dp2px
import com.githubyss.common.kit.util.logD
import com.githubyss.common.res.common.dimen.*
import com.githubyss.common.ui.R
import com.githubyss.common.ui.page.compose.PagePadding
import com.githubyss.common.ui.toolbar.compose.TopNavigationBar


/**
 * SpinnerComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/02 17:49:01
 */
class SpinnerComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = SpinnerComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val spinnerVm by viewModels<SpinnerVm>()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(spinnerVm.title) { activity?.onBackPressed() }
    }

    /**  */
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            Spinner()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun Spinner() {
        AndroidView(factory = { context ->
            val spinnerAdapter = activity?.applicationContext?.let { ArrayAdapter.createFromResource(it, R.array.comres_languages, R.layout.comui_spinner_item) }?.apply {
                setDropDownViewResource(R.layout.comui_spinner_dropdown_item)
            }
            Spinner(context).apply {
                setBackgroundResource(R.drawable.comui_spinner_bg_blue)
                setPopupBackgroundResource(R.drawable.comui_spinner_dropdown_white)
                layoutParams = ViewGroup.LayoutParams(200.dp2px.toInt(), 50.dp2px.toInt())
                dropDownVerticalOffset = 60.dp2px.toInt()
                dropDownWidth = 200.dp2px.toInt()
                adapter = spinnerAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        logD(TAG, "position: $position, id: $id")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
        })
    }
}
