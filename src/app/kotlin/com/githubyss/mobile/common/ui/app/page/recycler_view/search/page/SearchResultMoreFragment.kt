package com.githubyss.mobile.common.ui.app.page.recycler_view.search.page

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.kit.util.StringUtils
import com.githubyss.mobile.common.kit.util.ToastUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.bean.DirectJumpBean
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.bean.SearchResultModel
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.enumeration.SearchResultModuleKey
import com.githubyss.mobile.common.ui.app.page.recycler_view.search.util.LayoutListBuildUtils
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentSearchResultMoreBinding
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemLayout
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.template.layout.LayoutAdapter
import com.githubyss.mobile.common.ui.recycler_view.template.layout.LayoutModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONObject


/**
 * SearchResultMoreFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 20:05:25
 */
class SearchResultMoreFragment : BaseInlineBindingToolbarFragment(R.layout.comui_fragment_search_result_more) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = SearchResultMoreFragment::class.java.simpleName
    }

    private val binding by bindView<ComuiFragmentSearchResultMoreBinding>()

    private var searchWord: String = ""
    private var pageChannel: String = ""

    @SearchResultModuleKey
    private var moduleKey: String = SearchResultModuleKey.NONE

    private var pageNumber: Int = 0
    private var hasMoreData: Boolean = true
    private var isRequesting: Boolean = false

    private var layoutList = ArrayList<LayoutModel>()
    private var rvAdapter: LayoutAdapter? = null


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        initView()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_search_result_more_title)
    }

    override fun onResume() {
        super.onResume()
        resetData()
        requestData(this.searchWord, this.pageChannel, this.moduleKey)
    }

    override fun onDestroyView() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
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


    /** ****************************** Function ****************************** */

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onGetId(@SearchResultModuleKey key: String) {
        ToastUtils.showMessage("模板 key 为：${key}")
        this.moduleKey = key
        requestData("", this.pageChannel, this.moduleKey)
    }

    private fun initView() {
        rvAdapter = LayoutAdapter(layoutList, R.layout.comui_layout_bg_white_corner_none_margin_none)
        binding?.recyclerContainer?.setHasFixedSize(true)
        binding?.recyclerContainer?.layoutManager = LinearLayoutManager(activity)
        binding?.recyclerContainer?.adapter = rvAdapter
        rvAdapter?.onItemClickListener = onItemClickListener
        rvAdapter?.onLoadMoreListener = onLoadMoreListener
    }

    private fun resetData() {
        layoutList.clear()
        rvAdapter?.notifyDataSetChanged()
        pageNumber = 0
        hasMoreData = true
    }

    private fun requestData(searchWord: String, pageChannel: String, @SearchResultModuleKey moduleKey: String) {
        this.searchWord = searchWord
        this.pageChannel = pageChannel
        this.moduleKey = moduleKey

        if (!isRequesting) {
            requestDataByMock(activity ?: return, searchWord, pageChannel, moduleKey)
        }

        rvAdapter?.keyWord = searchWord
    }

    private fun requestDataByMock(context: Context, searchWord: String, pageChannel: String, @SearchResultModuleKey key: String) {
        isRequesting = true

        isRequesting = false
        var jsonString = "{}"
        val searchResultModel = SearchResultModel(JSONObject(jsonString))

        if (StringUtils.isEmpty(searchResultModel.keyWord) || searchResultModel.keyWord == this@SearchResultMoreFragment.searchWord) {
            if (searchResultModel.searchResultModuleList.isEmpty()) {
                hasMoreData = false
            }
            when (pageNumber) {
                0 -> {
                    layoutList.clear()
                    layoutList.addAll(LayoutListBuildUtils.buildLayoutList(context, searchResultModel, searchWord, false, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
                }
                else -> {
                    if (hasMoreData) {
                        layoutList.addAll(LayoutListBuildUtils.buildLayoutList(context, searchResultModel, searchWord, false, onItemClickListener, onLayoutClickListener, onDirectJumpListener))
                    }
                }
            }
            rvAdapter?.notifyDataSetChanged()
        }
    }


    /** ****************************** Implementations ****************************** */

    private val onItemClickListener = object : BaseItemAdapter.OnItemClickListener {
        override fun onItemClick(holder: RecyclerView.ViewHolder, position: Int, view: View?, data: BaseItemModel) {
            val id = view?.id
            // when (holder) {
            //     is EmptyItemHolder -> {
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
            //     is HotWordMapBean -> {
            //     }
            // }
        }
    }

    private val onDirectJumpListener = object : DirectJumpBean.OnDirectJumpListener {
        override fun onDirectJump(directJump: DirectJumpBean) {
        }
    }

    private val onLoadMoreListener = object : LayoutAdapter.OnLoadMoreListener {
        override fun onLoadMore() {
            if (hasMoreData) {
                pageNumber++
                requestDataByMock(activity ?: return, searchWord, pageChannel, moduleKey)
            }
        }
    }

}
