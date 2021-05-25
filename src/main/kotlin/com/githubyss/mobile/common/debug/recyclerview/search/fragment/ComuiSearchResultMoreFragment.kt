package com.githubyss.mobile.common.debug.recyclerview.search.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.bean.DirectJumpModel
import com.githubyss.mobile.common.debug.recyclerview.search.bean.HotWordMapModel
import com.githubyss.mobile.common.debug.recyclerview.search.bean.SearchResultModel
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SearchResultModuleKey
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
import com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic.SpecialTopicModel
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountHolder
import com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount.WealthAccountModel
import com.githubyss.mobile.common.debug.recyclerview.search.util.LayoutListBuildUtil
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.banner.BannerModel
import com.githubyss.mobile.common.ui.basemvp.BaseToolbarFragment
import com.githubyss.mobile.common.ui.databinding.ComuiDebugFragmentRecyclerViewBinding
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.template.emptyitem.EmptyItemHolder
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recyclerview.template.layout.LayoutModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONObject


/**
 * ComuiSearchResultMoreFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 20:05:25
 */
class ComuiSearchResultMoreFragment : BaseToolbarFragment<ComuiDebugFragmentRecyclerViewBinding>() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        val TAG = ComuiSearchResultMoreFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var searchWord: String = ""
    private var pageChannel: String = ""
    
    @SearchResultModuleKey
    private var moduleKey: String = SearchResultModuleKey.NONE
    
    private var pageNumber: Int = 0
    private var hasMoreData: Boolean = true
    private var isRequesting: Boolean = false
    
    private var layoutList = ArrayList<LayoutModel>()
    private var rvAdapter: LayoutAdapter? = null
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    // override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    //     rootView = inflater.inflate(R.layout.comui_debug_fragment_recycler_view, container, false)
    //     return rootView
    // }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!EventBus.getDefault()
                    .isRegistered(this)) {
            EventBus.getDefault()
                .register(this)
        }
    }
    
    override fun onResume() {
        super.onResume()
        resetData()
        requestData(this.searchWord, this.pageChannel, this.moduleKey)
    }
    
    override fun onDestroyView() {
        if (EventBus.getDefault()
                    .isRegistered(this)) {
            EventBus.getDefault()
                .unregister(this)
        }
        resetData()
        super.onDestroyView()
    }
    
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        resetData()
        if (!hidden) {
            requestData(this.searchWord, this.pageChannel, this.moduleKey)
        }
    }
    
    override fun init() {
        initView()
    }
    
    
    /** ********** ********** ********** Function ********** ********** ********** */
    
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onGetId(@SearchResultModuleKey key: String) {
        ToastUtils.showMessage("模板 key 为：${key}")
        this.moduleKey = key
        requestData("", this.pageChannel, this.moduleKey)
    }
    
    private fun resetData() {
        layoutList.clear()
        rvAdapter?.notifyDataSetChanged()
        pageNumber = 0
        hasMoreData = true
    }
    
    private fun initView() {
        rvAdapter = LayoutAdapter(layoutList, R.layout.comui_layout_bg_white_corner_none_margin_none)
        binding.recyclerViewContainer.setHasFixedSize(true)
        binding.recyclerViewContainer.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewContainer.adapter = rvAdapter
        rvAdapter?.onItemClickListener = onItemClickListener
        rvAdapter?.onLoadMoreListener = onLoadMoreListener
    }
    
    private fun requestData(searchWord: String, pageChannel: String, @SearchResultModuleKey moduleKey: String) {
        this.searchWord = searchWord
        this.pageChannel = pageChannel
        this.moduleKey = moduleKey
        
        if (!isRequesting) {
            requestDataByMock(fragmentContext ?: return, searchWord, pageChannel, moduleKey)
        }
        
        rvAdapter?.keyWord = searchWord
    }
    
    private fun requestDataByMock(context: Context, searchWord: String, pageChannel: String, @SearchResultModuleKey key: String) {
        isRequesting = true
        
        isRequesting = false
        var jsonString = "{}"
        val searchResultModel = SearchResultModel(JSONObject(jsonString))
        
        if (StringUtils.isEmpty(searchResultModel.keyWord) || searchResultModel.keyWord == this@ComuiSearchResultMoreFragment.searchWord) {
            if (searchResultModel.searchResultModuleList.isEmpty()) {
                hasMoreData = false
            }
            when (pageNumber) {
                0 -> {
                    layoutList.clear()
                    layoutList.addAll(LayoutListBuildUtil.buildLayoutList(context, searchResultModel, searchWord, false, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
                }
                else -> {
                    if (hasMoreData) {
                        layoutList.addAll(LayoutListBuildUtil.buildLayoutList(context, searchResultModel, searchWord, false, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
                    }
                }
            }
            rvAdapter?.notifyDataSetChanged()
        }
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            when (holder) {
                is EmptyItemHolder -> {
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
                is HotWordMapModel -> {
                }
            }
        }
    }
    
    private val onDirectJumpListener = object : DirectJumpModel.OnDirectJumpListener {
        override fun onDirectJump(directJump: DirectJumpModel) {
        }
    }
    
    private val onLoadMoreListener = object : LayoutAdapter.OnLoadMoreListener {
        override fun onLoadMore() {
            if (hasMoreData) {
                pageNumber++
                requestDataByMock(fragmentContext ?: return, searchWord, pageChannel, moduleKey)
            }
        }
    }
    
}
