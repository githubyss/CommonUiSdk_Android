package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;


/**
 * IApiFloatingMagnetView
 * <Description> 磁力吸附悬浮窗管理器对外接口
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface IApiFloatingMagnetView {

    ApiFloatingMagnetView add();

    ApiFloatingMagnetView remove();

    ApiFloatingMagnetView attach(Activity activity);

    ApiFloatingMagnetView attach(FrameLayout container);

    ApiFloatingMagnetView detach(Activity activity);

    ApiFloatingMagnetView detach(FrameLayout container);

    BaseFloatingMagnetView getMagnetView();

    // FloatingAudioPlayerView getAudioPlayerView();

    ApiFloatingMagnetView icon(@DrawableRes int resId);

    ApiFloatingMagnetView customView(BaseFloatingMagnetView viewGroup);

    ApiFloatingMagnetView customView(@LayoutRes int resource);

    ApiFloatingMagnetView layoutParams(ViewGroup.LayoutParams params);

    ApiFloatingMagnetView listener(MagnetViewListener magnetViewListener);
}
