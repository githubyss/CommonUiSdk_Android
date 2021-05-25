package com.githubyss.mobile.common.ui.baseviewbindingpage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.ui.R
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BaseViewBindingFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:27:32
 */
abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    
    var fragmentContext: Context? = null
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 利用反射，调用指定 ViewBinding 中的 inflate 方法填充视图
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[0] as Class<VB>
            try {
                val inflateMethod = cls.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                _binding = inflateMethod.invoke(null, inflater, container, false) as VB
            } catch (e: NoSuchMethodException) {
                LogcatUtils.e(t = e)
            } catch (e: IllegalAccessException) {
                LogcatUtils.e(t = e)
            } catch (e: InvocationTargetException) {
                LogcatUtils.e(t = e)
            }
        }
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContext = activity?.baseContext ?: ComuiApplication.instance
        init()
    }
    
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    
    
    /** ********* ********** ********** Open ********** ********** ********** */
    
    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    /** Init default value of Top-level variables, and so on. by Ace Yan */
    abstract fun init()
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Change button status. by Ace Yan */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }
    
    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragmentContainer) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
    
    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragmentContainer) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
