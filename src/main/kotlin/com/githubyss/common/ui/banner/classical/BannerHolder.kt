package com.githubyss.common.ui.banner.classical

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.common.ui.R


/**
 * BannerHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:56:10
 */
class BannerHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_banner) as LinearLayout
    var ivImage = view.findViewById(R.id.imageView_bannerItem) as ImageView
}
