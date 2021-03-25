package com.githubyss.mobile.common.debug.recyclerview.fund.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * SectionId
 * 区块 ID
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:12:44
 */
@Documented
@StringDef({SectionId.FUND_PRODUCT, SectionId.FUND_HOT, SectionId.FUND_HOT_MANAGER, SectionId.GOLD_PRODUCT})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SectionId {
    final String FUND_PRODUCT     = "fundProduct";
    final String FUND_HOT         = "fundHot";
    final String FUND_HOT_MANAGER = "fundHotManager";
    final String GOLD_PRODUCT     = "goldProduct";
}
