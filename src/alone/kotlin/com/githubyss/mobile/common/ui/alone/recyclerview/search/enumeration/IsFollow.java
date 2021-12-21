package com.githubyss.mobile.common.ui.alone.recyclerview.search.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * IsFollow
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:12:44
 */
@Documented
@StringDef({IsFollow.TRUE, IsFollow.FALSE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface IsFollow {
    final String TRUE  = "1";
    final String FALSE = "0";
}
