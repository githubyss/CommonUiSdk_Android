package com.githubyss.mobile.common.ui.alone.page.homepage

import android.os.Bundle
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseInlineToolbarActivity
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingAudioPlayer


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseInlineToolbarActivity() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_homepage_title)
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityCreated(activity: Activity, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreate")
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStarted(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStart")
        
        attachView()
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 Nothing
     *
     * @param
     * @return
     */
    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onRestart")
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityResumed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onResume")
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityPaused(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onPause")
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStopped(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStop")
        
        detachView()
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivitySaveInstanceState(activity: Activity, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    
    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityDestroyed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroy")
    }
    
    
    /** ****************************** Functions ****************************** */
    
    private fun attachView() {
        // AppFloatingIcon.getInstance(this)
        //     .attach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .attach(this)
    }
    
    private fun detachView() {
        // AppFloatingIcon.getInstance(this)
        //     .detach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .detach(this)
    }
}
