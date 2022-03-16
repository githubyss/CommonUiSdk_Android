package com.githubyss.mobile.common.ui.frame_layout.binding_inline

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.binding.binding_inflate.inflateBindingByViewGroup


inline fun <reified B : ViewBinding> FrameLayout.inflate(parent: ViewGroup): Lazy<B> {
    return lazy { inflateBindingByViewGroup(parent) }
}