package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.content.Context;
import android.widget.ImageView;

import com.githubyss.mobile.common.ui.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


/**
 * DesignatedFloatingMagnetView
 * <Description> 特定的磁力吸附悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:30:01
 */
public class DesignatedFloatingMagnetView extends BaseFloatingMagnetView {

    private final ImageView mIcon;

    public DesignatedFloatingMagnetView(@NonNull Context context) {
        this(context, R.layout.en_floating_view);
    }

    public DesignatedFloatingMagnetView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        inflate(context, resource, this);
        mIcon = findViewById(R.id.icon);
    }

    public void setIconImage(@DrawableRes int resId) {
        mIcon.setImageResource(resId);
    }
}
