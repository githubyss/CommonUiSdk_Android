package com.githubyss.mobile.common.ui.app.page.spinner

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.time_countdown.TimeCountdownViewModel
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentSpinnerBinding
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentTimeCountdownBinding


/**
 * SpinnerFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/04 21:23:49
 */
class SpinnerFragment : BaseReflectBindingViewModelToolbarFragment<ComuiFragmentSpinnerBinding>() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = SpinnerFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val spinnerVm by viewModels<SpinnerVm>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        val spinnerAdapter = activity?.applicationContext?.let { ArrayAdapter.createFromResource(it, R.array.comres_languages, R.layout.comui_spinner_item) }?.apply {
            setDropDownViewResource(R.layout.comui_spinner_dropdown_item)
        }

        binding.spinnerContainer.spinner.adapter = spinnerAdapter
        binding.spinnerContainer.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                logD(TAG, "position: $position, id: $id")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(spinnerVm.title)
    }
}
