package com.githubyss.mobile.common.debug.mvvm

import android.os.Bundle
import android.view.View
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarFragmentBindingInline
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentMvvmBinding


/**
 * MvvmFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:18:21
 */
class MvvmFragment : BaseToolbarFragmentBindingInline(R.layout.comui_fragment_mvvm) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = MvvmFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentMvvmBinding>()
    val mvvmVmObservableField: MvvmVmObservableField by lazy { ViewModelProvider(requireActivity()).get(MvvmVmObservableField::class.java) }
    val mvvmVmLiveData: MvvmVmLiveData by lazy { ViewModelProvider(requireActivity()).get(MvvmVmLiveData::class.java) }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        // initObservableField()
        initLiveData()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        setToolbarTitle(R.string.comui_mvvm_title)
        
        binding.buttonChangeText.setOnClickListener(onClickListener)
        binding.buttonShowText.setOnClickListener(onClickListener)
        binding.buttonHideText.setOnClickListener(onClickListener)
        
        binding.lifecycleOwner = viewLifecycleOwner
    }
    
    private fun initData() {
        binding.mvvm = mvvmVmLiveData
    }
    
    private fun initObservableField() {
        mvvmVmObservableField.isTextShow?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (mvvmVmObservableField.isTextShow?.get()) {
                    true -> binding.textDisplay.visibility = View.VISIBLE
                    false -> binding.textDisplay.visibility = View.INVISIBLE
                }
            }
        })
    }
    
    private fun initLiveData() {
        // mvvmVmLiveData.text?.observe(viewLifecycleOwner, Observer { t ->
        //
        // })
        mvvmVmLiveData.isTextShow?.observe(viewLifecycleOwner, Observer { b ->
            binding.textDisplay.visibility = if (b) View.VISIBLE else View.INVISIBLE
        })
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.button_change_text -> {
                val text = "Current Time: ${System.currentTimeMillis()}"
                mvvmVmObservableField.text?.set(text)
                mvvmVmLiveData.text?.value = text
            }
            R.id.button_show_text -> {
                val isTextShow = true
                mvvmVmObservableField.isTextShow?.set(isTextShow)
                mvvmVmLiveData.isTextShow?.value = isTextShow
            }
            R.id.button_hide_text -> {
                val isTextShow = false
                mvvmVmObservableField.isTextShow?.set(isTextShow)
                mvvmVmLiveData.isTextShow?.value = isTextShow
            }
        }
    }
}
