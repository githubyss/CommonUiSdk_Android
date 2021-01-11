package com.githubyss.mobile.common.ui.floatingview.container;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.githubyss.mobile.common.ui.audio.player.AudioPlayManager;
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.DesignatedAudioPlayerView;
import com.githubyss.mobile.common.ui.floatingview.designate.audioplayer.DesignatedAudioPlayerViewListener;
import com.githubyss.mobile.common.ui.floatingview.feature.FeatureAutoShortenViewListener;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;


/**
 * ApiFloatingViewWithinApp
 * <Description> 应用内悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/07 19:02:14
 */
public class ApiFloatingViewWithinApp implements ApiFloatingViewWithinAppInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static volatile ApiFloatingViewWithinApp instance;
    
    private Context containerContext;
    
    private WeakReference<FrameLayout> containerView;
    
    private DesignatedAudioPlayerView designatedView;
    private ViewGroup.LayoutParams    designatedLayoutParams;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ApiFloatingViewWithinApp(@NonNull Context context) {
        // super(context);
        init(context);
    }
    
    public static ApiFloatingViewWithinApp getInstance(Context context) {
        if (instance == null) {
            synchronized (ApiFloatingViewWithinApp.class) {
                if (instance == null) {
                    instance = new ApiFloatingViewWithinApp(context);
                }
            }
        }
        
        return instance;
    }
    
    // public <V extends View> V createDesignatedView(Class<V> clz) {
    //     View designatedView = null;
    //     try {
    //         designatedView = (View) Class.forName(clz.getName()).newInstance();
    //     } catch (Exception e) {
    //     }
    //     return (V) designatedView;
    // }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ApiFloatingViewWithinApp show() {
        initLayoutParams();
        ensureFloatingView();
        return this;
    }
    
    @Override
    public void close() {
        AudioPlayManager.getInstance().destroy();
        if (designatedView != null) {
            designatedView.closeFloatingWindow();
        }
    }
    
    @Override
    public void attach(Activity activity) {
        attach(getActivityRoot(activity));
    }
    
    @Override
    public void attach(FrameLayout container) {
        if (container == null || designatedView == null) {
            containerView = new WeakReference<>(container);
            return;
        }
        if (designatedView.getParent() == container) {
            return;
        }
        if (designatedView.getParent() != null) {
            ((ViewGroup) designatedView.getParent()).removeView(designatedView);
        }
        containerView = new WeakReference<>(container);
        container.addView(designatedView);
    }
    
    @Override
    public void detach(Activity activity) {
        detach(getActivityRoot(activity));
    }
    
    @Override
    public void detach(FrameLayout container) {
        if (designatedView != null && container != null && ViewCompat.isAttachedToWindow(designatedView)) {
            container.removeView(designatedView);
        }
        if (getContainerView() == container) {
            containerView = null;
        }
    }
    
    @Override
    public void layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedView != null) {
            designatedView.setLayoutParams(params);
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void init(Context context) {
        this.containerContext = context;
    }
    
    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(0, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 50);
    }
    
    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
    }
    
    private void ensureFloatingView() {
        synchronized (this) {
            if (designatedView == null) {
                designatedView = new DesignatedAudioPlayerView(containerContext);
                designatedView.setLayoutParams(getDesignatedLayoutParams());
                addViewToWindow(designatedView);
                designatedView.showFloatingWindow();
                designatedView.setFeatureAutoShortenViewListener(new FeatureAutoShortenViewListener() {
                    @Override
                    public void onShow() {
                    }
                    
                    @Override
                    public void onClose() {
                        removeFloatingView();
                    }
                    
                    @Override
                    public void onLengthen() {
                    }
                    
                    @Override
                    public void onShorten() {
                    }
                });
            }
        }
    }
    
    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedView != null) {
                    try {
                        if (getContainerView() != null && ViewCompat.isAttachedToWindow(designatedView)) {
                            getContainerView().removeView(designatedView);
                        }
                    } catch (Exception e) {
                    }
                    designatedView = null;
                }
            }
        });
    }
    
    private void addViewToWindow(final View designatedView) {
        if (getContainerView() == null) {
            return;
        }
        try {
            getContainerView().addView(designatedView);
        } catch (Exception e) {
        }
    }
    
    private FrameLayout getContainerView() {
        if (containerView == null) {
            return null;
        }
        return containerView.get();
    }
    
    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ApiFloatingViewWithinApp setDesignatedViewListener(DesignatedAudioPlayerViewListener designatedViewListener) {
        designatedView.setDesignatedViewListener(designatedViewListener);
        return this;
    }
}
