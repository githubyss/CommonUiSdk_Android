package com.githubyss.mobile.common.ui.floatingview.designate.icon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View.OnClickListener
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFloatingIconBinding
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.MagnetView


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
    
    private var _binding: ComuiFloatingIconBinding? = null
    private val binding: ComuiFloatingIconBinding get() = _binding!!
    
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
            // super.featureView = View.inflate(context, layoutId, this)
            // super.featureView = LayoutInflater.from(context).inflate(layoutId, this)
            _binding = ComuiFloatingIconBinding.inflate(LayoutInflater.from(context), this, true)
            super.featureView = binding.root
            designateView = this
            initInBase()
            initInDesignated()
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun customIcon(drawableId: Int) {
        iconDrawableId = drawableId
        GlideUtils.loadImage(iconDrawableId, binding.imageViewIcon, designateContext ?: return)
    }
    
    override fun customIcon(url: String?) {
        iconUrl = url
        GlideUtils.loadImage(iconUrl, binding.imageViewIcon, designateContext ?: return)
    }
    
    override fun customIcon(drawable: Drawable?) {
        iconDrawable = drawable
        if (drawable != null) {
            binding.imageViewIcon.setImageDrawable(iconDrawable)
        }
    }
    
    override fun customIcon(bitmap: Bitmap?) {
        iconBitmap = bitmap
        if (bitmap != null) {
            binding.imageViewIcon.setImageBitmap(iconBitmap)
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
        binding.imageViewIcon.setImageResource(iconDrawableId)
    }
    
    private fun initListener() {
        binding.imageViewClose.setOnClickListener(onClickListener)
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
