package com.githubyss.mobile.common.ui.floatingview.container.app;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.githubyss.mobile.common.kit.info.ScreenInfo;
import com.githubyss.mobile.common.ui.floatingview.designate.icon.DesignatedIconView;
import com.githubyss.mobile.common.ui.floatingview.designate.icon.DesignatedIconViewListener;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetView;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetViewToDesignateViewListener;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;


/**
 * ContainerFloatingWithinAppIcon
 * <Description> 应用级别悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 19:38:50
 */
public class ContainerFloatingWithinAppIcon implements ContainerFloatingWithinAppInterface<ContainerFloatingWithinAppIcon, DesignatedIconView, FeatureMagnetView> {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static volatile ContainerFloatingWithinAppIcon instance;
    
    private Context containerContext;
    
    private WeakReference<FrameLayout> containerView;
    
    private DesignatedIconView     designatedView;
    private ViewGroup.LayoutParams designatedLayoutParams;
    
    private ContainerFloatingWithinAppListener containerFloatingWithinAppListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private ContainerFloatingWithinAppIcon(@NonNull Context context) {
        // super(context);
        initInContainer(context);
    }
    
    public static ContainerFloatingWithinAppIcon getInstance(Context context) {
        if (instance == null) {
            synchronized (ContainerFloatingWithinAppIcon.class) {
                if (instance == null) {
                    instance = new ContainerFloatingWithinAppIcon(context);
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
    public ContainerFloatingWithinAppIcon show() {
        initLayoutParams();
        ensureFloatingView();
        return this;
    }
    
    @Override
    public void close() {
        removeFloatingView();
    }
    
    @Override
    public ContainerFloatingWithinAppIcon layoutParams(ViewGroup.LayoutParams params) {
        designatedLayoutParams = params;
        if (designatedView != null) {
            designatedView.setLayoutParams(params);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon setMovable(boolean isMovable) {
        if (designatedView != null) {
            designatedView.setMovable(isMovable);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon customIcon(Drawable drawable) {
        if (designatedView != null) {
            designatedView.customIcon(drawable);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon customIcon(Bitmap bitmap) {
        if (designatedView != null) {
            designatedView.customIcon(bitmap);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon customIcon(int drawableId) {
        if (designatedView != null) {
            designatedView.customIcon(drawableId);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon customView(DesignatedIconView viewGroup) {
        this.designatedView = viewGroup;
        if (designatedView != null) {
            designatedView.customView(viewGroup);
        }
        return this;
    }
    
    @Override
    public ContainerFloatingWithinAppIcon customView(int layoutId) {
        if (designatedView != null) {
            designatedView.customView(layoutId);
        }
        return this;
    }
    
    @Override
    public DesignatedIconView getDesignatedView() {
        return designatedView;
    }
    
    @Override
    public FeatureMagnetView getFeatureView() {
        return designatedView;
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
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initInContainer(Context context) {
        this.containerContext = context;
    }
    
    private void initLayoutParams() {
        getDesignatedLayoutParams().gravity = Gravity.BOTTOM | Gravity.START;
        getDesignatedLayoutParams().setMargins(ScreenInfo.INSTANCE.dp2Px(14.0f), getDesignatedLayoutParams().topMargin, getDesignatedLayoutParams().rightMargin, ScreenInfo.INSTANCE.dp2Px(14.0f));
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
                designatedView = new DesignatedIconView(containerContext);
                designatedView.setLayoutParams(getDesignatedLayoutParams());
                designatedView.setBackgroundColor(0x000000FF);
                initListener();
                addViewToWindow(designatedView);
            }
        }
    }
    
    private void initListener() {
        if (designatedView != null) {
            designatedView.setDesignatedIconViewListener(new DesignatedIconViewListener() {
                @Override
                public void onClose() {
                    removeFloatingView();
                }
                
                @Override
                public void onClick() {
                }
            });
            
            designatedView.setFeatureMagnetViewToDesignateViewListener(new FeatureMagnetViewToDesignateViewListener() {
                @Override
                public void onRemove(FeatureMagnetView magnetView) {
                    removeFloatingView();
                }
                
                @Override
                public void onClick(FeatureMagnetView magnetView) {
                
                }
            });
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
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public ContainerFloatingWithinAppIcon setContainerFloatingWithinAppListener(ContainerFloatingWithinAppListener containerFloatingWithinAppListener) {
        this.containerFloatingWithinAppListener = containerFloatingWithinAppListener;
        return this;
    }
}
