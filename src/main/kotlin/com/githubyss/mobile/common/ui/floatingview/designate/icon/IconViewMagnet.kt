package com.githubyss.mobile.common.ui.floatingview.designate.icon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.MagnetView
import kotlinx.android.synthetic.main.comui_floating_icon.view.*


/**
 * IconViewMagnet
 * 图标 View（磁吸）
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/10 16:32:27
 */
class IconViewMagnet : MagnetView, IconViewInterface {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        private val TAG = IconViewMagnet::class.simpleName ?: "simpleName is null"
        
        @LayoutRes
        private var designateLayoutId: Int = R.layout.comui_floating_icon
    }
    
    
    /** ********* ********** ********** Properties ********** ********** ********** */
    
    @DrawableRes
    private var iconDrawableId: Int = R.drawable.imuxuan
    private var iconUrl: String? = null
    private var iconDrawable: Drawable? = null
    private var iconBitmap: Bitmap? = null
    
    private var designateContext: Context? = null
    private var designateView: IconViewMagnet? = null
    
    override var isMovable: Boolean = true
        set(value) {
            field = value
            super.isMovable = value
        }
    
    var designateViewListener: IconViewListener? = null
    
    
    /** ********* ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, @LayoutRes layoutId: Int = designateLayoutId) : super(context, null) {
        designateContext = context
        if (super.featureView == null) {
            super.featureView = View.inflate(context, layoutId, this)
            designateView = this
            initInBase()
            initInDesignated()
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun customIcon(drawableId: Int) {
        iconDrawableId = drawableId
        GlideUtils.loadImage(designateContext ?: return, iconDrawableId, imageView_icon)
    }
    
    override fun customIcon(url: String?) {
        iconUrl = url
        GlideUtils.loadImage(designateContext ?: return, iconUrl, imageView_icon)
    }
    
    override fun customIcon(drawable: Drawable?) {
        iconDrawable = drawable
        if (drawable != null) {
            imageView_icon.setImageDrawable(iconDrawable)
        }
    }
    
    override fun customIcon(bitmap: Bitmap?) {
        iconBitmap = bitmap
        if (bitmap != null) {
            imageView_icon.setImageBitmap(iconBitmap)
        }
    }
    
    override fun customView(view: IconViewMagnet?) {
        designateView = view
    }
    
    override fun customView(layoutId: Int) {
        designateLayoutId = layoutId
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun isFloatingShow(): Boolean {
        return designateView?.isShown ?: false
    }
    
    
    /** ********* ********** ********** Private ********** ********** ********** */
    
    private fun initInDesignated() {
        initView()
        initListener()
    }
    
    private fun initView() {
        imageView_icon.setImageResource(iconDrawableId)
    }
    
    private fun initListener() {
        imageView_close.setOnClickListener(onClickListener)
    }
    
    
    /** ********* ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = OnClickListener { v ->
        when (v?.id) {
            R.id.imageView_close -> {
                designateViewListener?.onClose()
            }
        }
    }
}
