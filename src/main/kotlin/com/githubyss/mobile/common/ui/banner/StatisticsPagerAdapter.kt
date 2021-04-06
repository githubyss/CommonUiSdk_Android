package com.githubyss.mobile.common.ui.banner

import androidx.viewpager.widget.PagerAdapter


/**
 * StatisticsPagerAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 14:49:10
 */
abstract class StatisticsPagerAdapter : PagerAdapter() {
    // 重置埋点状态
    open fun resetScrollState() {}
    
    // 触发埋点事件
    open fun viewStatistic(index: Int) {}
}
