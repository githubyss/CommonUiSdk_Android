package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;

import com.githubyss.mobile.common.ui.audio.model.AudioModel;

import java.util.List;


/**
 * IApiFloatingMagnetView
 * <Description> 磁力吸附悬浮窗管理器对外接口
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:34:40
 */
public interface ApiAudioPlayerFloatingWindowInterface {

    ApiAudioPlayerFloatingWindow show();

    ApiAudioPlayerFloatingWindow close();

    ApiAudioPlayerFloatingWindow lengthen();

    ApiAudioPlayerFloatingWindow shorten();

    ApiAudioPlayerFloatingWindow initData(List<AudioModel> audioList);

    BaseAutoShortedFloatingView getAutoShortedView();

    ApiAudioPlayerFloatingWindow customView(DesignatedAudioPlayerFloatingView viewGroup);

    ApiAudioPlayerFloatingWindow customView(@LayoutRes int resource);

    ApiAudioPlayerFloatingWindow layoutParams(ViewGroup.LayoutParams params);

    ApiAudioPlayerFloatingWindow listener(DesignatedAudioPlayerFloatingViewListener designatedAudioPlayerFloatingViewListener);
}
