package com.githubyss.mobile.common.ui.recycler_view.enumeration;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * ItemType
 * 列表样式类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:06:10
 */
@Documented
@IntDef({ItemType.EMPTY, ItemType.HEADER, ItemType.FOOTER, ItemType.ITEM})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface ItemType {
    final int EMPTY  = 0x00;
    final int HEADER = 0x01;
    final int FOOTER = 0x02;
    final int ITEM   = 0x03;
}
