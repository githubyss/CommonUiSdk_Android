package com.githubyss.mobile.common.ui.app.page.recycler_view.three_layer.page

import androidx.recyclerview.widget.LinearLayoutManager
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentThreeLayerRecyclerViewBinding
import com.githubyss.mobile.common.ui.recycler_view.three_layer.ItemClickListener
import com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise.ProductInfo
import com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise.WithHeaderAdapter


/**
 * ThreeLayerRecyclerViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:02:35
 */
class ThreeLayerRecyclerViewFragment : BaseReflectBindingToolbarFragment<ComuiFragmentThreeLayerRecyclerViewBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ThreeLayerRecyclerViewFragment::class.java.simpleName
    }

    private var dataList = ArrayList<ProductInfo>()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        initView()
        initData()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_recycler_view_three_layer_title)
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        initRecyclerView(dataList)
    }

    private fun initRecyclerView(dataList: MutableList<ProductInfo>) {
        val rvAdapter = WithHeaderAdapter(activity ?: return, dataList, object : ItemClickListener<ProductInfo.ProductTemplate.ProductTemplateItem> {
            override fun onItemClick(itemData: ProductInfo.ProductTemplate.ProductTemplateItem) {

            }
        })

        binding?.recyclerviewContainer?.apply {
            layoutManager = object : LinearLayoutManager(activity) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }.apply {
                orientation = LinearLayoutManager.VERTICAL
                isSmoothScrollbarEnabled = true
                isAutoMeasureEnabled = true
            }
            adapter = rvAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }

    private fun initData() {
       // Rootresp( JsonUtils.getJSONObjectFromAssets("json/netres/product/product_info_structure.json"))
    }


    /** ****************************** Implementations ****************************** */
}
