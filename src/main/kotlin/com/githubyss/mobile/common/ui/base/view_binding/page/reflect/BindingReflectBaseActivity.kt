package com.githubyss.mobile.common.ui.base.view_binding.page.reflect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.base.view_binding.page.base.BaseActivity
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * BindingReflectBaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/08 10:48:25
 */
abstract class BindingReflectBaseActivity<B : ViewBinding> : BaseActivity() {

    /** ****************************** Properties ****************************** */

    private var _binding: B? = null
    val binding: B get() = _binding!!


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call inflate method to fill view according to specified ViewBinding by using java reflect.
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<B>?
                _binding = clazz?.getMethod("inflate", LayoutInflater::class.java)
                    ?.invoke(null, layoutInflater) as B
                setContentView(binding.root)
            }
            catch (e: NoSuchMethodException) {
                LogUtils.e(TAG, t = e)
            }
            catch (e: IllegalAccessException) {
                LogUtils.e(TAG, t = e)
            }
            catch (e: InvocationTargetException) {
                LogUtils.e(TAG, t = e)
            }
        }

        super.onCreate(savedInstanceState)
    }
}
