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
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import java.util.*


/**
 * BannerPagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:00:43
 */
class BannerPagerAdapter constructor(private val viewContext: Context, private val dataList: MutableList<BannerModel>) : StatisticsPagerAdapter() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = BannerPagerAdapter::class.simpleName ?: "simpleName is null"
        // private val MODID: String? = "div20200924102428002"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var inflater: LayoutInflater? = null
    private var viewCache: HashMap<String, LinearLayout> = HashMap()
    private var viewPosition = 0
    var onBannerClickListener: OnBannerClickListener? = null
    
    // 已曝光埋点列表，防止重复曝光
    // private var viewStatisticList: ArrayList<String> = ArrayList()
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        inflater = LayoutInflater.from(viewContext)
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemWidth = ViewGroup.LayoutParams.MATCH_PARENT
        val itemHeight = ViewGroup.LayoutParams.MATCH_PARENT
        
        viewPosition = position
        var convertView: LinearLayout
        var imageView: ImageView
        
        synchronized(viewCache) {
            val layout = viewCache[position.toString()]
            if (layout != null) {
                convertView = layout
                imageView = convertView.findViewById<View>(R.id.imageView_bannerItem) as ImageView
            } else {
                convertView = inflater?.inflate(R.layout.comui_banner_item, null) as LinearLayout
                imageView = convertView.findViewById<View>(R.id.imageView_bannerItem) as ImageView
                viewCache[position.toString()] = convertView
                
                if (position in 0 until count) {
                    val info = dataList[position]
                    if (!StringUtils.isEmpty(info.imageUrl)) {
                        if (info.imageUrl.startsWith("file:///")) {
                            loadBitmapDefault(viewContext, info.imageUrl.substring(8), imageView)
                        } else {
                            GlideUtils.loadImage(info.imageUrl, imageView, viewContext)
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
    
    // override fun resetScrollState() {
    //     viewStatisticList.clear()
    // }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun setBannerList(list: List<BannerModel>) {
        synchronized(dataList) {
            if (list.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(list)
                when {
                    dataList.size > 1 -> {
                        val info1 = dataList[0]
                        val info2 = dataList[dataList.size - 1]
                        dataList.add(0, info2)
                        dataList.add(info1)
                    }
                    dataList.size == 1 -> {
                        val info1 = dataList[0]
                    }
                    else -> {
                    }
                }
            } else {
                val info = BannerModel("", "", "", ItemType.ITEM)
                dataList.clear()
                dataList.add(info)
            }
        }
        synchronized(viewCache) { viewCache.clear() }
    }
    //    fun setBannerList(list: List<BannerModel>) {
    //        synchronized(dataList) {
    //            if (list != null && list.isNotEmpty()) {
    //                dataList.clear()
    //                dataList.addAll(list)
    //            } else {
    //                val info = BannerModel("","","", ItemType.ITEM)
    //                dataList.clear()
    //                dataList.add(info)
    //            }
    //        }
    //        synchronized(viewCache) { viewCache.clear() }
    //    }
    
    // override fun viewStatistic(index: Int) {
    //     if (index >= 0 && index < mImgList.size) {
    //         var info: BannerModel? = null
    //         if (mImgList.size == 1) {
    //             info = mImgList[0]
    //         } else if (index + 1 < mImgList.size) {
    //             info = mImgList[index + 1]
    //         }
    //         val eleid = getEleidByIndex(index + 1)
    //         if (!viewStatisticList.contains(eleid) && !StringUtils.isEmpty(info?.imageUrl)) {
    //             viewStatisticList.add(eleid)
    //             // DataStatisticsUtil.CommonView(HomeConstant.STAT_PAGE_HOME,
    //             //         MODID, eleid, null, info.contentTitle,  info.adid, null, null);
    //         }
    //     }
    // }
    
    //从本地加载默认的图片
    // private fun loadBitmapDefault(context: Context, iconName: String, imageview: View?) {
    //     val iconId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
    //     imageview?.setBackgroundResource(iconId)
    // }
    
    //从本地加载默认的图片
    private fun loadBitmapDefault(context: Context, iconName: String, imageView: ImageView?) {
        GlideUtils.loadImage(iconName, imageView, context)
        // val iconId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
        // imageView.setBackgroundResource(iconId)
    }
    
    // private fun getEleidByIndex(index: Int): String {
    //     var eleid = "" //默认给空
    //     when (index) {
    //         1 -> eleid = "pit20200924102504357"
    //         2 -> eleid = "pit20200924102522803"
    //         3 -> eleid = "pit20200924102529868"
    //         4 -> eleid = "pit20200924102536920"
    //         5 -> eleid = "pit20200924102545419"
    //     }
    //     return eleid
    // }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnBannerClickListener {
        fun onClick(position: Int, view: View, data: BannerModel)
    }
}
