package com.githubyss.mobile.common.ui.basemvp


/**
 * BaseContract
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:34
 */
interface BaseContract {
    interface ComuiBaseIView<in P> {
        fun setPresenter(iPresenter: P)
    }

    interface ComuiBaseIPresenter {
        fun onStandby()
    }
}
