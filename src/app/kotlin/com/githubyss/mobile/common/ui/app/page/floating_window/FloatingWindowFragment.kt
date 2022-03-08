package com.githubyss.mobile.common.ui.app.page.floating_window

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioModel
import com.githubyss.mobile.common.kit.util.dp2Px
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentFloatingWindowBinding
import com.githubyss.mobile.common.ui.floating_view.classical.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floating_view.classical.container.FloatingIconListener
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floating_view.classical.container.app.AppFloatingIcon
import com.githubyss.mobile.common.ui.floating_view.classical.container.system.SystemFloatingIcon


/**
 * FloatingWindowFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 16:22:15
 */
class FloatingWindowFragment : BaseReflectBindingToolbarFragment<ComuiFragmentFloatingWindowBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = FloatingWindowFragment::class.java.simpleName
    }

    private val floatingWindowVm: FloatingWindowViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    override fun setupData() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_floating_window_title)
    }

    override fun setupViewModel() {
        binding?.floatingWindowVm = floatingWindowVm
    }

    override fun observeViewModel() {
        this.floatingWindowVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.floatingWindowVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        super.onStop()
        detachView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
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
            R.id.btn_show_floating_within_app -> {
                val layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (dp2Px(14.0f)), (dp2Px(14.0f)))
                AppFloatingIcon.getInstance(activity as Context)
                    .show()
                    ?.layoutParams(layoutParams)
                    ?.setMovable(true)
                    ?.customIcon("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
                    ?.setFloatingListener(object : FloatingIconListener {
                        override fun onShow() {}
                        override fun onRemove() {}
                        override fun onIconClick() {}
                    })
            }
            R.id.btn_close_floating_within_app -> {
                AppFloatingIcon.getInstance(activity as Context)
                    .close()
            }
            R.id.btn_show_floating_within_system -> {
                SystemFloatingIcon.getInstance(activity as Context)
                    .show()
                    ?.setMovable(true)
                    ?.customIcon(R.drawable.comui_icon_lucky_money)
                    ?.setFloatingListener(object : FloatingIconListener {
                        override fun onShow() {}
                        override fun onRemove() {}
                        override fun onIconClick() {}
                    })
            }
            R.id.btn_close_floating_within_system -> {
                SystemFloatingIcon.getInstance(activity as Context)
                    .close()
            }

            R.id.button_show_auto_shorted_floating_view -> {
                // Fake data
                val audioList: MutableList<AudioModel> = ArrayList()
                audioList.add(AudioModel("00000", "测图", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/voice/male/1608360545089.wav", "http://ossprexg.cnsuning.com/fipcms/media/voice/female/1608360545089.wav"))
                audioList.add(AudioModel("00000", "汽车", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/voice/male/160877400382021.wav", "http://ossprexg.cnsuning.com/fipcms/media/voice/female/160877400382021.wav"))
                // audioList.add(AudioModel("", "ID空", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "Url空", "", "", "", "", ""))
                // audioList.add(AudioModel("00002", "单女声-唱歌-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/ReviewMyKisses-Amiena-1773391755-1.mp3"))
                // audioList.add(AudioModel("00001", "单男声-播报-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", ""))
                // audioList.add(AudioModel("00001", "单男声-播报-错误Url-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/11.wav", ""))
                // audioList.add(AudioModel("00001", "单女声-播报-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "单女声-播报-错误Url-标题", "", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/22.wav"))
                // audioList.add(AudioModel("00001", "男&女声-播报-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/111.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))
                // audioList.add(AudioModel("00001", "男&女声-播报-男声错误Url-标题", "", "", "", "http://ossprexg.cnsuning.com/fipcms/media/11.wav", "http://ossprexg.cnsuning.com/fipcms/media/222.wav"))

                /** 先 setContext 再 show 后 initData，顺序不能乱 */
                AppFloatingAudioPlayer.getInstance(activity as Context)
                    .show()
                    ?.setAppFloatingForNativeListener(object : FloatingAudioPlayerListener {
                        override fun onShow() {}
                        override fun onRemove() {}

                        override fun onUpdateAudioInfo(audioModel: AudioModel?) {
                            run {
                                logD(TAG, "FloatingAudioPlayerListener >>> onUpdateAudioInfo >> audioModel: $audioModel")
                                if (audioModel?.isPlaying ?: return) {
                                }
                                else {
                                }
                            }
                        }
                    })?.designateView?.play(audioList)
            }
            R.id.button_close_auto_shorted_floating_view -> {
                AppFloatingAudioPlayer.getInstance(activity as Context)
                    .close()
            }
            R.id.button_lengthen_auto_shorted_floating_view -> {
                AppFloatingAudioPlayer.getInstance(activity as Context)
                    .designateView?.lengthen()
            }
            R.id.button_shorten_auto_shorted_floating_view -> {
                AppFloatingAudioPlayer.getInstance(activity as Context)
                    .designateView?.shorten()
            }
        }
    }
}
