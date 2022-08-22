package com.githubyss.mobile.common.ui.app.page.time_countdown

import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentTimeCountdownBinding


/**
 * TimeCountdownFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/22 16:02:51
 */
class TimeCountdownFragment : BaseReflectBindingViewModelToolbarFragment<ComuiFragmentTimeCountdownBinding>() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = TimeCountdownFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val timeCountdownVm by viewModels<TimeCountdownViewModel>()


    /** ****************************** Override ****************************** */

    override fun setupData() {
        binding.timeCountdown.remainingMillisecond = timeCountdownVm.remainingMillisecond.value
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_time_countdown_title)
    }


    /** ****************************** Functions ****************************** */

}
