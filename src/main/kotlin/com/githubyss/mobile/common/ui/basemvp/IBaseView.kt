package com.githubyss.mobile.common.ui.basemvp

/**
 * IBaseView* <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
interface IBaseView<in P> {
    fun setPresenter(iPresenter: P)
}
