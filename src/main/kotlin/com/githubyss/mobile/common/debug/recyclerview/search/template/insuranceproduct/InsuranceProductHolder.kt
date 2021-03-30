package com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * InsuranceProductHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 16:54:12
 */
class InsuranceProductHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerInsuranceProductItem) as FlexboxLayout
    var ivImage = view.findViewById(R.id.imageView_recyclerInsuranceProductImage) as ImageView
    var tvTitle = view.findViewById(R.id.textView_recyclerInsuranceProductTitle) as TextView
    var tvHint = view.findViewById(R.id.textView_recyclerInsuranceProductHint) as TextView
    var tvPrice = view.findViewById(R.id.textView_recyclerInsuranceProductPrice) as TextView
}
