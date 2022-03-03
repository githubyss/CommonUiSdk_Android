package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.three_layer.ItemClickListener


class WithLayoutManagerAdapter(private val context: Context,
                               private var productTemplateList: MutableList<ProductInfo.ProductTemplate>,
                               private var itemClickListener: ItemClickListener<ProductInfo.ProductTemplate.ProductTemplateItem>) : RecyclerView.Adapter<WithLayoutManagerAdapter.ViewHolder>() {

    private val mLayoutInflater = LayoutInflater.from(context)

    companion object {
    }

    override fun getItemCount(): Int {
        return productTemplateList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_layout_with_layout_manager, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productTemplate = productTemplateList[position]
        //获取类型，然后创建不同的layout

        //贷款产品(030,040)， 热门活动(051)， 稳健理财(080)， 银行存款（图片090）， 银行存款（文字100）， 我的贷款(120)
        // 011-新人模板（0.75个屏幕），061-热门产品模板(0.75个屏幕)
        // 021-财富模板(三个网格) 111-基金模板（三个网格）  071-优选模板(两个网格) 151  191

        // 写一个方法，计算出背景类型，然后传给inAdapter
        // 在这里指定参数1（上圆下方），2（全方）3（上方下圆）
        if (!productTemplate.templateItemList.isNullOrEmpty()) {
            when (val templateType = productTemplate.templateType) {
                // 纵向线性布局，只显示一个数据
                "030" -> {
                    val templateAdapter = WithTemplateAdapter(context, templateType, productTemplate.templateItemList.subList(0, 1), itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = object : LinearLayoutManager(context) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }.apply {
                            orientation = LinearLayoutManager.VERTICAL
                            isSmoothScrollbarEnabled = true
                            isAutoMeasureEnabled = true
                        }
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                    // viewHolder.rvWithLayoutManager.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    //     override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    //         super.onScrollStateChanged(recyclerView, newState)
                    //     }
                    //
                    //     override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    //         super.onScrolled(recyclerView, dx, dy)
                    //     }
                    // })
                }
                // 纵向线性布局，有多少显示多少
                "080"-> {
                    val templateAdapter = WithTemplateAdapter(context, templateType, productTemplate.templateItemList, itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = object : LinearLayoutManager(context) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }.apply {
                            orientation = LinearLayoutManager.VERTICAL
                            isSmoothScrollbarEnabled = true
                            isAutoMeasureEnabled = true
                        }
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                }
                // 网格布局，固定显示3个，不足的空着
                "111" -> {
                    val newList = ArrayList<ProductInfo.ProductTemplate.ProductTemplateItem>()
                    if (productTemplate.templateItemList.size <= 3) {
                        newList.addAll(productTemplate.templateItemList)
                    }
                    else {
                        for (i in 0..2) {
                            newList.add(productTemplate.templateItemList[i])
                        }
                    }
                    val templateAdapter = WithTemplateAdapter(context, templateType, newList, itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = object : GridLayoutManager(context, 3) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }.apply {
                            orientation = LinearLayoutManager.VERTICAL
                            isSmoothScrollbarEnabled = true
                            isAutoMeasureEnabled = true
                        }
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                }
                // 网格布局，最多显示3个，不足的补齐
                "021" -> {
                    val newList = ArrayList<ProductInfo.ProductTemplate.ProductTemplateItem>()
                    if (productTemplate.templateItemList.size <= 3) {
                        newList.addAll(productTemplate.templateItemList)
                    }
                    else {
                        for (i in 0..2) {
                            newList.add(productTemplate.templateItemList[i])
                        }
                    }
                    val templateAdapter = WithTemplateAdapter(context, templateType, newList, itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = when (productTemplate.templateItemList.size) {
                            3 -> {
                                object : GridLayoutManager(context, 3) {
                                    override fun canScrollVertically(): Boolean {
                                        return false
                                    }
                                }
                            }
                            2 -> {
                                object : GridLayoutManager(context, 2) {
                                    override fun canScrollVertically(): Boolean {
                                        return false
                                    }
                                }
                            }
                            else -> {
                                object : LinearLayoutManager(context) {
                                    override fun canScrollVertically(): Boolean {
                                        return false
                                    }
                                }
                            }
                        }.apply {
                            orientation = LinearLayoutManager.VERTICAL
                            isSmoothScrollbarEnabled = true
                            isAutoMeasureEnabled = true
                        }
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                }
                // 网格布局，最多显示2个，不足的补齐
                "071" -> {
                    val newList = ArrayList<ProductInfo.ProductTemplate.ProductTemplateItem>()
                    if (productTemplate.templateItemList.size <= 2) {
                        newList.addAll(productTemplate.templateItemList)
                    }
                    else {
                        for (i in 0..1) {
                            newList.add(productTemplate.templateItemList[i])
                        }
                    }
                    val templateAdapter = WithTemplateAdapter(context, templateType, newList, itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = when (productTemplate.templateItemList.size) {
                            2 -> {
                                object : GridLayoutManager(context, 2) {
                                    override fun canScrollVertically(): Boolean {
                                        return false
                                    }
                                }
                            }
                            else -> {
                                object : LinearLayoutManager(context) {
                                    override fun canScrollVertically(): Boolean {
                                        return false
                                    }
                                }
                            }
                        }.apply {
                            orientation = LinearLayoutManager.VERTICAL
                            isSmoothScrollbarEnabled = true
                            isAutoMeasureEnabled = true
                        }
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                }
                // 横向瀑布流布局
                "011"-> {
                    val templateAdapter = WithTemplateAdapter(context, templateType, productTemplate.templateItemList, itemClickListener)
                    viewHolder.rvWithLayoutManager.apply {
                        layoutManager = StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL)
                        adapter = templateAdapter
                        setHasFixedSize(true)
                        isNestedScrollingEnabled = false
                    }
                    /** 控制每次滚动一个 item  */
                    pagerRecyclerView(viewHolder.rvWithLayoutManager)
                }
            }
        }
    }

    fun notifyDataChanged(productTemplateList: MutableList<ProductInfo.ProductTemplate>) {
        this.productTemplateList = productTemplateList
        notifyDataSetChanged()
    }

    /**
     * 让横向滚动的 RecyclerView 每次滚动一个 item
     * */
    private fun pagerRecyclerView(recyclerView: RecyclerView) {
        val snapHelper: PagerSnapHelper = object : PagerSnapHelper() {
            override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
                val top: Int = targetView.top
                val out: IntArray = intArrayOf(0, 0)
                if (layoutManager.canScrollHorizontally()) {
                    out[0] = distanceToCenter(layoutManager, targetView, OrientationHelper.createOrientationHelper(layoutManager, RecyclerView.HORIZONTAL))
                }
                else {
                    out[1] = top
                }

                return out
            }

            fun distanceToCenter(layoutManager: RecyclerView.LayoutManager, targetView: View, helper: OrientationHelper): Int {
                // 布局用 padding right 做的间隔，所以这里 -targetView.paddingRight， 这样才能保证中心位置准确
                val childCenter: Int = helper.getDecoratedStart(targetView) + (helper.getDecoratedMeasurement(targetView) - targetView.paddingRight) / 2
                val containerCenter: Int = if (layoutManager.clipToPadding) {
                    helper.startAfterPadding + helper.totalSpace / 2
                }
                else {
                    helper.end / 2
                }

                return childCenter - containerCenter
            }
        }
        snapHelper.attachToRecyclerView(recyclerView)
    }

    //【[{170}][{230}][{250}][{021}{021}{021}]】
    // private fun makeBg(position: Int): HomePageBgType {
    //     //        val mInItems = productTemplateList!![position]//得到最里面那一层的数组了
    //     if (productTemplateList.isNullOrEmpty()) {
    //         return HomePageBgType.ROUND_FOUR
    //     }
    //     else {
    //         val lastPosition: Int = productTemplateList.size - 1
    //         when (productTemplateList.size) { //不用关心当前item是不是250,250也当是普通的，只计算它的上面是不是250，下面是不是250
    //             1 -> { //【[{170}]】
    //                 return HomePageBgType.ROUND_FOUR
    //             }
    //             2 -> { //【[{170}][{230}]】
    //                 return when (position) {
    //                     0 -> {
    //                         when (productTemplateList[1][0].templateType) {
    //                             TYPE_250 -> HomePageBgType.ROUND_FOUR
    //                             else -> HomePageBgType.ROUND_TOP
    //                         }
    //                     }
    //                     else -> { //position ==1
    //                         when (productTemplateList[0][0].templateType) {
    //                             TYPE_250 -> HomePageBgType.ROUND_FOUR
    //                             else -> HomePageBgType.ROUND_BOTTOM
    //                         }
    //                     }
    //                 }
    //             }
    //             else -> { //【[{170}][{230}][{250}][{021}{021}{021}]】
    //                 return when (position) {
    //                     0 -> {
    //                         when (productTemplateList!![1][0].templateType) {
    //                             TYPE_250 -> HomePageBgType.ROUND_FOUR
    //                             else -> HomePageBgType.ROUND_TOP
    //                         }
    //                     }
    //                     lastPosition -> { //最后一个
    //                         when (productTemplateList!![lastPosition - 1][0].templateType) {
    //                             TYPE_250 -> HomePageBgType.ROUND_FOUR
    //                             else -> HomePageBgType.ROUND_BOTTOM
    //                         }
    //                     }
    //                     else -> { //position 中间
    //                         when { //(mInItems[position].templateType)
    //                             //上下都是250
    //                             (productTemplateList!![position - 1][0].templateType == TYPE_250
    //                                     && productTemplateList!![position + 1][0].templateType == TYPE_250) -> HomePageBgType.ROUND_FOUR
    //                             //上是250下不是250
    //                             (productTemplateList!![position - 1][0].templateType == TYPE_250
    //                                     && productTemplateList!![position + 1][0].templateType != TYPE_250) -> HomePageBgType.ROUND_TOP
    //                             //上不是250下是250
    //                             (productTemplateList!![position - 1][0].templateType != TYPE_250
    //                                     && productTemplateList!![position + 1][0].templateType == TYPE_250) -> HomePageBgType.ROUND_BOTTOM
    //                             //上下都不是250
    //                             else -> HomePageBgType.ROUND_MIDDLE
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvWithLayoutManager: RecyclerView = itemView.findViewById(R.id.rv_with_layout_manager)
    }
}