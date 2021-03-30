package com.githubyss.mobile.common.debug.recyclerview.search.enumeration;

import androidx.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * SectionId
 * 区块 ID
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:12:44
 */
@Documented
@StringDef({SectionId.ACTIVITY_ICON, SectionId.APP_ICON, SectionId.FUND_PRODUCT, SectionId.FUND_TOPIC, SectionId.FUND_MANAGER, SectionId.GOLD_PRODUCT, SectionId.INSURANCE_PRODUCT, SectionId.FINANCE_AQ, SectionId.FAQ, SectionId.INFORMATION, SectionId.WEALTH_ACCOUNT})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SectionId {
    final String ACTIVITY_ICON = "activityIcon";
    final String APP_ICON = "appIcon";
    final String FUND_PRODUCT = "fundProduct";
    final String FUND_TOPIC = "fundTopic";
    final String FUND_MANAGER = "fundManager";
    final String GOLD_PRODUCT = "goldProduct";
    final String INSURANCE_PRODUCT = "insuranceProduct";
    final String FINANCE_AQ = "financeAq";
    final String FAQ = "faq";
    final String INFORMATION = "information";
    final String WEALTH_ACCOUNT = "wealthAccount";
}
