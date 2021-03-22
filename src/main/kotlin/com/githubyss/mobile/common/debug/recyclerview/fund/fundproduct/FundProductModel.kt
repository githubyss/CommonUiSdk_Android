package com.githubyss.mobile.common.debug.recyclerview.fund.fundproduct


/**
 * FundProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:32
 */
data class FundProductModel constructor(var title: String, var riseFallRatio: String, var code: String, var risk: String, var classify: String, var riseFallTimeSpan: String, var followCount: String, var isFollowed: Boolean, var jumpUrl: String, @FundProductType var type: Int)
