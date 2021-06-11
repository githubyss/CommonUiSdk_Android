package com.githubyss.mobile.common.debug.mvvm

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.debug.mvvm.child.MvvmChildVm


/**
 * MvvmVmObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:40:41
 */
class MvvmVmObservableField : ViewModel() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    /** context */
    // var context: Activity? = null
    
    /** model（数据源 Java Bean） */
    var mvvmBean: MvvmModel.MvvmBean? = null
    
    /** 数据绑定，绑定到 UI 的字段（data field） */
    var text: ObservableField<String>? = null
    var imageUrl: ObservableField<String>? = null
    var isTextShow: ObservableBoolean? = null
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
        text = ObservableField()
        imageUrl = ObservableField()
        isTextShow = ObservableBoolean()
        
        text?.set(mvvmBean?.text)
        imageUrl?.set(mvvmBean?.imageUrl)
        isTextShow?.set(true)
    }
    
    private fun clearData() {
        mvvmBean = null
    }
    
    
    /** ********** ********** ********** Class ********** ********** ********** */
    
    // class ViewStyle {
    //     var isTextShow = ObservableBoolean()
    // }
}
