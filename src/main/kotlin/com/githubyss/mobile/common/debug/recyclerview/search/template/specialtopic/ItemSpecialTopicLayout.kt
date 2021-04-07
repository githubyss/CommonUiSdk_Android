package com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.fragment.MockRequest
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconAdapter
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.banner.BannerModel
import com.githubyss.mobile.common.ui.banner.BannerPagerAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import kotlinx.android.synthetic.main.comui_recycler_special_topic_view.view.*


/**
 * ItemSpecialTopicLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 09:43:59
 */
class ItemSpecialTopicLayout : FrameLayout {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ItemSpecialTopicLayout::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var bannerPagerAdapter: BannerPagerAdapter? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataModel: SpecialTopicModel, context: Context, listener: BaseItemAdapter.OnItemClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.comui_recycler_special_topic_view, this)
        
        /** 背景 */
        GlideUtils.loadImage(dataModel.bgImageUrl, imageView_specialTopicBg, R.drawable.comui_bg_special_topic, context)
        textView_specialTopicBgTitle.text = dataModel.bgTitle
        textView_specialTopicBgDescription.text = dataModel.bgDescription
        
        /** 标题头 */
        GlideUtils.loadImage(dataModel.topicIconUrl, imageView_specialTopicIcon, context)
        textView_specialTopicTitle.text = dataModel.topicTitle
        textView_specialTopicDescription.text = dataModel.topicDescription
        layout_specialTopicHeader.setOnClickListener { v -> }
        
        /** Banner 广告 */
        val bannerPageWidth: Int = (ScreenUtils.getAppScreenWidthPx() - (24 * ScreenUtils.getScreenDensity())).toInt()
        banner_specialTopicAd.layoutParams.height = (bannerPageWidth * 80.0f / 351.0f + 0.5f).toInt()
        bannerPagerAdapter = BannerPagerAdapter()
        banner_specialTopicAd.setAdapter(bannerPagerAdapter)
        bannerPagerAdapter?.onBannerClickListener = onBannerClickListener
        updateBannerView(MockRequest.requestSpecialTopic().advertList)
        
        /** 图标 */
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        list_specialTopicIcon.setHasFixedSize(true)
        list_specialTopicIcon.layoutManager = layoutManager
        val iconListAdapter = AppIconAdapter(dataModel.iconList)
        list_specialTopicIcon.adapter = iconListAdapter
        iconListAdapter.onItemClickListener = listener
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun updateBannerView(bannerList: List<BannerModel>) {
        if (bannerList.isNotEmpty()) {
            banner_specialTopicAd.visibility = View.VISIBLE
            bannerPagerAdapter?.setBannerList(bannerList)
            bannerPagerAdapter?.notifyDataSetChanged()
            banner_specialTopicAd.startCarouse()
        } else {
            banner_specialTopicAd.stopCarouse()
            banner_specialTopicAd.visibility = View.GONE
        }
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onBannerClickListener: BannerPagerAdapter.OnBannerClickListener = object : BannerPagerAdapter.OnBannerClickListener {
        override fun onClick(position: Int, view: View, data: BannerModel) {
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    // interface OnLayoutClickListener {
    //     fun onClick(position: Int, view: View, data: BannerModel)
    // }
}
