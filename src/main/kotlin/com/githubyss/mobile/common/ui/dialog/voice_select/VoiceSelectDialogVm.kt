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
    lateinit var title: MutableLiveData<String>
    lateinit var btnConfirm: MutableLiveData<String>
    lateinit var btnCancel: MutableLiveData<String>
    lateinit var voiceToneList: MutableLiveData<ArrayList<VoiceTone>>


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

    /** ******************** Data Handling ******************** */

    /**  */
    private fun initViewModelField() {
        title = MutableLiveData(TITLE_DEFAULT)
        btnConfirm = MutableLiveData(BTN_CONFIRM_DEFAULT)
        btnCancel = MutableLiveData(BTN_CANCEL_DEFAULT)
        voiceToneList = MutableLiveData(ArrayList())
    }

    /**  */
    fun setupData(title: String? = TITLE_DEFAULT, btnConfirm: String? = BTN_CONFIRM_DEFAULT, btnCancel: String? = BTN_CANCEL_DEFAULT, voiceToneList: ArrayList<VoiceTone>? = ArrayList()) {
        this.title.value = title ?: TITLE_DEFAULT
        this.btnConfirm.value = btnConfirm ?: BTN_CONFIRM_DEFAULT
        this.btnCancel.value = btnCancel ?: BTN_CANCEL_DEFAULT
        this.voiceToneList.value = voiceToneList ?: ArrayList()
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
