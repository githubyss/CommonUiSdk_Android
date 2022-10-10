package com.githubyss.common.ui.app.page.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.classical.BaseActivity
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.common.kit.util.*
import com.githubyss.common.res.common.dimen.SpaceNormal
import com.githubyss.common.ui.R
import com.githubyss.common.ui.app.page.animate.apng.ApngFragment
import com.githubyss.common.ui.app.page.animate.property.PropertyAnimatorFragment
import com.githubyss.common.ui.app.page.button.ButtonComposeFragment
import com.githubyss.common.ui.app.page.card.CardComposeFragment
import com.githubyss.common.ui.app.page.custom_view.CustomViewFragment
import com.githubyss.common.ui.app.page.floating_window.FloatingWindowFragment
import com.githubyss.common.ui.app.page.recycler_view.RecyclerViewFragment
import com.githubyss.common.ui.app.page.speech_recognition.SpeechRecognitionActivity
import com.githubyss.common.ui.app.page.spinner.SpinnerComposeFragment
import com.githubyss.common.ui.app.page.spinner.SpinnerFragment
import com.githubyss.common.ui.app.page.text.TextComposeFragment
import com.githubyss.common.ui.app.page.text_field.TextFieldComposeFragment
import com.githubyss.common.ui.app.page.time_countdown.TimeCountdownComposeFragment
import com.githubyss.common.ui.app.page.widget.WidgetComposeActivity
import com.githubyss.common.ui.app.ui.ButtonClickDefault
import com.githubyss.common.ui.dialog.hint.HintDialog
import com.githubyss.common.ui.dialog.hint.HintDialogVm
import com.githubyss.common.ui.dialog.voice_select.VoiceSelectDialog
import com.githubyss.common.ui.dialog.voice_select.VoiceSelectDialogVm
import com.githubyss.common.ui.dialog.voice_select.VoiceToneDataCenter
import com.githubyss.common.ui.floating_view.classical.container.app.AppFloatingAudioPlayer
import com.githubyss.common.ui.layout.compose.LayoutWeightHorizontal
import com.githubyss.common.ui.page.compose.PagePadding
import com.githubyss.common.ui.toolbar.compose.TopNavigationBar


/**
 * HomepageComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/30 17:50:30
 */
class HomepageComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = HomepageComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val homepageVm: HomepageComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(homepageVm.title)
    }

    /**  */
    @Preview
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun Buttons() {
        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_button_compose)) {
            switchFragmentByAddHideShow(ButtonComposeFragment(), ButtonComposeFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_card_compose)) {
            switchFragment(CardComposeFragment(), CardComposeFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_text_compose)) {
            switchFragment(TextComposeFragment(), TextComposeFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_text_field_compose)) {
            switchFragment(TextFieldComposeFragment(), TextFieldComposeFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        LayoutWeightHorizontal {
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_animate_property),
                modifier = Modifier.weight(1F),
            ) {
                switchFragment(PropertyAnimatorFragment(), PropertyAnimatorFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_animate_tween),
                modifier = Modifier.weight(1F),
            ) {
            }
        }

        LayoutWeightHorizontal {
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_animate_apng),
                modifier = Modifier.weight(1F),
            ) {
                switchFragment(ApngFragment(), ApngFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_animate_lottie),
                modifier = Modifier.weight(1F),
            ) {
            }
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_recycler_view)) {
            switchFragment(RecyclerViewFragment(), RecyclerViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        LayoutWeightHorizontal {
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_spinner), modifier = Modifier.weight(1F),
            ) {
                switchFragment(SpinnerFragment(), SpinnerFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_spinner_compose), modifier = Modifier.weight(1F),
            ) {
                switchFragment(SpinnerComposeFragment(), SpinnerComposeFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
            }
        }
        LayoutWeightHorizontal {
            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_dialog_hint),
                modifier = Modifier.weight(1F),
            ) {
                HintDialog.instance.showDialog(
                    parentFragmentManager,
                    "这是标题", "这是内容，可以在这里写下给用户的提示信息。这是内容，可以在这里写下给用户的提示信息。",
                    HintDialogVm.BTN_LEFT_DEFAULT, HintDialogVm.BTN_RIGHT_DEFAULT,
                    {
                        logD(msg = "调用方点击了左按钮")
                        showToast("调用方点击了左按钮")
                    },
                    {
                        logD(msg = "调用方点击了右按钮")
                        showToast("调用方点击了右按钮")
                    },
                    false)
            }

            ButtonClickDefault(
                text = getStringFromRes(R.string.comui_homepage_button_dialog_voice_select), modifier = Modifier.weight(1F),
            ) {
                VoiceSelectDialog.instance.showDialog(
                    parentFragmentManager,
                    VoiceSelectDialogVm.TITLE_DEFAULT,
                    VoiceSelectDialogVm.BTN_CONFIRM_DEFAULT, VoiceSelectDialogVm.BTN_CANCEL_DEFAULT,
                    VoiceToneDataCenter.voiceToneList)
            }
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_floating_window)) {
            switchFragment(FloatingWindowFragment(), FloatingWindowFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_speech_recognition)) {
            startActivityExt(activity, SpeechRecognitionActivity::class.java)
        }

        ButtonClickDefault(text = getStringFromRes(R.string.comui_homepage_button_widget)) {
            startActivityExt(activity, WidgetComposeActivity::class.java)
        }

        ButtonClickDefault(text = "自定义View") {
            switchFragment(CustomViewFragment(), CustomViewFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        ButtonClickDefault(text = "倒计时") {
            switchFragment(TimeCountdownComposeFragment(), TimeCountdownComposeFragment.TAG, this, BaseActivity.FRAGMENT_BASE_CONTAINER_ID, true)
        }

        LayoutWeightHorizontal {
            ButtonClickDefault(
                text = homepageVm.playController,
                modifier = Modifier.weight(1F),
            ) {
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
            ButtonClickDefault(
                text = getStringFromRes(R.string.comres_audio_player_previous),
                modifier = Modifier.weight(1F),
            ) {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.previous()
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comres_audio_player_next),
                modifier = Modifier.weight(1F),
            ) {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.next()
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comres_audio_player_switch_voice),
                modifier = Modifier.weight(1F),
            ) {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.switchVoice()
            }
            ButtonClickDefault(
                text = getStringFromRes(R.string.comres_audio_player_stop),
                modifier = Modifier.weight(1F),
            ) {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.stop()
            }
        }
    }
}
