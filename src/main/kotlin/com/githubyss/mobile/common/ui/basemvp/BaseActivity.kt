package com.githubyss.mobile.common.ui.basemvp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.ui.R
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 10:48:25
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 利用反射，调用指定 ViewBinding 中的 inflate 方法填充视图
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val cls = type.actualTypeArguments[0] as Class<VB>
                val inflateMethod = cls.getMethod("inflate", LayoutInflater::class.java)
                _binding = inflateMethod.invoke(null, layoutInflater) as VB
                val rootView = binding.root
                setContentView(rootView)
            } catch (e: NoSuchMethodException) {
                LogcatUtils.e(t = e)
            } catch (e: IllegalAccessException) {
                LogcatUtils.e(t = e)
            } catch (e: InvocationTargetException) {
                LogcatUtils.e(t = e)
            }
        }
        
        init()
    }
    
    
    /** ********* ********** ********** Open ********** ********** ********** */
    
    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    /** Init default value of Top-level variables, and so on. by Ace Yan */
    abstract fun init()
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragmentContainer) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }
        
        fragment.arguments = intent.extras
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
    
    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragmentContainer) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }
        
        fragment.arguments = intent.extras
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}
