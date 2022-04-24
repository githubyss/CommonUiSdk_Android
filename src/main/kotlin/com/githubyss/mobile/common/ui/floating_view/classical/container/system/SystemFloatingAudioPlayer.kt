package com.githubyss.mobile.common.ui.floating_view.classical.container.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.githubyss.design_pattern.singleton.SingletonHolder1Arg
import com.githubyss.mobile.common.kit.constant.Constants
import com.githubyss.mobile.common.kit.enumeration.VersionCode
import com.githubyss.mobile.common.kit.enumeration.WindowManagerLayoutParamsType
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioModel
import com.githubyss.mobile.common.kit.manager.audio_player.player.AudioPlayManager
import com.githubyss.mobile.common.kit.util.dp2Px
import com.githubyss.mobile.common.ui.floating_view.classical.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floating_view.classical.designate.audioplayer.AudioPlayerViewAutoShorten
import com.githubyss.mobile.common.ui.floating_view.classical.designate.audioplayer.AudioPlayerViewListener
import com.githubyss.mobile.common.ui.floating_view.classical.feature.autoshorten.AutoShortenViewListener
import com.githubyss.mobile.common.ui.utils.PermissionOverlayUtils


/**
 * SystemFloatingAudioPlayer
 * 系统级 Floating（音乐播放器）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 10:37:57
 */
class SystemFloatingAudioPlayer : SystemFloatingInterface<SystemFloatingAudioPlayer, AudioPlayerViewAutoShorten> {

    /** ****************************** Properties ****************************** */

    companion object : SingletonHolder1Arg<SystemFloatingAudioPlayer, Context>(::SystemFloatingAudioPlayer) {
        private val TAG: String = SystemFloatingAudioPlayer::class.java.simpleName
    }

    private var containerLayoutParams: WindowManager.LayoutParams? = null
    private var designateLayoutParams: ViewGroup.LayoutParams? = null

    private var windowManager: WindowManager? = null

    private var containerContext: Context? = null
    private var containerView: ViewGroup? = null

    var designateView: AudioPlayerViewAutoShorten? = null

    /** 是否跳转过悬浮窗权限设置页  */
    private var isJumpToOverlayPermission = true

    /** 原生 Listener  */
    var floatingForNativeListener: FloatingAudioPlayerListener? = null

    /** Web 端 Listener  */
    var floatingForWebListener: FloatingAudioPlayerListener? = null


    /** ****************************** Constructors ****************************** */

    constructor(context: Context?) {
        initInContainer(context)
    }


    /** ****************************** Override ****************************** */

    override fun show(): SystemFloatingAudioPlayer? {
        if (!checkPermission(true)) return null
        if (!initLayoutParams()) return null

        ensureFloatingView()
        return this
    }

    override fun close(): SystemFloatingAudioPlayer {
        AudioPlayManager.INSTANCE.destroy()

        LocalBroadcastManager.getInstance(containerContext ?: return this)
            .unregisterReceiver(voiceReceiver ?: return this)

        designateView?.removeAnimator()
        return this
    }

    override fun layoutParams(params: ViewGroup.LayoutParams?): SystemFloatingAudioPlayer {
        designateLayoutParams = params
        designateView?.layoutParams = params
        return this
    }

    override fun setMovable(isMovable: Boolean): SystemFloatingAudioPlayer {
        designateView?.isMovable = isMovable
        return this
    }

    override fun customView(view: AudioPlayerViewAutoShorten?): SystemFloatingAudioPlayer {
        this.designateView = view
        designateView?.customView(view)
        return this
    }

    override fun customView(layoutId: Int): SystemFloatingAudioPlayer {
        designateView?.customView(layoutId)
        return this
    }

    override fun refreshViewWhenAppForeground() {
        containerView?.visibility = View.VISIBLE
    }

    override fun refreshViewWhenAppBackground() {
        containerView?.visibility = View.GONE
    }


    /** ****************************** Functions ****************************** */

    fun setAppFloatingForNativeListener(listener: FloatingAudioPlayerListener): SystemFloatingAudioPlayer {
        floatingForNativeListener = listener
        return this
    }

    fun setAppFloatingForWebListener(listener: FloatingAudioPlayerListener): SystemFloatingAudioPlayer {
        floatingForWebListener = listener
        return this
    }


    /** ****************************** Private ****************************** */

    private fun initInContainer(context: Context?) {
        containerContext = context
        initLocalBroadcastReceiver()
    }

