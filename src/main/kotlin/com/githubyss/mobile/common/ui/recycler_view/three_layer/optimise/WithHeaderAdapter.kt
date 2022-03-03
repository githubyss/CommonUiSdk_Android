package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.constant.Strs
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.three_layer.ItemClickListener


class WithHeaderAdapter(private val context: Context,
                        private var productInfoList: MutableList<ProductInfo>,
                        private var itemClickListener: ItemClickListener<ProductInfo.ProductTemplate.ProductTemplateItem>) : RecyclerView.Adapter<WithHeaderAdapter.ViewHolder>() {

    private val mLayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return productInfoList.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_layout_with_header, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val productInfo = productInfoList[position]
        val homeProductOutAdapter = WithLayoutManagerAdapter(context, productInfo.productTemplateList, itemClickListener)

        val layoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager.apply {
            orientation = LinearLayoutManager.VERTICAL
            isSmoothScrollbarEnabled = true
            isAutoMeasureEnabled = true
        }
        viewHolder.recyclerViewContainer.apply {
            this.layoutManager = layoutManager
            adapter = homeProductOutAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
        if (Strs.ONE == productInfo.isMore) {
            viewHolder.tvHeaderToMore.visibility = View.VISIBLE
        }
        else {
            viewHolder.tvHeaderToMore.visibility = View.GONE
        }
        if (productInfo.productIsShow == Strs.ZERO) { //1-表示展示，0表示不展示
            viewHolder.tvHeaderName.visibility = View.GONE
        }
        else {
            viewHolder.tvHeaderName.visibility = View.VISIBLE
            viewHolder.tvHeaderName.text = productInfo.productName
        }
        viewHolder.ivHeaderIcon.visibility = View.GONE
        viewHolder.tvHeaderToMore.setOnClickListener(View.OnClickListener {
            productInfo.moreJoinType
            val productTemplateItem = ProductInfo.ProductTemplate.ProductTemplateItem(null)
            productTemplateItem.joinUrl = productInfo.moreJoinUrl
            productTemplateItem.joinType = productInfo.moreJoinType
            productTemplateItem.joinTip = productInfo.moreJoinTip
            productTemplateItem.isLogin = productInfo.moreIsLogin
            productTemplateItem.isRealName = productInfo.moreIsRealName
            if (productTemplateItem.joinType == Strs.ONE
                || productTemplateItem.joinType == Strs.ZERO
                || productTemplateItem.joinType == Strs.FOUR
                || !productInfo.moreJoinTip.isNullOrBlank()) {
                itemClickListener.onItemClick(productTemplateItem)
            }
        })
        //TODO 逻辑重复了，应该只保留一个
        viewHolder.layoutHeader.visibility = if (productInfo.productIsShow == "1") View.VISIBLE else View.GONE
        //viewHolder.layoutHeader.visibility = if (areaIsHide == "1") View.GONE else View.VISIBLE
    }

    fun notifyDataChanged(productInfoList: MutableList<ProductInfo>) {
        this.productInfoList = productInfoList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivHeaderIcon: ImageView = itemView.findViewById(R.id.iv_header_icon)
        val tvHeaderName: TextView = itemView.findViewById(R.id.tv_header_name)
        val tvHeaderToMore: TextView = itemView.findViewById(R.id.tv_header_to_more)
        val recyclerViewContainer: RecyclerView = itemView.findViewById(R.id.recyclerView_container)
        val layoutHeader: LinearLayout = itemView.findViewById(R.id.layout_header)
    }
}
