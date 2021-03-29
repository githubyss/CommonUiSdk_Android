package com.githubyss.mobile.common.debug.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.debug.recyclerview.fund.enumeration.SectionId
import com.githubyss.mobile.common.debug.recyclerview.fund.template.activityicon.ActivityIconAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.activityicon.ActivityIconHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.activityicon.ActivityIconModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.appicon.AppIconAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.appicon.AppIconHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.appicon.AppIconModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhot.FundHotAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhot.FundHotHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhot.FundHotModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhotmanager.FundHotManagerAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhotmanager.FundHotManagerHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhotmanager.FundHotManagerModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreModel
import com.githubyss.mobile.common.debug.recyclerview.viewholder.EmptyNoneHolder
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.ItemListLayout
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutModel
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListFirstLevelAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*


/**
 * ComuiRecyclerViewByMultiLayoutFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class ComuiRecyclerViewByMultiLayoutFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiRecyclerViewByMultiLayoutFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootView: View? = null
    private var layoutList = ArrayList<BaseItemModel>()
    private var rvAdapter: LayoutAdapter? = null
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View, data: BaseItemModel) {
            val id = view.id
            when (holder) {
                is EmptyNoneHolder -> {
                }
                is HeaderSeeMoreHolder -> {
                    if (data is HeaderSeeMoreModel) {
                        when (id) {
                            R.id.layout_recyclerHeaderSeeMoreItem -> {
                                ToastUtils.showMessage("${data.header}")
                            }
                            R.id.textView_recyclerHeaderSeeMoreSeeMore -> {
                                ToastUtils.showMessage("${data.header}-更多-${data.id}")
                            }
                        }
                    }
                }
                is ActivityIconHolder -> {
                    if (data is ActivityIconModel) {
                        when (id) {
                            R.id.layout_recyclerActivityIconItem -> {
                                ToastUtils.showMessage("${data.label}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is AppIconHolder -> {
                    if (data is AppIconModel) {
                        when (id) {
                            R.id.layout_recyclerAppIconItem -> {
                                ToastUtils.showMessage("${data.label}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is FundProductHolder -> {
                    if (data is FundProductModel) {
                        when (id) {
                            R.id.layout_recyclerFundProductItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                            R.id.button_recyclerFundProductIsFollowed -> {
                                ToastUtils.showMessage("${data.title}-自选状态-${holder.tglBtnIsFollowed.isChecked}")
                            }
                        }
                    }
                }
                is FundHotHolder -> {
                    if (data is FundHotModel) {
                        when (id) {
                            R.id.layout_recyclerFundHotItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is FundHotManagerHolder -> {
                    if (data is FundHotManagerModel) {
                        when (id) {
                            R.id.layout_recyclerFundHotManagerItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is GoldProductHolder -> {
                    if (data is GoldProductModel) {
                        when (id) {
                            R.id.layout_recyclerGoldProductItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
        return rootView
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    
    override fun initData() {
        val context = activity?.baseContext ?: ComuiApplication.instance
        
        val activityIconList = ArrayList<BaseItemModel>()
        activityIconList.add(ActivityIconModel("", "分享券包", "https://ActivityIcon1", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "挖宝", "https://ActivityIcon2", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "养猫", "https://ActivityIcon3", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "签到打卡", "https://ActivityIcon4", ItemType.ITEM))
        val activityList = ArrayList<BaseItemModel>()
        activityList.add(HeaderSeeMoreModel(SectionId.ACTIVITY_ICON, "活动", ItemType.HEADER))
        activityList.add(ListModel(ActivityIconAdapter(activityIconList), RecyclerView.HORIZONTAL, context, ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(activityList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val appIconList = ArrayList<BaseItemModel>()
        appIconList.add(AppIconModel("", "定投管理", "https://AppIcon1", ItemType.ITEM))
        appIconList.add(AppIconModel("", "组合投资", "https://AppIcon2", ItemType.ITEM))
        appIconList.add(AppIconModel("", "苏宁智投", "https://AppIcon3", ItemType.ITEM))
        appIconList.add(AppIconModel("", "慧智盈", "https://AppIcon4", ItemType.ITEM))
        val appList = ArrayList<BaseItemModel>()
        appList.add(HeaderSeeMoreModel(SectionId.ACTIVITY_ICON, "应用", ItemType.HEADER))
        appList.add(ListModel(AppIconAdapter(appIconList), RecyclerView.HORIZONTAL, context, ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(appList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundProductList = ArrayList<BaseItemModel>()
        fundProductList.add(HeaderSeeMoreModel(SectionId.FUND_PRODUCT, "基金产品", ItemType.HEADER))
        fundProductList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", false, "https://FundProduct1", ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", true, "https://FundProduct2", ItemType.ITEM))
        fundProductList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", true, "https://FundProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundProductAdapter(fundProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundHotList = ArrayList<BaseItemModel>()
        fundHotList.add(HeaderSeeMoreModel(SectionId.FUND_HOT, "热门基金产品", ItemType.HEADER))
        fundHotList.add(FundHotModel("易方达消费行业主题", "+15.26%", "1000万用户的投资选择", "最近一年增长率", "https://FundHot1", ItemType.ITEM))
        fundHotList.add(FundHotModel("易方达人民币主题", "+12.26%", "找10年赚10倍的方法", "最近一年增长率", "https://FundHot2", ItemType.ITEM))
        fundHotList.add(FundHotModel("易方达股票主题", "+10.65%", "123个相关产品", "最近一年增长率", "https://FundHot3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundHotAdapter(fundHotList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundHotManagerList = ArrayList<BaseItemModel>()
        fundHotManagerList.add(HeaderSeeMoreModel(SectionId.FUND_HOT_MANAGER, "热门经理人", ItemType.HEADER))
        fundHotManagerList.add(FundHotManagerModel("张静", "", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundHotManager1", ItemType.ITEM))
        fundHotManagerList.add(FundHotManagerModel("张坤", "", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundHotManager2", ItemType.ITEM))
        fundHotManagerList.add(FundHotManagerModel("王远", "", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundHotManager3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundHotManagerAdapter(fundHotManagerList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val goldProductList = ArrayList<BaseItemModel>()
        goldProductList.add(HeaderSeeMoreModel(SectionId.GOLD_PRODUCT, "黄金产品", ItemType.HEADER))
        goldProductList.add(GoldProductModel("博时黄金ETF联接C", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct1", ItemType.ITEM))
        goldProductList.add(GoldProductModel("易方达原油人民币B", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct2", ItemType.ITEM))
        goldProductList.add(GoldProductModel("易方达沥青人民币A", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(GoldProductAdapter(goldProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
    }
    
    override fun initView() {
        rvAdapter = LayoutAdapter(layoutList)
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
}
