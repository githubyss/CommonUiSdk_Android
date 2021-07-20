// package com.githubyss.mobile.common.debug.recyclerview.search.fragment
//
// import android.content.Context
// import androidx.recyclerview.widget.RecyclerView
// import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.HasMore
// import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
// import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SectionId
// import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.headerhasmore.HeaderHasMoreModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic.SpecialTopicModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountAdapter
// import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountModel
// import com.githubyss.mobile.common.ui.banner.BannerModel
// import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
// import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
// import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
// import com.githubyss.mobile.common.ui.recyclerview.template.list.ListModel
// import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
//
//
// object MockRequest {
//     var keyList = ArrayList<String>()
//
//     fun requestSpecialTopic(): SpecialTopicModel {
//         val advertList = ArrayList<BannerModel>()
//         // advertList.add(BannerModel("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", "", "", ItemType.ITEM))
//         // advertList.add(BannerModel("https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", "", "", ItemType.ITEM))
//         // advertList.add(BannerModel("https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", "", "", ItemType.ITEM))
//         advertList.add(BannerModel("https://goss1.cfp.cn/creative/vcg/800/version23/VCG2156289abef.jpg", "", "", ItemType.ITEM))
//         advertList.add(BannerModel("https://goss4.cfp.cn/creative/vcg/800/version23/VCG211be3c9c31.jpg", "", "", ItemType.ITEM))
//         advertList.add(BannerModel("https://goss.cfp.cn/creative/vcg/800/version23/VCG41501259465.jpg", "", "", ItemType.ITEM))
//
//         val iconList = ArrayList<AppIconModel>()
//         iconList.add(AppIconModel("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif", "严选好基", "https://AppIcon1", "", ItemType.ITEM))
//         iconList.add(AppIconModel("https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif", "基金榜单", "https://AppIcon2", "", ItemType.ITEM))
//         iconList.add(AppIconModel("https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif", "购买基金", "https://AppIcon3", "", ItemType.ITEM))
//         iconList.add(AppIconModel("", "基金定投", "https://AppIcon4", "", ItemType.ITEM))
//
//         return SpecialTopicModel("", "新年开门红理财基金好物推荐榜单", "广告卖点", "", "", "基金", "买基金1折起，支持极速卖出，买基金1折起", "", advertList, iconList, ItemType.ITEM)
//     }
//
//     fun requestActivityIcon(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val activityIconList = ArrayList<BaseItemModel>()
//         activityIconList.add(ActivityIconModel("", "分享券包", "https://ActivityIcon1", "", ItemType.ITEM))
//         activityIconList.add(ActivityIconModel("", "挖宝", "https://ActivityIcon2", "", ItemType.ITEM))
//         activityIconList.add(ActivityIconModel("", "养猫", "https://ActivityIcon3", "", ItemType.ITEM))
//         activityIconList.add(ActivityIconModel("", "签到打卡", "https://ActivityIcon4", "", ItemType.ITEM))
//
//         val activityList = ArrayList<BaseItemModel>()
//         if (hasHeader) activityList.add(HeaderHasMoreModel(SectionId.ACTIVITY_ICON, "活动", HasMore.TRUE, activityIconList, keyList, ItemType.HEADER))
//         activityList.add(ListModel(ActivityIconAdapter(activityIconList, keyList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
//
//         return activityList
//     }
//
//     fun requestAppIcon(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val appIconList = ArrayList<BaseItemModel>()
//         appIconList.add(AppIconModel("", "定投管理", "https://AppIcon1", "", ItemType.ITEM))
//         appIconList.add(AppIconModel("", "组合投资", "https://AppIcon2", "", ItemType.ITEM))
//         appIconList.add(AppIconModel("", "苏宁智投", "https://AppIcon3", "", ItemType.ITEM))
//         appIconList.add(AppIconModel("", "慧智盈", "https://AppIcon4", "", ItemType.ITEM))
//
//         val appList = ArrayList<BaseItemModel>()
//         if (hasHeader) appList.add(HeaderSeeMoreModel(SectionId.ACTIVITY_ICON, "应用", HasMore.TRUE, ItemType.HEADER))
//         appList.add(ListModel(AppIconAdapter(appIconList, keyList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
//
//         return appList
//     }
//
//     fun requestFundProduct(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val fundProductItemList = ArrayList<BaseItemModel>()
//         fundProductItemList.add(FundProductModel("易方达消费行业基金", "+15.79%", "112041", "高风险", "混合型", "最近一年增长率", "超过800万关注", IsFollow.FALSE, "https://FundProduct1", ItemType.ITEM))
//         fundProductItemList.add(FundProductModel("易方达地产行业基金", "+12.88%", "112042", "中高风险", "混合型", "最近一年增长率", "超过800万关注", IsFollow.TRUE, "https://FundProduct2", ItemType.ITEM))
//         fundProductItemList.add(FundProductModel("博时家电行业基金", "+10.65%", "112043", "中低风险", "混合型", "最近一年增长率", "超过100万关注", IsFollow.TRUE, "https://FundProduct3", ItemType.ITEM))
//
//         val fundProductList = ArrayList<BaseItemModel>()
//         if (hasHeader) fundProductList.add(HeaderSeeMoreModel(SectionId.FUND_PRODUCT, "基金产品", HasMore.TRUE, ItemType.HEADER))
//         fundProductList.add(ListModel(FundProductAdapter(fundProductItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return fundProductList
//     }
//
//     fun requestFundTopic(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val fundTopicItemList = ArrayList<BaseItemModel>()
//         fundTopicItemList.add(FundTopicModel("易方达消费行业主题", "+15.26%", "1000万用户的投资选择", "最近一年增长率", "https://FundTopic1", ItemType.ITEM))
//         fundTopicItemList.add(FundTopicModel("易方达人民币主题", "+12.26%", "找10年赚10倍的方法", "最近一年增长率", "https://FundTopic2", ItemType.ITEM))
//         fundTopicItemList.add(FundTopicModel("易方达股票主题", "+10.65%", "123个相关产品", "最近一年增长率", "https://FundTopic3", ItemType.ITEM))
//
//         val fundTopicList = ArrayList<BaseItemModel>()
//         if (hasHeader) fundTopicList.add(HeaderSeeMoreModel(SectionId.FUND_TOPIC, "热门基金主题", HasMore.TRUE, ItemType.HEADER))
//         fundTopicList.add(ListModel(FundTopicAdapter(fundTopicItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return fundTopicList
//     }
//
//     fun requestFundManager(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val fundManagerItemList = ArrayList<BaseItemModel>()
//         fundManagerItemList.add(FundManagerModel("", "张静", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager1", ArrayList<BaseItemModel>(), ItemType.ITEM))
//         fundManagerItemList.add(FundManagerModel("", "张坤", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager2", ArrayList<BaseItemModel>(), ItemType.ITEM))
//         fundManagerItemList.add(FundManagerModel("", "王远", "任期最佳回报", "+15.26%", "基金经理简介基金经理简介基金经理简介基金经理简介基金经理简介基金经理基金经理简介基金经理简…", "https://FundManager3", ArrayList<BaseItemModel>(), ItemType.ITEM))
//
//         val fundManagerList = ArrayList<BaseItemModel>()
//         if (hasHeader) fundManagerList.add(HeaderHasMoreModel(SectionId.FUND_MANAGER, "热门经理人", HasMore.TRUE, fundManagerItemList, keyList, ItemType.HEADER))
//         fundManagerList.add(ListModel(FundManagerAdapter(fundManagerItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return fundManagerList
//     }
//
//     fun requestGoldProduct(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val goldProductItemList = ArrayList<BaseItemModel>()
//         goldProductItemList.add(GoldProductModel("博时黄金ETF联接C", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct1", ItemType.ITEM))
//         goldProductItemList.add(GoldProductModel("易方达原油人民币B", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct2", ItemType.ITEM))
//         goldProductItemList.add(GoldProductModel("易方达沥青人民币A", "256.18", "元/克", "002013", "中低风险", "混合型", "05-26 最新金价", "https://GoldProduct3", ItemType.ITEM))
//
//         val goldProductList = ArrayList<BaseItemModel>()
//         if (hasHeader) goldProductList.add(HeaderSeeMoreModel(SectionId.GOLD_PRODUCT, "黄金产品", true, ItemType.HEADER))
//         goldProductList.add(ListModel(GoldProductAdapter(goldProductItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return goldProductList
//     }
//
//     fun requestInsuranceProduct(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val insuranceProductItemList = ArrayList<BaseItemModel>()
//         insuranceProductItemList.add(InsuranceProductModel("", "华保健康百万医疗险", "最高24.3万｜无免赔｜成长无忧", "1元首付", "https://InsuranceProduct1", ItemType.ITEM))
//         insuranceProductItemList.add(InsuranceProductModel("", "交通工具意外险", "最高24.3万｜无免赔｜成长无忧", "0.99元起", "https://InsuranceProduct1", ItemType.ITEM))
//         insuranceProductItemList.add(InsuranceProductModel("", "苏小宝少儿意外险", "最高24.3万｜无免赔｜成长无忧", "6元/月起", "https://InsuranceProduct1", ItemType.ITEM))
//
//         val insuranceProductList = ArrayList<BaseItemModel>()
//         if (hasHeader) insuranceProductList.add(HeaderSeeMoreModel(SectionId.INSURANCE_PRODUCT, "保险产品", true, ItemType.HEADER))
//         insuranceProductList.add(ListModel(InsuranceProductAdapter(insuranceProductItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return insuranceProductList
//     }
//
//     fun requestFinanceAq(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val financeAqItemList = ArrayList<BaseItemModel>()
//         financeAqItemList.add(FinanceAqModel("一只基金从各方面看你都满意，但是基金的一…", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能可能不包…", "https://FinanceFaq1", ItemType.ITEM))
//         financeAqItemList.add(FinanceAqModel("基金的风险大吗？", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能…", "https://FinanceFaq2", ItemType.ITEM))
//         financeAqItemList.add(FinanceAqModel("一只基金从各方面看你都满意，但是基金的…", "这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段这是财顾的问答，可能不包含搜索字段可能…", "https://FinanceFaq3", ItemType.ITEM))
//
//         val financeAqList = ArrayList<BaseItemModel>()
//         if (hasHeader) financeAqList.add(HeaderSeeMoreModel(SectionId.FINANCE_AQ, "财顾问答", true, ItemType.HEADER))
//         financeAqList.add(ListModel(FinanceAqAdapter(financeAqItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return financeAqList
//     }
//
//     fun requestFaq(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val faqItemList = ArrayList<BaseItemModel>()
//         faqItemList.add(FaqModel("红包使用规则", "https://Faq1", ItemType.ITEM))
//         faqItemList.add(FaqModel("如何买卖交易？", "https://Faq2", ItemType.ITEM))
//         faqItemList.add(FaqModel("常见问题常见问题常见问题？", "https://Faq3", ItemType.ITEM))
//
//         val faqList = ArrayList<BaseItemModel>()
//         if (hasHeader) faqList.add(HeaderSeeMoreModel(SectionId.FAQ, "常见问题", true, ItemType.HEADER))
//         faqList.add(ListModel(FaqAdapter(faqItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return faqList
//     }
//
//     fun requestInformation(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val informationItemList = ArrayList<BaseItemModel>()
//         informationItemList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information1", ItemType.ITEM))
//         informationItemList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information2", ItemType.ITEM))
//         informationItemList.add(InformationModel("", "2019年中国厨房料理小家电销售过万2019年...", "在经销商体系最完善和成熟的北美，过去数年中，最大的汽车零售商是谁？答案或", "03-16 12:56", "相关机构", "https://Information3", ItemType.ITEM))
//
//         val informationList = ArrayList<BaseItemModel>()
//         if (hasHeader) informationList.add(HeaderSeeMoreModel(SectionId.INFORMATION, "资讯", true, ItemType.HEADER))
//         informationList.add(ListModel(InformationAdapter(informationItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return informationList
//     }
//
//     fun requestWealthAccount(context: Context, hasHeader: Boolean = false, onItemClickListener: BaseItemAdapter.OnItemClickListener? = null): ArrayList<BaseItemModel> {
//         val wealthAccountItemList = ArrayList<BaseItemModel>()
//         wealthAccountItemList.add(WealthAccountModel("", "广发基金", "说明文案说明文案说明文案说明…", "", IsFollow.FALSE, "https://WealthAccount1", ItemType.ITEM))
//         wealthAccountItemList.add(WealthAccountModel("", "苏宁银行金融市场", "说明文案说明文案说明文案说明…", "", IsFollow.TRUE, "https://WealthAccount2", ItemType.ITEM))
//         wealthAccountItemList.add(WealthAccountModel("", "投资策略研究中心", "说明文案说明文案说明文案说明…", "", IsFollow.TRUE, "https://WealthAccount3", ItemType.ITEM))
//
//         val wealthAccountList = ArrayList<BaseItemModel>()
//         if (hasHeader) wealthAccountList.add(HeaderSeeMoreModel(SectionId.WEALTH_ACCOUNT, "财富号", true, ItemType.HEADER))
//         wealthAccountList.add(ListModel(WealthAccountAdapter(wealthAccountItemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
//
//         return wealthAccountList
//     }
// }