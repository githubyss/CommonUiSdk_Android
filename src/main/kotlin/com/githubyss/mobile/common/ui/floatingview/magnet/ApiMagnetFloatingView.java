package com.githubyss.mobile.common.ui.floatingview.magnet;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

    private WeakReference<FrameLayout> containerView;
    private BaseMagnetFloatingView     mDesignatedFloatingView;
    private ViewGroup.LayoutParams     mLayoutParams = getParams();
    @LayoutRes
    private int                        mLayoutId = R.layout.en_floating_view;
    @DrawableRes
    private int                        mIconRes = R.drawable.imuxuan;

    private ApiMagnetFloatingView() {
    }

    public static ApiMagnetFloatingView get() {
        if (instance == null) {
            synchronized (ApiMagnetFloatingView.class) {
                if (instance == null) {
                    instance = new ApiMagnetFloatingView();
                }
            }
        }
        return instance;
    }

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
                if (mDesignatedFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(mDesignatedFloatingView) && getContainer() != null) {
                    getContainer().removeView(mDesignatedFloatingView);
                }
                mDesignatedFloatingView = null;
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
        if (container == null || mDesignatedFloatingView == null) {
            containerView = new WeakReference<>(container);
            return this;
        }
        if (mDesignatedFloatingView.getParent() == container) {
            return this;
        }
        if (mDesignatedFloatingView.getParent() != null) {
            ((ViewGroup) mDesignatedFloatingView.getParent()).removeView(mDesignatedFloatingView);
        }
        containerView = new WeakReference<>(container);
        container.addView(mDesignatedFloatingView);
        return this;
    }

    @Override
    public ApiMagnetFloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override
    public ApiMagnetFloatingView detach(FrameLayout container) {
        if (mDesignatedFloatingView != null && container != null && ViewCompat.isAttachedToWindow(mDesignatedFloatingView)) {
            container.removeView(mDesignatedFloatingView);
        }
        if (getContainer() == container) {
            containerView = null;
        }
        return this;
    }

    @Override
    public BaseMagnetFloatingView getMagnetView() {
        return mDesignatedFloatingView;
    }

    @Override
    public ApiMagnetFloatingView icon(@DrawableRes int resId) {
        mIconRes = resId;
        return this;
    }

    @Override
    public ApiMagnetFloatingView customView(BaseMagnetFloatingView viewGroup) {
        mDesignatedFloatingView = viewGroup;
        return this;
    }

    @Override
    public ApiMagnetFloatingView customView(@LayoutRes int resource) {
        mLayoutId = resource;
        return this;
    }

    @Override
    public ApiMagnetFloatingView layoutParams(ViewGroup.LayoutParams params) {
        mLayoutParams = params;
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.setLayoutParams(params);
        }
        return this;
    }

    @Override
    public ApiMagnetFloatingView listener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener) {
        if (mDesignatedFloatingView != null) {
            mDesignatedFloatingView.setMagnetViewListener(designatedMagnetFloatingViewListener);
        }
        return this;
    }

    private void ensureFloatingView() {
        synchronized (this) {
            if (mDesignatedFloatingView != null) {
                return;
            }
            DesignatedMagnetFloatingView designatedFloatingView = new DesignatedMagnetFloatingView(EnContext.get(), mLayoutId);
            mDesignatedFloatingView = designatedFloatingView;
            designatedFloatingView.setLayoutParams(mLayoutParams);
            designatedFloatingView.setIconImage(mIconRes);
            addViewToWindow(designatedFloatingView);
        }
    }

    private void addViewToWindow(final View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    private FrameLayout getContainer() {
        if (containerView == null) {
            return null;
        }
        return containerView.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.START;
        params.setMargins(13, params.topMargin, params.rightMargin, 500);
        return params;
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
}