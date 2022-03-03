// package com.githubyss.mobile.common.ui.recycler_view.three_layer.old
//
// import android.content.Context
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import android.widget.ImageView
// import android.widget.LinearLayout
// import android.widget.TextView
// import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.RecyclerView
// import com.githubyss.mobile.common.kit.constant.Strs
// import com.githubyss.mobile.common.ui.R
// import com.githubyss.mobile.common.ui.recycler_view.three_layer.ItemClickListener
//
//
// class HomeProductAllAdapter(val context: Context,
//                             var productInfos: MutableList<ProductInfo>?,
//                             private var mProductItemHomeClickListener: ItemClickListener<ProductItem>)
//     : RecyclerView.Adapter<HomeProductAllAdapter.ViewHolder>() {
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
//         val view = mLayoutInflater.inflate(R.layout.item_layout_with_header, viewGroup, false)
//         return ViewHolder(view)
//     }
//
//     override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//         val mProductInfos = productInfos!![position]
//         val homeProductOutAdapter = HomeProductOutAdapter(context, mProductInfos.productName, mProductInfos.prodRowModels, mProductItemHomeClickListener)
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
//         }
//         else {
//             viewHolder.tvHomeAssetPageProductMore.visibility = View.GONE
//         }
//         if (mProductInfos.productIsShow == Strs.ZERO) { //1-表示展示，0表示不展示
//             viewHolder.tvHomePageProductName.visibility = View.GONE
//         }
//         else {
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
//             if (productItem.joinType == Strs.ONE
//                 || productItem.joinType == Strs.ZERO
//                 || productItem.joinType == Strs.FOUR
//                 || !mProductInfos.moreJoinTip.isNullOrBlank()) {
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
//         val tvHomePageProductUrl: ImageView = itemView.findViewById(R.id.iv_header_icon)
//         val tvHomePageProductName: TextView = itemView.findViewById(R.id.tv_header_name)
//         val tvHomeAssetPageProductMore: TextView = itemView.findViewById(R.id.tv_header_to_more)
//         val itemProductRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView_container)
//         val llTitleArea: LinearLayout = itemView.findViewById(R.id.layout_header)
//     }
// }