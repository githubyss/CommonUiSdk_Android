package com.githubyss.mobile.common.ui.base.view_binding.page.inline

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.base.view_binding.function.inflateBindingLayoutInflater


inline fun <reified B : ViewBinding> Activity.inflate(): Lazy<B> {
    return lazy { inflateBindingLayoutInflater<B>(layoutInflater).apply { setContentView(root) } }
}
