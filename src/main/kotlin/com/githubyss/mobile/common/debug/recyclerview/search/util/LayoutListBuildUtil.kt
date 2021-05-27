package com.githubyss.mobile.common.debug.recyclerview.search.util

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.bean.DirectJumpModel
import com.githubyss.mobile.common.debug.recyclerview.search.bean.SearchResultModel
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SearchResultModuleKey
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptypage.EmptyPageLayout
import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductAdapter
import com.githubyss.mobile.common.debug.recyclerview.search.template.loadmore.LoadMoreLayout
import com.githubyss.mobile.common.debug.recyclerview.search.template.seemore.SeeMoreLayout
import com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic.SpecialTopicLayout
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutModel
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListLayout
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * LayoutListBuildUtil
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/01 17:20:38
 */
object LayoutListBuildUtil {
    fun buildLayoutList(context: Context, searchResultModel: SearchResultModel?, keyWord: String, hasHeader: Boolean, onItemClickListener: BaseItemAdapter.OnItemClickListener?, onLayoutClickListener: BaseItemLayout.OnLayoutClickListener?, onDirectJumpListener: DirectJumpModel.OnDirectJumpListener?): ArrayList<LayoutModel> {
        val layoutList = ArrayList<LayoutModel>()
        if (searchResultModel == null) return layoutList
        
        val directJump = searchResultModel.directJump
        val hotWordsMap = searchResultModel.hotWordsMap
        val searchResultModuleList = searchResultModel.searchResultModuleList
        when {
            directJump != null -> {
                onDirectJumpListener?.onDirectJump(directJump)
            }
            searchResultModuleList.isNotEmpty() -> {
                for (aSearchResultModule in searchResultModuleList) {
                    val integralList = ArrayList<BaseItemModel>()
                    val itemList = aSearchResultModule.itemList
                    val keyList = aSearchResultModule.keyList
                    if (itemList.isNotEmpty()) {
                        when (aSearchResultModule.moduleKey) {
                            SearchResultModuleKey.SPECIAL_TOPIC -> {
                                layoutList.add(LayoutModel(SpecialTopicLayout(itemList[0], keyList, context, onLayoutClickListener), ItemType.ITEM))
                            }
                            SearchResultModuleKey.SEE_MORE -> {
                                layoutList.add(LayoutModel(SeeMoreLayout(itemList[0], keyWord, context, onLayoutClickListener), ItemType.ITEM))
                            }
                            else -> {
                                if (hasHeader) integralList.add(aSearchResultModule)
                                when (aSearchResultModule.moduleKey) {
                                    SearchResultModuleKey.ACTIVITY -> {
                                        val listSize = itemList.size
                                        when {
                                            listSize == 1 -> {
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize == 2 -> {
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize == 3 -> {
                                                itemList.add(ActivityIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize >= 4 -> {
                                                itemList.subList(0, 4)
                                            }
                                        }
                                        integralList.add(ListModel(ActivityIconAdapter(itemList, keyList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.APP -> {
                                        val listSize = itemList.size
                                        when {
                                            listSize == 1 -> {
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize == 2 -> {
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize == 3 -> {
                                                itemList.add(AppIconModel("", "", "", "", ItemType.ITEM))
                                            }
                                            listSize >= 4 -> {
                                                itemList.subList(0, 4)
                                            }
                                        }
                                        integralList.add(ListModel(AppIconAdapter(itemList, keyList), RecyclerView.HORIZONTAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.FUND_PRODUCT -> {
                                        integralList.add(ListModel(FundProductAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.FUND_TOPIC -> {
                                        integralList.add(ListModel(FundTopicAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.FUND_MANAGER -> {
                                        val listSize = itemList.size
                                        when {
                                            listSize == 1 -> {
                                                // val fundManagerItemList = ArrayList<BaseItemModel>()
                                                when (val item = itemList[0]) {
                                                    is FundManagerModel -> {
                                                        // fundManagerItemList.add(aItem)
                                                        // fundManagerItemList.addAll(aItem.fundProductList)
                                                        
                                                        integralList.add(ListModel(FundManagerAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                                        integralList.add(ListModel(FundProductAdapter(item.fundProductList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                                    }
                                                }
                                            }
                                            listSize > 1 -> {
                                                integralList.add(ListModel(FundManagerAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                            }
                                        }
                                    }
                                    SearchResultModuleKey.GOLD_PRODUCT -> {
                                        integralList.add(ListModel(GoldProductAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.INSURANCE_PRODUCT -> {
                                        integralList.add(ListModel(InsuranceProductAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.FINANCE_AQ -> {
                                        integralList.add(ListModel(FinanceAqAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.FAQ -> {
                                        integralList.add(ListModel(FaqAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.INFORMATION -> {
                                        integralList.add(ListModel(InformationAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                    SearchResultModuleKey.WEALTH_ACCOUNT -> {
                                        integralList.add(ListModel(WealthAccountAdapter(itemList, keyList), RecyclerView.VERTICAL, context, onItemClickListener, ItemType.ITEM))
                                    }
                                }
                                layoutList.add(LayoutModel(ListLayout(ListAdapter(integralList), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
                            }
                        }
                    }
                }
                layoutList.add(LayoutModel(LoadMoreLayout(context, onLayoutClickListener), ItemType.ITEM))
            }
            else -> {
                layoutList.add(LayoutModel(EmptyPageLayout(hotWordsMap, keyWord, context, onLayoutClickListener), ItemType.ITEM))
            }
        }
        return layoutList
    }
}
