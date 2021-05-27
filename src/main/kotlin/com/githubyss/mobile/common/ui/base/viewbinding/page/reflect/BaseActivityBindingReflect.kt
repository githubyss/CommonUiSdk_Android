package com.githubyss.mobile.common.ui.base.viewbinding.page.reflect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.ui.base.viewbinding.page.BaseActivity
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BaseActivityBindingReflect
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 10:48:25
 */
abstract class BaseActivityBindingReflect<B : ViewBinding> : BaseActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var _binding: B? = null
    val binding: B get() = _binding!!
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<B>?
                _binding = clazz?.getMethod("inflate", LayoutInflater::class.java)
                    ?.invoke(null, layoutInflater) as B
                setContentView(binding.root)
            } catch (e: NoSuchMethodException) {
                LogcatUtils.e(t = e)
            } catch (e: IllegalAccessException) {
                LogcatUtils.e(t = e)
            } catch (e: InvocationTargetException) {
                LogcatUtils.e(t = e)
            }
        }
    }
}
