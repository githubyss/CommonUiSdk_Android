package com.githubyss.mobile.common.ui.basemvp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * BaseToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:21
 */
abstract class BaseToolbarActivity<VB : ViewBinding> : BaseActivity<VB>() {
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        /** Make sure that you can use Toolbar as simple as ActionBar. by Ace Yan */
        if (binding is ComuiActivityBaseBinding) setSupportActionBar((binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase)
    }
    
    override fun init() {
        TODO("Not yet implemented")
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
        // toolbarBase.text = ResourceUtils.getString(this@BaseToolbarActivity, titleResId)
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setTitle(titleResId)
    }
    
    /** Setup Toolbar text by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.title = titleString
    }
    
    /** Setup Toolbar navigation icon by ResId. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
        // toolbarBase.navigationIcon = ResourceUtils.getDrawable(this@BaseToolbarActivity, iconResId)
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setNavigationIcon(iconResId)
    }
    
    /** Setup Toolbar navigation icon by Drawable. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.navigationIcon = iconDrawable
    }
    
    /** Setup Toolbar navigation click listener. by Ace Yan */
    protected fun setToolbarNavigationOnClickListener(onBaseToolbarNavigationClickListener: OnBaseToolbarNavigationClickListener) {
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setNavigationOnClickListener { v ->
            onBaseToolbarNavigationClickListener.onClick(v)
        }
    }
    
    /** Setup Toolbar menu item click listener. by Ace Yan */
    protected fun setToolbarMenuItemOnClickListener(onBaseToolbarMenuItemClickListener: OnBaseToolbarMenuItemClickListener) {
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setOnMenuItemClickListener { item ->
            onBaseToolbarMenuItemClickListener.onClick(item)
        }
    }
    
    /** Get the menu in Toolbar. by Ace Yan */
    protected fun getMenu(): Menu? {
        return if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.menu else null
    }
    
    protected fun setToolbarOnLongClickListener(onBaseToolbarLongClickListener: OnBaseToolbarLongClickListener) {
        if (binding is ComuiActivityBaseBinding) (binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setOnLongClickListener { v ->
            onBaseToolbarLongClickListener.onLongClick(v)
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    /** Toolbar navigation click listener in BaseToolbarActivity. by Ace Yan */
    interface OnBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }
    
    /** Toolbar menu item click listener in BaseToolbarActivity. by Ace Yan */
    interface OnBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }
    
    interface OnBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }
}
