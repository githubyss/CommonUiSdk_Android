package com.githubyss.mobile.common.ui.basemvp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.ui.R
import kotlinx.android.synthetic.main.comui_toolbar_base.*

/**
 * ComuiBaseActivity.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class ComuiBaseActivity : AppCompatActivity() {
    /** Toolbar navigation click listener in ComuiBaseActivity. by Ace Yan */
    interface OnComuiBaseToolbarNavigationClickListener {
        fun onClick(v: View)
    }

    /** Toolbar menu item click listener in ComuiBaseActivity. by Ace Yan */
    interface OnComuiBaseToolbarMenuItemClickListener {
        fun onClick(item: MenuItem): Boolean
    }

    interface OnComuiBaseToolbarLongClickListener {
        fun onLongClick(v: View): Boolean
    }


    /** Bind Presenter. by Ace Yan */
    open fun bindPresenter() {}

    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    open fun initView() {}

    /** Init default value of Top-level variables, and so on. by Ace Yan */
    open fun initData() {}

    /** Refresh Views. by Ace Yan */
    open fun refreshView() {}


    /** Setup Toolbar title by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
//        toolbarBase.title = ResUtils.getString(this@ComuiBaseActivity, titleResId)
        toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar title by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        toolbarBase.title = titleString
    }

    /** Setup Toolbar navigation icon by ResId. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconResId: Int) {
//        toolbarBase.navigationIcon = ResUtils.getDrawable(this@ComuiBaseActivity, iconResId)
        toolbarBase.setNavigationIcon(iconResId)
    }

    /** Setup Toolbar navigation icon by Drawable. by Ace Yan */
    protected fun setToolbarNavigationIcon(iconDrawable: Drawable) {
        toolbarBase.navigationIcon = iconDrawable
    }

    /** Setup Toolbar navigation click listener. by Ace Yan */
    protected fun setToolbarNavigationOnClickListener(onComuiBaseToolbarNavigationClickListener: OnComuiBaseToolbarNavigationClickListener) {
        toolbarBase.setNavigationOnClickListener { v ->
            onComuiBaseToolbarNavigationClickListener.onClick(v)
        }
    }

    /** Setup Toolbar menu item click listener. by Ace Yan */
    protected fun setToolbarMenuItemOnClickListener(onComuiBaseToolbarMenuItemClickListener: OnComuiBaseToolbarMenuItemClickListener) {
        toolbarBase.setOnMenuItemClickListener { item ->
            onComuiBaseToolbarMenuItemClickListener.onClick(item)
        }
    }

    /** Get the menu in Toolbar. by Ace Yan */
    protected fun getMenu(): Menu {
        return toolbarBase.menu
    }

    protected fun setToolbarOnLongClickListener(onComuiBaseToolbarLongClickListener: OnComuiBaseToolbarLongClickListener) {
        toolbarBase.setOnLongClickListener { v ->
            onComuiBaseToolbarLongClickListener.onLongClick(v)
        }
    }

    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = intent.extras
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = intent.extras
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.comui_activity_base)

        /** Make sure that you can use Toolbar as simple as ActionBar. by Ace Yan */
        setSupportActionBar(toolbarBase)
    }
}
