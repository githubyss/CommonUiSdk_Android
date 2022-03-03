package com.githubyss.mobile.common.ui.floating_view.classical.feature.autoshorten


/**
 * AutoShortenViewListener
 * 自动收起 View 回调监听
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/10 16:04:48
 */
interface AutoShortenViewListener {
    fun onShow()
    fun onRemove()
    fun onLengthen()
    fun onShorten()
}
