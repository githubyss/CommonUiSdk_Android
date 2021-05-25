package com.githubyss.mobile.common.ui.baseviewbindinglayout

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
 * BaseViewBindingFrameLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:39:37
 */
abstract class BaseViewBindingFrameLayout<VB : ViewBinding> : FrameLayout {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    open lateinit var binding: VB
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        val type: ParameterizedType? = javaClass.genericSuperclass as ParameterizedType?
        val cls: Class<*>? = type?.actualTypeArguments?.get(0) as Class<*>?
        try {
            val inflateMethod: Method? = cls?.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            // binding = inflateMethod?.invoke(null, inflater, container, false) as VB
            // binding = inflateMethod?.invoke(null, LayoutInflater.from(context), this, true) as VB
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }
}
