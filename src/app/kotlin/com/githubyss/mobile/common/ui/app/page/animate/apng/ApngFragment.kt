package com.githubyss.mobile.common.ui.app.page.animate.apng

import com.github.sahasbhop.apngview.ApngDrawable
import com.github.sahasbhop.apngview.ApngImageLoader
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentAnimateApngBinding


/**
 * ApngFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/30 15:49:09
 */
class ApngFragment : BaseReflectBindingViewModelToolbarFragment<ComuiFragmentAnimateApngBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = ApngFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val apngDrawable
        get() = ApngDrawable.getFromView(binding.imageApng)

    // Display image from a file in assets
    private var uri = ""

    // Display image from a file in file path
    // var uri = "file:///sdcard/apng_geneva_drive.png"

    // Display image from a file in web url
    // var uri = "http://littlesvr.ca/apng/images/clock.png"


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_apng_title)
    }

    /**  */
    override fun bindXmlData() {
        binding.page = this
    }

    /**  */
    override fun startAnimator() {
        // apngDrawable.numPlays = 3 // 动画循环次数
        apngDrawable.start() // 开始播放动画
    }

    /**  */
    override fun stopAnimator() {
        apngDrawable.stop() // 停止播放动画
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun onImageClick() {
        when {
            apngDrawable.isRunning -> stopAnimator()
            else -> startAnimator()
        }
    }

    /**  */
    fun onLoadImageSpeakingClick() {
        // 加载 Apng
        uri = "assets://apng/comui_anim_speaking.png"
        ApngImageLoader.getInstance().displayImage(uri, binding.imageApng)

        // 加载 Apng 后立即开始动画
        // ApngImageLoader.getInstance().displayApng(uri, binding.imageApng, ApngConfig(3, true))
    }

    /**  */
    fun onLoadImageListeningClick() {
        uri = "assets://apng/comui_anim_listening.png"
        ApngImageLoader.getInstance().displayImage(uri, binding.imageApng)
    }
}
