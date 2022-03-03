// package com.githubyss.mobile.common.ui.recycler_view.template.multi_type
//
// import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.RecyclerView
// import com.githubyss.mobile.common.ui.R
//
// class ProductAllAdapter {}
//
// package com.suning.mobile.snbank.homek.adapter
//
// import android.content.Context
// import android.support.v7.widget.LinearLayoutManager
// import android.support.v7.widget.RecyclerView
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import android.widget.ImageView
// import android.widget.LinearLayout
// import android.widget.TextView
// import com.suning.mobile.snbank.R
// import com.suning.mobile.snbank.bankcard.util.PinYinUtil
// import com.githubyss.mobile.common.ui.recycler_view.three_layer.old.ProductInfo
// import com.githubyss.mobile.common.ui.recycler_view.three_layer.old.ProductItem
// import com.suning.mobile.snbank.homek.view.HomeItemClickListener
// import com.suning.mobile.snbank.kit.common.Strs
// import com.suning.mobile.snbank.ums.UmsClickListener
// import com.suning.mobile.snbank.ums.UmsConstant
// import com.suning.mobile.snbank.ums.UmsManager
//
// /**
//  * @author ：17021029
//  * @since : 2021/1/7
//  */
// class com.githubyss.mobile.common.ui.recycler_view.three_layer.old.HomeProductAllAdapter(val context: Context,
//                             var productInfos: MutableList<ProductInfo>?,
//                             private var mProductItemHomeClickListener: HomeItemClickListener<ProductItem>)
//     : RecyclerView.Adapter<com.githubyss.mobile.common.ui.recycler_view.three_layer.old.HomeProductAllAdapter.ViewHolder>() {
//     private val mLayoutInflater = LayoutInflater.from(context)
//
//     fun notifyDataChanged(products: MutableList<ProductInfo>?) {
//         productInfos = products
//         notifyDataSetChanged()
//     }
//
//     //    30 40 80 100 120 加背景
//     //    11 21 51 61 71 111 90(就一个图)不加
//     override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
//         //        if (areaIsHide == "1") {
//         //            val view = mLayoutInflater.inflate(R.layout.page_item_layout_home_asset_part_no_title, viewGroup, false)
//         //            return ViewHolder(view)
//         //        }
//         val view = mLayoutInflater.inflate(R.layout.page_item_layout_home_asset_part, viewGroup, false)
//         return ViewHolder(view)
//     }
//
//     override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//         val mProductInfos = productInfos!![position]
//         val homeProductOutAdapter = com.githubyss.mobile.common.ui.recycler_view.three_layer.old.HomeProductOutAdapter(context, mProductInfos.productName, false, mProductInfos.prodRowModels, mProductItemHomeClickListener)
//         val mLayoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
//             override fun canScrollVertically(): Boolean {
//                 return false
//             }
//         }
//         mLayoutManager.apply {
//             orientation = LinearLayoutManager.VERTICAL
//             isSmoothScrollbarEnabled = true
//             isAutoMeasureEnabled = true
//         }
//         viewHolder.itemProductRecyclerView.apply {
//             layoutManager = mLayoutManager
//             adapter = homeProductOutAdapter
//             setHasFixedSize(true)
//             isNestedScrollingEnabled = false
//         }
//         if (Strs.ONE == mProductInfos.isMore) {
//             viewHolder.tvHomeAssetPageProductMore.visibility = View.VISIBLE
//         } else {
//             viewHolder.tvHomeAssetPageProductMore.visibility = View.GONE
//         }
//         if (mProductInfos.productIsShow == Strs.ZERO) {//1-表示展示，0表示不展示
//             viewHolder.tvHomePageProductName.visibility = View.GONE
//         } else {
//             viewHolder.tvHomePageProductName.visibility = View.VISIBLE
//             viewHolder.tvHomePageProductName.text = mProductInfos.productName
//         }
//         viewHolder.tvHomePageProductUrl.visibility = View.GONE
//         viewHolder.tvHomeAssetPageProductMore.setOnClickListener(View.OnClickListener {
//             mProductInfos.moreJoinType
//             val productItem = ProductItem()
//             productItem.joinUrl = mProductInfos.moreJoinUrl
//             productItem.joinType = mProductInfos.moreJoinType
//             productItem.joinTip = mProductInfos.moreJoinTip
//             productItem.isLogin = mProductInfos.moreIsLogin
//             productItem.isRealName = mProductInfos.moreIsRealName
//             UmsManager.getInstance().onFirstPageAction(PinYinUtil.getPinYin(mProductInfos.productName) + "_" + UmsConstant.GENGDUO)
//             if (productItem.joinType == Strs.ONE
//                     || productItem.joinType == Strs.ZERO
//                     || productItem.joinType == Strs.FOUR
//                     || !mProductInfos.moreJoinTip.isNullOrBlank()) {
//                 mProductItemHomeClickListener.onItemClick(productItem)
//             }
//         })
//         //TODO 逻辑重复了，应该只保留一个
//         viewHolder.llTitleArea.visibility = if (mProductInfos.productIsShow == "1") View.VISIBLE else View.GONE
//         //viewHolder.llTitleArea.visibility = if (areaIsHide == "1") View.GONE else View.VISIBLE
//     }
//
//     override fun getItemCount(): Int {
//         return productInfos?.size ?: 0
//     }
//
//     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//         val tvHomePageProductUrl: ImageView = itemView.findViewById(R.id.tv_home_page_product_url)
//         val tvHomePageProductName: TextView = itemView.findViewById(R.id.tv_home_page_product_name)
//         val tvHomeAssetPageProductMore: TextView = itemView.findViewById(R.id.tv_home_asset_page_product_more)
//         val itemProductRecyclerView: RecyclerView = itemView.findViewById(R.id.item_product_recycler_view)
//         val llTitleArea: LinearLayout = itemView.findViewById(R.id.ll_title_area)
//     }
// }