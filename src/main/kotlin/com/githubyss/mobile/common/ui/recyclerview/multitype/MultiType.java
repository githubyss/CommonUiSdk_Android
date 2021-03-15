package com.githubyss.mobile.common.ui.recyclerview.multitype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * MultiType
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/10 16:42:51
 */
@Documented
@IntDef({MultiType.EMPTY, MultiType.HEADER, MultiType.FOOTER, MultiType.TEXT, MultiType.IMAGE, MultiType.FRAGMENT})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface MultiType {
    final int EMPTY    = 0x00;
    final int HEADER   = 0x01;
    final int FOOTER   = 0x02;
    final int TEXT     = 0x03;
    final int IMAGE    = 0x04;
    final int FRAGMENT = 0x05;
}
