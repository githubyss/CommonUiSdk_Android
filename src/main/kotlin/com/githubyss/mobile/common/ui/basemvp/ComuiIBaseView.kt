package com.githubyss.mobile.common.ui.basemvp

/**
 * ComuiIBaseView.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
interface ComuiIBaseView<in P> {
    fun setPresenter(iPresenter: P)
}
