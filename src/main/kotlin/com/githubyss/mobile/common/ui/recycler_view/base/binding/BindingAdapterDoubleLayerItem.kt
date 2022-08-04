package com.githubyss.mobile.common.ui.recycler_view.base.binding


/**
 * BindingAdapterDoubleLayerItem
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 15:38:30
 */
interface BindingAdapterDoubleLayerItem : BindingAdapterItem {
    val innerItems: List<BindingAdapterItem>
}
