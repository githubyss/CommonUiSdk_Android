package com.githubyss.mobile.common.debug.mvvm

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.debug.mvvm.child.MvvmChildVm


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
        loadData()
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCleared() {
        super.onCleared()
        clearData()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun loadData() {
        mvvmBean?.text = "Init."
        mvvmBean?.imageUrl = "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif"
        initViewModelField()
    }
    
    private fun initViewModelField() {
        text = MutableLiveData()
        imageUrl = MutableLiveData()
        isTextShow = MutableLiveData()
        
        text?.value = mvvmBean?.text
        imageUrl?.value = mvvmBean?.imageUrl
        isTextShow?.value = true
    }
    
    private fun clearData() {
        mvvmBean = null
    }
    
    fun refreshData() {
    
    }
    
    
    /** ********** ********** ********** Class ********** ********** ********** */
    
    // class ViewStyle {
    //     var isTextShow = ObservableBoolean()
    // }
}
