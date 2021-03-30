package com.githubyss.mobile.common.debug.recyclerview

import android.content.Context
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
import com.githubyss.mobile.common.debug.recyclerview.fund.template.faq.FaqAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.faq.FaqHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.faq.FaqModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.financeaq.FinanceAqAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.financeaq.FinanceAqHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.financeaq.FinanceAqModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundmanager.FundManagerAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundmanager.FundManagerHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundmanager.FundManagerModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundproduct.FundProductModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundtopic.FundTopicAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundtopic.FundTopicHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.fundtopic.FundTopicModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.goldproduct.GoldProductModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.header.HeaderSeeMoreModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.information.InformationAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.information.InformationHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.information.InformationModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.insuranceproduct.InsuranceProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.insuranceproduct.InsuranceProductHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.insuranceproduct.InsuranceProductModel
import com.githubyss.mobile.common.debug.recyclerview.fund.template.wealthaccount.WealthAccountAdapter
import com.githubyss.mobile.common.debug.recyclerview.fund.template.wealthaccount.WealthAccountHolder
import com.githubyss.mobile.common.debug.recyclerview.fund.template.wealthaccount.WealthAccountModel
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
    
    private var rootContext: Context? = null
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
                                
                                layoutList.clear()
                                when (data.id) {
                                    SectionId.ACTIVITY_ICON -> {
                                    }
                                    SectionId.APP_ICON -> {
                                    }
                                    SectionId.FUND_PRODUCT -> {
                                        moreFundProduct()
                                    }
                                    SectionId.FUND_TOPIC -> {
                                        moreFundTopic()
                                    }
                                    SectionId.FUND_MANAGER -> {
                                        moreFundManager()
                                    }
                                    SectionId.GOLD_PRODUCT -> {
                                        moreGoldProduct()
                                    }
                                    SectionId.INSURANCE_PRODUCT -> {
                                        moreInsuranceProduct()
                                    }
                                    SectionId.FINANCE_AQ -> {
                                        moreFinanceAq()
                                    }
                                    SectionId.FAQ -> {
                                        moreFaq()
                                    }
                                    SectionId.INFORMATION -> {
                                        moreInformation()
                                    }
                                    SectionId.WEALTH_ACCOUNT -> {
                                        moreWealthAccount()
                                    }
                                }
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
                is FundTopicHolder -> {
                    if (data is FundTopicModel) {
                        when (id) {
                            R.id.layout_recyclerFundTopicItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is FundManagerHolder -> {
                    if (data is FundManagerModel) {
                        when (id) {
                            R.id.layout_recyclerFundManagerItem -> {
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
                is InsuranceProductHolder -> {
                    if (data is InsuranceProductModel) {
                        when (id) {
                            R.id.layout_recyclerInsuranceProductItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is FinanceAqHolder -> {
                    if (data is FinanceAqModel) {
                        when (id) {
                            R.id.layout_recyclerFinanceAqItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is FaqHolder -> {
                    if (data is FaqModel) {
                        when (id) {
                            R.id.layout_recyclerFaqItem -> {
                                ToastUtils.showMessage("${data.content}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is InformationHolder -> {
                    if (data is InformationModel) {
                        when (id) {
                            R.id.layout_recyclerInformationItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                        }
                    }
                }
                is WealthAccountHolder -> {
                    if (data is WealthAccountModel) {
                        when (id) {
                            R.id.layout_recyclerWealthAccountItem -> {
                                ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
                            }
                            R.id.button_recyclerWealthAccountIsFollowed -> {
                                ToastUtils.showMessage("${data.title}-关注状态-${holder.tglBtnIsFollowed.isChecked}")
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
        rootContext = activity?.baseContext ?: ComuiApplication.instance
        initData()
        initView()
    }
    
    override fun initData() {
        val context = rootContext ?: return
        val activityIconList = ArrayList<BaseItemModel>()
        activityIconList.add(ActivityIconModel("", "分享券包", "https://ActivityIcon1", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "挖宝", "https://ActivityIcon2", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "养猫", "https://ActivityIcon3", ItemType.ITEM))
        activityIconList.add(ActivityIconModel("", "签到打卡", "https://ActivityIcon4", ItemType.ITEM))
        val activityList = ArrayList<BaseItemModel>()
        activityList.add(HeaderSeeMoreModel(SectionId.ACTIVITY_ICON, "活动", ItemType.HEADER))
        activityList.add(ListModel(ActivityIconAdapter(activityIconList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(activityList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val appIconList = ArrayList<BaseItemModel>()
        appIconList.add(AppIconModel("", "定投管理", "https://AppIcon1", ItemType.ITEM))
        appIconList.add(AppIconModel("", "组合投资", "https://AppIcon2", ItemType.ITEM))
        appIconList.add(AppIconModel("", "苏宁智投", "https://AppIcon3", ItemType.ITEM))
        appIconList.add(AppIconModel("", "慧智盈", "https://AppIcon4", ItemType.ITEM))
        val appList = ArrayList<BaseItemModel>()
        appList.add(HeaderSeeMoreModel(SectionId.ACTIVITY_ICON, "应用", ItemType.HEADER))
        appList.add(ListModel(AppIconAdapter(appIconList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(appList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundProductList = ArrayList<BaseItemModel>()
        fundProductList.add(HeaderSeeMoreModel(SectionId.FUND_PRODUCT, "基金产品", ItemType.HEADER))
        fundProductList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", false, "https://FundProduct1", ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", true, "https://FundProduct2", ItemType.ITEM))
        fundProductList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", true, "https://FundProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundProductAdapter(fundProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundTopicList = ArrayList<BaseItemModel>()
        fundTopicList.add(HeaderSeeMoreModel(SectionId.FUND_TOPIC, "热门基金主题", ItemType.HEADER))
        fundTopicList.add(FundTopicModel("易方达消费行业主题", "+15.26%", "1000万用户的投资选择", "最近一年增长率", "https://FundTopic1", ItemType.ITEM))
        fundTopicList.add(FundTopicModel("易方达人民币主题", "+12.26%", "找10年赚10倍的方法", "最近一年增长率", "https://FundTopic2", ItemType.ITEM))
        fundTopicList.add(FundTopicModel("易方达股票主题", "+10.65%", "123个相关产品", "最近一年增长率", "https://FundTopic3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundTopicAdapter(fundTopicList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val fundManagerList = ArrayList<BaseItemModel>()
        fundManagerList.add(HeaderSeeMoreModel(SectionId.FUND_MANAGER, "热门经理人", ItemType.HEADER))
        fundManagerList.add(FundManagerModel("", "张静", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager1", ItemType.ITEM))
        fundManagerList.add(FundManagerModel("", "张坤", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager2", ItemType.ITEM))
        fundManagerList.add(FundManagerModel("", "王远", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundManagerAdapter(fundManagerList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val goldProductList = ArrayList<BaseItemModel>()
        goldProductList.add(HeaderSeeMoreModel(SectionId.GOLD_PRODUCT, "黄金产品", ItemType.HEADER))
        goldProductList.add(GoldProductModel("博时黄金ETF联接C", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct1", ItemType.ITEM))
        goldProductList.add(GoldProductModel("易方达原油人民币B", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct2", ItemType.ITEM))
        goldProductList.add(GoldProductModel("易方达沥青人民币A", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(GoldProductAdapter(goldProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val insuranceProductList = ArrayList<BaseItemModel>()
        insuranceProductList.add(HeaderSeeMoreModel(SectionId.INSURANCE_PRODUCT, "保险产品", ItemType.HEADER))
        insuranceProductList.add(InsuranceProductModel("", "华保健康百万医疗险", "最高24.3万｜无免赔｜成长无忧", "1元首付", "https://InsuranceProduct1", ItemType.ITEM))
        insuranceProductList.add(InsuranceProductModel("", "交通工具意外险", "最高24.3万｜无免赔｜成长无忧", "0.99元起", "https://InsuranceProduct1", ItemType.ITEM))
        insuranceProductList.add(InsuranceProductModel("", "苏小宝少儿意外险", "最高24.3万｜无免赔｜成长无忧", "6元/月起", "https://InsuranceProduct1", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(InsuranceProductAdapter(insuranceProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val financeAqList = ArrayList<BaseItemModel>()
        financeAqList.add(HeaderSeeMoreModel(SectionId.FINANCE_AQ, "财顾问答", ItemType.HEADER))
        financeAqList.add(FinanceAqModel("一只基金从各方面看你都满意，但是基金的一…", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能可能不包…", "https://FinanceFaq1", ItemType.ITEM))
        financeAqList.add(FinanceAqModel("基金的风险大吗？", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能…", "https://FinanceFaq2", ItemType.ITEM))
        financeAqList.add(FinanceAqModel("一只基金从各方面看你都满意，但是基金的…", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能…", "https://FinanceFaq3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FinanceAqAdapter(financeAqList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val faqList = ArrayList<BaseItemModel>()
        faqList.add(HeaderSeeMoreModel(SectionId.FAQ, "常见问题", ItemType.HEADER))
        faqList.add(FaqModel("红包使用规则", "https://Faq1", ItemType.ITEM))
        faqList.add(FaqModel("如何买卖交易？", "https://Faq2", ItemType.ITEM))
        faqList.add(FaqModel("常见问题常见问题常见问题？", "https://Faq3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FaqAdapter(faqList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val informationList = ArrayList<BaseItemModel>()
        informationList.add(HeaderSeeMoreModel(SectionId.INFORMATION, "资讯", ItemType.HEADER))
        informationList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information1", ItemType.ITEM))
        informationList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information2", ItemType.ITEM))
        informationList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(InformationAdapter(informationList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        
        val wealthAccountList = ArrayList<BaseItemModel>()
        wealthAccountList.add(HeaderSeeMoreModel(SectionId.WEALTH_ACCOUNT, "财富号", ItemType.HEADER))
        wealthAccountList.add(WealthAccountModel("", "广发基金", "说明文案说明文案说明文案说明…", false, "https://WealthAccount1", ItemType.ITEM))
        wealthAccountList.add(WealthAccountModel("", "苏宁银行金融市场", "说明文案说明文案说明文案说明…", true, "https://WealthAccount2", ItemType.ITEM))
        wealthAccountList.add(WealthAccountModel("", "投资策略研究中心", "说明文案说明文案说明文案说明…", true, "https://WealthAccount3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(WealthAccountAdapter(wealthAccountList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
    }
    
    override fun initView() {
        rvAdapter = LayoutAdapter(layoutList)
        
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
    
    override fun refreshView() {
        rvAdapter = LayoutAdapter(layoutList)
        recyclerView_container.adapter = rvAdapter
    }
    
    fun moreFundProduct() {
        val context = rootContext ?: return
        val fundProductList = ArrayList<BaseItemModel>()
        fundProductList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", false, "https://FundProduct1", ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", true, "https://FundProduct2", ItemType.ITEM))
        fundProductList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", true, "https://FundProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundProductAdapter(fundProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", false, "https://FundProduct1", ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", true, "https://FundProduct2", ItemType.ITEM))
        fundProductList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", true, "https://FundProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundProductAdapter(fundProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", false, "https://FundProduct1", ItemType.ITEM))
        fundProductList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", true, "https://FundProduct2", ItemType.ITEM))
        fundProductList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", true, "https://FundProduct3", ItemType.ITEM))
        layoutList.add(LayoutModel(ItemListLayout(FundProductAdapter(fundProductList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        refreshView()
    }
    
    fun moreFundTopic() {
    
    }
    
    fun moreFundManager() {
    
    }
    
    fun moreGoldProduct() {
    
    }
    
    fun moreInsuranceProduct() {
    
    }
    
    fun moreFinanceAq() {
    
    }
    
    fun moreFaq() {
    
    }
    
    fun moreInformation() {
    
    }
    
    fun moreWealthAccount() {
    
    }
}
