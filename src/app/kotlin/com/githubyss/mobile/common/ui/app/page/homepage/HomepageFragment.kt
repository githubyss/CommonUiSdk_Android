package com.githubyss.mobile.common.ui.app.page.homepage

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.animate.property.PropertyAnimatorFragment
import com.githubyss.mobile.common.ui.app.page.floating_window.FloatingWindowFragment
import com.githubyss.mobile.common.ui.app.page.recycler_view.RecyclerViewFragment
import com.githubyss.mobile.common.ui.app.page.speech_recognition.SpeechRecognitionActivity
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentHomepageBinding
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingIcon


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:22
 */
class HomepageFragment : BaseReflectBindingToolbarFragment<ComuiFragmentHomepageBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }

    private val homepageVm: HomepageViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    override fun setupData() {
        this.homepageVm.viewId?.value = 0
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_homepage_title)
    }

    override fun setupViewModel() {
        binding?.homepageVm = homepageVm
    }

    override fun observeViewModel() {
        this.homepageVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.homepageVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        super.onStop()
        detachView()
    }


    /** ****************************** Functions ****************************** */

    private fun attachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .attach(binding?.layoutPage)
    }

    private fun detachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .detach(binding?.layoutPage)
    }

    /** ****************************** Implementations ****************************** */

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_property_animation -> FragmentUtils.switchFragmentByAddHideShow(PropertyAnimatorFragment(), PropertyAnimatorFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_tween_animation -> {
            }

            R.id.button_recycler_view -> switchFragment(RecyclerViewFragment(), RecyclerViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_floating_window -> switchFragment(FloatingWindowFragment(), FloatingWindowFragment.TAG, this, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)

            R.id.button_play_pause_controller -> {
                when (binding?.buttonPlayPauseController?.text) {
                    "播放" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.start()
                    }
                    "暂停" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.pause()
                    }
                }
            }
            R.id.button_previous -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.previous()
            }
            R.id.button_next -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.next()
            }
            R.id.button_switch_voice -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.switchVoice()
            }
            R.id.button_stop -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.stop()
            }

            R.id.button_speech_recognition -> {
                ActivityUtils.startActivity(activity, SpeechRecognitionActivity::class.java)
            }
        }
    }
}
