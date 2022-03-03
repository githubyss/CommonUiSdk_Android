package com.githubyss.mobile.common.ui.app.page.recycler_view

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * RecyclerViewViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 17:50:44
 */
class RecyclerViewViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** model（数据源 Java Bean） */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var viewId: MutableLiveData<Int>? = null


    /** ****************************** Constructors ****************************** */

    init {
        initViewModelField()
    }


    /** ****************************** Override ****************************** */

    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    private fun initViewModelField() {
        this.viewId = MutableLiveData()
    }

    private fun clearData() {
        this.viewId = null
    }

    /** ******************** Event Handling ******************** */

    fun onAnyButtonClick(view: View) {
        this.viewId?.value = view.id
    }
}
