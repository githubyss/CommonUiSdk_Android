package com.githubyss.mobile.common.ui.floatingview.audioplayer;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubyss.mobile.common.ui.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


/**
 * DesignatedFloatingAudioPlayerView
 * <Description> 特定的音频播放器悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:30:01
 */
public class DesignatedFloatingAudioPlayerView extends BaseFloatingAutoShortedView {

    // ---------- ---------- ---------- Properties ---------- ---------- ----------

    private TextView textView_title;
    private TextView textView_timeFlow;
    private ImageView imageView_playController;
    private ImageView imageView_switchVoice;
    private ImageView imageView_close;
    private ImageView imageView_lengthen;

    private DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener;


    // ---------- ---------- ---------- Constructors ---------- ---------- ----------

    public DesignatedFloatingAudioPlayerView(@NonNull Context context) {
        this(context, R.layout.floating_window_audio_player);
    }

    public DesignatedFloatingAudioPlayerView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        rootView = inflate(context, resource, this);
        initBase();
        initDesignated();
    }


    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------

    private void initDesignated() {
        initViewFindById(rootView);
        initListener();
    }

    private void initViewFindById(View view) {
        textView_title = view.findViewById(R.id.textView_title);
        textView_timeFlow = view.findViewById(R.id.textView_timeFlow);
        imageView_playController = view.findViewById(R.id.imageView_playController);
        imageView_switchVoice = view.findViewById(R.id.imageView_switchVoice);
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_lengthen = view.findViewById(R.id.imageView_lengthen);
    }

    private void initListener() {
        textView_title.setOnClickListener(onClickListener);
        textView_timeFlow.setOnClickListener(onClickListener);
        imageView_playController.setOnClickListener(onClickListener);
        imageView_switchVoice.setOnClickListener(onClickListener);
        imageView_close.setOnClickListener(onClickListener);
        imageView_lengthen.setOnClickListener(onClickListener);

        this.setBaseFloatingAutoShortedViewListener(new BaseFloatingAutoShortedViewListener() {
            @Override
            public void onSlide(boolean isShow) {
                refreshCloseButton(isShow);
            }

            @Override
            public void onClose() {

            }
        });
    }

    private void refreshCloseButton(boolean isShow) {
        if (isShow) {
            imageView_close.setVisibility(View.VISIBLE);
            imageView_lengthen.setVisibility(View.GONE);
        } else {
            imageView_close.setVisibility(View.GONE);
            imageView_lengthen.setVisibility(View.VISIBLE);
        }
    }


    // ---------- ---------- ---------- Implementations ---------- ---------- ----------

    /**
     * 点击监听
     * yanss 2017/04/07 14:19:25
     */
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.textView_title) {
            } else if (id == R.id.textView_timeFlow) {

            } else if (id == R.id.imageView_playController) {

            } else if (id == R.id.imageView_switchVoice) {

            } else if (id == R.id.imageView_close) {
                // hideFloatingWindow();
                shortenFloatingWindow();
            } else if (id == R.id.imageView_lengthen) {
                lengthenFloatingWindow();
            }
        }
    };


    // ---------- ---------- ---------- Setter ---------- ---------- ----------

    public void setDesignatedFloatingAudioPlayerViewListener(DesignatedFloatingAudioPlayerViewListener designatedFloatingAudioPlayerViewListener) {
        this.designatedFloatingAudioPlayerViewListener = designatedFloatingAudioPlayerViewListener;
    }
}
