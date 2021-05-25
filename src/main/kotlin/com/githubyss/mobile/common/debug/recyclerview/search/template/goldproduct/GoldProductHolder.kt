package com.githubyss.mobile.common.debug.recyclerview.search.template.goldproduct

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.textview.KeyWordHighlightTextView
import com.google.android.flexbox.FlexboxLayout


/**
 * GoldProductHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:28
 */
class GoldProductHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerGoldProductItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerGoldProductTitle) as KeyWordHighlightTextView
    var tvPrice = view.findViewById(R.id.textView_recyclerGoldProductPrice) as TextView
    var tvUnit = view.findViewById(R.id.textView_recyclerGoldProductUnit) as TextView
    var tvCode = view.findViewById(R.id.textView_recyclerGoldProductCode) as TextView
    var tvRisk = view.findViewById(R.id.textView_recyclerGoldProductRisk) as TextView
    var tvClassify = view.findViewById(R.id.textView_recyclerGoldProductClassify) as TextView
    var tvPriceTime = view.findViewById(R.id.textView_recyclerGoldProductPriceTime) as TextView
}
