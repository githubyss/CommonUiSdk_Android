package com.githubyss.mobile.common.ui.floatingview.magnet;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.utils.EnContext;

import java.lang.ref.WeakReference;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.core.view.ViewCompat;


/**
 * ApiMagnetFloatingView
 * <Description> 磁力吸附悬浮窗管理器
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:32:57
 */
public class ApiMagnetFloatingView implements ApiMagnetFloatingViewInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static volatile ApiMagnetFloatingView instance;
    
    private Context                      containerContext;
    private WeakReference<FrameLayout>   containerView;
    private DesignatedMagnetFloatingView designatedFloatingView;
    private ViewGroup.LayoutParams       designatedLayoutParams;
    @LayoutRes
    private int                          layoutId = R.layout.comui_floating_icon;
    @DrawableRes
    private int                          iconRes  = R.drawable.imuxuan;
    private ApiMagnetFloatingListener    apiMagnetFloatingListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ApiMagnetFloatingView(Context context) {
        containerContext = context;
        initLayoutParams();
    }
    
    public static ApiMagnetFloatingView getInstance(Context context) {
        if (instance == null) {
            synchronized (ApiMagnetFloatingView.class) {
                if (instance == null) {
                    instance = new ApiMagnetFloatingView(context);
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ApiMagnetFloatingView add() {
        ensureFloatingView();
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(designatedFloatingView) && getContainerView() != null) {
                    getContainerView().removeView(designatedFloatingView);
                }
                designatedFloatingView = null;
            }
        });
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView attach(FrameLayout container) {
        if (container == null || designatedFloatingView == null) {
            containerView = new WeakReference<>(container);
            return this;
        }
        if (designatedFloatingView.getParent() == container) {
            return this;
        }
        if (designatedFloatingView.getParent() != null) {
            ((ViewGroup) designatedFloatingView.getParent()).removeView(designatedFloatingView);
        }
        containerView = new WeakReference<>(container);
        container.addView(designatedFloatingView);
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView detach(FrameLayout container) {
        if (designatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(designatedFloatingView)) {
            container.removeView(designatedFloatingView);
        }
        if (getContainerView() == container) {
            containerView = null;
        }
        return this;
    }
    
    @Override
    public BaseMagnetFloatingView getMagnetView() {
        return designatedFloatingView;
    }
    
    @Override
    public ApiMagnetFloatingView icon(@DrawableRes int resId) {
        iconRes = resId;
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView customView(DesignatedMagnetFloatingView viewGroup) {
        designatedFloatingView = viewGroup;
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView customView(@LayoutRes int resource) {
        layoutId = resource;
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedFloatingView != null) {
            designatedFloatingView.setLayoutParams(params);
        }
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView listener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener) {
        if (designatedFloatingView != null) {
            designatedFloatingView.setDesignatedMagnetViewListener(designatedMagnetFloatingViewListener);
        }
        return this;
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(13, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 500);
    }
    
    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
    }
    
    private void ensureFloatingView() {
        synchronized (this) {
            if (designatedFloatingView != null) {
                return;
            }
            designatedFloatingView = new DesignatedMagnetFloatingView(containerContext, layoutId);
            designatedFloatingView.setLayoutParams(getDesignatedLayoutParams());
            designatedFloatingView.setIconImage(iconRes);
            addViewToWindow(designatedFloatingView);
        }
    }
    
    private void addViewToWindow(final View view) {
        if (getContainerView() == null) {
            return;
        }
        getContainerView().addView(view);
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
    
    public ApiMagnetFloatingView setContainerContext(Context containerContext) {
        this.containerContext = containerContext;
        return this;
    }
    
    public void setApiAudioPlayerFloatingWindowListener(ApiMagnetFloatingListener apiMagnetFloatingListener) {
        this.apiMagnetFloatingListener = apiMagnetFloatingListener;
    }
}