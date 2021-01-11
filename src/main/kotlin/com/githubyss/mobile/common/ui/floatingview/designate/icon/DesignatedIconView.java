package com.githubyss.mobile.common.ui.floatingview.designate.icon;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetView;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetViewToDesignateViewListener;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;


/**
 * DesignatedFloatingMagnetView
 * <Description> 特定的磁力吸附悬浮窗
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/09 11:30:01
 */
public class DesignatedIconView extends FeatureMagnetView implements DesignatedIconViewInterface {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private ImageView imageView_icon;
    
    @LayoutRes
    private static int layoutId = R.layout.comui_floating_icon;
    
    private DesignatedIconView designatedView;
    
    private DesignatedIconViewListener designatedIconViewListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public DesignatedIconView(@NonNull Context context) {
        this(context, layoutId);
    }
    
    public DesignatedIconView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        if (rootView == null) {
            rootView = inflate(context, resource, this);
            designatedView = this;
            initInBase();
            initInDesignated();
        }
    }
    
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    @Override
    public void icon(int resId) {
        imageView_icon.setImageResource(resId);
    }
    
    @Override
    public void customView(DesignatedIconView viewGroup) {
        designatedView = viewGroup;
    }
    
    @Override
    public void customView(int resource) {
        layoutId = resource;
    }
    
    @Override
    public boolean isFloatingShow() {
        if (designatedView != null) {
            return designatedView.isShown();
        }
        return false;
    }
    
    @Override
    public DesignatedIconView getDesignatedView() {
        return designatedView;
    }
    
    @Override
    public FeatureMagnetView getMagnetView() {
        return designatedView;
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initInDesignated() {
        initViewFindById(rootView);
        initListener();
    }
    
    private void initViewFindById(View view) {
        imageView_icon = view.findViewById(R.id.imageView_icon);
    }
    
    private void initListener() {
        // imageView_icon.setOnClickListener(onClickListener);
        
        designatedView.setFeatureMagnetViewToDesignateViewListener(new FeatureMagnetViewToDesignateViewListener() {
            @Override
            public void onRemove(FeatureMagnetView magnetView) {
                // TODO: 2021/1/11 onRemove
                designatedIconViewListener.onRemove(magnetView);
            }
            
            @Override
            public void onClick(FeatureMagnetView magnetView) {
                // TODO: 2021/1/11 onClick
                designatedIconViewListener.onClick(magnetView);
            }
        });
    }
    
    
    // ---------- ---------- ---------- Implementations ---------- ---------- ----------
    
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public void setDesignatedIconViewListener(DesignatedIconViewListener designatedIconViewListener) {
        this.designatedIconViewListener = designatedIconViewListener;
    }
}
