package com.githubyss.mobile.common.ui.dialog.voice_select

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * VoiceSelectDialogVm
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/25 17:55:43
 */
class VoiceSelectDialogVm : ViewModel() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        const val TITLE_DEFAULT = "发音人选择"
        const val BTN_CONFIRM_DEFAULT = "保存"
        const val BTN_CANCEL_DEFAULT = "取消"
    }


    /** ****************************** Properties ****************************** */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val title by lazy { MutableLiveData<String>() }
    val btnConfirm by lazy { MutableLiveData<String>() }
    val btnCancel by lazy { MutableLiveData<String>() }
    val voiceToneList by lazy { MutableLiveData<ArrayList<VoiceTone>>() }


    /** ****************************** Constructors ****************************** */

    /**  */
    init {
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    /**  */
    fun setupData(title: String, btnConfirm: String, btnCancel: String, voiceToneList: ArrayList<VoiceTone>) {
        this.title.value = title
        this.btnConfirm.value = btnConfirm
        this.btnCancel.value = btnCancel
        this.voiceToneList.value = voiceToneList
    }

    fun voiceToneListSelected(voiceTone: VoiceTone) {
        val newList = ArrayList<VoiceTone>()
        voiceToneList.value?.let { list ->
            list.mapTo(newList) {
                when (it.id) {
                    voiceTone.id -> {
                        // it.selected = VoiceToneSelectState.YES
                        it.selected.set(VoiceToneSelectState.YES)
                        // it.selected.value = VoiceToneSelectState.YES
                    }
                    else -> {
                        // it.selected = VoiceToneSelectState.NO
                        it.selected.set(VoiceToneSelectState.NO)
                        // it.selected.value = VoiceToneSelectState.NO
                    }
                }
                it
            }
        }
        voiceToneList.value?.clear()
        voiceToneList.value?.addAll(newList)
    }

    /**  */
    fun clearData() {
    }


    /** ******************** Event Handling ******************** */

    // /**  */
    // fun onBtnConfirmClick(v: View) {
    //     logD(msg = "点击了确认")
    // }
    //
    // fun onBtnCancelClick(v: View) {
    //     logD(msg = "点击了取消")
    // }
}
