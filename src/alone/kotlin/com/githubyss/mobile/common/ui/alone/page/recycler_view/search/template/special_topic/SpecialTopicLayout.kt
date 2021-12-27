package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.special_topic

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.app_icon.AppIconAdapter
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.ScreenUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.banner.BannerModel
import com.githubyss.mobile.common.ui.banner.BannerPagerAdapter
import com.githubyss.mobile.common.ui.databinding.ComuiListItemSpecialTopicBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel


/**
 * SpecialTopicLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 09:43:59
 */
class SpecialTopicLayout : BaseItemLayout<ComuiListItemSpecialTopicBinding> {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = SpecialTopicLayout::class.java.simpleName
    }
    
    private var bannerPagerAdapter: BannerPagerAdapter? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(dataModel: BaseItemModel, keyList: ArrayList<String>, context: Context, listener: OnLayoutClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        onLayoutClickListener = listener
        
        if (dataModel is SpecialTopicModel) {
            /** 背景图 */
            // imageViewSpecialTopicBg?.visibility = if (StringUtils.isEmpty(dataModel.bgImageUrl)) View.GONE else View.VISIBLE
            // GlideUtils.loadImage(imageViewSpecialTopicBg, context, dataModel.bgImageUrl, R.drawable.new_search_result_bg_special_topic)
            GlideUtils.loadBackground(binding.flexboxItemSpecialTopic, this, dataModel.bgImageUrl)
            
            /** 背景标题&描述 */
            binding.flexboxSpecialTopicBg.visibility = if (StringUtils.isEmpty(dataModel.bgImageUrl)) View.GONE else View.VISIBLE
            binding.textSpecialTopicBgTitle.visibility = if (StringUtils.isEmpty(dataModel.bgTitle)) View.GONE else View.VISIBLE
            binding.textSpecialTopicBgDescription.visibility = if (StringUtils.isEmpty(dataModel.bgDescription)) View.GONE else View.VISIBLE
            binding.textSpecialTopicBgTitle.text = dataModel.bgTitle
            binding.textSpecialTopicBgDescription.text = dataModel.bgDescription
            binding.flexboxSpecialTopicBg.setOnClickListener { v ->
                onLayoutClickListener?.onClick(0, v, dataModel)
            }
            
            /** 头部头像&标题&描述 */
            binding.flexboxSpecialTopicHeader.visibility = if (StringUtils.isEmpty(dataModel.topicTitle) && StringUtils.isEmpty(dataModel.topicDescription)) View.GONE else View.VISIBLE
            GlideUtils.loadImage(binding.imageSpecialTopicIcon, this, dataModel.topicIconUrl)
            binding.textSpecialTopicTitle.text = dataModel.topicTitle
            binding.textSpecialTopicDescription.text = dataModel.topicDescription
            binding.flexboxSpecialTopicHeader.setOnClickListener { v ->
                onLayoutClickListener?.onClick(0, v, dataModel)
            }
            
            /** Banner 广告 */
            binding.bannerSpecialTopicAd.visibility = if (dataModel.advertList.isEmpty()) View.GONE else View.VISIBLE
            val bannerPageWidth: Int = (ScreenUtils.getAppScreenWidthPx() - (24 * ScreenUtils.getScreenDensity())).toInt()
            binding.bannerSpecialTopicAd.layoutParams?.height = (bannerPageWidth * 80.0f / 351.0f + 0.5f).toInt()
            bannerPagerAdapter = BannerPagerAdapter()
            binding.bannerSpecialTopicAd.setAdapter(bannerPagerAdapter)
            bannerPagerAdapter?.onBannerClickListener = object : BannerPagerAdapter.OnBannerClickListener {
                override fun onClick(position: Int, view: View, data: BannerModel) {
                    onLayoutClickListener?.onClick(position, view, data)
                }
            }
            updateBannerView(dataModel.advertList)
            
            /** 图标 */
            binding.recyclerSpecialTopicIcon.visibility = if (dataModel.iconList.isEmpty()) View.GONE else View.VISIBLE
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = RecyclerView.HORIZONTAL
            binding.recyclerSpecialTopicIcon.setHasFixedSize(true)
            binding.recyclerSpecialTopicIcon.layoutManager = layoutManager
            val iconListAdapter = AppIconAdapter(dataModel.iconList, keyList)
            binding.recyclerSpecialTopicIcon.adapter = iconListAdapter
            iconListAdapter.onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
                override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View?, data: BaseItemModel) {
                    onLayoutClickListener?.onClick(position, view, data)
                }
            }
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun updateBannerView(bannerList: List<BannerModel>) {
        if (bannerList.isNotEmpty()) {
            binding.bannerSpecialTopicAd.visibility = View.VISIBLE
            bannerPagerAdapter?.setBannerList(bannerList)
            bannerPagerAdapter?.notifyDataSetChanged()
            binding.bannerSpecialTopicAd.startCarouse()
        } else {
            binding.bannerSpecialTopicAd.stopCarouse()
            binding.bannerSpecialTopicAd.visibility = View.GONE
        }
    }
}
