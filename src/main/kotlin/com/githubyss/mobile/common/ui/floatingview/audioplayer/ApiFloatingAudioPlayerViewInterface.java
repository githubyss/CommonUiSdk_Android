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
public interface ApiFloatingAudioPlayerViewInterface {

    ApiFloatingAudioPlayerView show();

    ApiFloatingAudioPlayerView close();

    ApiFloatingAudioPlayerView lengthen();

    ApiFloatingAudioPlayerView shorten();

    ApiFloatingAudioPlayerView attach(Activity activity);

    ApiFloatingAudioPlayerView attach(FrameLayout container);

    ApiFloatingAudioPlayerView detach(Activity activity);

    ApiFloatingAudioPlayerView detach(FrameLayout container);

    ApiFloatingAudioPlayerView initData(List<AudioModel> audioList);

    BaseFloatingAutoShortedView getAutoShortedView();

    ApiFloatingAudioPlayerView customView(DesignatedFloatingAudioPlayerView viewGroup);

    ApiFloatingAudioPlayerView customView(@LayoutRes int resource);

    ApiFloatingAudioPlayerView layoutParams(ViewGroup.LayoutParams params);

    ApiFloatingAudioPlayerView listener(DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener);
}
