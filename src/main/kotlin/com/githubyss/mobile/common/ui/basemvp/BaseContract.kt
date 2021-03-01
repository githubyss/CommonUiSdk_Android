package com.githubyss.mobile.common.ui.basemvp

/**
 * BaseContract.ktDescription>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
interface BaseContract {
    interface ComuiBaseIView<in P> {
        fun setPresenter(iPresenter: P)
    }

    interface ComuiBaseIPresenter {
        fun onStandby()
    }
}
