package com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic

import com.githubyss.mobile.common.debug.recyclerview.search.template.appicon.AppIconModel
import com.githubyss.mobile.common.ui.banner.BannerModel


/**
 * SpecialTopicModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 16:10:02
 */
data class SpecialTopicModel constructor(var bgImageUrl: String, var bgTitle: String, var bgDescription: String, var topicIconUrl: String, var topicTitle: String, var topicDescription: String, var advertList: MutableList<BannerModel>, var iconList: MutableList<AppIconModel>)
