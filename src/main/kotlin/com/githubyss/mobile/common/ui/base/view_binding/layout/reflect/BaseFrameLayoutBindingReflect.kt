package com.githubyss.mobile.common.ui.base.view_binding.layout.reflect

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType


/**
 * BaseFrameLayoutBindingReflect
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:39:37
 */
abstract class BaseFrameLayoutBindingReflect<B : ViewBinding> : FrameLayout {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var _binding: B? = null
    val binding: B get() = _binding!!
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */

    @Suppress("LeakingThis")
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type: ParameterizedType? = javaClass.genericSuperclass as ParameterizedType?
        val cls = type?.actualTypeArguments?.get(0) as Class<B>?
        try {
            val inflateMethod: Method? = cls?.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            _binding = inflateMethod?.invoke(null, LayoutInflater.from(context), this, true) as B
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }
}
