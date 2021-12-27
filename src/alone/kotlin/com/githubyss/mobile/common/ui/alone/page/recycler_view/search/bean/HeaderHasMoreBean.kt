package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.bean

// import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SearchResultModuleKey
// import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.seemore.SeeMoreModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic.SpecialTopicModel
// import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.enumeration.SearchResultModuleKey
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.activity_icon.ActivityIconModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.app_icon.AppIconModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.faq.FaqModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.finance_aq.FinanceAqModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.fund_manager.FundManagerModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.fund_product.FundProductModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.fund_topic.FundTopicModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.gold_product.GoldProductModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.information.InformationModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.insurance_product.InsuranceProductModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.see_more.SeeMoreModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.special_topic.SpecialTopicModel
import com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.wealth_account.WealthAccountModel
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * HeaderHasMoreBean
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/11 09:32:11
 */
data class HeaderHasMoreBean constructor(val json: JSONObject?, @ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var moduleKey: String = ""
        private set
    
    var title: String = ""
        private set
    
    var hasMore: String = ""
        private set
    
    var itemList = ArrayList<BaseItemModel>()
        private set
    
    var keyList = ArrayList<String>()
        private set
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            moduleKey = json.optString("modelName")
            title = json.optString("title")
            hasMore = json.optString("hasMore")
            
            val itemListJson = json.optJSONArray("items")
            itemListJson?.let {
                val itemListLength = itemListJson.length()
                if (itemListLength > 0) {
                    for (i in 0 until itemListLength) {
                        val itemJson = itemListJson.getJSONObject(i)
                        when (moduleKey) {
                            SearchResultModuleKey.SPECIAL_TOPIC -> {
                                itemList.add(SpecialTopicModel(itemJson.optJSONObject("subject"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.ACTIVITY -> {
                                itemList.add(ActivityIconModel(itemJson.optJSONObject("activity2"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.APP -> {
                                itemList.add(AppIconModel(itemJson.optJSONObject("application"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.FUND_PRODUCT -> {
                                itemList.add(FundProductModel(itemJson.optJSONObject("fund"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.FUND_TOPIC -> {
                                itemList.add(FundTopicModel(itemJson.optJSONObject("fundTopic"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.FUND_MANAGER -> {
                                itemList.add(FundManagerModel(itemJson.optJSONObject("fundManager"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.GOLD_PRODUCT -> {
                                itemList.add(GoldProductModel(itemJson.optJSONObject("gold"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.INSURANCE_PRODUCT -> {
                                itemList.add(InsuranceProductModel(itemJson.optJSONObject("insurance"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.INFORMATION -> {
                                itemList.add(InformationModel(itemJson.optJSONObject("information"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.FAQ -> {
                                itemList.add(FaqModel(itemJson.optJSONObject("help"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.FINANCE_AQ -> {
                                itemList.add(FinanceAqModel(itemJson.optJSONObject("question"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.WEALTH_ACCOUNT -> {
                                itemList.add(WealthAccountModel(itemJson.optJSONObject("fortune"), ItemType.ITEM))
                            }
                            SearchResultModuleKey.SEE_MORE -> {
                                itemList.add(SeeMoreModel(itemJson.optJSONObject("hasMoreType"), ItemType.ITEM))
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
            val keyListJson = json.optJSONArray("keylist")
            if (keyListJson != null) {
                val keyListLength = keyListJson.length()
                if (keyListLength > 0) {
                    for (i in 0 until keyListLength) {
                        keyList.add(keyListJson.optString(i))
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
