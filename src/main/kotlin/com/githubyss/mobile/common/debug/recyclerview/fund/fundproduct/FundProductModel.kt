package com.githubyss.mobile.common.debug.recyclerview.fund.fundproduct


/**
 * FundProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:32
 */
data class FundProductModel constructor(var title: String, var detail: String, var riseFallRatio: String, var riseFallTimeSpan: String, var isFollowed: Boolean, var followCount: String,var jumpUrl: String, @FundProductType var type: Int)
