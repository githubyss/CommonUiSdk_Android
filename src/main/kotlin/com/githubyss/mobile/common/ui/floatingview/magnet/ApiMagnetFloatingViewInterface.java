package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;


/**
 * ApiMagnetFloatingViewInterface
 * <Description> 磁力吸附悬浮窗管理器对外接口
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiMagnetFloatingViewInterface {

    ApiMagnetFloatingView add();

    ApiMagnetFloatingView remove();

    ApiMagnetFloatingView attach(Activity activity);

    ApiMagnetFloatingView attach(FrameLayout container);

    ApiMagnetFloatingView detach(Activity activity);

    ApiMagnetFloatingView detach(FrameLayout container);

    BaseMagnetFloatingView getMagnetView();

    // FloatingAudioPlayerView getAudioPlayerView();

    ApiMagnetFloatingView icon(@DrawableRes int resId);

    ApiMagnetFloatingView customView(DesignatedMagnetFloatingView viewGroup);

    ApiMagnetFloatingView customView(@LayoutRes int resource);

    ApiMagnetFloatingView layoutParams(ViewGroup.LayoutParams params);

    ApiMagnetFloatingView listener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener);
}
