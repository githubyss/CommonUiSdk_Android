package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.kit.constant.Strs
import com.githubyss.mobile.common.kit.util.isSpace
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.three_layer.HomePageBgType
import com.githubyss.mobile.common.ui.recycler_view.three_layer.ItemClickListener
import com.githubyss.mobile.common.ui.recycler_view.three_layer.OnGapClickListener
import com.google.android.flexbox.FlexboxLayout


/**
 * 暂时只用六种同一布局管理器的情况
 * 贷款产品(030,040)，热门活动(051)，稳健理财(080,130,140(略有变动))，银行存款（图片090），银行存款（文字100），我的贷款(120)
 * 021-财富模板(三个网格) 111-基金模板（三个网格）  071-优选模板(两个网格)
 * 011-新人模板（0.75个屏幕），061-热门产品模板(0.75个屏幕)
 */
class WithTemplateAdapter(private val mContext: Context,
                          private val templateType: String,
                          private var templateItemList: MutableList<ProductInfo.ProductTemplate.ProductTemplateItem>,
                          private var itemClickListener: ItemClickListener<ProductInfo.ProductTemplate.ProductTemplateItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun getItemCount(): Int {
        return templateItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        var type = 0
        when (templateType) {
            "011" -> type = 11
            "021" -> type = if (templateItemList.size == 1) 9921 else if (templateItemList.size == 2) 99921 else 21
            "030" -> type = 30
            "040" -> type = 40
            "051" -> type = 51
            "061" -> type = 61
            "071" -> type = if (templateItemList.size == 1) 9971 else 71
            "080" -> type = 80
            "090" -> type = 90
            "100" -> type = 100
            "111" -> type = 111 //该模版不要了
            "120" -> type = 120
            "130" -> type = 130
            "140" -> type = 140
            "151" -> type = if (templateItemList.size == 1) 99151 else if (templateItemList.size == 2) 999151 else 151
            "170" -> type = 170 //160是财顾，不在这里渲染
            "180" -> type = 180
            "191" -> type = if (templateItemList.size == 1) 99191 else 191
            "200" -> type = 200
            "210" -> type = 210
            "220" -> type = 220
            "230" -> type = 230
            "240" -> type = 240
            "250" -> type = 250
            "260" -> type = 260
            "270" -> type = 270
            "300" -> type = 300
            "310" -> type = 310
        }
        return type
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TypeAbstractViewHolder {
        return when (viewType) {
            //021-财富模板(三个网格) 111-基金模板（三个网格）  071-优选模板(两个网格) 151网格  191网格
            11 -> NewViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_new, viewGroup, false)) //新人专区
            21 -> WealthViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_wealth, viewGroup, false)) //财富模板(3个网格)
            9921 -> WealthViewHolderSingle(mLayoutInflater.inflate(R.layout.home_item_type_wealth_single, viewGroup, false)) //财富模板(不要网格)
            99921 -> WealthViewHolderDouble(mLayoutInflater.inflate(R.layout.home_item_type_wealth_double, viewGroup, false)) //财富模板(2个网格)
            30 -> LoanViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan_multi, viewGroup, false)) //升级贷
            40 -> Loan2ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan_multi, viewGroup, false)) //贷款微商贷
            51 -> HotActivityViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_hot_activity2, viewGroup, false)) //热门活动
            61 -> ProductViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_product_hot, viewGroup, false)) //热门产品0.75屏幕
            71 -> BetterViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_better_product, viewGroup, false)) //为你优选两个
            9971 -> BetterViewHolderSingle(mLayoutInflater.inflate(R.layout.home_item_type_better_product_single, viewGroup, false)) //为你优选不要网格
            80 -> FinanceViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_finance, viewGroup, false)) //稳健理财
            90 -> BannerSingleViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_banner_single, viewGroup, false)) //就一张图片
            100, 210 -> DepositViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_deposit, viewGroup, false)) //存款模版，也是随存随取
            111 -> FundViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_fund, viewGroup, false)) //老基金模块
            120 -> MineLoanViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan_mine, viewGroup, false)) //我的贷款模块
            130 -> FinanceViewHolder2(mLayoutInflater.inflate(R.layout.home_item_type_finance2, viewGroup, false)) //稳健理财2
            140 -> FinanceViewHolder3(mLayoutInflater.inflate(R.layout.home_item_type_finance3, viewGroup, false)) //稳健理财3
            151 -> WealthViewHolder2(mLayoutInflater.inflate(R.layout.home_item_type_wealth2, viewGroup, false)) //精选保险（融合）
            99151 -> WealthViewHolder2Single(mLayoutInflater.inflate(R.layout.home_item_type_wealth2_single, viewGroup, false)) //精选保险（融合）
            999151 -> WealthViewHolder2Double(mLayoutInflater.inflate(R.layout.home_item_type_wealth2_double, viewGroup, false)) //精选保险（融合），不要网格
            170, 220, 230 -> CommonViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_common, viewGroup, false)) //普通通用模版
            180 -> LoanTopViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan_top, viewGroup, false)) //借钱的头部
            191 -> Better191ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_better2_product, viewGroup, false)) //贷款产品，两个网格
            99191 -> Better191ViewHolderSingle(mLayoutInflater.inflate(R.layout.home_item_type_better2_product_single, viewGroup, false)) //贷款产品，不要网格
            200 -> Loan200ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan_multi3, viewGroup, false))
            240 -> Loan240ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_loan2_multi, viewGroup, false)) //我的页新微商贷
            250 -> NullViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_line, viewGroup, false)) //返回10个空间距
            260 -> Licai260ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_licai, viewGroup, false))
            270 -> HomeSimpleProduct270ViewHolder(mLayoutInflater.inflate(R.layout.list_item_home_simple_product, viewGroup, false))
            300 -> Licai300ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_licai_normal, viewGroup, false))
            310 -> Licai310ViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_licai_hot, viewGroup, false))
            else -> NullViewHolder(mLayoutInflater.inflate(R.layout.home_item_type_empty, viewGroup, false)) //遇到不认识的就返回空布局
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        if (viewHolder is TypeAbstractViewHolder) {
            viewHolder.bindHolder(templateItemList[i], i)
        }
    }

    fun setNotification(productTemplateItemList: ArrayList<ProductInfo.ProductTemplate.ProductTemplateItem>) {
        this.templateItemList = productTemplateItemList
        notifyDataSetChanged()
    }

    fun getBgResourceID(homePageBgType: HomePageBgType): Int {
        return when (homePageBgType) {
            HomePageBgType.ROUND_FOUR -> R.drawable.loan_corner_bg
            HomePageBgType.ROUND_TOP -> R.drawable.loan_corner_bg_top
            HomePageBgType.ROUND_MIDDLE -> R.drawable.loan_corner_bg_center
            HomePageBgType.ROUND_BOTTOM -> R.drawable.loan_corner_bg_bottom
        }
    }

    /**
     * 011-新人模板（0.75个屏幕）
     */
    inner class NewViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var rlNewLayout: LinearLayout = itemView.findViewById(R.id.rl_new_layout) as LinearLayout
        private var ivNewBackground: ImageView = itemView.findViewById(R.id.iv_new_background) as ImageView
        private var tvNewLeftNum: TextView = itemView.findViewById(R.id.tv_new_left_num) as TextView
        private var tvNewLeftNum2: TextView = itemView.findViewById(R.id.tv_new_left_num2) as TextView
        private var tvNewLeftNumSign: TextView = itemView.findViewById(R.id.tv_new_left_num_sign) as TextView
        private var tvNewLeftDesc: TextView = itemView.findViewById(R.id.tv_new_left_desc) as TextView
        private var tvNewRightTop: TextView = itemView.findViewById(R.id.tv_new_right_top) as TextView
        private var tvNewRightMiddle: TextView = itemView.findViewById(R.id.tv_new_right_middle) as TextView
        private var tvNewRightBottom: TextView = itemView.findViewById(R.id.tv_new_right_bottom) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // rlNewLayout.layoutParams.width = (AppConfig.getAppMobileWidth() * 0.75).toInt()
            //手机的密度
            val phoneDensity = mContext.resources.displayMetrics.density
            if (pos == ((templateItemList?.size ?: 0) - 1)) {
                rlNewLayout.setPadding(0, 0, 0, 0)
            }
            else {
                rlNewLayout.setPadding(0, 0, (10 * phoneDensity).toInt(), 0)
            }
            var oncePageWidth = rlNewLayout.layoutParams.width + (10 * phoneDensity).toInt()
            templateItem?.detail?.let {
                // loadImage(mContext, it.newBgImgUrl, ivNewBackground)
                tvNewRightBottom.text = it.newBtnName ?: ""
                //tvNewLeftNum.text = it.newRate ?: ""
                // splitRateStr(it.newRate, tvNewLeftNum, tvNewLeftNum2)
                tvNewLeftNumSign.text = it.newSign ?: ""
                tvNewLeftDesc.text = it.newRateContent ?: ""
                tvNewRightTop.text = it.newProdName ?: ""
                val sb = StringBuilder().append("")
                if (!it.newDesc1.isNullOrBlank()) {
                    sb.append(it.newDesc1)
                }
                if (!it.newDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.newDesc2)
                }
                if (!it.newDesc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.newDesc3)
                }
                tvNewRightMiddle.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 021-财富模板(三个网格)，网格
     */
    inner class WealthViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name) as TextView
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num) as TextView
        private var tvWealthNum2: TextView = itemView.findViewById(R.id.tv_wealth_num2) as TextView
        private var tvWealthNumSign: TextView = itemView.findViewById(R.id.tv_wealth_num_sign) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc) as TextView
        private var llWealthLayout: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout) as LinearLayout
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg) as LinearLayout


        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.marginLeft
            templateItem?.marginLeft
            templateItem?.roundTopLeft
            templateItem?.roundTopRight
            templateItem?.roundBottomLeft
            templateItem?.roundBottomRight
            templateItem?.marginLeft
            templateItem?.marginTop

            val phoneDensity = mContext.resources.displayMetrics.density
            // when (templateItemList.size) {
            //     1 -> { //不可能进来了
            //         when (homePageBgType) {
            //             HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //             HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //             HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //             HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //         }
            //     }
            //     2 -> { //不可能进来了
            //         if (pos == 0) {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                 HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //             }
            //         }
            //         else {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                 HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //             }
            //             llWealthLayout.setPadding((1 * phoneDensity).toInt(), 0, 0, 0)
            //         }
            //     }
            //     else -> { //只有这一种情况，只能是三个，多于三个就截取
            //         when {
            //             (pos + 1) % 3 == 0 -> { //第三个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //                 }
            //                 llWealthLayout.setPadding(0, 0, 0, 0)
            //             }
            //             pos % 3 == 0 -> { //第一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //                 }
            //                 llWealthLayout.setPadding(0, 0, 0, 0)
            //             }
            //             else -> { //中间的
            //                 llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 llWealthLayout.setPadding((1 * phoneDensity).toInt(), 0, (1 * phoneDensity).toInt(), 0)
            //             }
            //         }
            //     }
            // }
            templateItem?.detail?.let {
                tvWealthName.text = it.wealthProdName ?: ""
                //                tvWealthNum.text = it.wealthRate ?: ""
                // splitRateStr(it.wealthRate, tvWealthNum, tvWealthNum2)
                tvWealthNumSign.text = it.wealthSign ?: ""
                tvWealthDesc.text = it.wealthRateContent ?: ""
                if (!it.wealthDesc1.isNullOrBlank() && !it.wealthDesc2.isNullOrBlank()) {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(""" | """).append(it.wealthDesc2).toString()
                }
                else {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(it.wealthDesc2).append("").toString()
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class WealthViewHolderSingle(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name_single) as TextView
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num_single) as TextView
        private var tvWealthNum2: TextView = itemView.findViewById(R.id.tv_wealth_num2_single) as TextView
        private var tvWealthNumSign: TextView = itemView.findViewById(R.id.tv_wealth_num_sign_single) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc_single) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc_single) as TextView
        private var llWealthLayout: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_single) as LinearLayout
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg_single) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // when (homePageBgType) {
            //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            // }
            templateItem?.detail?.let {
                tvWealthName.text = it.wealthProdName ?: ""
                //                tvWealthNum.text = it.wealthRate ?: ""
                // splitRateStr(it.wealthRate, tvWealthNum, tvWealthNum2)
                tvWealthNumSign.text = it.wealthSign ?: ""
                tvWealthDesc.text = it.wealthRateContent ?: ""
                if (!it.wealthDesc1.isNullOrBlank() && !it.wealthDesc2.isNullOrBlank()) {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(""" | """).append(it.wealthDesc2).toString()
                }
                else {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(it.wealthDesc2).append("").toString()
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class WealthViewHolderDouble(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name_double) as TextView
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num_double) as TextView
        private var tvWealthNum2: TextView = itemView.findViewById(R.id.tv_wealth_num2_double) as TextView
        private var tvWealthNumSign: TextView = itemView.findViewById(R.id.tv_wealth_num_sign_double) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc_double) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc_double) as TextView
        private var llWealthLayout: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_double) as LinearLayout
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg_double) as LinearLayout


        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            if (pos == 0) {
                // when (homePageBgType) {
                //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
                //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
                //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
                //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
                // }
                llWealthLayout.setPadding(0, 0, (5 * phoneDensity).toInt(), 0)
            }
            else {
                // when (homePageBgType) {
                //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
                //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
                //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
                //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
                // }
                llWealthLayout.setPadding((5 * phoneDensity).toInt(), 0, 0, 0)
            }

            templateItem?.detail?.let {
                tvWealthName.text = it.wealthProdName ?: ""
                //                tvWealthNum.text = it.wealthRate ?: ""
                // splitRateStr(it.wealthRate, tvWealthNum, tvWealthNum2)
                tvWealthNumSign.text = it.wealthSign ?: ""
                tvWealthDesc.text = it.wealthRateContent ?: ""
                if (!it.wealthDesc1.isNullOrBlank() && !it.wealthDesc2.isNullOrBlank()) {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(""" | """).append(it.wealthDesc2).toString()
                }
                else {
                    tvWealthBottomDesc.text = StringBuilder().append(it.wealthDesc1).append(it.wealthDesc2).append("").toString()
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 贷款模版30，升级贷，需要使用计算出的背景
     */
    inner class LoanViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvRightTop: TextView = itemView.findViewById(R.id.tv_right_top) as TextView
        private var tvLoanTopName: TextView = itemView.findViewById<View>(R.id.tv_loan_top_name) as TextView
        private var tvLoanTopDesc: TextView = itemView.findViewById<View>(R.id.tv_loan_top_desc) as TextView
        private var tvLoanMiddleDesc: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_desc) as TextView
        private var tvLoanMiddleNum: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_num) as TextView
        private var tvLoanMiddleNumSign: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_num_sign) as TextView
        private var tvBottomNum: TextView = itemView.findViewById<View>(R.id.tv_bottom_num) as TextView
        private var tvBottomSign: TextView = itemView.findViewById<View>(R.id.tv_bottom_num_sign) as TextView
        private var tvBottomDesc: TextView = itemView.findViewById<View>(R.id.tv_bottom_desc) as TextView
        private var btBottomOne: TextView = itemView.findViewById<View>(R.id.bt_bottom_one) as TextView
        private var btBottomTwo: TextView = itemView.findViewById<View>(R.id.bt_bottom_two) as TextView
        private var llMulti: LinearLayout = itemView.findViewById<View>(R.id.ll_multi) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llMulti.setBackgroundResource(getBgResourceID(homePageBgType))
                if (it.topFlag.isNullOrBlank()) {
                    tvRightTop.visibility = View.INVISIBLE
                }
                else {
                    tvRightTop.visibility = View.VISIBLE
                    tvRightTop.text = it.topFlag
                    if (pos % 3 == 0) {
                        tvRightTop.setBackgroundResource(R.drawable.loan_corner_ok)
                    }
                    else {
                        tvRightTop.setBackgroundResource(R.drawable.loan_corner_ok_middle)
                    }
                }
                tvLoanTopName.text = it.prodName ?: ""
                val sb = StringBuilder().append("")
                if (!it.top1.isNullOrBlank()) {
                    sb.append(it.top1)
                }
                if (!it.top2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.top2)
                }
                if (!it.top3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.top3)
                }
                tvLoanTopDesc.text = sb.toString()
                if (it.middle1.isNullOrBlank()) {
                    tvLoanMiddleDesc.visibility = View.GONE
                }
                else {
                    tvLoanMiddleDesc.visibility = View.VISIBLE
                    tvLoanMiddleDesc.text = it.middle1
                }
                if (it.middle2.isNullOrBlank()) {
                    tvLoanMiddleNum.visibility = View.GONE
                }
                else {
                    tvLoanMiddleNum.visibility = View.VISIBLE
                    tvLoanMiddleNum.text = it.middle2
                }
                if (it.bottom1.isNullOrBlank()) {
                    tvBottomNum.visibility = View.INVISIBLE
                }
                else {
                    tvBottomNum.visibility = View.VISIBLE
                    tvBottomNum.text = it.bottom1
                }
                if (it.bottom2.isNullOrBlank()) {
                    tvBottomSign.visibility = View.GONE
                }
                else {
                    tvBottomSign.visibility = View.VISIBLE
                    tvBottomSign.text = it.bottom2
                }
                if (it.bottom3.isNullOrBlank()) {
                    tvBottomDesc.visibility = View.GONE
                }
                else {
                    tvBottomDesc.visibility = View.VISIBLE
                    tvBottomDesc.text = it.bottom3
                }
                if (it.button1.isNullOrBlank()) {
                    btBottomOne.visibility = View.GONE
                }
                else {
                    btBottomOne.visibility = View.VISIBLE
                    btBottomOne.text = it.button1
                }
                if (it.button2.isNullOrBlank()) {
                    btBottomTwo.visibility = View.GONE
                }
                else {
                    btBottomTwo.visibility = View.VISIBLE
                    btBottomTwo.text = it.button2
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 贷款模版40，微商贷，需要使用计算出的背景
     */
    inner class Loan2ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvRightTop: TextView = itemView.findViewById(R.id.tv_right_top) as TextView
        private var tvLoanTopName: TextView = itemView.findViewById<View>(R.id.tv_loan_top_name) as TextView
        private var tvLoanTopDesc: TextView = itemView.findViewById<View>(R.id.tv_loan_top_desc) as TextView
        private var tvLoanMiddleDesc: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_desc) as TextView
        private var tvLoanMiddleNum: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_num) as TextView
        private var tvLoanMiddleNumSign: TextView = itemView.findViewById<View>(R.id.tv_loan_middle_num_sign) as TextView
        private var tvBottomNum: TextView = itemView.findViewById<View>(R.id.tv_bottom_num) as TextView
        private var tvBottomDesc: TextView = itemView.findViewById<View>(R.id.tv_bottom_desc) as TextView
        private var btBottomOne: TextView = itemView.findViewById<View>(R.id.bt_bottom_one) as TextView
        private var btBottomTwo: TextView = itemView.findViewById<View>(R.id.bt_bottom_two) as TextView
        private var llMulti: LinearLayout = itemView.findViewById<View>(R.id.ll_multi) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            //左边八个都一样的处理逻辑，右边三个单独处理
            templateItem?.detail?.let {
                // llMulti.setBackgroundResource(getBgResourceID(homePageBgType))
                tvLoanTopName.text = it.loanProdName ?: ""
                tvLoanMiddleDesc.text = it.loanRateContent ?: ""
                tvLoanMiddleNum.text = it.loanRate ?: ""
                tvLoanMiddleNumSign.text = it.loanSign ?: ""
                tvBottomDesc.text = it.loanAmountContent ?: ""
                tvBottomNum.text = it.loanAmount ?: ""
                val sb = StringBuilder().append("")
                if (!it.loanDesc1.isNullOrBlank()) {
                    sb.append(it.loanDesc1)
                }
                if (!it.loanDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.loanDesc2)
                }
                if (!it.loanDesc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.loanDesc3)
                }
                tvLoanTopDesc.text = sb.toString()
                tvRightTop.visibility = View.INVISIBLE
                btBottomOne.visibility = View.GONE
                btBottomTwo.visibility = View.VISIBLE
                btBottomTwo.text = it.buttonName
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 热门活动，51
     */
    inner class HotActivityViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var ivActivityOne: ImageView = itemView.findViewById(R.id.iv_activity_one) as ImageView
        private var ivActivityTwo: ImageView = itemView.findViewById(R.id.iv_activity_two) as ImageView
        private var ivActivityThree: ImageView = itemView.findViewById(R.id.iv_activity_three) as ImageView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // loadImage(mContext, templateItem?.detail?.hotEventImgUrl1, ivActivityOne)
            // loadImage(mContext, templateItem?.detail?.hotEventImgUrl2, ivActivityTwo)
            // loadImage(mContext, templateItem?.detail?.hotEventImgUrl3, ivActivityThree)

            ivActivityOne.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        templateItem.joinType = templateItem.detail?.hotEventSkipType1
                        templateItem.joinUrl = templateItem.detail?.hotEventSkipUrl1
                        itemClickListener.onItemClick(templateItem)
                    }
                }
            })
            ivActivityTwo.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        templateItem.joinType = templateItem.detail?.hotEventSkipType2
                        templateItem.joinUrl = templateItem.detail?.hotEventSkipUrl2
                        itemClickListener.onItemClick(templateItem)
                    }
                }
            })
            ivActivityThree.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        templateItem.joinType = templateItem.detail?.hotEventSkipType3
                        templateItem.joinUrl = templateItem.detail?.hotEventSkipUrl3
                        itemClickListener.onItemClick(templateItem)
                    }
                }
            })
        }
    }

    /**
     * 061-热门产品模板(0.75个屏幕)
     * 添加翻页效果，不需要背景
     */
    inner class ProductViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var ivProductBg: ImageView = itemView.findViewById(R.id.iv_hot_product_bg) as ImageView
        private var tvHotName: TextView = itemView.findViewById(R.id.tv_hot_name) as TextView
        private var tvHotNum: TextView = itemView.findViewById(R.id.tv_hot_num) as TextView
        private var tvHotNum2: TextView = itemView.findViewById(R.id.tv_hot_num2) as TextView
        private var tvHotNumSign: TextView = itemView.findViewById(R.id.tv_hot_num_sign) as TextView
        private var tvHotMiddleDesc: TextView = itemView.findViewById(R.id.tv_hot_middle_desc) as TextView
        private var tvHotDescOne: TextView = itemView.findViewById(R.id.tv_hot_desc_one) as TextView
        private var tvHotEscTwo: TextView = itemView.findViewById(R.id.tv_hot_esc_two) as TextView
        private var tvHotDescThree: TextView = itemView.findViewById(R.id.tv_hot_desc_three) as TextView
        private var btNewBottom: TextView = itemView.findViewById(R.id.bt_new_bottom) as TextView
        private var btNewBottomLength: TextView = itemView.findViewById(R.id.bt_new_bottom_length) as TextView
        private var rlHotLayout: RelativeLayout = itemView.findViewById(R.id.rl_hot_layout) as RelativeLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // rlHotLayout.layoutParams.width = (AppConfig.getAppMobileWidth() * 0.75).toInt()
            //手机的密度
            val phoneDensity = mContext.resources.displayMetrics.density
            if (pos == ((templateItemList?.size ?: 0) - 1)) {
                rlHotLayout.setPadding(0, 0, 0, 0)
            }
            else {
                rlHotLayout.setPadding(0, 0, (10 * phoneDensity).toInt(), 0)
            }

            templateItem?.detail?.let {
                if (!it.hotButtonDesc.isNullOrBlank()) {
                    if (it.hotButtonDesc?.length!! > 4) {
                        btNewBottom.visibility = View.GONE
                        btNewBottomLength.visibility = View.VISIBLE
                        btNewBottomLength.text = it.hotButtonDesc
                    }
                    else {
                        btNewBottomLength.visibility = View.GONE
                        btNewBottom.visibility = View.VISIBLE
                        btNewBottom.text = it.hotButtonDesc
                    }
                }
                else {
                    btNewBottom.visibility = View.GONE
                    btNewBottomLength.visibility = View.GONE
                }
                tvHotName.text = it.hotProdName ?: ""
                //tvHotNum.text = it.hotRate ?: ""
                // splitRateStr(it.hotRate, tvHotNum, tvHotNum2)
                tvHotNumSign.text = it.hotSign ?: ""
                tvHotMiddleDesc.text = it.hotRateComment ?: ""
                // loadImage(mContext, it.hotBgImgUrl, ivProductBg)
                if (it.hotDesc1.isNullOrBlank()) {
                    tvHotDescOne.visibility = View.GONE
                }
                else {
                    tvHotDescOne.visibility = View.VISIBLE
                    tvHotDescOne.text = it.hotDesc1
                }
                if (it.hotDesc2.isNullOrBlank()) {
                    tvHotEscTwo.visibility = View.GONE
                }
                else {
                    tvHotEscTwo.visibility = View.VISIBLE
                    tvHotEscTwo.text = it.hotDesc2
                }
                if (it.hotDesc3.isNullOrBlank()) {
                    tvHotDescThree.visibility = View.GONE
                }
                else {
                    tvHotDescThree.visibility = View.VISIBLE
                    tvHotDescThree.text = it.hotDesc3
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 071-优选模板(两个网格)，最多两个不允许超过两个
     * 有大间隔，背景要调整
     */
    inner class BetterViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvBetterProName: TextView = itemView.findViewById(R.id.tv_better_pro_name) as TextView
        private var tvBetterProNum: TextView = itemView.findViewById(R.id.tv_better_pro_num) as TextView
        private var tvBetterProNum2: TextView = itemView.findViewById(R.id.tv_better_pro_num2) as TextView
        private var tvBetterProNumSign: TextView = itemView.findViewById(R.id.tv_better_pro_num_sign) as TextView
        private var tvBetterProDesc: TextView = itemView.findViewById(R.id.tv_better_pro_desc) as TextView
        private var tvBetterProBottomOne: TextView = itemView.findViewById(R.id.tv_better_pro_bottom_one) as TextView
        private var llBetterLayout: LinearLayout = itemView.findViewById(R.id.ll_better_layout) as LinearLayout
        private var llBetterBg: LinearLayout = itemView.findViewById(R.id.ll_better_bg) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            // when (templateItemList?.size) {
            //     1 -> { //这个不可能进入了，换布局了
            //         when (homePageBgType) {
            //             HomePageBgType.ROUND_FOUR -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //             HomePageBgType.ROUND_TOP -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //             HomePageBgType.ROUND_MIDDLE -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //             HomePageBgType.ROUND_BOTTOM -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //         }
            //     }
            //     else -> { //一定也只能是2
            //         when (pos % 2) {
            //             1 -> { //最后一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //                     HomePageBgType.ROUND_TOP -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //                 }
            //                 llBetterLayout.setPadding((5 * phoneDensity).toInt(), 0, 0, 0)
            //             }
            //             0 -> { //第一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //                     HomePageBgType.ROUND_TOP -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //                 }
            //                 llBetterLayout.setPadding(0, 0, (5 * phoneDensity).toInt(), 0)
            //             }
            //         }
            //     }
            // }

            templateItem?.detail?.let {
                tvBetterProName.text = it.goodProdName ?: ""
                //tvBetterProNum.text = it.goodRate ?: ""
                // splitRateStr(it.goodRate, tvBetterProNum, tvBetterProNum2)
                tvBetterProNumSign.text = it.goodRateSign ?: ""
                tvBetterProDesc.text = it.goodRateContent ?: ""
                val sb = StringBuilder()
                sb.append("")
                if (!it.goodDesc1.isNullOrBlank()) {
                    sb.append(it.goodDesc1)
                }
                if (!it.goodDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.goodDesc2)
                }
                tvBetterProBottomOne.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class BetterViewHolderSingle(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvBetterProName: TextView = itemView.findViewById(R.id.tv_better_pro_name_single) as TextView
        private var tvBetterProNum: TextView = itemView.findViewById(R.id.tv_better_pro_num_single) as TextView
        private var tvBetterProNum2: TextView = itemView.findViewById(R.id.tv_better_pro_num2_single) as TextView
        private var tvBetterProNumSign: TextView = itemView.findViewById(R.id.tv_better_pro_num_sign_single) as TextView
        private var tvBetterProDesc: TextView = itemView.findViewById(R.id.tv_better_pro_desc_single) as TextView
        private var tvBetterProBottomOne: TextView = itemView.findViewById(R.id.tv_better_pro_bottom_one_single) as TextView
        private var llBetterBg: LinearLayout = itemView.findViewById(R.id.ll_better_bg_single) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // when (homePageBgType) {
            //     HomePageBgType.ROUND_FOUR -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //     HomePageBgType.ROUND_TOP -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //     HomePageBgType.ROUND_MIDDLE -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //     HomePageBgType.ROUND_BOTTOM -> llBetterBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            // }
            templateItem?.detail?.let {
                tvBetterProName.text = it.goodProdName ?: ""
                // splitRateStr(it.goodRate, tvBetterProNum, tvBetterProNum2)
                tvBetterProNumSign.text = it.goodRateSign ?: ""
                tvBetterProDesc.text = it.goodRateContent ?: ""
                val sb = StringBuilder()
                sb.append("")
                if (!it.goodDesc1.isNullOrBlank()) {
                    sb.append(it.goodDesc1)
                }
                if (!it.goodDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.goodDesc2)
                }
                tvBetterProBottomOne.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 稳健理财,80，需要背景
     */
    inner class FinanceViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvFinanceTopName: TextView = itemView.findViewById(R.id.tv_finance_top_name) as TextView
        private var tvFinanceTopDescOne: TextView = itemView.findViewById(R.id.tv_finance_top_desc_one) as TextView
        private var tvFinanceTopDescTwo: TextView = itemView.findViewById(R.id.tv_finance_top_desc_two) as TextView
        private var tvFinanceTopDescThree: TextView = itemView.findViewById(R.id.tv_finance_top_desc_three) as TextView
        private var tvFinanceBottomNum: TextView = itemView.findViewById(R.id.tv_finance_bottom_num) as TextView
        private var tvFinanceBottom2Num: TextView = itemView.findViewById(R.id.tv_finance_bottom2_num) as TextView
        private var tvFinanceBottomNumSign: TextView = itemView.findViewById(R.id.tv_finance_bottom_num_sign) as TextView
        private var tvFinanceBottomDesc: TextView = itemView.findViewById(R.id.tv_finance_bottom_desc) as TextView
        private var tvFinanceBottomNum2: TextView = itemView.findViewById(R.id.tv_finance_bottom_num2) as TextView
        private var tvFinanceBottomNum2Sign: TextView = itemView.findViewById(R.id.tv_finance_bottom_num2_sign) as TextView
        private var tvFinanceBottomDesc2: TextView = itemView.findViewById(R.id.tv_finance_bottom_desc2) as TextView
        private var tvFinanceBottomNum3: TextView = itemView.findViewById(R.id.tv_finance_bottom_num3) as TextView
        private var tvFinanceBottomNum3Sign: TextView = itemView.findViewById(R.id.tv_finance_bottom_num3_sign) as TextView
        private var tvFinanceBottomDesc3: TextView = itemView.findViewById(R.id.tv_finance_bottom_desc3) as TextView
        private var llFinance: LinearLayout = itemView.findViewById(R.id.ll_finance) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llFinance.setBackgroundResource(getBgResourceID(homePageBgType))
                tvFinanceTopName.text = it.financeProdName ?: ""
                //tvFinanceBottomNum.text = it.financeRate ?: ""
                // splitRateStr(it.financeRate, tvFinanceBottomNum, tvFinanceBottom2Num)
                tvFinanceBottomNumSign.text = it.financeRateSign ?: ""
                tvFinanceBottomDesc.text = it.financeRateContent ?: ""
                tvFinanceBottomNum2.text = it.financeAmount ?: ""
                tvFinanceBottomNum2Sign.text = it.amtSign ?: ""
                tvFinanceBottomDesc2.text = it.financeAmountContent ?: ""
                tvFinanceBottomNum3.text = it.financeTerm ?: ""
                tvFinanceBottomNum3Sign.text = it.termSign ?: ""
                tvFinanceBottomDesc3.text = it.financeTermContent ?: ""
                if (it.financeDesc1.isNullOrBlank()) {
                    tvFinanceTopDescOne.visibility = View.GONE
                }
                else {
                    tvFinanceTopDescOne.visibility = View.VISIBLE
                    tvFinanceTopDescOne.text = it.financeDesc1
                }
                if (it.financeDesc2.isNullOrBlank()) {
                    tvFinanceTopDescTwo.visibility = View.GONE
                }
                else {
                    tvFinanceTopDescTwo.visibility = View.VISIBLE
                    tvFinanceTopDescTwo.text = it.financeDesc2
                }
                if (it.financeDesc3.isNullOrBlank()) {
                    tvFinanceTopDescThree.visibility = View.GONE
                }
                else {
                    tvFinanceTopDescThree.visibility = View.VISIBLE
                    tvFinanceTopDescThree.text = it.financeDesc3
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 银行存款（图片090）
     */
    inner class BannerSingleViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var itemTypeBannerSingle: ImageView = itemView.findViewById(R.id.item_type_banner_single) as ImageView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // loadImage(mContext, templateItem?.detail?.bannerImgUrl, itemTypeBannerSingle)
            itemTypeBannerSingle.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 银行存款（文字100）,确定可以合210合并
     */
    inner class DepositViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvDepositMsg1Left: TextView = itemView.findViewById(R.id.tv_deposit_msg1_left) as TextView
        private var tvDepositMsg1Left2: TextView = itemView.findViewById(R.id.tv_deposit_msg1_left2) as TextView
        private var tvDepositMsg1LeftSign: TextView = itemView.findViewById(R.id.tv_deposit_msg1_left_sign) as TextView
        private var tvDepositMsg1Right: TextView = itemView.findViewById(R.id.tv_deposit_msg1_right) as TextView
        private var tvDepositMsg2Left: TextView = itemView.findViewById(R.id.tv_deposit_msg2_left) as TextView
        private var tvDepositDesc1: TextView = itemView.findViewById(R.id.tv_deposit_desc1) as TextView
        private var llDeposit: LinearLayout = itemView.findViewById(R.id.ll_deposit) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llDeposit.setBackgroundResource(getBgResourceID(homePageBgType))
                //tvDepositMsg1Left.text = it.depositRate ?: ""
                // splitRateStr(it.depositRate, tvDepositMsg1Left, tvDepositMsg1Left2)
                tvDepositMsg1LeftSign.text = it.depositRateSign ?: ""
                val stringBuffer = StringBuffer()
                stringBuffer.append("")
                stringBuffer.append(it.depositProdName ?: "")
                if (!it.depositProdName2.isNullOrBlank()) {
                    stringBuffer.append(""" """)
                    stringBuffer.append(it.depositProdName2)
                }

                tvDepositMsg1Right.text = stringBuffer
                tvDepositMsg2Left.text = it.depositRateContent ?: ""
                //                if (it.depositDesc1.isNullOrBlank()) {
                //                    tvDepositDesc1.visibility = View.GONE
                //                } else {
                //                    tvDepositDesc1.visibility = View.VISIBLE
                //                    tvDepositDesc1.text = it.depositDesc1
                //                }

                val sb = StringBuilder()
                sb.append("")
                if (!it.depositDesc1.isNullOrBlank()) {
                    sb.append(it.depositDesc1)
                }
                if (!it.depositDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.depositDesc2)
                }
                if (!it.depositDesc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.depositDesc3)
                }
                tvDepositDesc1.text = sb.toString()
                //                if (it.depositDesc2.isNullOrBlank()) {
                //                    tvDepositDesc2.visibility = View.GONE
                //                } else {
                //                    tvDepositDesc2.visibility = View.VISIBLE
                //                    tvDepositDesc2.text = it.depositDesc2
                //                }
                //                if (it.depositDesc3.isNullOrBlank()) {
                //                    tvDepositDesc3.visibility = View.GONE
                //                } else {
                //                    tvDepositDesc3.visibility = View.VISIBLE
                //                    tvDepositDesc3.text = it.depositDesc3
                //                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 111-基金模板（三个网格）
     */
    inner class FundViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        // private var rivFundImage: RoundImageView = itemView.findViewById(R.id.riv_fund_image) as RoundImageView
        private var tvFundName: TextView = itemView.findViewById(R.id.tv_fund_name) as TextView
        private var tvFundDesc1: TextView = itemView.findViewById(R.id.tv_fund_desc1) as TextView
        private var tvFundDesc2: TextView = itemView.findViewById(R.id.tv_fund_desc2) as TextView
        private var llFundLayout: LinearLayout = itemView.findViewById(R.id.ll_fund_layout) as LinearLayout
        private var llFundBg: LinearLayout = itemView.findViewById(R.id.ll_fund_bg) as LinearLayout
        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            // when (templateItemList?.size) {
            //     1 -> {
            //         when (homePageBgType) {
            //             HomePageBgType.ROUND_FOUR -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //             HomePageBgType.ROUND_TOP -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //             HomePageBgType.ROUND_MIDDLE -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //             HomePageBgType.ROUND_BOTTOM -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //         }
            //     }
            //     2 -> {
            //         if (pos == 0) {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                 HomePageBgType.ROUND_TOP -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //             }
            //         }
            //         else {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                 HomePageBgType.ROUND_TOP -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //             }
            //             llFundLayout.setPadding((1 * phoneDensity).toInt(), 0, 0, 0)
            //         }
            //     }
            //     else -> {
            //         when {
            //             (pos + 1) % 3 == 0 -> { //第三个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                     HomePageBgType.ROUND_TOP -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //                 }
            //                 llFundLayout.setPadding(0, 0, 0, 0)
            //             }
            //             pos % 3 == 0 -> { //第一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                     HomePageBgType.ROUND_TOP -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //                 }
            //                 llFundLayout.setPadding(0, 0, 0, 0)
            //             }
            //             else -> { //中间的
            //                 llFundBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 llFundLayout.setPadding((1 * phoneDensity).toInt(), 0, (1 * phoneDensity).toInt(), 0)
            //             }
            //         }
            //     }
            // }
            templateItem?.detail?.let {
                tvFundName.text = it.fundProdName ?: ""
                tvFundDesc1.text = it.fundDesc1 ?: ""
                if (!it.fundDesc2.isNullOrBlank() && !it.fundDesc3.isNullOrBlank()) {
                    tvFundDesc2.text = StringBuilder().append(it.fundDesc2).append(""" | """).append(it.fundDesc3).toString()
                }
                else {
                    tvFundDesc2.text = StringBuilder().append(it.fundDesc2).append(it.fundDesc3).append("").toString()
                }
                // loadImage(mContext, it.fundImgUrl, rivFundImage)
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }


    /**
     * 我的贷款(120)
     */
    inner class MineLoanViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvMineRightTop: TextView = itemView.findViewById(R.id.tv_mine_right_top)
        private var tvMineTop: TextView = itemView.findViewById(R.id.tv_mine_top)
        private var tvMineTopDesc: TextView = itemView.findViewById(R.id.tv_mine_top_desc)
        private var tvMineMiddleNum: TextView = itemView.findViewById(R.id.tv_mine_middle_num)
        private var tvMineMiddleSign: TextView = itemView.findViewById(R.id.tv_mine_middle_sign)
        private var tvMineMiddleDesc: TextView = itemView.findViewById(R.id.tv_mine_middle_desc)
        private var tvMineMiddleRightDesc: TextView = itemView.findViewById(R.id.tv_mine_middle_right_desc)
        private var llMineMiddleRightDesc: LinearLayout = itemView.findViewById(R.id.ll_mine_middle_right_desc)
        private var tvMineBottom: TextView = itemView.findViewById(R.id.tv_mine_bottom)
        private var tvMineBottomTwo: TextView = itemView.findViewById(R.id.tv_mine_bottom_two)
        private var tvMineBottomThree: TextView = itemView.findViewById(R.id.tv_mine_bottom_three)
        private var llDEF: LinearLayout = itemView.findViewById(R.id.ll_def)
        private var llABC: LinearLayout = itemView.findViewById(R.id.ll_abc)
        private var tvMineMiddleDescOne: TextView = itemView.findViewById(R.id.tv_mine_middle_desc_one)
        private var tvMineMiddleDescTwo: TextView = itemView.findViewById(R.id.tv_mine_middle_desc_two)
        private var tvMineMiddleDescThree: TextView = itemView.findViewById(R.id.tv_mine_middle_desc_three)
        private var llLoanMine: LinearLayout = itemView.findViewById(R.id.ll_loan_mine)

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llLoanMine.setBackgroundResource(getBgResourceID(homePageBgType))
                if (it.myTopFlag.isNullOrBlank()) {
                    tvMineRightTop.visibility = View.INVISIBLE
                }
                else {
                    tvMineRightTop.visibility = View.VISIBLE
                    //                    tvMineRightTop.setBackgroundResource(R.drawable.loan_corner_ok)
                    //                    tvMineRightTop.setTextColor(Color.parseColor("#D6BA78"))
                    tvMineRightTop.text = it.myTopFlag
                }
                tvMineTop.text = it.myProdName ?: ""
                tvMineTopDesc.text = it.myProdDesc ?: ""
                if (it.myTop1.isNullOrBlank() && it.myTop2.isNullOrBlank() && it.myTop3.isNullOrBlank()) {
                    llDEF.visibility = View.GONE
                    llABC.visibility = View.VISIBLE
                }
                else {
                    llDEF.visibility = View.VISIBLE
                    llABC.visibility = View.GONE
                }
                if (it.myTop1.isNullOrBlank()) {
                    tvMineMiddleNum.visibility = View.GONE
                }
                else {
                    tvMineMiddleNum.visibility = View.VISIBLE
                    tvMineMiddleNum.text = it.myTop1
                }
                if (it.myTop2.isNullOrBlank()) {
                    tvMineMiddleSign.visibility = View.GONE
                }
                else {
                    tvMineMiddleSign.visibility = View.VISIBLE
                    tvMineMiddleSign.text = it.myTop2
                }
                if (it.myTop3.isNullOrBlank()) {
                    tvMineMiddleDesc.visibility = View.GONE
                }
                else {
                    tvMineMiddleDesc.visibility = View.VISIBLE
                    tvMineMiddleDesc.text = it.myTop3
                }
                if (it.myTop4.isNullOrBlank()) {
                    tvMineMiddleRightDesc.visibility = View.GONE
                    llMineMiddleRightDesc.visibility = View.GONE
                }
                else {
                    tvMineMiddleRightDesc.visibility = View.VISIBLE
                    llMineMiddleRightDesc.visibility = View.VISIBLE
                    tvMineMiddleRightDesc.text = it.myTop4
                }
                if (it.myMiddle1.isNullOrBlank()) {
                    tvMineMiddleDescOne.visibility = View.GONE
                }
                else {
                    tvMineMiddleDescOne.visibility = View.VISIBLE
                    tvMineMiddleDescOne.text = it.myMiddle1
                }
                if (it.myMiddle2.isNullOrBlank()) {
                    tvMineMiddleDescTwo.visibility = View.GONE
                }
                else {
                    tvMineMiddleDescTwo.visibility = View.VISIBLE
                    tvMineMiddleDescTwo.text = it.myMiddle2
                }
                if (it.myMiddle3.isNullOrBlank()) {
                    tvMineMiddleDescThree.visibility = View.GONE
                }
                else {
                    tvMineMiddleDescThree.visibility = View.VISIBLE
                    tvMineMiddleDescThree.text = it.myMiddle3
                }
                if (it.myBottom1.isNullOrBlank()) {
                    tvMineBottom.visibility = View.GONE
                }
                else {
                    tvMineBottom.visibility = View.VISIBLE
                    tvMineBottom.text = it.myBottom1
                }
                if (it.myBottom2.isNullOrBlank()) {
                    tvMineBottomTwo.visibility = View.GONE
                }
                else {
                    tvMineBottomTwo.visibility = View.VISIBLE
                    tvMineBottomTwo.text = it.myBottom2
                }
                if (it.myBottom3.isNullOrBlank()) {
                    tvMineBottomThree.visibility = View.GONE
                }
                else {
                    tvMineBottomThree.visibility = View.VISIBLE
                    tvMineBottomThree.text = it.myBottom3
                }
            }

            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 130-财富模版2（融合:目标盈30天-1期）
     */
    inner class FinanceViewHolder2(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvFinanceTopName: TextView = itemView.findViewById(R.id.tv_finance_top_name3) as TextView
        private var tvFinanceTopDescOne: TextView = itemView.findViewById(R.id.tv_finance_top_desc_one3) as TextView
        private var tvFinanceBottomNum: TextView = itemView.findViewById(R.id.tv3_finance_bottom_num) as TextView
        private var tvFinanceBottom2Num: TextView = itemView.findViewById(R.id.tv3_finance_bottom2_num) as TextView
        private var tvFinanceBottomNumSign: TextView = itemView.findViewById(R.id.tv3_finance_bottom_num_sign) as TextView
        private var tv3FinanceBottomAdd: TextView = itemView.findViewById(R.id.tv3_finance_bottom_add) as TextView
        private var tvFinanceBottomDesc: TextView = itemView.findViewById(R.id.tv3_finance_bottom_desc) as TextView
        private var tvFinanceBottomNum2: TextView = itemView.findViewById(R.id.tv3_finance_bottom_num2) as TextView
        private var tvFinanceBottomDesc2: TextView = itemView.findViewById(R.id.tv3_finance_bottom_desc2) as TextView
        private var llFinance2: LinearLayout = itemView.findViewById(R.id.ll_finance2) as LinearLayout

        @SuppressLint("SetTextI18n")
        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llFinance2.setBackgroundResource(getBgResourceID(homePageBgType))
                tvFinanceTopName.text = it.productName ?: ""
                tv3FinanceBottomAdd.text = it.addSign ?: ""
                if (it.desc6.isNullOrBlank()) {
                    tvFinanceTopDescOne.visibility = View.GONE
                }
                else {
                    tvFinanceTopDescOne.visibility = View.VISIBLE
                    tvFinanceTopDescOne.text = it.desc6
                }
                // splitRateStr(it.rate, tvFinanceBottomNum, tvFinanceBottom2Num)
                tvFinanceBottomNumSign.text = it.rateSign ?: ""
                tvFinanceBottomDesc.text = it.rateDesc ?: ""
                val sb = StringBuilder()
                sb.append("")
                if (!it.desc1.isNullOrBlank()) {
                    sb.append(it.desc1)
                }
                if (!it.desc2.isNullOrBlank()) {
                    sb.append(""" """)
                    sb.append(it.desc2)
                }
                tvFinanceBottomNum2.text = sb.toString()

                val sb1 = StringBuilder()
                sb1.append("")
                if (!it.desc3.isNullOrBlank()) {
                    sb1.append(it.desc3)
                }
                if (!it.desc4.isNullOrBlank()) {
                    sb1.append(""" | """)
                    sb1.append(it.desc4)
                }
                if (!it.desc5.isNullOrBlank()) {
                    sb1.append(""" | """)
                    sb1.append(it.desc5)
                }
                tvFinanceBottomDesc2.text = sb1.toString()

            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 140-财富模版3（融合:创赢16号产品）
     */
    inner class FinanceViewHolder3(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvFinanceTopName: TextView = itemView.findViewById(R.id.tv_finance_top_name2) as TextView
        private var tvFinanceTopDescOne: TextView = itemView.findViewById(R.id.tv_finance_top_desc_one2) as TextView
        private var tvFinanceBottomNum: TextView = itemView.findViewById(R.id.tv2_finance_bottom_num) as TextView
        private var tvFinanceBottom2Num: TextView = itemView.findViewById(R.id.tv2_finance_bottom2_num) as TextView
        private var tvFinanceBottomNumSign: TextView = itemView.findViewById(R.id.tv2_finance_bottom_num_sign) as TextView
        private var tvFinanceBottomDesc: TextView = itemView.findViewById(R.id.tv2_finance_bottom_desc) as TextView
        private var tvFinanceBottomNum2: TextView = itemView.findViewById(R.id.tv2_finance_bottom_num2) as TextView
        private var tvFinanceBottomDesc2: TextView = itemView.findViewById(R.id.tv2_finance_bottom_desc2) as TextView
        private var tvFinanceBottomNum3: TextView = itemView.findViewById(R.id.tv2_finance_bottom_num3) as TextView
        private var tvFinanceBottomDesc3: TextView = itemView.findViewById(R.id.tv2_finance_bottom_desc3) as TextView
        private var llFinance3: LinearLayout = itemView.findViewById(R.id.ll_finance3) as LinearLayout

        @SuppressLint("SetTextI18n")
        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llFinance3.setBackgroundResource(getBgResourceID(homePageBgType))
                tvFinanceTopName.text = it.productName ?: ""
                if (it.productDesc1.isNullOrBlank() && it.productDesc2.isNullOrBlank()) {
                    tvFinanceTopDescOne.visibility = View.GONE
                }
                else {
                    tvFinanceTopDescOne.visibility = View.VISIBLE
                    tvFinanceTopDescOne.text = it.productDesc1 + """ """ + it.productDesc2
                }
                // splitRateStr(it.rate, tvFinanceBottomNum, tvFinanceBottom2Num)
                tvFinanceBottomNumSign.text = it.rateSign ?: ""
                tvFinanceBottomDesc.text = it.rateDesc ?: ""
                tvFinanceBottomNum2.text = it.desc1 ?: ""
                tvFinanceBottomDesc2.text = it.desc3 ?: ""
                tvFinanceBottomNum3.text = it.desc2 ?: ""
                tvFinanceBottomDesc3.text = it.desc4 ?: ""

            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 151-精选保险（融合），网格，计算自己的内部背景即可
     */
    inner class WealthViewHolder2(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name2) as TextView
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num2) as TextView
        private var tvWealthNumSign: TextView = itemView.findViewById(R.id.tv_wealth_num_sign2) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc2) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc2) as TextView
        private var llWealthLayout: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout2) as LinearLayout
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg2) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            // when (templateItemList?.size) {
            //     1 -> { //不可能进来了
            //         when (homePageBgType) {
            //             HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //             HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //             HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //             HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //         }
            //     }
            //     2 -> { //不可能进来了
            //         if (pos == 0) {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                 HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //             }
            //         }
            //         else {
            //             when (homePageBgType) {
            //                 HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                 HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                 HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //             }
            //             llWealthLayout.setPadding((1 * phoneDensity).toInt(), 0, 0, 0)
            //         }
            //     }
            //     else -> {
            //         when {
            //             (pos + 1) % 3 == 0 -> { //第三个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right)
            //                     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_right_bottom)
            //                 }
            //                 llWealthLayout.setPadding(0, 0, 0, 0)
            //             }
            //             pos % 3 == 0 -> { //第一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left)
            //                     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_left_bottom)
            //                 }
            //                 llWealthLayout.setPadding(0, 0, 0, 0)
            //             }
            //             else -> { //中间的
            //                 llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                 llWealthLayout.setPadding((1 * phoneDensity).toInt(), 0, (1 * phoneDensity).toInt(), 0)
            //             }
            //         }
            //     }
            // }
            templateItem?.detail?.let {
                tvWealthName.text = it.productName ?: ""
                tvWealthNum.text = it.desc1 ?: ""
                //splitRateStr(it.wealthRate, tvWealthNum, tvWealthNum2)
                //                tvWealthNumSign.text = it.wealthSign ?: ""
                tvWealthDesc.text = it.desc2 ?: ""
                if (!it.desc3.isNullOrBlank() && !it.desc4.isNullOrBlank()) {
                    tvWealthBottomDesc.text = StringBuilder().append(it.desc3).append(""" """).append(it.desc4).toString()
                }
                else {
                    tvWealthBottomDesc.text = StringBuilder().append(it.desc3).append(it.desc4).append("").toString()
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class WealthViewHolder2Single(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name2_single) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc2_single) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc2_single) as TextView
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg2_single) as LinearLayout
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num2_single) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // when (homePageBgType) {
            //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
            //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            // }
            templateItem?.detail?.let {
                tvWealthName.text = it.productName ?: ""
                tvWealthNum.text = it.desc1 ?: ""
                tvWealthDesc.text = it.desc2 ?: ""
                val sb = StringBuilder().append("")
                if (!it.desc1.isNullOrBlank() && (!it.desc3.isNullOrBlank() || !it.desc4.isNullOrBlank())) {
                    sb.append(""" | """)
                }
                if (!it.desc3.isNullOrBlank()) {
                    sb.append(it.desc3)
                }
                if (!it.desc4.isNullOrBlank()) {
                    sb.append(if (!it.desc3.isNullOrBlank()) """ | """ else "")
                    sb.append(it.desc4)
                }
                tvWealthBottomDesc.text = sb.toString()

            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class WealthViewHolder2Double(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvWealthName: TextView = itemView.findViewById(R.id.tv_wealth_name2_double) as TextView
        private var tvWealthNum: TextView = itemView.findViewById(R.id.tv_wealth_num2_double) as TextView
        private var tvWealthNumSign: TextView = itemView.findViewById(R.id.tv_wealth_num_sign2_double) as TextView
        private var tvWealthDesc: TextView = itemView.findViewById(R.id.tv_wealth_desc2_double) as TextView
        private var tvWealthBottomDesc: TextView = itemView.findViewById(R.id.tv_wealth_bottom_desc2_double) as TextView
        private var llWealthLayout: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout2_double) as LinearLayout
        private var llWealthLayoutBg: LinearLayout = itemView.findViewById(R.id.ll_wealth_layout_bg2_double) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            if (pos == 0) {
                // when (homePageBgType) {
                //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
                //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
                //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
                //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
                // }
                llWealthLayout.setPadding(0, 0, (5 * phoneDensity).toInt(), 0)
            }
            else {
                // when (homePageBgType) {
                //     HomePageBgType.ROUND_FOUR -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg)
                //     HomePageBgType.ROUND_TOP -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_top)
                //     HomePageBgType.ROUND_MIDDLE -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_center)
                //     HomePageBgType.ROUND_BOTTOM -> llWealthLayoutBg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
                // }
                llWealthLayout.setPadding((5 * phoneDensity).toInt(), 0, 0, 0)
            }


            templateItem?.detail?.let {
                tvWealthName.text = it.productName ?: ""
                tvWealthNum.text = it.desc1 ?: ""
                //splitRateStr(it.wealthRate, tvWealthNum, tvWealthNum2)
                //                tvWealthNumSign.text = it.wealthSign ?: ""
                tvWealthDesc.text = it.desc2 ?: ""
                if (!it.desc3.isNullOrBlank() && !it.desc4.isNullOrBlank()) {
                    tvWealthBottomDesc.text = StringBuilder().append(it.desc3).append(""" """).append(it.desc4).toString()
                }
                else {
                    tvWealthBottomDesc.text = StringBuilder().append(it.desc3).append(it.desc4).append("").toString()
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 170\220\230我的页的普通的只有一行的模版
     * TODO
     */
    inner class CommonViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var ivCommonIcon: ImageView = itemView.findViewById(R.id.iv_common_icon) as ImageView
        private var tvCommonName: TextView = itemView.findViewById(R.id.tv_common_name) as TextView
        private var tvCommonDesc: TextView = itemView.findViewById(R.id.tv_common_desc) as TextView
        private var rlTypeCommon: RelativeLayout = itemView.findViewById(R.id.rl_type_common) as RelativeLayout


        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // rlTypeCommon.setBackgroundResource(getBgResourceID(homePageBgType))
                tvCommonName.text = it.productName
                tvCommonDesc.text = it.productDesc
                // loadImage(mContext, it.productIcon, ivCommonIcon)
                rlTypeCommon.setOnClickListener(object : OnGapClickListener() {
                    override fun onGapClick(v: View?) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                })
            }
        }
    }

    /**
     * 180借钱页面的头部布局
     */
    inner class LoanTopViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {

        private var tvLoanTopProductName = itemView.findViewById(R.id.tv_loan_top_product_name) as TextView
        private var ivLoanTopHotIcon = itemView.findViewById(R.id.iv_loan_top_hot_icon) as ImageView
        private var tvLoanTopDesc1 = itemView.findViewById(R.id.tv_loan_top_desc1) as TextView
        private var tvLoanTopDesc2 = itemView.findViewById(R.id.tv_loan_top_desc2) as TextView
        private var tvLoanTopDesc3 = itemView.findViewById(R.id.tv_loan_top_desc3) as TextView
        private var tvLoanTopDesc4 = itemView.findViewById(R.id.tv_loan_top_desc4) as TextView
        private var fblLoanTopCard = itemView.findViewById(R.id.fbl_loan_top_card) as FlexboxLayout
        private var flLoanTopView = itemView.findViewById(R.id.fl_loan_top_view) as FlexboxLayout
        private var tvLoanTopAmt = itemView.findViewById(R.id.tv_loan_top_amt) as TextView
        private var btnLoanTopPrimary = itemView.findViewById(R.id.btn_loan_top_primary) as Button

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            flLoanTopView.visibility = View.VISIBLE
            templateItem?.detail?.let {
                tvLoanTopProductName.text = it.productName
                // loadImage(mContext, it.hotIcon, ivLoanTopHotIcon)

                if (isSpace(it.desc1)) {
                    tvLoanTopDesc1.visibility = View.GONE
                }
                else {
                    tvLoanTopDesc1.visibility = View.VISIBLE
                    tvLoanTopDesc1.text = it.desc1
                }

                if (isSpace(it.desc2)) {
                    tvLoanTopDesc2.visibility = View.GONE
                }
                else {
                    tvLoanTopDesc2.visibility = View.VISIBLE
                    tvLoanTopDesc2.text = it.desc2
                }

                if (isSpace(it.desc3)) {
                    tvLoanTopDesc3.visibility = View.GONE
                }
                else {
                    tvLoanTopDesc3.visibility = View.VISIBLE
                    tvLoanTopDesc3.text = it.desc3
                }

                if (isSpace(it.desc4)) {
                    tvLoanTopDesc4.visibility = View.GONE
                }
                else {
                    tvLoanTopDesc4.visibility = View.VISIBLE
                    tvLoanTopDesc4.text = it.desc4
                }

                if (isSpace(it.amt)) {
                    tvLoanTopAmt.visibility = View.GONE
                }
                else {
                    tvLoanTopAmt.visibility = View.VISIBLE
                    tvLoanTopAmt.text = it.amt
                }

                if (isSpace(it.buttonName)) {
                    btnLoanTopPrimary.visibility = View.GONE
                }
                else {
                    btnLoanTopPrimary.visibility = View.VISIBLE
                    btnLoanTopPrimary.text = it.buttonName
                }
                btnLoanTopPrimary.setOnClickListener { v ->
                    itemClickListener.onItemClick(templateItem)
                }
            }
        }
    }

    /**
     * 191，最多两个不允许超过两个
     */
    inner class Better191ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvBetter2ProName: TextView = itemView.findViewById(R.id.tv_better2_pro_name) as TextView
        private var tvBetter2ProNum: TextView = itemView.findViewById(R.id.tv_better2_pro_num) as TextView
        private var tvBetter2ProNum2: TextView = itemView.findViewById(R.id.tv_better2_pro_num2) as TextView
        private var tvBetter2ProNumSign: TextView = itemView.findViewById(R.id.tv_better2_pro_num_sign) as TextView
        private var tvBetter2ProDesc: TextView = itemView.findViewById(R.id.tv_better2_pro_desc) as TextView
        private var tvBetter2ProBottomOne: TextView = itemView.findViewById(R.id.tv_better2_pro_bottom_one) as TextView
        private var llBetter2Layout: LinearLayout = itemView.findViewById(R.id.ll_better2_layout) as LinearLayout
        private var llBetter2Bg: LinearLayout = itemView.findViewById(R.id.ll_better2_bg) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            val phoneDensity = mContext.resources.displayMetrics.density
            // when (templateItemList?.size) {
            //     1 -> { //这个不可能进入了
            //         when (homePageBgType) {
            //             HomePageBgType.ROUND_FOUR -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg)
            //             HomePageBgType.ROUND_TOP -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //             HomePageBgType.ROUND_MIDDLE -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //             HomePageBgType.ROUND_BOTTOM -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //         }
            //     }
            //     else -> { //一定也只能是2
            //         when (pos % 2) {
            //             1 -> { //最后一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg)
            //                     HomePageBgType.ROUND_TOP -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //                 }
            //                 llBetter2Layout.setPadding((5 * phoneDensity).toInt(), 0, 0, 0)
            //             }
            //             0 -> { //第一个
            //                 when (homePageBgType) {
            //                     HomePageBgType.ROUND_FOUR -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg)
            //                     HomePageBgType.ROUND_TOP -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //                     HomePageBgType.ROUND_MIDDLE -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //                     HomePageBgType.ROUND_BOTTOM -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            //                 }
            //                 llBetter2Layout.setPadding(0, 0, (5 * phoneDensity).toInt(), 0)
            //             }
            //         }
            //     }
            // }

            templateItem?.detail?.let {
                tvBetter2ProName.text = it.productName ?: ""
                // splitRateStr(it.amt, tvBetter2ProNum, tvBetter2ProNum2)
                tvBetter2ProNumSign.text = it.amtSign ?: ""
                tvBetter2ProDesc.text = it.desc1 ?: ""
                val sb = StringBuilder()
                sb.append("")
                if (!it.desc2.isNullOrBlank()) {
                    sb.append(it.desc2)
                }
                if (!it.desc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.desc3)
                }
                tvBetter2ProBottomOne.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class Better191ViewHolderSingle(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvBetter2ProName: TextView = itemView.findViewById(R.id.tv_better2_pro_name_single) as TextView
        private var tvBetter2ProNum: TextView = itemView.findViewById(R.id.tv_better2_pro_num_single) as TextView
        private var tvBetter2ProNum2: TextView = itemView.findViewById(R.id.tv_better2_pro_num2_single) as TextView
        private var tvBetter2ProNumSign: TextView = itemView.findViewById(R.id.tv_better2_pro_num_sign_single) as TextView
        private var tvBetter2ProDesc: TextView = itemView.findViewById(R.id.tv_better2_pro_desc_single) as TextView
        private var tvBetter2ProBottomOne: TextView = itemView.findViewById(R.id.tv_better2_pro_bottom_one_single) as TextView

        //        private var llBetter2Layout: LinearLayout = itemView.findViewById(R.id.ll_better2_layout_single) as LinearLayout
        private var llBetter2Bg: LinearLayout = itemView.findViewById(R.id.ll_better2_bg_single) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            // when (homePageBgType) {
            //     HomePageBgType.ROUND_FOUR -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg)
            //     HomePageBgType.ROUND_TOP -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_top)
            //     HomePageBgType.ROUND_MIDDLE -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_center)
            //     HomePageBgType.ROUND_BOTTOM -> llBetter2Bg.setBackgroundResource(R.drawable.loan_corner_bg_bottom)
            // }
            templateItem?.detail?.let {
                tvBetter2ProName.text = it.productName ?: ""
                // splitRateStr(it.amt, tvBetter2ProNum, tvBetter2ProNum2)
                tvBetter2ProNumSign.text = it.amtSign ?: ""
                tvBetter2ProDesc.text = it.desc1 ?: ""
                val sb = StringBuilder()
                sb.append("")
                if (!it.desc2.isNullOrBlank()) {
                    sb.append(it.desc2)
                }
                if (!it.desc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.desc3)
                }
                tvBetter2ProBottomOne.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 200,借钱页
     */
    inner class Loan200ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvLoanTop3Name: TextView = itemView.findViewById<View>(R.id.tv_loan_top3_name) as TextView
        private var tvLoanTop3Desc: TextView = itemView.findViewById<View>(R.id.tv_loan_top3_desc) as TextView
        private var tvBottomNum3Sign: TextView = itemView.findViewById<View>(R.id.tv_bottom_num3_sign) as TextView
        private var tvBottom3Num: TextView = itemView.findViewById<View>(R.id.tv_bottom3_num) as TextView
        private var tvBottom3Desc: TextView = itemView.findViewById<View>(R.id.tv_bottom3_desc) as TextView
        private var btBottom3Two: TextView = itemView.findViewById<View>(R.id.bt_bottom3_two) as TextView
        private var llMulti3: LinearLayout = itemView.findViewById<View>(R.id.ll_multi3) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            //左边八个都一样的处理逻辑，右边三个单独处理
            templateItem?.detail?.let {
                // llMulti3.setBackgroundResource(getBgResourceID(homePageBgType))
                tvLoanTop3Name.text = it.productName ?: ""
                tvBottom3Desc.text = it.desc2 ?: ""
                tvBottom3Num.text = it.desc1 ?: ""
                tvBottomNum3Sign.text = it.desc1Sign ?: ""
                val sb = StringBuilder().append("")
                if (!it.productDesc1.isNullOrBlank()) {
                    sb.append(it.productDesc1)
                }
                if (!it.productDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.productDesc2)
                }
                if (!it.productDesc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.productDesc3)
                }
                tvLoanTop3Desc.text = sb.toString()
                if (templateItem.detail?.buttonName.isNullOrBlank()) {
                    btBottom3Two.visibility = View.GONE
                }
                else {
                    btBottom3Two.visibility = View.VISIBLE
                    btBottom3Two.text = templateItem.detail?.buttonName
                }
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 240，企业贷款融合
     */
    inner class Loan240ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvLoanTop2Name: TextView = itemView.findViewById<View>(R.id.tv_loan_top2_name) as TextView
        private var tvLoanTop2Desc: TextView = itemView.findViewById<View>(R.id.tv_loan_top2_desc) as TextView
        private var tvBottom2Desc: TextView = itemView.findViewById<View>(R.id.tv_bottom2_desc) as TextView
        private var btBottom2: TextView = itemView.findViewById<View>(R.id.bt_bottom2) as TextView
        private var llMulti2: LinearLayout = itemView.findViewById<View>(R.id.ll_multi2) as LinearLayout

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            //左边八个都一样的处理逻辑，右边三个单独处理
            templateItem?.detail?.let {
                // llMulti2.setBackgroundResource(getBgResourceID(homePageBgType))
                tvLoanTop2Name.text = it.loanProdName ?: ""
                val sb = StringBuilder().append("")
                if (!it.loanDesc1.isNullOrBlank()) {
                    sb.append(it.loanDesc1)
                }
                if (!it.loanDesc2.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.loanDesc2)
                }
                if (!it.loanDesc3.isNullOrBlank()) {
                    sb.append(""" | """)
                    sb.append(it.loanDesc3)
                }
                tvLoanTop2Desc.text = sb.toString()
                val sb2 = StringBuffer()
                sb2.append("")
                sb2.append(it.loanAmountContent ?: "")
                sb2.append(it.loanAmount ?: "")
                tvBottom2Desc.text = sb2
                btBottom2.text = templateItem.detail?.buttonName
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 260，理财子
     */
    inner class Licai260ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var tvLicaiName: TextView = itemView.findViewById<View>(R.id.tv_licai_name) as TextView
        private var ivLicaiLine: ImageView = itemView.findViewById<View>(R.id.iv_licai_line) as ImageView
        private var tvTopLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_top_licai_desc) as TextView
        private var tvLicaiNum: TextView = itemView.findViewById<View>(R.id.tv_licai_num) as TextView
        private var tvLicaiSign: TextView = itemView.findViewById<View>(R.id.tv_licai_sign) as TextView
        private var tvLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_licai_desc) as TextView
        private var tvBottomLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_bottom_licai_desc) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                //                ll_layout_licai.setBackgroundResource(getBgResourceID(homePageBgType))
                tvLicaiName.text = it.productName ?: ""
                val sb = StringBuilder().append("")
                if (!it.desc1.isNullOrBlank()) {
                    sb.append(it.desc1)
                }
                if (!it.desc2.isNullOrBlank()) {
                    sb.append(""" """)
                    sb.append(it.desc2)
                }
                if (!it.desc3.isNullOrBlank()) {
                    sb.append(""" """)
                    sb.append(it.desc3)
                }
                tvTopLicaiDesc.text = sb.toString()
                if (sb.toString().isNullOrBlank()) {
                    ivLicaiLine.visibility = View.GONE
                }
                else {
                    ivLicaiLine.visibility = View.VISIBLE
                }
                tvLicaiNum.text = it.rate
                tvLicaiSign.text = it.rateSign
                tvLicaiDesc.text = it.rateDesc
                tvBottomLicaiDesc.text = it.desc4
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 270，简易版首页产品
     */
    inner class HomeSimpleProduct270ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var flSimpleProduct: FlexboxLayout = itemView.findViewById<View>(R.id.fl_simple_product) as FlexboxLayout
        private var tvProductName: TextView = itemView.findViewById<View>(R.id.tv_product_name) as TextView
        private var tvProductLabel: TextView = itemView.findViewById<View>(R.id.tv_product_label) as TextView
        private var tvFinanceRate: TextView = itemView.findViewById<View>(R.id.tv_finance_rate) as TextView
        private var tvFinanceRateDesc: TextView = itemView.findViewById<View>(R.id.tv_finance_rate_desc) as TextView
        private var btnButtonName: Button = itemView.findViewById<View>(R.id.btn_button_name) as Button
        private var tvProductDesc: TextView = itemView.findViewById<View>(R.id.tv_product_desc) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // flSimpleProduct.setBackgroundResource(getBgResourceID(homePageBgType))
                tvProductName.text = it.productName ?: ""
                tvProductLabel.text = it.financeDesc1 ?: ""
                tvFinanceRate.text = it.financeRate ?: ""
                tvFinanceRateDesc.text = it.financeRateContent ?: ""
                btnButtonName.text = it.buttonName ?: ""

                val sb = StringBuilder().append("")
                if (!it.desc1.isNullOrBlank()) {
                    sb.append(it.desc1)
                }
                if (!it.desc2.isNullOrBlank()) {
                    sb.append(" | ")
                    sb.append(it.desc2)
                }
                tvProductDesc.text = sb.toString()
            }
            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 300，理财子普通
     */
    inner class Licai300ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var llFinance: LinearLayout = itemView.findViewById(R.id.ll_finance) as LinearLayout
        private var tvLicaiTitle: TextView = itemView.findViewById<View>(R.id.tv_licai_title) as TextView
        private var ivLicaiDiv: ImageView = itemView.findViewById<View>(R.id.iv_licai_div) as ImageView
        private var tvLicaiSubtitle: TextView = itemView.findViewById<View>(R.id.tv_licai_subtitle) as TextView
        private var tvTopLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_top_licai_desc) as TextView
        private var tvLicaiRateStart: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_start) as TextView
        private var tvLicaiSignStart: TextView = itemView.findViewById<View>(R.id.tv_licai_sign_start) as TextView
        private var tvLicaiRateJoin: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_join) as TextView
        private var tvLicaiRateEnd: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_end) as TextView
        private var tvLicaiSignEnd: TextView = itemView.findViewById<View>(R.id.tv_licai_sign_end) as TextView
        private var tvFinanceRateDesc: TextView = itemView.findViewById<View>(R.id.tv_finance_rate_desc) as TextView
        private var tvFinanceMonth: TextView = itemView.findViewById<View>(R.id.tv_finance_month) as TextView
        private var tvFinanceMonthDesc: TextView = itemView.findViewById<View>(R.id.tv_finance_month_desc) as TextView
        private var tvFinanceAmount: TextView = itemView.findViewById<View>(R.id.tv_finance_amount) as TextView
        private var tvFinanceAmountDesc: TextView = itemView.findViewById<View>(R.id.tv_finance_amount_desc) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llFinance.setBackgroundResource(getBgResourceID(homePageBgType))

                tvLicaiTitle.text = it.productName ?: ""
                ivLicaiDiv.visibility = if (isSpace(it.desc1)) View.GONE else View.VISIBLE
                tvLicaiSubtitle.text = it.desc1 ?: ""
                tvTopLicaiDesc.text = it.desc2
                tvTopLicaiDesc.visibility = if (isSpace(it.desc2)) View.GONE else View.VISIBLE


                if (!isSpace(it.rate)) {
                    tvLicaiRateStart.text = it.rate
                    tvLicaiSignStart.text = it.rateSign
                    tvLicaiRateStart.visibility = View.VISIBLE
                    tvLicaiSignStart.visibility = View.VISIBLE
                }
                else {
                    tvLicaiRateStart.visibility = View.GONE
                    tvLicaiSignStart.visibility = View.GONE
                }
                if (!isSpace(it.rate2)) {
                    tvLicaiRateEnd.text = it.rate2
                    tvLicaiSignEnd.text = it.rateSign2
                    tvLicaiRateJoin.visibility = View.VISIBLE
                    tvLicaiRateEnd.visibility = View.VISIBLE
                    tvLicaiSignEnd.visibility = View.VISIBLE
                }
                else {
                    tvLicaiRateJoin.visibility = View.GONE
                    tvLicaiRateEnd.visibility = View.GONE
                    tvLicaiSignEnd.visibility = View.GONE
                }

                tvFinanceRateDesc.text = it.desc5

                tvFinanceMonth.text = it.desc3
                tvFinanceMonthDesc.text = it.desc6

                tvFinanceAmount.text = it.desc4
                tvFinanceAmountDesc.text = it.desc7
            }

            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    /**
     * 310，理财子爆款
     */
    inner class Licai310ViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        private var llFinance: LinearLayout = itemView.findViewById(R.id.ll_finance) as LinearLayout
        private var tvLicaiTitle: TextView = itemView.findViewById<View>(R.id.tv_licai_title) as TextView
        private var ivLicaiDiv: ImageView = itemView.findViewById<View>(R.id.iv_licai_div) as ImageView
        private var tvLicaiSubtitle: TextView = itemView.findViewById<View>(R.id.tv_licai_subtitle) as TextView
        private var tvTopLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_top_licai_desc) as TextView
        private var tvLicaiRateStart: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_start) as TextView
        private var tvLicaiSignStart: TextView = itemView.findViewById<View>(R.id.tv_licai_sign_start) as TextView
        private var tvLicaiRateJoin: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_join) as TextView
        private var tvLicaiRateEnd: TextView = itemView.findViewById<View>(R.id.tv_licai_rate_end) as TextView
        private var tvLicaiSignEnd: TextView = itemView.findViewById<View>(R.id.tv_licai_sign_end) as TextView
        private var tvLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_licai_desc) as TextView
        private var tvBottomLicaiDesc: TextView = itemView.findViewById<View>(R.id.tv_bottom_licai_desc) as TextView

        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
            templateItem?.detail?.let {
                // llFinance.setBackgroundResource(getBgResourceID(homePageBgType))

                tvLicaiTitle.text = it.productName ?: ""
                ivLicaiDiv.visibility = if (isSpace(it.desc1)) View.GONE else View.VISIBLE
                tvLicaiSubtitle.text = it.desc1 ?: ""
                tvTopLicaiDesc.text = it.desc2
                tvTopLicaiDesc.visibility = if (isSpace(it.desc2)) View.GONE else View.VISIBLE

                if (!isSpace(it.rate)) {
                    tvLicaiRateStart.text = it.rate
                    tvLicaiSignStart.text = it.rateSign
                    tvLicaiRateStart.visibility = View.VISIBLE
                    tvLicaiSignStart.visibility = View.VISIBLE
                }
                else {
                    tvLicaiRateStart.visibility = View.GONE
                    tvLicaiSignStart.visibility = View.GONE
                }
                if (!isSpace(it.rate2)) {
                    tvLicaiRateEnd.text = it.rate2
                    tvLicaiSignEnd.text = it.rateSign2
                    tvLicaiRateJoin.visibility = View.VISIBLE
                    tvLicaiRateEnd.visibility = View.VISIBLE
                    tvLicaiSignEnd.visibility = View.VISIBLE
                }
                else {
                    tvLicaiRateJoin.visibility = View.GONE
                    tvLicaiRateEnd.visibility = View.GONE
                    tvLicaiSignEnd.visibility = View.GONE
                }

                tvLicaiDesc.text = it.desc3

                val sb = StringBuilder().append("")
                if (!isSpace(it.desc4)) {
                    sb.append(it.desc4)
                }
                if (!isSpace(it.desc5)) {
                    sb.append(" | ")
                    sb.append(it.desc5)
                }
                tvBottomLicaiDesc.text = sb.toString()
            }

            itemView.setOnClickListener(object : OnGapClickListener() {
                override fun onGapClick(v: View?) {
                    if (templateItem != null) {
                        if (templateItem.joinType == Strs.ONE || templateItem.joinType == Strs.ZERO || templateItem.joinType == Strs.FOUR
                            || !templateItem.joinTip.isNullOrBlank()) {
                            itemClickListener.onItemClick(templateItem)
                        }
                    }
                }
            })
        }
    }

    inner class NullViewHolder(itemView: View) : TypeAbstractViewHolder(itemView) {
        override fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int) {
        }
    }

    abstract inner class TypeAbstractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindHolder(templateItem: ProductInfo.ProductTemplate.ProductTemplateItem?, pos: Int)
    }
}