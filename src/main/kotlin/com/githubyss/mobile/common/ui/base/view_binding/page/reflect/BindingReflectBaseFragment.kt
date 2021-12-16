package com.githubyss.mobile.common.ui.base.view_binding.page.reflect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.base.view_binding.page.base.BaseFragment
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BindingReflectBaseFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 11:27:32
 */
abstract class BindingReflectBaseFragment<B : ViewBinding> : BaseFragment() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private var _binding: B? = null
    val binding: B get() = _binding!!
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clazz = type.actualTypeArguments[0] as Class<B>?
            try {
                _binding = clazz?.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                    ?.invoke(null, inflater, container, false) as B
            } catch (e: NoSuchMethodException) {
                LogUtils.e(t = e)
            } catch (e: IllegalAccessException) {
                LogUtils.e(t = e)
            } catch (e: InvocationTargetException) {
                LogUtils.e(t = e)
            }
        }
        
        return binding.root
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
