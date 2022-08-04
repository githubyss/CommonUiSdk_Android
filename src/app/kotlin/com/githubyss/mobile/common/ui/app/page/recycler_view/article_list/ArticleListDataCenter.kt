package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ArticleListDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 16:10:14
 */
object ArticleListDataCenter {
    @JvmStatic
    val items by lazy {
        val items = ArrayList<BindingAdapterItem>()
        items.add(ItemDatetime(0, "今天"))
        items.add(ItemTitle("20899", "人民网这样说！", false, true))
        items.add(ItemTitle("20765", "金士顿火了！", true, true))
        items.add(ItemDatetime(1, "昨天"))
        items.add(ItemTitle("20033", "Google 宣布以下事项！", false, false))
        items.add(ItemDatetime(2, "一个月前"))
        items.add(ItemTitle("20011", "苏宁进入退市流程！", false, false))
        items.add(ItemTitle("19766", "互联网寒冬还要持续多久！", false, false))
        items.add(ItemTitle("19679", "三亚热死人了！", false, false))
        items
    }

    @JvmStatic
    val itemsEditing by lazy {
        val items = ArrayList<BindingAdapterItem>()
        items.add(ItemDatetimeEditing("今天", CheckState.CHECK_YES))
        items.add(ItemTitleEditing("20899", "人民网这样说！", CheckState.CHECK_YES))
        items.add(ItemTitleEditing("20765", "金士顿火了！", CheckState.CHECK_YES))
        items.add(ItemDatetimeEditing("昨天", CheckState.CHECK_NO))
        items.add(ItemTitleEditing("20033", "Google 宣布以下事项！", CheckState.CHECK_NO))
        items.add(ItemDatetimeEditing("一个月前", CheckState.CHECK_PARTLY))
        items.add(ItemTitleEditing("20011", "苏宁进入退市流程！", CheckState.CHECK_YES))
        items.add(ItemTitleEditing("19766", "互联网寒冬还要持续多久！", CheckState.CHECK_YES))
        items.add(ItemTitleEditing("19679", "三亚热死人了！", CheckState.CHECK_NO))
        items
    }

    @JvmStatic
    val itemsCombine by lazy {
        val items = ArrayList<BindingAdapterDoubleLayerItem>()
        val articleList1 = ArrayList<ItemTitle>()
        articleList1.add(ItemTitle("20899", "人民网这样说！", false, true))
        articleList1.add(ItemTitle("20765", "金士顿火了！", true, true))
        items.add(ItemCombine(0, "今天", articleList1))

        val articleList2 = ArrayList<ItemTitle>()
        articleList2.add(ItemTitle("20033", "Google 宣布以下事项！", false, false))
        items.add(ItemCombine(1, "昨天", articleList2))

        val articleList3 = ArrayList<ItemTitle>()
        articleList3.add(ItemTitle("20011", "苏宁进入退市流程！", false, false))
        articleList3.add(ItemTitle("19766", "互联网寒冬还要持续多久！", false, false))
        articleList3.add(ItemTitle("19679", "三亚热死人了！", false, false))
        items.add(ItemCombine(2, "一个月前", articleList3))
        items
    }

    @JvmStatic
    val itemsCombineEditing by lazy {
        val items = ArrayList<BindingAdapterDoubleLayerItem>()
        val articleList1 = ArrayList<ItemTitleEditing>()
        articleList1.add(ItemTitleEditing("20899", "人民网这样说！", CheckState.CHECK_YES))
        articleList1.add(ItemTitleEditing("20765", "金士顿火了！", CheckState.CHECK_YES))
        items.add(ItemCombineEditing("今天", CheckState.CHECK_YES, articleList1))

        val articleList2 = ArrayList<ItemTitleEditing>()
        articleList2.add(ItemTitleEditing("20033", "Google 宣布以下事项！", CheckState.CHECK_NO))
        items.add(ItemCombineEditing("昨天", CheckState.CHECK_YES, articleList2))

        val articleList3 = ArrayList<ItemTitleEditing>()
        articleList3.add(ItemTitleEditing("20011", "苏宁进入退市流程！", CheckState.CHECK_YES))
        articleList3.add(ItemTitleEditing("19766", "互联网寒冬还要持续多久！", CheckState.CHECK_YES))
        articleList3.add(ItemTitleEditing("19679", "三亚热死人了！", CheckState.CHECK_NO))
        items.add(ItemCombineEditing("一个月前", CheckState.CHECK_YES, articleList3))
        items
    }
}
