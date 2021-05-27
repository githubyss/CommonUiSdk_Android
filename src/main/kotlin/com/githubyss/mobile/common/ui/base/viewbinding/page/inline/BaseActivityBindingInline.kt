package com.githubyss.mobile.common.ui.base.viewbinding.page.inline

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.base.viewbinding.inflateBinding


inline fun <reified B : ViewBinding> Activity.inflate(): Lazy<B> {
    return lazy { inflateBinding<B>(layoutInflater).apply { setContentView(root) } }
}
