package com.githubyss.common.ui.app.page.recycler_view.multi

import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.ui.R


object MultiTypeDataCenter {
    @JvmStatic
    val items = ArrayList<BindingAdapterItem>()

    init {
        initData()
    }

    private fun initData() {
        items.add(ItemText("叶子"))
        items.add(ItemImage(R.drawable.comui_head_yezi))
        items.add(ItemText("虫虫"))
        items.add(ItemImage(R.drawable.comui_head_chongchong))
        items.add(ItemText("晓媛"))
        items.add(ItemImage(R.drawable.comui_head_xiaoyuan))
        items.add(ItemText("一菲"))
        items.add(ItemImage(R.drawable.comui_head_yifei))
        items.add(ItemText("子晴"))
        items.add(ItemImage(R.drawable.comui_head_ziqing))
    }
}
