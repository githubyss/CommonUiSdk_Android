package com.githubyss.mobile.common.debug.homepage

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.githubyss.mobile.common.debug.animation.property.PropertyAnimationFragment
import com.githubyss.mobile.common.debug.recyclerview.multi.page.RecyclerViewByMultiTypeFragment
import com.githubyss.mobile.common.debug.recyclerview.search.page.SearchResultFragment
import com.githubyss.mobile.common.debug.viewbinding.inline.ViewBindingInlineActivity
import com.githubyss.mobile.common.debug.viewbinding.reflect.ViewBindingReflectActivity
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarFragmentBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentHomepageBinding
import com.githubyss.mobile.common.ui.floatingview.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floatingview.container.FloatingIconListener
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingIcon
import com.githubyss.mobile.common.ui.floatingview.container.system.SystemFloatingIcon
import java.util.*


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:22
 */
class HomepageFragment : BaseToolbarFragmentBindingInline(R.layout.comui_fragment_homepage) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = HomepageFragment::class.simpleName ?: "simpleName is null"
    }
    
    private val binding by bindView<ComuiFragmentHomepageBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        setToolbarTitle(R.string.comui_homepage_title)
        
        binding.btnBindingReflect.setOnClickListener(onClickListener)
        binding.btnBindingInline.setOnClickListener(onClickListener)
        
        binding.btnPropertyAnimation.setOnClickListener(onClickListener)
        binding.btnTweenAnimation.setOnClickListener(onClickListener)
        
        binding.btnRecyclerViewMultiType.setOnClickListener(onClickListener)
        binding.btnRecyclerViewMultiView.setOnClickListener(onClickListener)
        
        binding.btnShowFloatingWithinApp.setOnClickListener(onClickListener)
        binding.btnCloseFloatingWithinApp.setOnClickListener(onClickListener)
        binding.btnShowFloatingWithinSystem.setOnClickListener(onClickListener)
        binding.btnCloseFloatingWithinSystem.setOnClickListener(onClickListener)
        
        binding.btnShowAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.btnCloseAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.btnLengthenAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.btnShortenAutoShortedFloatingView.setOnClickListener(onClickListener)
        
        binding.btnPlayPauseController.setOnClickListener(onClickListener)
        binding.btnPrevious.setOnClickListener(onClickListener)
        binding.btnNext.setOnClickListener(onClickListener)
        binding.btnSwitchVoice.setOnClickListener(onClickListener)
        binding.btnStop.setOnClickListener(onClickListener)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btnBindingReflect -> ActivityUtils.startActivity(activity, ViewBindingReflectActivity::class.java)
            R.id.btnBindingInline -> ActivityUtils.startActivity(activity, ViewBindingInlineActivity::class.java)
            
            R.id.btnPropertyAnimation -> replaceFragment(PropertyAnimationFragment(), PropertyAnimationFragment.TAG, true)
            R.id.btnTweenAnimation -> return@OnClickListener
            
            R.id.btnRecyclerViewMultiType -> replaceFragment(RecyclerViewByMultiTypeFragment(), RecyclerViewByMultiTypeFragment.TAG, true)
            R.id.btnRecyclerViewMultiView -> replaceFragment(SearchResultFragment(), SearchResultFragment.TAG, true)
            
            R.id.btnShowFloatingWithinApp -> {
                val layoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.gravity = Gravity.BOTTOM or Gravity.END
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (ScreenUtils.dp2Px(14.0f)), (ScreenUtils.dp2Px(14.0f)))
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
                // SystemFloatingIcon.getInstance(activity as Context)
                //         .show()
                //         ?.setMovable(true)
                //         ?.customIcon(R.drawable.comui_icon_lucky_money)
                //         ?.setFloatingListener(object : FloatingIconListener {
                //             override fun onShow() {}
                //             override fun onRemove() {}
                //             override fun onIconClick() {}
                //         })
            }
            R.id.btnCloseFloatingWithinApp -> {
                AppFloatingIcon.getInstance(activity as Context)
                    .close()
                // SystemFloatingIcon.getInstance(activity as Context)
                //         .close()
            }
            R.id.btnShowFloatingWithinSystem -> {
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
            R.id.btnCloseFloatingWithinSystem -> {
                SystemFloatingIcon.getInstance(activity as Context)
                    .close()
            }
            
            R.id.btnShowAutoShortedFloatingView -> {
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
                                LogcatUtils.d(TAG, "FloatingAudioPlayerListener >>> onUpdateAudioInfo >> audioModel: $audioModel")
                                if (audioModel?.isPlaying ?: return) {
                                    binding.btnPlayPauseController.text = "暂停"
                                } else {
                                    binding.btnPlayPauseController.text = "播放"
                                }
                            }
                        }
                    })?.designateView?.play(audioList)
            }
            R.id.btnCloseAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context)
                    .close()
            }
            R.id.btnLengthenAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.lengthen()
            }
            R.id.btnShortenAutoShortedFloatingView -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.shorten()
            }
            
            R.id.btnPlayPauseController -> {
                when (binding.btnPlayPauseController.text) {
                    "播放" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.start()
                    }
                    "暂停" -> {
                        AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.pause()
                    }
                }
            }
            R.id.btnPrevious -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.previous()
            }
            R.id.btnNext -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.next()
            }
            R.id.btnSwitchVoice -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.switchVoice()
            }
            R.id.btnStop -> {
                AppFloatingAudioPlayer.getInstance(activity as Context).designateView?.stop()
            }
        }
    }
}
