package com.githubyss.mobile.common.ui.floatingview.container.app

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import com.githubyss.mobile.common.kit.designpattern.SingletonHolder
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.floatingview.container.FloatingIconListener
import com.githubyss.mobile.common.ui.floatingview.designate.icon.IconViewMagnet
import com.githubyss.mobile.common.ui.floatingview.designate.icon.IconViewListener
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.MagnetViewListener
import java.lang.ref.WeakReference


/**
 * AppFloatingIcon
 * 应用级 Floating（图标）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/20 17:01:14
 */
class AppFloatingIcon : AppFloatingInterface<AppFloatingIcon, IconViewMagnet> {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object : SingletonHolder<AppFloatingIcon, Context>(::AppFloatingIcon) {
        private val TAG = AppFloatingIcon::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var designateLayoutParams: ViewGroup.LayoutParams? = null
    
    private var containerContext: Context? = null
    private var containerView: WeakReference<FrameLayout?>? = null
    
    var designateView: IconViewMagnet? = null
    
    private var floatingListener: FloatingIconListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context?) {
        containerContext = context
    }
    
    // public <V extends View> V createDesignatedView(Class<V> clz) {
    //     View designateView = null;
    //     try {
    //         designateView = (View) Class.forName(clz.getName()).newInstance();
    //     } catch (Exception e) {
    //     }
    //     return (V) designateView;
    // }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun show(): AppFloatingIcon? {
        if (!initLayoutParams()) return null
        
        ensureFloatingView()
        return this
    }
    
    override fun close(): AppFloatingIcon {
        removeFloatingView()
        return this
    }
    
    override fun layoutParams(params: ViewGroup.LayoutParams?): AppFloatingIcon {
        designateLayoutParams = params
        designateView?.layoutParams = params
        return this
    }
    
    override fun setMovable(isMovable: Boolean): AppFloatingIcon {
        designateView?.isMovable = isMovable
        return this
    }
    
    override fun customView(view: IconViewMagnet?): AppFloatingIcon {
        designateView = view
        designateView?.customView(view)
        return this
    }
    
    override fun customView(layoutId: Int): AppFloatingIcon {
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
    
    fun customIcon(drawableId: Int): AppFloatingIcon {
        designateView?.customIcon(drawableId)
        return this
    }
    
    fun customIcon(url: String?): AppFloatingIcon {
        designateView?.customIcon(url)
        return this
    }
    
    fun customIcon(drawable: Drawable?): AppFloatingIcon {
        designateView?.customIcon(drawable)
        return this
    }
    
    fun customIcon(bitmap: Bitmap?): AppFloatingIcon {
        designateView?.customIcon(bitmap)
        return this
    }
    
    fun setFloatingListener(listener: FloatingIconListener): AppFloatingIcon {
        floatingListener = listener
        return this
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun initLayoutParams(): Boolean {
        getDesignateLayoutParams().gravity = Gravity.BOTTOM or Gravity.START
        getDesignateLayoutParams().setMargins(ScreenUtils.dp2Px(14.0f) ?: return false, getDesignateLayoutParams().topMargin, getDesignateLayoutParams().rightMargin, ScreenUtils.dp2Px(14.0f) ?: return false)
        return true
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
                designateView = IconViewMagnet(containerContext ?: return)
                designateView?.layoutParams = getDesignateLayoutParams()
                designateView?.setBackgroundColor(0x000000FF)
                initListener()
                addViewToWindow(designateView)
            }
        }
    }
    
    private fun initListener() {
        designateView?.designateViewListener = object : IconViewListener {
            override fun onClose() {
                removeFloatingView()
                floatingListener?.onRemove()
            }
        }
        
        designateView?.featureViewListener = object : MagnetViewListener {
            override fun onShow() {}
            
            override fun onRemove() {
                removeFloatingView()
                floatingListener?.onRemove()
            }
            
            override fun onIconClick() {
                floatingListener?.onIconClick()
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
    
    private fun addViewToWindow(designatedView: View?) {
        if (getContainerView() == null) {
            return
        }
        try {
            getContainerView()?.addView(designatedView)
        } catch (e: Exception) {
        }
    }
    
    private fun getContainerView(): FrameLayout? {
        return if (containerView == null) null else containerView?.get()
    }
    
    private fun getActivityRoot(activity: Activity?): FrameLayout? {
        if (activity == null) return null
        try {
            return activity.window.decorView.findViewById(R.id.content) as FrameLayout
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}