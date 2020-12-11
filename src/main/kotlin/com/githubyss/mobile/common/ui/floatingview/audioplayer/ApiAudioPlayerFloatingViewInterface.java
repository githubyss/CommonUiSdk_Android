package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;

import java.util.List;

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
public interface ApiAudioPlayerFloatingViewInterface {

    ApiAudioPlayerFloatingView show();

    ApiAudioPlayerFloatingView close();

    ApiAudioPlayerFloatingView lengthen();

    ApiAudioPlayerFloatingView shorten();

    ApiAudioPlayerFloatingView attach(Activity activity);

    ApiAudioPlayerFloatingView attach(FrameLayout container);

    ApiAudioPlayerFloatingView detach(Activity activity);

    ApiAudioPlayerFloatingView detach(FrameLayout container);

    ApiAudioPlayerFloatingView initData(List<AudioModel> audioList);

    BaseAutoShortedFloatingView getAutoShortedView();

    ApiAudioPlayerFloatingView customView(DesignatedAudioPlayerFloatingView viewGroup);

    ApiAudioPlayerFloatingView customView(@LayoutRes int resource);

    ApiAudioPlayerFloatingView layoutParams(ViewGroup.LayoutParams params);

    ApiAudioPlayerFloatingView listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener);
}
