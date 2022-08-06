package com.githubyss.mobile.common.ui.app.page.recycler_view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.*
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * RecyclerViewViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 17:50:44
 */
class RecyclerViewViewModel : ViewModel() {
    val isEditing by lazy { MutableLiveData(false) }
    val isAllSelected by lazy { MutableLiveData(false) }
    val ids by lazy { MutableLiveData(HashSet<String>()) }

    @JvmField
    val itemsCombineEditing = ArticleListDataCenter.itemsCombineEditing

    @JvmField
    val itemsCombine = ArticleListDataCenter.itemsCombine


    fun updateArticleData() {
        ArticleListDataCenter.updateData()
    }

    fun refreshCheckState(data: BindingAdapterItem) {
        when (data) {
            is ItemCombineEditing -> {
                ArticleListDataCenter.selectItemCombine(data)
            }
            is ItemTitleEditing -> {
                ArticleListDataCenter.selectItemTitle(data)
            }
        }
    }

    fun refreshSelectAllState() {
        isAllSelected.value = isAllSelected.value?.not() ?: return
    }

    fun refreshEditingState(editing: Boolean) {
        isEditing.value = editing
    }

    fun selectAllItems() {
        ArticleListDataCenter.selectAllItems(isAllSelected.value ?: return)
    }

    fun deleteItemTitle() {
        ArticleListDataCenter.deleteItemTitle(ids.value ?: return)
        // TODO("实际上，数据的获取是从服务器后台，数据的删除也是在服务器后台，所以这里的删除操作，需要通过网络接口，把 ids 传给服务器后台，然后收到删除成功的消息后，重新请求数据，刷新界面")
        // TODO("requestDelete(ids)")
        // TODO("updateData()")
        // TODO("refreshList()")
    }

    fun refreshIds() {
        ids.value = getCheckedIds()
    }

    private fun getCheckedIds(): HashSet<String> {
        val ids = HashSet<String>()
        for (itemCombine in ArticleListDataCenter.itemsCombineEditing) {
            for (itemTitle in itemCombine.innerItems) {
                if (itemTitle.checkState.get() == CheckState.CHECK_YES) {
                    ids.add(itemTitle.id)
                }
            }
        }
        return ids
    }
}
