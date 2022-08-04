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
        items.add(ItemArticleTitle("20899", "人民网这样说！", false, true))
        items.add(ItemArticleTitle("20765", "金士顿火了！", true, true))
        items.add(ItemDatetime(1, "昨天"))
        items.add(ItemArticleTitle("20033", "Google 宣布以下事项！", false, false))
        items.add(ItemDatetime(2, "一个月前"))
        items.add(ItemArticleTitle("20011", "苏宁进入退市流程！", false, false))
        items.add(ItemArticleTitle("19766", "互联网寒冬还要持续多久！", false, false))
        items.add(ItemArticleTitle("19679", "三亚热死人了！", false, false))
        items
    }

    @JvmStatic
    val itemsEditable by lazy {
        val items = ArrayList<BindingAdapterItem>()
        items.add(ItemDatetimeEditable("今天", CheckState.CHECK_YES))
        items.add(ItemArticleTitleEditable("20899", "人民网这样说！", CheckState.CHECK_YES))
        items.add(ItemArticleTitleEditable("20765", "金士顿火了！", CheckState.CHECK_YES))
        items.add(ItemDatetimeEditable("昨天", CheckState.CHECK_NO))
        items.add(ItemArticleTitleEditable("20033", "Google 宣布以下事项！", CheckState.CHECK_NO))
        items.add(ItemDatetimeEditable("一个月前", CheckState.CHECK_PARTLY))
        items.add(ItemArticleTitleEditable("20011", "苏宁进入退市流程！", CheckState.CHECK_YES))
        items.add(ItemArticleTitleEditable("19766", "互联网寒冬还要持续多久！", CheckState.CHECK_YES))
        items.add(ItemArticleTitleEditable("19679", "三亚热死人了！", CheckState.CHECK_NO))
        items
    }

    @JvmStatic
    val itemsCombine by lazy {
        val items = ArrayList<BindingAdapterDoubleLayerItem>()
        val articleList1 = ArrayList<ItemArticleTitle>()
        articleList1.add(ItemArticleTitle("20899", "人民网这样说！", false, true))
        articleList1.add(ItemArticleTitle("20765", "金士顿火了！", true, true))
        items.add(ItemArticleCombine(0, "今天", CheckState.CHECK_YES, articleList1))

        val articleList2 = ArrayList<ItemArticleTitle>()
        articleList2.add(ItemArticleTitle("20033", "Google 宣布以下事项！", false, false))
        items.add(ItemArticleCombine(1, "昨天", CheckState.CHECK_YES, articleList2))

        val articleList3 = ArrayList<ItemArticleTitle>()
        articleList3.add(ItemArticleTitle("20011", "苏宁进入退市流程！", false, false))
        articleList3.add(ItemArticleTitle("19766", "互联网寒冬还要持续多久！", false, false))
        articleList3.add(ItemArticleTitle("19679", "三亚热死人了！", false, false))
        items.add(ItemArticleCombine(2, "一个月前", CheckState.CHECK_YES, articleList3))
        items
    }
}
