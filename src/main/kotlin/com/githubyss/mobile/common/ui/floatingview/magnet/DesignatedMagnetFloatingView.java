package com.githubyss.mobile.common.ui.floatingview.magnet;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.floatingview.feature.FeatureMagnetFloatingView;
import com.githubyss.mobile.common.ui.floatingview.feature.FeatureMagnetFloatingViewListener;

import androidx.annotation.DrawableRes;
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
public class DesignatedMagnetFloatingView extends FeatureMagnetFloatingView {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private ImageView imageView_icon;
    
    private DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public DesignatedMagnetFloatingView(@NonNull Context context) {
        this(context, R.layout.comui_floating_icon);
    }
    
    public DesignatedMagnetFloatingView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        if (rootView == null) {
            rootView = inflate(context, resource, this);
            initDesignated();
        }
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    public void setIconImage(@DrawableRes int resId) {
        imageView_icon.setImageResource(resId);
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private void initDesignated() {
        initViewFindById(rootView);
        initListener();
    }
    
    private void initViewFindById(View view) {
        imageView_icon = view.findViewById(R.id.imageView_icon);
    }
    
    private void initListener() {
        // imageView_icon.setOnClickListener(onClickListener);
        
        this.setFeatureMagnetFloatingViewListener(new FeatureMagnetFloatingViewListener() {
            @Override
            public void onRemove(FeatureMagnetFloatingView magnetView) {
                designatedMagnetFloatingViewListener.onRemove(magnetView);
            }
            
            @Override
            public void onClick(FeatureMagnetFloatingView magnetView) {
                designatedMagnetFloatingViewListener.onClick(magnetView);
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
    
    public void setDesignatedMagnetFloatingViewListener(DesignatedMagnetFloatingViewListener designatedMagnetFloatingViewListener) {
        this.designatedMagnetFloatingViewListener = designatedMagnetFloatingViewListener;
    }
}
