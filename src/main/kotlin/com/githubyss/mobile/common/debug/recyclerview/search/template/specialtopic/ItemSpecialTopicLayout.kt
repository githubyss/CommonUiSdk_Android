package com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconAdapter
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.ui.R
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
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    // constructor(context: Context) : this(context, null, 0)
    // constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(dataModel: SpecialTopicModel, context: Context, listener: BaseItemAdapter.OnItemClickListener? = null, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.comui_recycler_special_topic_view, this)
        
        GlideUtils.loadImage(dataModel.bgImageUrl, imageView_specialTopicBg, R.drawable.comui_bg_special_topic, context)
        textView_specialTopicBgTitle.text = dataModel.bgTitle
        textView_specialTopicBgDescription.text = dataModel.bgDescription
        
        GlideUtils.loadImage(dataModel.topicIconUrl, imageView_specialTopicIcon, context)
        textView_specialTopicTitle.text = dataModel.topicTitle
        textView_specialTopicDescription.text = dataModel.topicDescription
        layout_specialTopicHeader.setOnClickListener { v -> }
        
        val bannerAdapter = BannerPagerAdapter(context, dataModel.advertList)
        banner_specialTopicAd.setAdapter(bannerAdapter)
        
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        list_specialTopicIcon.setHasFixedSize(true)
        list_specialTopicIcon.layoutManager = layoutManager
        val iconListAdapter = AppIconAdapter(dataModel.iconList)
        list_specialTopicIcon.adapter = iconListAdapter
        iconListAdapter.onItemClickListener = listener
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    // interface OnItemClickListener {
    //     fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View, data:)
    // }
}
