package com.githubyss.mobile.common.debug.recyclerview.search.page

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.search.bean.DirectJumpBean
import com.githubyss.mobile.common.debug.recyclerview.search.bean.SearchResultModel
import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SearchResultModuleKey
import com.githubyss.mobile.common.debug.recyclerview.search.util.LayoutListBuildUtils
import com.githubyss.mobile.common.kit.mock.OnResponse
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarFragment
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentSearchResultBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recycler_view.template.layout.LayoutModel
import org.greenrobot.eventbus.EventBus


/**
 * SearchResultFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/15 16:51:37
 */
class SearchResultFragment : BaseToolbarFragment(R.layout.comui_fragment_search_result) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = SearchResultFragment::class.simpleName ?: "simpleName is null"
    }
    
    private val binding by bindView<ComuiFragmentSearchResultBinding>()
    
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
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        initView()
        requestData(this.searchWord, this.moduleTab, this.pageChannel, this.moduleKey)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_search_result)
    }
    
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
    
    
    /** ********** ********** ********** Function ********** ********** ********** */
    
    private fun resetData() {
        layoutList.clear()
        rvAdapter?.notifyDataSetChanged()
        isDirectJump = false
    }
    
    private fun initView() {
        rvAdapter = LayoutAdapter(layoutList)
        binding.recyclerContainer.setHasFixedSize(true)
        binding.recyclerContainer.layoutManager = LinearLayoutManager(activity)
        binding.recyclerContainer.adapter = rvAdapter
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
        
        SearchResultModel.request(searchWord, object : OnResponse<SearchResultModel> {
            override fun onSuccess(model: SearchResultModel) {
                isRequesting = false
                // if (searchResultModel.keyWord != this@SearchResultFragment.searchWord) return
                // if (searchResultModel.moduleTab == this@SearchResultFragment.moduleTab) return
                layoutList.clear()
                layoutList.addAll(LayoutListBuildUtils.buildLayoutList(context, model, searchWord, true, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
                rvAdapter?.notifyDataSetChanged()
            }
            
            override fun onFail(message: String) {
            }
        })
    }
    
    private fun gotoResultMore(@SearchResultModuleKey key: String) {
        EventBus.getDefault().postSticky(key)
        replaceFragment(SearchResultMoreFragment(), SearchResultMoreFragment.TAG, true)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            // when (holder) {
            //     is EmptyItemHolder -> {
            //     }
            //     is HeaderHasMoreHolder -> {
            //         if (data is HeaderHasMoreBean) {
            //             when (id) {
            //                 R.id.layout_recyclerHeaderHasMoreItem -> {
            //                     ToastUtils.showMessage("标题：${data.title}")
            //                     if (data.hasMore == HasMore.TRUE) {
            //                         when (data.moduleKey) {
            //                             SearchResultModuleKey.FUND_PRODUCT -> {
            //                             }
            //                             SearchResultModuleKey.FUND_TOPIC -> {
            //                             }
            //                             SearchResultModuleKey.FUND_MANAGER -> {
            //                             }
            //                             SearchResultModuleKey.GOLD_PRODUCT -> {
            //                             }
            //                             SearchResultModuleKey.INSURANCE_PRODUCT -> {
            //                             }
            //                             SearchResultModuleKey.FINANCE_AQ -> {
            //                             }
            //                             SearchResultModuleKey.FAQ -> {
            //                             }
            //                             SearchResultModuleKey.INFORMATION -> {
            //                             }
            //                             SearchResultModuleKey.WEALTH_ACCOUNT -> {
            //                             }
            //                             else -> {
            //                             }
            //                         }
            //
            //                         // gotoResultMore(this@SearchResultFragment.searchWord, this@SearchResultFragment.moduleTab, this@SearchResultFragment.pageChannel, data.moduleKey)
            //                         onMoreClickListener?.onHeaderMoreClick(data.moduleKey)
            //                     }
            //                 }
            //             }
            //         }
            //     }
            //     is ActivityIconHolder -> {
            //         if (data is ActivityIconModel) {
            //             when (id) {
            //                 R.id.layout_recyclerActivityIconItem -> {
            //                     ToastUtils.showMessage("${data.label}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is AppIconHolder -> {
            //         if (data is AppIconModel) {
            //             when (id) {
            //                 R.id.layout_recyclerAppIconItem -> {
            //                     ToastUtils.showMessage("${data.label}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is FundProductHolder -> {
            //         if (data is FundProductModel) {
            //             when (id) {
            //                 R.id.layout_recyclerFundProductItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //                 R.id.button_recyclerFundProductIsFollowed -> {
            //                     ToastUtils.showMessage("${data.title}-自选状态-${holder.tglBtnIsFollowed.text}")
            //                 }
            //             }
            //         }
            //     }
            //     is FundTopicHolder -> {
            //         if (data is FundTopicModel) {
            //             when (id) {
            //                 R.id.layout_recyclerFundTopicItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is FundManagerHolder -> {
            //         if (data is FundManagerModel) {
            //             when (id) {
            //                 R.id.layout_recyclerFundManagerItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is GoldProductHolder -> {
            //         if (data is GoldProductModel) {
            //             when (id) {
            //                 R.id.layout_recyclerGoldProductItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is InsuranceProductHolder -> {
            //         if (data is InsuranceProductModel) {
            //             when (id) {
            //                 R.id.flexboxItemInsuranceProduct -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is FinanceAqHolder -> {
            //         if (data is FinanceAqModel) {
            //             when (id) {
            //                 R.id.layout_recyclerFinanceAqItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is FaqHolder -> {
            //         if (data is FaqModel) {
            //             when (id) {
            //                 R.id.layout_recyclerFaqItem -> {
            //                     ToastUtils.showMessage("${data.content}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is InformationHolder -> {
            //         if (data is InformationModel) {
            //             when (id) {
            //                 R.id.layout_recyclerInformationItem -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //             }
            //         }
            //     }
            //     is WealthAccountHolder -> {
            //         if (data is WealthAccountModel) {
            //             when (id) {
            //                 R.id.flexboxItemWealthAccount -> {
            //                     ToastUtils.showMessage("${data.title}-${data.jumpUrl}")
            //                 }
            //                 R.id.buttonWealthAccountIsFollowed -> {
            //                     ToastUtils.showMessage("${data.title}-关注状态-${holder.binding.buttonWealthAccountIsFollowed.text}")
            //                 }
            //             }
            //         }
            //     }
            // }
        }
    }
    
    private val onLayoutClickListener: BaseItemLayout.OnLayoutClickListener = object : BaseItemLayout.OnLayoutClickListener {
        override fun onClick(position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            // when (data) {
            //     is SpecialTopicModel -> {
            //         when (id) {
            //             R.id.layout_specialTopicBg -> {
            //             }
            //             R.id.layout_specialTopicHeader -> {
            //             }
            //         }
            //     }
            //     is BannerModel -> {
            //     }
            //     is AppIconModel -> {
            //     }
            //     is SeeMoreModel -> {
            //         onMoreClickListener?.onItemMoreClick("")
            //     }
            //     is HotWordMapBean -> {
            //     }
            // }
        }
    }
    
    private val onDirectJumpListener = object : DirectJumpBean.OnDirectJumpListener {
        override fun onDirectJump(directJump: DirectJumpBean) {
            isDirectJump = true
            val jumpUrl = directJump.jumpUrl
            val uri = Uri.parse(jumpUrl)
            val scheme = uri.scheme
            if (StringUtils.isEmpty(scheme)) {
            } else {
            }
            if (ActivityUtils.isActivityAlive(activity) && StringUtils.isNotEmpty(directJump.jumpUrl)) {
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
