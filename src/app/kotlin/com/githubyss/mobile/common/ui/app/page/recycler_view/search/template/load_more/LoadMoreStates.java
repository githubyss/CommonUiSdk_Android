package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.load_more;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


@Documented
@IntDef({LoadMoreStates.LOADING, LoadMoreStates.NO_MORE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface LoadMoreStates {
    final int LOADING = 0x01;
    final int NO_MORE = 0x02;
}
