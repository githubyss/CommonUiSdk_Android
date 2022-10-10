package com.githubyss.common.ui.recycler_view.enumeration;

import androidx.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * HasMore
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:12:44
 */
@Documented
@StringDef({HasMore.TRUE, HasMore.FALSE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface HasMore {
    final String TRUE  = "1";
    final String FALSE = "0";
}