    private fun initLocalBroadcastReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.INTENT_ACTION_IS_FOREGROUND)
        intentFilter.addAction(Constants.INTENT_ACTION_CLOSE_FLOAT)
        // 注册广播接收器
        LocalBroadcastManager.getInstance(containerContext ?: return)
            .registerReceiver(voiceReceiver ?: return, intentFilter)
    }

    private fun initLayoutParams(): Boolean {
        if (Build.VERSION.SDK_INT >= VersionCode.O) {
            if (PermissionOverlayUtils.isMiUiO()) {
                getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_PRESENTATION
            }
            else {
                getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_APPLICATION_OVERLAY
            }
        }
        else {
            getContainerLayoutParams().type = WindowManagerLayoutParamsType.TYPE_PHONE
        }

        // if (Build.VERSION.SDK_INT >= VersionCode.O) {
        //     layoutParams.type = WindowManagerLayoutParamsType.TYPE_APPLICATION_OVERLAY;
        // } else {
        //     layoutParams.type = WindowManagerLayoutParamsType.TYPE_TOAST;
        // }

        getContainerLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH) or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        // getContainerLayoutParams().flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        getContainerLayoutParams().gravity = Gravity.LEFT or Gravity.BOTTOM
        getContainerLayoutParams().format = PixelFormat.TRANSLUCENT
        getContainerLayoutParams().width = WindowManager.LayoutParams.WRAP_CONTENT
        getContainerLayoutParams().height = WindowManager.LayoutParams.WRAP_CONTENT
        getContainerLayoutParams().x = 0
        getContainerLayoutParams().y = dp2Px(70.0f).toInt()
        // getContainerLayoutParams().windowAnimations = android.R.style.Animation_Translucent;
        getDesignateLayoutParams().gravity = Gravity.BOTTOM or Gravity.START
        getDesignateLayoutParams().setMargins(0, getDesignateLayoutParams().topMargin, getDesignateLayoutParams().rightMargin, 0)
        return true
    }

    private fun getContainerLayoutParams(): WindowManager.LayoutParams {
        if (containerLayoutParams == null) {
            containerLayoutParams = WindowManager.LayoutParams()
        }
        return containerLayoutParams as WindowManager.LayoutParams
    }

    private fun getDesignateLayoutParams(): FrameLayout.LayoutParams {
        if (designateLayoutParams == null) {
            designateLayoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
        return designateLayoutParams as FrameLayout.LayoutParams
    }

    private fun ensureFloatingView() {
        synchronized(this) {
            if (designateView == null) {
                designateView = AudioPlayerViewAutoShorten(containerContext ?: return)
                designateView?.layoutParams = getDesignateLayoutParams()
                designateView?.setBackgroundColor(0x000000FF)
                initListener()
                addViewToWindow(designateView)
                designateView?.showAnimator()
            }
        }

        // if (!isShown) {
        // } else {
        //     windowManager().updateViewLayout(containerView, layoutParams);
        // }
    }

    private fun initListener() {
        designateView?.designateViewListener = object : AudioPlayerViewListener {
            override fun onUpdateAudioInfo(audioModel: AudioModel?) {
                floatingForNativeListener?.onUpdateAudioInfo(audioModel)
                floatingForWebListener?.onUpdateAudioInfo(audioModel)
            }
        }

        designateView?.featureViewListener = object : AutoShortenViewListener {
            override fun onShow() {}

            override fun onRemove() {
                removeFloatingView()
            }

            override fun onLengthen() {
                designateView?.refreshPlayerVisibility(false)
            }

            override fun onShorten() {
                designateView?.refreshPlayerVisibility(true)
            }
        }
    }

    private fun removeFloatingView() {
        Handler(Looper.getMainLooper()).post {
            if (designateView != null) {
                try {
                    if (getContainerView() != null && getContainerView()?.childCount ?: return@post > 0) {
                        getContainerView()?.removeView(designateView)
                    }
                    getWindowManager()?.removeView(getContainerView())
                }
                catch (e: Exception) {
                }
                designateView = null
            }
        }
    }

    private fun addViewToWindow(designateView: View?) {
        if (getContainerView() == null) {
            return
        }
        try {
            getContainerView()?.addView(designateView)
            getWindowManager()?.addView(getContainerView(), getContainerLayoutParams())
        }
        catch (e: Exception) {
        }
    }

    private fun getContainerView(): FrameLayout? {
        if (containerView == null) {
            containerView = FrameLayout(containerContext ?: return null)
            containerView?.setBackgroundColor(0x00FFFF33)
        }
        return containerView as FrameLayout?
    }

    private fun getWindowManager(): WindowManager? {
        if (windowManager == null && containerContext != null) {
            windowManager = containerContext?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        return windowManager
    }

    private fun checkPermission(isNeedJumpToOverlayPermission: Boolean): Boolean {
        // 获取悬浮窗权限状态
        val isHasOverlayPermission = PermissionOverlayUtils.hasPermission(containerContext)
        // 没有悬浮窗权限，并且需要去开启权限，跳转悬浮窗权限设置页
        if (!isHasOverlayPermission && isNeedJumpToOverlayPermission) {
            // 跳转悬浮窗权限设置页
            PermissionOverlayUtils.jumpToOverlayPermission(containerContext)
        }
        return isHasOverlayPermission
    }


    /** ****************************** Implementations ****************************** */

    /** 监听前后台切换  */
    private val voiceReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            // 前后台事件
            if (action == Constants.INTENT_ACTION_IS_FOREGROUND) {
                // 回到前台
                if (intent.getBooleanExtra("isForeground", true)) {
                    refreshViewWhenAppForeground()

                    if (isJumpToOverlayPermission) {
                        // 跳转状态重置为假
                        isJumpToOverlayPermission = false

                        // 悬浮窗权限是关的，拦截后续逻辑，等待用户重新点击
                        if (!checkPermission(false)) {
                            return
                        }
                        if (show() != null) {
                            designateView?.play(AudioPlayManager.INSTANCE.getAudioList())
                        }
                    }
                }
                // 进入后台
                else {
                    refreshViewWhenAppBackground()
                }
            }
            // 关闭浮窗事件
            else if (action == Constants.INTENT_ACTION_CLOSE_FLOAT) {
                close()
            }
        }
    }
}
