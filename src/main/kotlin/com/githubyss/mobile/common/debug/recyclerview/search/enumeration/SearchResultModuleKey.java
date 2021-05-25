package com.githubyss.mobile.common.debug.recyclerview.search.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * SearchResultModuleKey
 * 模块 Key
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:12:44
 */
@Documented
@StringDef({SearchResultModuleKey.NONE, SearchResultModuleKey.SPECIAL_TOPIC, SearchResultModuleKey.ACTIVITY, SearchResultModuleKey.APP, SearchResultModuleKey.FUND_PRODUCT, SearchResultModuleKey.FUND_TOPIC, SearchResultModuleKey.FUND_MANAGER, SearchResultModuleKey.GOLD_PRODUCT, SearchResultModuleKey.INSURANCE_PRODUCT, SearchResultModuleKey.FINANCE_AQ, SearchResultModuleKey.FAQ, SearchResultModuleKey.INFORMATION, SearchResultModuleKey.WEALTH_ACCOUNT, SearchResultModuleKey.SEE_MORE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SearchResultModuleKey {
    final String NONE              = "";
    final String SPECIAL_TOPIC     = "subject";
    final String ACTIVITY          = "activity2";
    final String APP               = "application";
    final String FUND_PRODUCT      = "fund";
    final String FUND_TOPIC        = "fundTopic";
    final String FUND_MANAGER      = "fundManager";
    final String GOLD_PRODUCT      = "gold";
    final String INSURANCE_PRODUCT = "insurance";
    final String FINANCE_AQ        = "question";
    final String FAQ               = "help";
    final String INFORMATION       = "information";
    final String WEALTH_ACCOUNT    = "fortune";
    final String SEE_MORE          = "hasMoreType";
}
