package com.githubyss.mobile.common.ui.alone.homepage

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.alone.animation.property.PropertyAnimationFragment
import com.githubyss.mobile.common.ui.alone.mvvm.MvvmActivity
import com.githubyss.mobile.common.ui.alone.recyclerview.multi.page.RecyclerViewByMultiTypeFragment
import com.githubyss.mobile.common.ui.alone.recyclerview.search.page.SearchResultFragment
import com.githubyss.mobile.common.ui.alone.view_binding.inline.InlineActivity
import com.githubyss.mobile.common.ui.alone.view_binding.inline.InlineToolbarActivity
import com.githubyss.mobile.common.ui.alone.view_binding.reflect.ReflectActivity
import com.githubyss.mobile.common.ui.alone.view_binding.reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarFragment
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentHomepageBinding
import com.githubyss.mobile.common.ui.floating_view.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floating_view.container.FloatingIconListener
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingIcon
import com.githubyss.mobile.common.ui.floating_view.container.system.SystemFloatingIcon
import java.util.*


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:22
 */
class HomepageFragment : BaseToolbarFragment(R.layout.comui_fragment_homepage) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentHomepageBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        initView()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_homepage)
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context)
     *
     * @param context
     * @return
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onAttach")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreate")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 Nothing
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?)
     *
     * @param view
     * @param savedInstanceState
     * @return
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onViewCreated")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onActivityCreated")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStarted(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStart")
        
        attachView()
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentResumed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onResume")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentPaused(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onPause")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStopped(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStop")
        
        detachView()
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onSaveInstanceState")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentViewDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroyView")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroy")
    }
    
    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDetached(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDetach() {
        super.onDetach()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDetach")
    }
    
    /**
     * 当一个子 Fragment attach 到当前 Fragment 时
     *
     * @param childFragment
     * @return
     */
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onAttachFragment")
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        binding.buttonMvvm.setOnClickListener(onClickListener)
        
        binding.buttonBindingReflect.setOnClickListener(onClickListener)
        binding.buttonBindingInline.setOnClickListener(onClickListener)
        binding.buttonBindingReflectToolbar.setOnClickListener(onClickListener)
        binding.buttonBindingInlineToolbar.setOnClickListener(onClickListener)
        
        binding.buttonPropertyAnimation.setOnClickListener(onClickListener)
        binding.buttonTweenAnimation.setOnClickListener(onClickListener)
        
        binding.buttonRecyclerViewMultiType.setOnClickListener(onClickListener)
        binding.buttonRecyclerViewMultiView.setOnClickListener(onClickListener)
        
        binding.btnShowFloatingWithinApp.setOnClickListener(onClickListener)
        binding.btnCloseFloatingWithinApp.setOnClickListener(onClickListener)
        binding.btnShowFloatingWithinSystem.setOnClickListener(onClickListener)
        binding.btnCloseFloatingWithinSystem.setOnClickListener(onClickListener)
        
        binding.buttonShowAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.buttonCloseAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.buttonLengthenAutoShortedFloatingView.setOnClickListener(onClickListener)
        binding.buttonShortenAutoShortedFloatingView.setOnClickListener(onClickListener)
        
        binding.buttonPlayPauseController.setOnClickListener(onClickListener)
        binding.buttonPrevious.setOnClickListener(onClickListener)
        binding.buttonNext.setOnClickListener(onClickListener)
        binding.buttonSwitchVoice.setOnClickListener(onClickListener)
        binding.buttonStop.setOnClickListener(onClickListener)
    }
    
    private fun attachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .attach(binding.layoutPage)
    }
    
    private fun detachView() {
        AppFloatingIcon.getInstance(activity as Context)
            .detach(binding.layoutPage)
    }
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.button_mvvm -> ActivityUtils.startActivity(activity, MvvmActivity::class.java)
            
            R.id.button_binding_reflect -> ActivityUtils.startActivity(activity, ReflectActivity::class.java)
            R.id.button_binding_inline -> ActivityUtils.startActivity(activity, InlineActivity::class.java)
            R.id.button_binding_reflect_toolbar -> ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
            R.id.button_binding_inline_toolbar -> ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)
            
            R.id.button_property_animation -> replaceFragment(PropertyAnimationFragment(), PropertyAnimationFragment.TAG, true)
            R.id.button_tween_animation -> return@OnClickListener
            
            R.id.button_recycler_view_multi_type -> replaceFragment(RecyclerViewByMultiTypeFragment(), RecyclerViewByMultiTypeFragment.TAG, true)
            R.id.button_recycler_view_multi_view -> replaceFragment(SearchResultFragment(), SearchResultFragment.TAG, true)
            
            R.id.btn_show_floating_within_app -> {
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
                                LogUtils.d(TAG, "FloatingAudioPlayerListener >>> onUpdateAudioInfo >> audioModel: $audioModel")
                                if (audioModel?.isPlaying ?: return) {
                                    binding.buttonPlayPauseController.text = "暂停"
                                }
                                else {
                                    binding.buttonPlayPauseController.text = "播放"
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
            
            R.id.button_play_pause_controller -> {
                when (binding.buttonPlayPauseController.text) {
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
        }
    }
}
