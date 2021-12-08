// package com.githubyss.mobile.common.ui.recycler_view.template.multi_type
//
// import androidx.recyclerview.widget.RecyclerView
// import com.githubyss.mobile.common.ui.R
//
// class ProductOutAdapter {}
//
// package com.suning.mobile.snbank.homek.adapter
//
// import android.content.Context
// import android.support.v7.widget.*
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import com.suning.mobile.snbank.R
// import com.suning.mobile.snbank.homek.model.ProductItem
// import com.suning.mobile.snbank.homek.view.HomeItemClickListener
//
// /**
//  * @author ：17021029
//  * @since : 2021/1/7
//  */
// class HomeProductOutAdapter(val context: Context,
//                             val areaName: String?,
//                             val show180: Boolean,
//                             var mProductListItems: ArrayList<ArrayList<ProductItem>>?,
//                             var mProductItemHomeClickListener: HomeItemClickListener<ProductItem>)
//     : RecyclerView.Adapter<HomeProductOutAdapter.ViewHolder>() {
//     private val mLayoutInflater = LayoutInflater.from(context)
//
//     companion object {
//         const val TYPE_250: String = "250"
//     }
//
//     fun notifyDataChanged(list: ArrayList<ArrayList<ProductItem>>?) {
//         mProductListItems = list
//         notifyDataSetChanged()
//     }
//
//     override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
//         val view = mLayoutInflater.inflate(R.layout.home_out_item, viewGroup, false)
//         return ViewHolder(view)
//     }
//
//     //【[{170}][{230}][{250}][{021}{021}{021}]】
//     fun makeBg(position: Int): HomePageBgType {
//         //        val mInItems = mProductListItems!![position]//得到最里面那一层的数组了
//         if (mProductListItems.isNullOrEmpty()) {
//             return HomePageBgType.ROUND_FOUR
//         } else {
//             val lastPosition: Int = mProductListItems!!.size - 1
//             when (mProductListItems!!.size) {//不用关心当前item是不是250,250也当是普通的，只计算它的上面是不是250，下面是不是250
//                 1 -> {//【[{170}]】
//                     return HomePageBgType.ROUND_FOUR
//                 }
//                 2 -> {//【[{170}][{230}]】
//                     return when (position) {
//                         0 -> {
//                             when (mProductListItems!![1][0].templateType) {
//                                 TYPE_250 -> HomePageBgType.ROUND_FOUR
//                                 else -> HomePageBgType.ROUND_TOP
//                             }
//                         }
//                         else -> {//position ==1
//                             when (mProductListItems!![0][0].templateType) {
//                                 TYPE_250 -> HomePageBgType.ROUND_FOUR
//                                 else -> HomePageBgType.ROUND_BOTTOM
//                             }
//                         }
//                     }
//                 }
//                 else -> {//【[{170}][{230}][{250}][{021}{021}{021}]】
//                     return when (position) {
//                         0 -> {
//                             when (mProductListItems!![1][0].templateType) {
//                                 TYPE_250 -> HomePageBgType.ROUND_FOUR
//                                 else -> HomePageBgType.ROUND_TOP
//                             }
//                         }
//                         lastPosition -> {//最后一个
//                             when (mProductListItems!![lastPosition - 1][0].templateType) {
//                                 TYPE_250 -> HomePageBgType.ROUND_FOUR
//                                 else -> HomePageBgType.ROUND_BOTTOM
//                             }
//                         }
//                         else -> {//position 中间
//                             when {//(mInItems[position].templateType)
//                                 //上下都是250
//                                 (mProductListItems!![position - 1][0].templateType == TYPE_250
//                                         && mProductListItems!![position + 1][0].templateType == TYPE_250) -> HomePageBgType.ROUND_FOUR
//                                 //上是250下不是250
//                                 (mProductListItems!![position - 1][0].templateType == TYPE_250
//                                         && mProductListItems!![position + 1][0].templateType != TYPE_250) -> HomePageBgType.ROUND_TOP
//                                 //上不是250下是250
//                                 (mProductListItems!![position - 1][0].templateType != TYPE_250
//                                         && mProductListItems!![position + 1][0].templateType == TYPE_250) -> HomePageBgType.ROUND_BOTTOM
//                                 //上下都不是250
//                                 else -> HomePageBgType.ROUND_MIDDLE
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//     }
//
//     override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//         val mProductItems = mProductListItems!![position]
//         //获取类型，然后创建不同的layout
//
//         //贷款产品(030,040)， 热门活动(051)， 稳健理财(080)， 银行存款（图片090）， 银行存款（文字100）， 我的贷款(120)
//         // 011-新人模板（0.75个屏幕），061-热门产品模板(0.75个屏幕)
//         // 021-财富模板(三个网格) 111-基金模板（三个网格）  071-优选模板(两个网格)
//
//         //写一个方法，计算出背景类型，然后传给inAdapter
//
//         if (!mProductItems.isNullOrEmpty()) {//在这里指定参数1（上圆下方），2（全方）3（上方下圆）
//             when (mProductItems[0].templateType) {//已经是最里面的那一层了
//                 "030", "040", "051", "180" -> { //一个list，只能有一个数据，只要多于一个就删除
//                     val newProduct = ArrayList<ProductItem>()
//                     if (mProductItems.size > 1) {
//                         newProduct.add(mProductItems[0])
//                     }
//                     val homeProductInAdapter = HomeProductInAdapter(context, areaName, makeBg(position), show180,
//                             if (mProductItems.size > 1) newProduct else mProductItems, mProductItemHomeClickListener)
//                     viewHolder.rvHomeOut.apply {
//                         layoutManager = object : LinearLayoutManager(context) {
//                             override fun canScrollVertically(): Boolean {
//                                 return false
//                             }
//                         }.apply {
//                             orientation = LinearLayoutManager.VERTICAL
//                             isSmoothScrollbarEnabled = true
//                             isAutoMeasureEnabled = true
//                         }
//                         adapter = homeProductInAdapter
//                         setHasFixedSize(true)
//                         isNestedScrollingEnabled = false
//                     }
//                     viewHolder.rvHomeOut.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                         override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                             super.onScrollStateChanged(recyclerView, newState)
//                             //TODO 添加滑动监听和修改......
//                         }
//
//                         override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                             super.onScrolled(recyclerView, dx, dy)
//                             //TODO 添加滑动监听和修改......
//                         }
//                     })
//                 }
//                 "080", "090", "100", "120", "130", "170", "220", "210", "240", "230", "200", "140", "250" -> {
//                     val homeProductInAdapter = HomeProductInAdapter(context, areaName, makeBg(position), show180, mProductItems, mProductItemHomeClickListener)
//                     viewHolder.rvHomeOut.apply {
//                         layoutManager = object : LinearLayoutManager(context) {
//                             override fun canScrollVertically(): Boolean {
//                                 return false
//                             }
//                         }.apply {
//                             orientation = LinearLayoutManager.VERTICAL
//                             isSmoothScrollbarEnabled = true
//                             isAutoMeasureEnabled = true
//                         }
//                         adapter = homeProductInAdapter
//                         setHasFixedSize(true)
//                         isNestedScrollingEnabled = false
//                     }
//                 }
//                 "021", "151", "111" -> {//接下来网格布局
//                     val newProduct = ArrayList<ProductItem>()
//                     if (mProductItems.size > 3) {
//                         for (i in 0..2) {
//                             newProduct.add(mProductItems[i])
//                         }
//                     }
//                     val homeProductInAdapter = HomeProductInAdapter(context, areaName, makeBg(position), show180,
//                             if (mProductItems.size > 3) newProduct else mProductItems, mProductItemHomeClickListener)
//                     viewHolder.rvHomeOut.apply {
//                         layoutManager = object : GridLayoutManager(context, 3) {
//                             override fun canScrollVertically(): Boolean {
//                                 return false
//                             }
//                         }.apply {
//                             orientation = LinearLayoutManager.VERTICAL
//                             isSmoothScrollbarEnabled = true
//                             isAutoMeasureEnabled = true
//                         }
//                         adapter = homeProductInAdapter
//                         setHasFixedSize(true)
//                         isNestedScrollingEnabled = false
//                     }
//                 }
//                 "071", "191" -> {//接下来网格布局
//                     val newProduct = ArrayList<ProductItem>()
//                     if (mProductItems.size > 2) {
//                         for (i in 0..1) {
//                             newProduct.add(mProductItems[i])
//                         }
//                     }
//                     val homeProductInAdapter = HomeProductInAdapter(context, areaName, makeBg(position), show180,
//                             if (mProductItems.size > 2) newProduct else mProductItems, mProductItemHomeClickListener)
//                     viewHolder.rvHomeOut.apply {
//                         layoutManager = object : GridLayoutManager(context, 2) {
//                             override fun canScrollVertically(): Boolean {
//                                 return false
//                             }
//                         }.apply {
//                             orientation = LinearLayoutManager.VERTICAL
//                             isSmoothScrollbarEnabled = true
//                             isAutoMeasureEnabled = true
//                         }
//                         adapter = homeProductInAdapter
//                         setHasFixedSize(true)
//                         isNestedScrollingEnabled = false
//                     }
//                 }
//                 "011", "061" -> {//瀑布流布局
//                     val homeProductInAdapter = HomeProductInAdapter(context, areaName, makeBg(position), show180, mProductItems, mProductItemHomeClickListener)
//                     //手机的密度
//                     val phoneDensity = context.resources.displayMetrics.density
//                     viewHolder.rvHomeOut.apply {
//                         layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
//                         adapter = homeProductInAdapter
//                         setHasFixedSize(true)
//                         isNestedScrollingEnabled = false
//                     }
//                     /** 控制每次滚动一个 item  */
//                     pagerRecyclerView(viewHolder.rvHomeOut)
//                 }
//             }
//         }
//     }
//
//     /**
//      * 让横向滚动的 RecyclerView 每次滚动一个 item
//      * */
//     private fun pagerRecyclerView(recyclerView: RecyclerView) {
//         var snapHelper: PagerSnapHelper = object : PagerSnapHelper() {
//             override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
//                 var top: Int = targetView.top;
//                 var out: IntArray = intArrayOf(0, 0)
//                 if (layoutManager.canScrollHorizontally()) {
//                     out[0] = distanceToCenter(layoutManager, targetView, OrientationHelper.createOrientationHelper(layoutManager, 0))
//                 } else {
//                     out[1] = top
//                 }
//
//                 return out
//             }
//
//             fun distanceToCenter(layoutManager: RecyclerView.LayoutManager, targetView: View, helper: OrientationHelper): Int {
//                 // 布局用 padding right 做的间隔，所以这里 -targetView.paddingRight， 这样才能保证中心位置准确
//                 var childCenter: Int = helper.getDecoratedStart(targetView) + (helper.getDecoratedMeasurement(targetView) - targetView.paddingRight) / 2
//                 var containerCenter: Int = if (layoutManager.clipToPadding) {
//                     helper.startAfterPadding + helper.totalSpace / 2
//                 } else {
//                     helper.end / 2
//                 }
//
//                 return childCenter - containerCenter
//             }
//         }
//         //        var snapHelper = PagerSnapHelper()
//         snapHelper.attachToRecyclerView(recyclerView)
//     }
//
//     override fun getItemCount(): Int {
//         return mProductListItems?.size ?: 0
//     }
//
//     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//         val rvHomeOut: RecyclerView = itemView.findViewById(R.id.rv_home_out)
//     }
// }