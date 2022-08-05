package com.githubyss.mobile.common.ui.app.page.recycler_view

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.*
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ArticleListDataCenter.changeAllCheckState
import com.githubyss.mobile.common.ui.dialog.voice_select.VoiceTone
import com.githubyss.mobile.common.ui.dialog.voice_select.VoiceToneSelectState
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem
import java.io.DataInput


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
}
