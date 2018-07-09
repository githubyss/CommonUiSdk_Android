package com.githubyss.mobile.common.ui.basemvp

/**
 * ComuiBaseContract.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
interface ComuiBaseContract {
    interface ComuiBaseIView<in P> {
        fun setPresenter(iPresenter: P)
    }

    interface ComuiBaseIPresenter {
        fun onStandby()
    }
}
