package com.githubyss.mobile.common.ui.base.view_binding.function

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


@Suppress("UNCHECKED_CAST")
inline fun <reified B : ViewBinding> inflateBindingLayoutInflater(layoutInflater: LayoutInflater): B {
    return B::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as B
}

@Suppress("UNCHECKED_CAST")
inline fun <reified B : ViewBinding> inflateBindingViewGroup(parent: ViewGroup): B {
    return B::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        .invoke(null, LayoutInflater.from(parent.context), parent, false) as B
}
