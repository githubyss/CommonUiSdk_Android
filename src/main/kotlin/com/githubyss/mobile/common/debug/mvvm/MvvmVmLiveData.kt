package com.githubyss.mobile.common.debug.mvvm

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.debug.mvvm.child.MvvmChildVm
import com.githubyss.mobile.common.ui.R


/**
 * MvvmVmLiveData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/11 11:12:44
 */
class MvvmVmLiveData : ViewModel() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    /** context */
    // var context: Activity? = null
    
    /** model（数据源 Java Bean） */
    var mvvmBean: MvvmModel.MvvmBean? = null
    
    /** 数据绑定，绑定到 UI 的字段（data field） */
    var text: MutableLiveData<String>? = null
    var imageUrl: MutableLiveData<String>? = null
    var isTextShow: MutableLiveData<Boolean>? = null
    // var viewStyle = ViewStyle()
    
    /** 命令绑定（command） */
    // val onButtonShowCommand=ReplyCom
    
    /** 子 ViewModel */
    var itemViewModel: ObservableArrayList<MvvmChildVm>? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        initViewModelField()
        loadData()
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCleared() {
        super.onCleared()
        clearData()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Data Handling ********** ********** */
    
    private fun initViewModelField() {
        this.text = MutableLiveData()
        this.imageUrl = MutableLiveData()
        this.isTextShow = MutableLiveData()
    }
    
    private fun loadData() {
        mvvmBean = MvvmModel.MvvmBean("Init.", "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
        
        this.text?.value = mvvmBean?.text
        this.imageUrl?.value = mvvmBean?.imageUrl
        this.isTextShow?.value = true
    }
    
    private fun clearData() {
        mvvmBean = null
    }
    
    /** ********** ********** Event Handling ********** ********** */
    
    fun onButtonClick(view: View) {
        when (view.id) {
            // R.id.button_change_text -> {
            //     val text = "Current Time: ${System.currentTimeMillis()}"
            //     this.text?.value = text
            // }
            R.id.button_show_text -> {
                val isTextShow = true
                this.isTextShow?.value = isTextShow
            }
            R.id.button_hide_text -> {
                val isTextShow = false
                this.isTextShow?.value = isTextShow
            }
        }
    }
    
    fun onButtonChangeTextClick() {
        val text = "Current Time: ${System.currentTimeMillis()}"
        this.text?.value = text
    }
    
    // fun onButtonShowTextClick() {
    //     val isTextShow = true
    //     this.isTextShow?.value = isTextShow
    // }
    //
    // fun onButtonHideTextClick() {
    //     val isTextShow = false
    //     this.isTextShow?.value = isTextShow
    // }
    
    
    /** ********** ********** ********** Class ********** ********** ********** */
    
    // class ViewStyle {
    //     var isTextShow = ObservableBoolean()
    // }
}
