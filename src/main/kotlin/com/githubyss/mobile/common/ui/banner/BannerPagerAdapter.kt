package com.githubyss.mobile.common.ui.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import java.util.*
import kotlin.collections.ArrayList


/**
 * BannerPagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:00:43
 */
class BannerPagerAdapter : PagerAdapter() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = BannerPagerAdapter::class.java.simpleName
    }
    
    private var dataList: MutableList<BannerModel> = ArrayList()
    private var viewCache: HashMap<Int, LinearLayout> = HashMap()
    private var viewPosition = 0
    var onBannerClickListener: OnBannerClickListener? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemWidth = ViewGroup.LayoutParams.MATCH_PARENT
        val itemHeight = ViewGroup.LayoutParams.MATCH_PARENT
        
        viewPosition = position
        var convertView: LinearLayout
        var imageView: ImageView
        
        synchronized(viewCache) {
            val layout = viewCache[position]
            convertView = layout ?: LayoutInflater.from(container.context)
                ?.inflate(R.layout.comui_banner_item, container, false) as LinearLayout
            imageView = convertView.findViewById(R.id.imageView_bannerItem) as ImageView
            
            if (layout == null) {
                viewCache[position] = convertView
                if (position in 0 until count) {
                    val banner = dataList[position]
                    if (StringUtils.isNotEmpty(banner.imageUrl)) {
                        if (banner.imageUrl.startsWith("file:///")) {
                            loadBitmapDefault(container.context, banner.imageUrl.substring(8), imageView)
                        } else {
                            GlideUtils.loadImage(imageView, container, banner.imageUrl)
                        }
                    }
                }
            }
        }
        
        container.addView(convertView)
        val convertViewParams: ViewPager.LayoutParams = convertView.layoutParams as ViewPager.LayoutParams
        convertViewParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        convertViewParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        convertView.layoutParams = convertViewParams
        
        val dataModel = dataList[position]
        convertView.setOnClickListener { v ->
            onBannerClickListener?.onClick(position, v, dataModel)
        }
        
        val imageViewParams = imageView.layoutParams as LinearLayout.LayoutParams
        imageViewParams.width = itemWidth
        imageViewParams.height = itemHeight
        imageView.layoutParams = imageViewParams
        
        return convertView
    }
    
    override fun getCount(): Int {
        return dataList.size
    }
    
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    
    override fun getItemPosition(`object`: Any): Int {
        // notifyDataSetChanged 有时不能使 ViewPager 刷新，需要返回 position_none
        return if (viewCache.size == 0) {
            POSITION_NONE
        } else {
            super.getItemPosition(`object`)
        }
    }
    
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            container.removeView(`object`)
        }
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun setBannerList(bannerList: List<BannerModel>) {
        synchronized(dataList) {
            if (bannerList.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(bannerList)
                when {
                    dataList.size > 1 -> {
                        val bannerFirst = dataList[0]
                        val bannerLast = dataList[dataList.size - 1]
                        dataList.add(0, bannerLast)
                        dataList.add(bannerFirst)
                    }
                    else -> {
                    }
                }
            } else {
                val banner = BannerModel("", "", "", ItemType.ITEM)
                dataList.clear()
                dataList.add(banner)
            }
        }
        synchronized(viewCache) { viewCache.clear() }
    }
    
    /**
     * 从本地加载默认的图片
     *
     * @param
     * @return
     */
    private fun loadBitmapDefault(context: Context, iconName: String, imageView: ImageView?) {
        GlideUtils.loadImage(imageView, context, iconName)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnBannerClickListener {
        fun onClick(position: Int, view: View, data: BannerModel)
    }
}
