package com.githubyss.mobile.common.ui.base.view_binding.dialog

import android.app.Dialog
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.base.view_binding.inflateBinding


inline fun <reified B : ViewBinding> Dialog.inflate(): Lazy<B> {
    return lazy { inflateBinding<B>(layoutInflater).apply { setContentView(root) } }
}
