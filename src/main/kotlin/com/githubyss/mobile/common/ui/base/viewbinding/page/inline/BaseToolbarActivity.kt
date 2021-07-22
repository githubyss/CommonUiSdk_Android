package com.githubyss.mobile.common.ui.base.viewbinding.page.inline

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.githubyss.mobile.common.ui.base.viewbinding.page.base.BaseActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * BaseToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:45:23
 */
abstract class BaseToolbarActivity : BaseActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
     val binding by inflate<ComuiActivityBaseToolbarBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /** Make sure that you can use Toolbar as simple as ActionBar. */
        setSupportActionBar(binding.toolbarBase.toolbarBase)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        // toolbarBase.text = ResourceUtils.getString(this@BaseToolbarActivity, titleResId)
        binding.toolbarBase.toolbarBase.setTitle(titleResId)
    }
    
    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        binding.toolbarBase.toolbarBase.title = titleString
    }
    
    /** Setup Toolbar navigation icon by ResId. */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
        // toolbarBase.navigationIcon = ResourceUtils.getDrawable(this@BaseToolbarActivity, iconResId)
        binding.toolbarBase.toolbarBase.setNavigationIcon(iconResId)
    }
    
    /** Setup Toolbar navigation icon by Drawable. */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        binding.toolbarBase.toolbarBase.navigationIcon = iconDrawable
    }
    
    /** Setup Toolbar navigation click listener. */
    protected fun setToolbarNavigationOnClickListener(onBaseToolbarNavigationClickListener: OnBaseToolbarNavigationClickListener) {
        binding.toolbarBase.toolbarBase.setNavigationOnClickListener { v ->
            onBaseToolbarNavigationClickListener.onClick(v)
        }
    }
    
    /** Setup Toolbar menu item click listener. */
    protected fun setToolbarMenuItemOnClickListener(onBaseToolbarMenuItemClickListener: OnBaseToolbarMenuItemClickListener) {
        binding.toolbarBase.toolbarBase.setOnMenuItemClickListener { item ->
            onBaseToolbarMenuItemClickListener.onClick(item)
        }
    }
    
    /** Get the menu in Toolbar. */
    protected fun getMenu(): Menu? {
        return binding.toolbarBase.toolbarBase.menu
    }
    
    protected fun setToolbarOnLongClickListener(onBaseToolbarLongClickListener: OnBaseToolbarLongClickListener) {
        binding.toolbarBase.toolbarBase.setOnLongClickListener { v ->
            onBaseToolbarLongClickListener.onLongClick(v)
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    /** Toolbar navigation click listener in BaseToolbarActivity. */
    interface OnBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }
    
    /** Toolbar menu item click listener in BaseToolbarActivity. */
    interface OnBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }
    
    interface OnBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }
}
