package com.githubyss.mobile.common.ui.recyclerview.layout;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * LayoutType
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:37:33
 */
@Documented
@IntDef({LayoutType.EMPTY, LayoutType.HEADER, LayoutType.FOOTER, LayoutType.FRAGMENT, LayoutType.VIEW})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface LayoutType {
    final int EMPTY    = 0x00;
    final int HEADER   = 0x01;
    final int FOOTER   = 0x02;
    final int FRAGMENT = 0x03;
    final int VIEW     = 0x04;
}
