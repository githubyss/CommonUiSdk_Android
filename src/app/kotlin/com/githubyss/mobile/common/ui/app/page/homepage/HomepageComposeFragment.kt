package com.githubyss.mobile.common.ui.app.page.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.animate.property.PropertyAnimatorFragment
import com.githubyss.mobile.common.ui.app.page.compose_button.ComposeButtonFragment
import com.githubyss.mobile.common.ui.app.page.compose_card.ComposeCardFragment
import com.githubyss.mobile.common.ui.app.page.custom_view.CustomViewFragment
import com.githubyss.mobile.common.ui.app.page.floating_window.FloatingWindowFragment
import com.githubyss.mobile.common.ui.app.page.recycler_view.RecyclerViewFragment
import com.githubyss.mobile.common.ui.app.page.speech_recognition.SpeechRecognitionActivity
import com.githubyss.mobile.common.ui.button_click.compose.ButtonTextClickBlueWeightHorizontalMarginPadding
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.layout.compose.LayoutWeightHorizontal
import com.githubyss.mobile.common.ui.page.compose.PageSidePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * HomepageComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/30 17:50:30
 */
class HomepageComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = HomepageComposeFragment::class.java.simpleName
    }

    private val homepageVm: HomepageComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(homepageVm.title)
    }

    @Preview
    @Composable
    override fun Content() {
        PageSidePadding(
            paddingVertical = Dp.SideNormal,
        )
        {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Buttons() {
        ButtonTextClickBlueWeightHorizontalMarginPadding(text = getStringFromRes(R.string.comui_homepage_button_compose_button))
        {
            FragmentUtils.switchFragmentByAddHideShow(ComposeButtonFragment(), ComposeButtonFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = getStringFromRes(R.string.comui_homepage_button_compose_card))
        {
            FragmentUtils.switchFragmentByAddHideShow(ComposeCardFragment(), ComposeCardFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        LayoutWeightHorizontal {
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comui_homepage_button_property_animation),
                modifier = Modifier.weight(1F),
            )
            {
                FragmentUtils.switchFragmentByAddHideShow(PropertyAnimatorFragment(), PropertyAnimatorFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comui_homepage_button_tween_animation),
                modifier = Modifier.weight(1F),
            )
            {
            }
        }

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = getStringFromRes(R.string.comui_homepage_button_recycler_view))
        {
            switchFragment(RecyclerViewFragment(), RecyclerViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = getStringFromRes(R.string.comui_homepage_button_floating_window))
        {
            switchFragment(FloatingWindowFragment(), FloatingWindowFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = getStringFromRes(R.string.comui_homepage_button_speech_recognition))
        {
            ActivityUtils.startActivity(activity, SpeechRecognitionActivity::class.java)
        }

        LayoutWeightHorizontal {
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = homepageVm.playController,
                modifier = Modifier.weight(1F),
            )
            {
                when (homepageVm.playController) {
                    homepageVm.playControllerPlay -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.start()
                        homepageVm.switchPlayText(homepageVm.playControllerPause)
                    }
                    homepageVm.playControllerPause -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.pause()
                        homepageVm.switchPlayText(homepageVm.playControllerPlay)
                    }
                }
            }
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comres_audio_player_previous),
                modifier = Modifier.weight(1F),
            )
            {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.previous()
            }
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comres_audio_player_next),
                modifier = Modifier.weight(1F),
            )
            {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.next()
            }
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comres_audio_player_switch_voice),
                modifier = Modifier.weight(1F),
            )
            {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.switchVoice()
            }
            ButtonTextClickBlueWeightHorizontalMarginPadding(
                text = getStringFromRes(R.string.comres_audio_player_stop),
                modifier = Modifier.weight(1F),
            )
            {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.stop()
            }
        }

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = "自定义View")
        {
            FragmentUtils.switchFragmentByAddHideShow(CustomViewFragment(), CustomViewFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }
    }
}
