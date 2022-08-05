package com.githubyss.mobile.common.ui.app.page.recycler_view

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemCombine
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemCombineEditing
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemTitle
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ItemTitleEditing
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
    val inEdit by lazy { MutableLiveData<Boolean>(false) }

    fun refreshCheckState(data: BindingAdapterItem) {
        when (data) {
            is ItemCombineEditing -> {
                when (data.checkState.get()) {
                    CheckState.CHECK_YES -> {
                        data.checkState.set(CheckState.CHECK_NO)
                        data.innerItems = data.innerItems.changeAllCheckState(CheckState.CHECK_NO)
                    }
                    CheckState.CHECK_NO -> {
                        data.checkState.set(CheckState.CHECK_YES)
                        data.innerItems = data.innerItems.changeAllCheckState(CheckState.CHECK_YES)
                    }
                }
            }
            is ItemTitleEditing -> {
                when (data.checkState.get()) {
                    CheckState.CHECK_YES -> {
                        data.checkState.set(CheckState.CHECK_NO)
                    }
                    CheckState.CHECK_NO -> {
                        data.checkState.set(CheckState.CHECK_YES)
                    }
                }
            }
        }
    }

    private fun List<ItemTitleEditing>.changeAllCheckState(checkState: String) = this.map {
        it.checkState.set(checkState)
        it
    }
}
