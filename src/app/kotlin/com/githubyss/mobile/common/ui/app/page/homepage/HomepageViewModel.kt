package com.githubyss.mobile.common.ui.app.page.homepage

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.ui.R


/**
 * HomepageViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/16 15:58:07
 */
class HomepageViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    /**  */
    private val titleDefault = getStringFromRes(R.string.comui_homepage_title)

    /**  */
    var viewId: MutableLiveData<Int>? = null

    /**  */
    var title: String by mutableStateOf(titleDefault)
        private set

    /** ****************************** Constructors ****************************** */

    /**  */
    init {
        initViewModelField()
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /**  */
    fun changeTitle(title: String) {
        this.title = title
    }

    /** ******************** Data Handling ******************** */

    /**  */
    private fun initViewModelField() {
        this.viewId = MutableLiveData()
    }

    /**  */
    private fun clearData() {
        this.viewId = null
    }

    /** ******************** Event Handling ******************** */

    /**  */
    fun onAnyButtonClick(view: View) {
        this.viewId?.value = view.id
    }
}
