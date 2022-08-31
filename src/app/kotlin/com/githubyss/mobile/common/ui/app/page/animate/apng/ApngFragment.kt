package com.githubyss.mobile.common.ui.app.page.animate.apng

import com.github.sahasbhop.apngview.ApngDrawable
import com.github.sahasbhop.apngview.ApngImageLoader
import com.github.sahasbhop.apngview.ApngImageLoader.ApngConfig
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentAnimateApngBinding
import com.githubyss.mobile.common.ui.floatingwindow.ComuiAutoHideFloatingWindow


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
    private val apngDrawable by lazy { ApngDrawable.getFromView(binding.imageApng) }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_apng_title)
    }

    /**  */
    override fun setupUi() {
        // Display image from a file in assets
        val uri = "assets://apng/comui_anim_speaking.png"

        // Display image from a file in file path
        // val uri = "file:///sdcard/apng_geneva_drive.png"

        // Display image from a file in web url
        // val uri = "http://littlesvr.ca/apng/images/clock.png"

        // 加载 Apng
        ApngImageLoader.getInstance().displayImage(uri, binding.imageApng)

        // 加载 Apng 后立即开始动画
        // ApngImageLoader.getInstance().displayApng(uri, binding.imageApng, ApngConfig(3, true))
    }

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
        if (apngDrawable.isRunning) {
            stopAnimator()
        }
        else {
            startAnimator()
        }
    }
}
