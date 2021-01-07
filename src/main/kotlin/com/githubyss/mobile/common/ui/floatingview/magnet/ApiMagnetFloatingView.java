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
import com.githubyss.mobile.common.ui.audio.model.AudioModel;
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
    private int                          iconRes  = R.drawable.comui_lucky_money;
    private ApiMagnetFloatingListener    apiMagnetFloatingListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ApiMagnetFloatingView() {
    }
    
    public static ApiMagnetFloatingView getInstance() {
        if (instance == null) {
            synchronized (ApiMagnetFloatingView.class) {
                if (instance == null) {
                    instance = new ApiMagnetFloatingView();
                }
            }
        }
        
        return instance;
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public ApiMagnetFloatingView init(Context context) {
        this.containerContext = context;
        return this;
    }
    
    @Override
    public ApiMagnetFloatingView show() {
        initLayoutParams();
        ensureFloatingView();
        listener(new DesignatedMagnetFloatingViewListener() {
            @Override
            public void onRemove(BaseMagnetFloatingView magnetView) {
                removeFloatingView();
            }
            
            @Override
            public void onClick(BaseMagnetFloatingView magnetView) {
                apiMagnetFloatingListener.onClick(magnetView);
            }
        });
        return this;
    }
    
    @Override
    public void close() {
        removeFloatingView();
    }
    
    @Override
    public BaseMagnetFloatingView getMagnetView() {
        return designatedFloatingView;
    }
    
    @Override
    public void icon(@DrawableRes int resId) {
        iconRes = resId;
    }
    
    @Override
    public void customView(DesignatedMagnetFloatingView viewGroup) {
        designatedFloatingView = viewGroup;
    }
    
    @Override
    public void customView(@LayoutRes int resource) {
        layoutId = resource;
    }
    
    @Override
    public void layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedFloatingView != null) {
            designatedFloatingView.setLayoutParams(params);
        }
    }
    
    @Override
    public void listener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener) {
        if (designatedFloatingView != null) {
            designatedFloatingView.setDesignatedMagnetFloatingViewListener(designatedMagnetFloatingViewListener);
        }
    }
    
    @Override
    public void attach(Activity activity) {
        attach(getActivityRoot(activity));
    }
    
    @Override
    public void attach(FrameLayout container) {
        if (container == null || designatedFloatingView == null) {
            containerView = new WeakReference<>(container);
            return;
        }
        if (designatedFloatingView.getParent() == container) {
            return;
        }
        if (designatedFloatingView.getParent() != null) {
            ((ViewGroup) designatedFloatingView.getParent()).removeView(designatedFloatingView);
        }
        containerView = new WeakReference<>(container);
        container.addView(designatedFloatingView);
    }
    
    @Override
    public void detach(Activity activity) {
        detach(getActivityRoot(activity));
    }
    
    @Override
    public void detach(FrameLayout container) {
        if (designatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(designatedFloatingView)) {
            container.removeView(designatedFloatingView);
        }
        if (getContainerView() == container) {
            containerView = null;
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(0, getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, 0);
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
    
    private void removeFloatingView() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (designatedFloatingView != null) {
                    try {
                        if (ViewCompat.isAttachedToWindow(designatedFloatingView) && getContainerView() != null) {
                            getContainerView().removeView(designatedFloatingView);
                        }
                    } catch (Exception e) {
                    }
                    designatedFloatingView = null;
                }
            }
        });
    }
    
    private void addViewToWindow(final View view) {
        if (getContainerView() == null) {
            return;
        }
        try {
            getContainerView().addView(view);
        } catch (Exception e) {
        }
    }
    
    private FrameLayout getContainerView() {
        if (containerView == null) {
            return null;
        }
        return containerView.get();
    }
    
    private FrameLayout.LayoutParams getDesignatedLayoutParams() {
        if (designatedLayoutParams == null) {
            designatedLayoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return (FrameLayout.LayoutParams) designatedLayoutParams;
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
    
    public ApiMagnetFloatingView setApiMagnetFloatingListener(ApiMagnetFloatingListener apiMagnetFloatingListener) {
        this.apiMagnetFloatingListener = apiMagnetFloatingListener;
        return this;
    }
}