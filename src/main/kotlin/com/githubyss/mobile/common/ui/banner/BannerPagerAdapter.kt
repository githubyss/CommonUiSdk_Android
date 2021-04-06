package com.githubyss.mobile.common.ui.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.githubyss.mobile.common.kit.glide.GlideUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.R
import java.util.*


/**
 * BannerPagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:00:43
 */
class BannerPagerAdapter constructor(private val viewContext: Context, private val dataList: List<BannerModel>) : StatisticsPagerAdapter() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = BannerPagerAdapter::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    // private var inflater: LayoutInflater? = null
    private var viewCache: HashMap<String, LinearLayout> = HashMap()
    private var viewPosition = 0
    var onItemClickListener: OnItemClickListener? = null
    
    // 已曝光埋点列表，防止重复曝光
    private var viewStatisticList: ArrayList<String> = ArrayList()
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        // inflater = LayoutInflater.from(viewContext)
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemWidth = ViewGroup.LayoutParams.MATCH_PARENT
        val itemHeight = ViewGroup.LayoutParams.MATCH_PARENT
        
        viewPosition = position
        var convertView: LinearLayout
        var imageView: ImageView
        
        synchronized(viewCache) {
            convertView = viewCache[position.toString()] ?: LayoutInflater.from(viewContext).inflate(R.layout.comui_banner_item, null) as LinearLayout
            imageView = convertView.findViewById<View>(R.id.imageView_bannerItem) as ImageView
            viewCache[position.toString()] = convertView
            // imageView.setBackgroundResource(R.drawable.home_default_banner);
            
            val dataModel = dataList[position]
            if (position in 0 until count) {
                if (dataModel != null) {
                    if (!StringUtils.isEmpty(dataModel.imageUrl)) {
                        when {
                            dataModel.imageUrl.startsWith("file:///") -> {
                                loadBitmapDefault(viewContext, dataModel.imageUrl.substring(8), imageView)
                            }
                            dataModel.imageUrl.endsWith(".gif") -> {
                                GlideUtils.loadImage(dataModel.imageUrl, imageView, viewContext)
                            }
                            else -> {
                                GlideUtils.loadImage(dataModel.imageUrl, imageView, viewContext)
                            }
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
            onItemClickListener?.onItemClick(position, v, dataModel)
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
        return if (viewCache == null || viewCache.size == 0) {
            POSITION_NONE
        } else {
            super.getItemPosition(`object`)
        }
    }
    
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` != null && `object` is View) {
            container.removeView(`object`)
        }
    }
    
    override fun resetScrollState() {
        viewStatisticList.clear()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    // fun setCardModel(model: BannerModel?) {
    //     synchronized(mImgList) {
    //         if (model != null && model.mImgList != null && model.mImgList.size() > 0) {
    //             mImgList.clear()
    //             mImgList.addAll(model.mImgList)
    //             if (mImgList.size > 1) {
    //                 val info1: CommonItemInfo = mImgList.get(0)
    //                 val info2: CommonItemInfo = mImgList.get(mImgList.size - 1)
    //                 mImgList.add(0, info2)
    //                 mImgList.add(info1)
    //
    //                 //解决第一张图曝光不正常的问题
    //                 val eleid: String = getEleidByIndex(1)
    //                 if (viewStatisticList != null && !viewStatisticList.contains(eleid) && !StringUtils.isEmpty(info1.linkUrl)) {
    //                     viewStatisticList.add(eleid)
    //                     DataStatisticsUtil.CommonView(HomeConstant.STAT_PAGE_HOME, BannerPagerAdapter.MODID, eleid, null, info1.contentTitle, info1.adid, null, null)
    //                 }
    //             } else if (mImgList.size == 1) {
    //                 val info1: CommonItemInfo = mImgList.get(0)
    //                 //解决只有一张图曝光不正常的问题
    //                 val eleid: String = getEleidByIndex(1)
    //                 if (viewStatisticList != null && !viewStatisticList.contains(eleid) && info1 != null && !StringUtils.isEmpty(info1.linkUrl)) {
    //                     viewStatisticList.add(eleid)
    //                     DataStatisticsUtil.CommonView(HomeConstant.STAT_PAGE_HOME, BannerPagerAdapter.MODID, eleid, null, info1.contentTitle, info1.adid, null, null)
    //                 }
    //             }
    //         } else {
    //             val info = CommonItemInfo()
    //             info.imgUrl = ""
    //             info.linkUrl = ""
    //             mImgList.clear()
    //             mImgList.add(info)
    //         }
    //     }
    //     synchronized(viewCache) { viewCache.clear() }
    // }
    
    // fun viewStatistic(index: Int) {
    //     if (mImgList != null && index >= 0 && index < mImgList.size) {
    //         var info: CommonItemInfo? = null
    //         if (mImgList.size == 1) {
    //             info = mImgList.get(0)
    //         } else if (index + 1 < mImgList.size) {
    //             info = mImgList.get(index + 1)
    //         }
    //         val eleid = getEleidByIndex(index + 1)
    //         if (viewStatisticList != null && !viewStatisticList.contains(eleid) && !StringUtils.isEmpty(info.linkUrl)) {
    //             viewStatisticList.add(eleid)
    //             DataStatisticsUtil.CommonView(HomeConstant.STAT_PAGE_HOME, BannerPagerAdapter.MODID, eleid, null, info.contentTitle, info.adid, null, null)
    //         }
    //     }
    // }
    
    //从本地加载默认的图片
    private fun loadBitmapDefault(context: Context, iconName: String, imageView: ImageView) {
        GlideUtils.loadImage(iconName, imageView, context)
        // val iconId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
        // imageView.setBackgroundResource(iconId)
    }
    
    private fun getEleidByIndex(index: Int): String {
        var eleid = "" //默认给空
        when (index) {
            1 -> eleid = "pit20200924102504357"
            2 -> eleid = "pit20200924102522803"
            3 -> eleid = "pit20200924102529868"
            4 -> eleid = "pit20200924102536920"
            5 -> eleid = "pit20200924102545419"
        }
        return eleid
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View, data: BannerModel)
    }
}
