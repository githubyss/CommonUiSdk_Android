package com.githubyss.mobile.common.debug.recyclerview.search.page

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.bean.DirectJumpModel
import com.githubyss.mobile.common.debug.recyclerview.search.bean.HotWordMapModel
import com.githubyss.mobile.common.debug.recyclerview.search.bean.SearchResultModel
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.HasMore
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SearchResultModuleKey
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.activityicon.ActivityIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.emptyitem.EmptyItemHolder
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
import com.githubyss.mobile.common.debug.recyclerview.search.template.headerhasmore.HeaderHasMoreHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.headerhasmore.HeaderHasMoreModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.information.InformationModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct.InsuranceProductModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.seemore.SeeMoreModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic.SpecialTopicModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountModel
import com.githubyss.mobile.common.debug.recyclerview.search.util.LayoutListBuildUtil
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.banner.BannerModel
import com.githubyss.mobile.common.ui.baseviewbindingpage.BaseToolbarViewBindingFragment
import com.githubyss.mobile.common.ui.databinding.ComuiDebugFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutModel
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject


/**
 * ComuiSearchResultFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class ComuiSearchResultFragment : BaseToolbarViewBindingFragment<ComuiDebugFragmentRecyclerViewBinding>() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiSearchResultFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var searchWord: String = ""
    private var moduleTab: String = ""
    private var pageChannel: String = ""
    private var moduleKey: String = ""
    
    private var isRequesting: Boolean = false
    private var isFirstInit: Boolean = false
    
    private var layoutList = ArrayList<LayoutModel>()
    private var rvAdapter: LayoutAdapter? = null
    private var isDirectJump = false
    
    var onMoreClickListener: OnMoreClickListener? = null
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    // override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //     rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
    //     return rootView
    // }
    
    // override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //     super.onViewCreated(view, savedInstanceState)
    // }
    
    override fun onResume() {
        super.onResume()
        resetData()
        requestData(this.searchWord, this.moduleTab, this.pageChannel, this.moduleKey)
    }
    
    override fun onDestroyView() {
        resetData()
        super.onDestroyView()
    }
    
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        resetData()
        if (!hidden) {
            if (!isDirectJump) requestData(this.searchWord, this.moduleTab, this.pageChannel, this.moduleKey)
        }
    }
    
    override fun init() {
        initView()
        requestData(this.searchWord, this.moduleTab, this.pageChannel, this.moduleKey)
    }
    
    
    /** ********** ********** ********** Function ********** ********** ********** */
    
    private fun resetData() {
        layoutList.clear()
        rvAdapter?.notifyDataSetChanged()
        isDirectJump = false
    }
    
    private fun initView() {
        rvAdapter = LayoutAdapter(layoutList)
        binding.recyclerViewContainer.setHasFixedSize(true)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewContainer.adapter = rvAdapter
    }
    
    private fun requestData(searchWord: String, moduleTab: String, pageChannel: String, moduleKey: String) {
        this.searchWord = searchWord
        this.moduleTab = moduleTab
        this.pageChannel = pageChannel
        this.moduleKey = moduleKey
        
        if (!isRequesting && !isFirstInit) {
            requestDataByMock(fragmentContext ?: return, searchWord)
        }
        isFirstInit = false
        
        rvAdapter?.keyWord = searchWord
        
    }
    
    private fun requestDataByMock(context: Context, searchWord: String) {
        isRequesting = true
        layoutList.clear()
        
        isRequesting = false
        val jsonString = "{}"
        val searchResultModel = SearchResultModel(JSONObject(jsonString))
        if (searchResultModel.keyWord != this@ComuiSearchResultFragment.searchWord) return
        layoutList.clear()
        if (searchResultModel.moduleTab == this@ComuiSearchResultFragment.moduleTab) {
            layoutList.addAll(LayoutListBuildUtil.buildLayoutList(context, searchResultModel, searchWord, true, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
        }
        rvAdapter?.notifyDataSetChanged()
    }
    
    private fun gotoResultMore(@SearchResultModuleKey key: String) {
        EventBus.getDefault()
            .postSticky(key)
        replaceFragment(ComuiSearchResultMoreFragment(), ComuiSearchResultMoreFragment.TAG, true)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            when (holder) {
                is EmptyItemHolder -> {
                }
                is HeaderHasMoreHolder -> {
                    if (data is HeaderHasMoreModel) {
                        when (id) {
                            R.id.layout_recyclerHeaderHasMoreItem -> {
                                ToastUtils.showMessage("标题：${data.title}")
                                if (data.hasMore == HasMore.TRUE) {
                                    when (data.moduleKey) {
                                        SearchResultModuleKey.FUND_PRODUCT -> {
                                        }
                                        SearchResultModuleKey.FUND_TOPIC -> {
                                        }
                                        SearchResultModuleKey.FUND_MANAGER -> {
                                        }
                                        SearchResultModuleKey.GOLD_PRODUCT -> {
                                        }
                                        SearchResultModuleKey.INSURANCE_PRODUCT -> {
                                        }
                                        SearchResultModuleKey.FINANCE_AQ -> {
                                        }
                                        SearchResultModuleKey.FAQ -> {
                                        }
                                        SearchResultModuleKey.INFORMATION -> {
                                        }
                                        SearchResultModuleKey.WEALTH_ACCOUNT -> {
                                        }
                                        else -> {
                                        }
                                    }
                                    
                                    // gotoResultMore(this@SearchResultFragment.searchWord, this@SearchResultFragment.moduleTab, this@SearchResultFragment.pageChannel, data.moduleKey)
                                    onMoreClickListener?.onHeaderMoreClick(data.moduleKey)
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
                                ToastUtils.showMessage("${data.title}-自选状态-${holder.tglBtnIsFollowed.text}")
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
                                ToastUtils.showMessage("${data.title}-关注状态-${holder.tglBtnIsFollowed.text}")
                            }
                        }
                    }
                }
            }
        }
    }
    
    private val onLayoutClickListener: BaseItemLayout.OnLayoutClickListener = object : BaseItemLayout.OnLayoutClickListener {
        override fun onClick(position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            when (data) {
                is SpecialTopicModel -> {
                    when (id) {
                        R.id.layout_specialTopicBg -> {
                        }
                        R.id.layout_specialTopicHeader -> {
                        }
                    }
                }
                is BannerModel -> {
                }
                is AppIconModel -> {
                }
                is SeeMoreModel -> {
                    onMoreClickListener?.onItemMoreClick("")
                }
                is HotWordMapModel -> {
                }
            }
        }
    }
    
    private val onDirectJumpListener = object : DirectJumpModel.OnDirectJumpListener {
        override fun onDirectJump(directJump: DirectJumpModel) {
            isDirectJump = true
            val jumpUrl = directJump.jumpUrl
            val uri = Uri.parse(jumpUrl)
            val scheme = uri.scheme
            if (StringUtils.isEmpty(scheme)) {
            } else {
            }
            if (!ActivityUtils.isActivityDestroy(activity) && !StringUtils.isEmpty(directJump.jumpUrl)) {
                activity?.finish()
            }
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnMoreClickListener {
        fun onHeaderMoreClick(moduleKey: String)
        fun onItemMoreClick(key: String)
    }
}
