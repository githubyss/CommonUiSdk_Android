package com.githubyss.mobile.common.debug.recyclerview.search.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SectionId
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.faq.FaqModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq.FinanceAqModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager.FundManagerModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic.FundTopicModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct.GoldProductModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountModel
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseFragment
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreHolder
import com.githubyss.mobile.common.ui.recyclerview.template.headerseemore.HeaderSeeMoreModel
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.ItemListLayout
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutModel
import com.githubyss.mobile.common.ui.recyclerview.template.list.ListFirstLevelAdapter
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import kotlinx.android.synthetic.main.comui_debug_fragment_recycler_view.*
import org.greenrobot.eventbus.EventBus


/**
 * ComuiSearchResultFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class ComuiSearchResultFragment : BaseFragment() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiSearchResultFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var rootContext: Context? = null
    private var rootView: View? = null
    private var dataList = ArrayList<BaseItemModel>()
    private var rvAdapter: BaseItemAdapter? = null
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View, data: BaseItemModel) {
            val id = view.id
            when (holder) {
                is EmptyItemHolder -> {
                }
                is HeaderSeeMoreHolder -> {
                    if (data is HeaderSeeMoreModel) {
                        when (id) {
                            R.id.layout_recyclerHeaderSeeMoreItem -> {
                                ToastUtils.showMessage("标题：${data.header}")
                                when (data.id) {
                                    SectionId.ACTIVITY_ICON, SectionId.APP_ICON -> {
                                    }
                                    SectionId.FUND_PRODUCT, SectionId.FUND_TOPIC, SectionId.FUND_MANAGER, SectionId.GOLD_PRODUCT, SectionId.INSURANCE_PRODUCT, SectionId.FINANCE_AQ, SectionId.FAQ, SectionId.INFORMATION, SectionId.WEALTH_ACCOUNT -> {
                                        gotoResultMore(data.id)
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
        rootContext = activity?.baseContext ?: ComuiApplication.instance
        rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
        return rootView
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        dataList.clear()
    }
    
    override fun initView() {
        initAdapter()
        recyclerView_container.setHasFixedSize(true)
        recyclerView_container.layoutManager = LinearLayoutManager(activity)
        recyclerView_container.adapter = rvAdapter
    }
    
    
    /** ********** ********** ********** Function ********** ********** ********** */
    
    private fun initAdapter() {
        requestData(rootContext ?: return)
        rvAdapter = LayoutAdapter(dataList)
    }
    
    private fun requestData(context: Context) {
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestActivityIcon(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestAppIcon(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFundProduct(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFundTopic(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFundManager(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFundManagerWithProduct(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestGoldProduct(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestInsuranceProduct(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFinanceAq(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestFaq(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestInformation(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
        dataList.add(LayoutModel(ItemListLayout(ListFirstLevelAdapter(MockRequest.requestWealthAccount(context, true, onItemClickListener)), RecyclerView.VERTICAL, context, onItemClickListener), ItemType.ITEM))
    }
    
    private fun gotoResultMore(@SectionId id: String) {
        EventBus.getDefault().postSticky(id)
        replaceFragment(ComuiSearchResultMoreFragment(), ComuiSearchResultMoreFragment.TAG, true)
    }
}
