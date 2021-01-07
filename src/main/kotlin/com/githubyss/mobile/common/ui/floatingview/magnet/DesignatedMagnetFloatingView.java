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
public class DesignatedMagnetFloatingView extends BaseMagnetFloatingView {

    private final ImageView imageView_icon;

    public DesignatedMagnetFloatingView(@NonNull Context context) {
        this(context, R.layout.comui_floating_icon);
    }

    public DesignatedMagnetFloatingView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        inflate(context, resource, this);
        imageView_icon = findViewById(R.id.imageView_icon);
    }

    public void setIconImage(@DrawableRes int resId) {
        imageView_icon.setImageResource(resId);
    }
}
