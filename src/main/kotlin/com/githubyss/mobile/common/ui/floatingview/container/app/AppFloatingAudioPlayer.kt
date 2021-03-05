package com.githubyss.mobile.common.ui.floatingview.container.app

import android.R
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import com.githubyss.mobile.common.kit.designpattern.SingletonHolder
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel
import com.githubyss.mobile.common.kit.manager.audio.player.AudioPlayManager
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.floatingview.container.FloatingAudioPlayerListener
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.AudioPlayerViewAutoShorten
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.AudioPlayerViewListener
import com.githubyss.mobile.common.ui.floatingview.feature.autoshorten.AutoShortenViewListener
import java.lang.ref.WeakReference


/**
 * AppFloatingAudioPlayer
 * 应用级 Floating（音乐播放器）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/22 19:50:06
 */
class AppFloatingAudioPlayer : AppFloatingInterface<AppFloatingAudioPlayer, AudioPlayerViewAutoShorten> {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    
    companion object : SingletonHolder<AppFloatingAudioPlayer, Context>(::AppFloatingAudioPlayer) {
        private val TAG = AppFloatingAudioPlayer::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var designateLayoutParams: ViewGroup.LayoutParams? = null
    
    private var containerContext: Context? = null
    private var containerView: WeakReference<FrameLayout?>? = null
    
    var designateView: AudioPlayerViewAutoShorten? = null
    
    /** 原生 Listener  */
    var floatingForNativeListener: FloatingAudioPlayerListener? = null
    
    /** Web 端 Listener  */
    var floatingForWebListener: FloatingAudioPlayerListener? = null
    
    
    /** ********* ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context?) {
        containerContext = context
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun show(): AppFloatingAudioPlayer? {
        if (!initLayoutParams()) return null
        
        ensureFloatingView()
        return this
    }
    
    override fun close(): AppFloatingAudioPlayer {
        AudioPlayManager.INSTANCE.destroy()
        designateView?.removeAnimator()
        return this
    }
    
    override fun layoutParams(params: ViewGroup.LayoutParams?): AppFloatingAudioPlayer {
        designateLayoutParams = params
        designateView?.layoutParams = params
        return this
    }
    
    override fun setMovable(isMovable: Boolean): AppFloatingAudioPlayer {
        designateView?.isMovable = isMovable
        return this
    }
    
    override fun customView(view: AudioPlayerViewAutoShorten?): AppFloatingAudioPlayer {
        designateView = view
        designateView?.customView(view)
        return this
    }
    
    override fun customView(layoutId: Int): AppFloatingAudioPlayer {
        designateView?.customView(layoutId)
        return this
    }
    
    override fun attach(activity: Activity?) {
        attach(getActivityRoot(activity))
    }
    
    override fun attach(container: FrameLayout?) {
        if (container == null || designateView == null) {
            containerView = WeakReference(container)
            return
        }
        if (designateView?.parent === container) {
            return
        }
        if (designateView?.parent != null) {
            (designateView?.parent as ViewGroup).removeView(designateView)
        }
        containerView = WeakReference(container)
        container.addView(designateView)
    }
    
    override fun detach(activity: Activity?) {
        detach(getActivityRoot(activity))
    }
    
    override fun detach(container: FrameLayout?) {
        if (designateView != null && container != null && ViewCompat.isAttachedToWindow(designateView ?: return)) {
            container.removeView(designateView)
        }
        if (getContainerView() === container) {
            containerView = null
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun setAppFloatingForNativeListener(listener: FloatingAudioPlayerListener): AppFloatingAudioPlayer {
        floatingForNativeListener = listener
        return this
    }
    
    fun setAppFloatingForWebListener(listener: FloatingAudioPlayerListener): AppFloatingAudioPlayer {
        floatingForWebListener = listener
        return this
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun initLayoutParams(): Boolean {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM or Gravity.START
        getDesignatedLayoutParams().setMargins(ScreenUtils.dp2Px(0.0f) ?: return false, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, ScreenUtils.dp2Px(50.0f) ?: return false)
        return true
    }
    
    private fun getDesignatedLayoutParams(): FrameLayout.LayoutParams {
        if (designateLayoutParams == null) {
            designateLayoutParams = FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
        return designateLayoutParams as FrameLayout.LayoutParams
    }
    
    private fun ensureFloatingView() {
        synchronized(this) {
            if (designateView == null) {
                designateView = AudioPlayerViewAutoShorten(containerContext ?: return)
                designateView?.layoutParams = getDesignatedLayoutParams()
                designateView?.setBackgroundColor(0x000000FF)
                initListener()
                addViewToWindow(designateView)
                designateView?.showAnimator()
            }
        }
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
                    if (getContainerView() != null && ViewCompat.isAttachedToWindow(designateView ?: return@post)) {
                        getContainerView()?.removeView(designateView)
                    }
                } catch (e: Exception) {
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
        } catch (e: Exception) {
        }
    }
    
    private fun getContainerView(): FrameLayout? {
        return if (containerView == null) null else containerView?.get()
    }
    
    private fun getActivityRoot(activity: Activity?): FrameLayout? {
        if (activity == null) return null
        try {
            return activity.window.decorView.findViewById<View>(R.id.content) as FrameLayout
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
