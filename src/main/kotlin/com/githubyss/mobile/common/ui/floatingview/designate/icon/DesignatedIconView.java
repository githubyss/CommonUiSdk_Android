package com.githubyss.mobile.common.ui.floatingview.designate.icon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.githubyss.mobile.common.kit.glide.GlideUtils;
import com.githubyss.mobile.common.ui.R;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetView;
import com.githubyss.mobile.common.ui.floatingview.feature.magnet.FeatureMagnetViewToDesignateViewListener;

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
public class DesignatedIconView extends FeatureMagnetView implements DesignatedIconViewInterface {
    
    /** ********* ********** ********** Properties ********** ********** ********** */
    
    private ImageView imageView_icon;
    private ImageView imageView_close;
    
    @LayoutRes
    private static int designatedLayoutId = R.layout.comui_floating_icon;
    
    @DrawableRes
    private int      iconId  = R.drawable.imuxuan;
    private String   iconUrl = "";
    private Drawable iconDrawable;
    private Bitmap   iconBitmap;
    
    private DesignatedIconView designatedView;
    
    private DesignatedIconViewListener designatedIconViewListener;
    
    
    /** ********* ********** ********** Constructors ********** ********** ********** */
    
    public DesignatedIconView(@NonNull Context context) {
        this(context, designatedLayoutId);
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
    
    
    /** ********* ********** ********** Override Methods ********** ********** ********** */
    
    @Override
    public boolean isFloatingShow() {
        if (designatedView != null) {
            return designatedView.isShown();
        }
        return false;
    }
    
    @Override
    public void setMovable(boolean isMovable) {
        super.isMovable = isMovable;
    }
    
    @Override
    public void customIcon(int drawableId) {
        iconId = drawableId;
        GlideUtils.INSTANCE.loadImage(context, iconId, imageView_icon);
    }
    
    @Override
    public void customIcon(String url) {
        iconUrl = url;
        GlideUtils.INSTANCE.loadImage(context, iconUrl, imageView_icon);
    }
    
    @Override
    public void customIcon(Drawable drawable) {
        iconDrawable = drawable;
        if (drawable != null) {
            imageView_icon.setImageDrawable(iconDrawable);
        }
    }
    
    @Override
    public void customIcon(Bitmap bitmap) {
        iconBitmap = bitmap;
        if (bitmap != null) {
            imageView_icon.setImageBitmap(iconBitmap);
        }
    }
    
    @Override
    public void customView(DesignatedIconView viewGroup) {
        designatedView = viewGroup;
    }
    
    @Override
    public void customView(int layoutId) {
        designatedLayoutId = layoutId;
    }
    
    
    /** ********** ********** ********** Public Methods ********** ********** ********** */
    
    
    /** ********* ********** ********** Private Methods ********** ********** ********** */
    
    private void initInDesignated() {
        initViewFindById(rootView);
        initListener();
    }
    
    private void initViewFindById(View view) {
        imageView_icon = view.findViewById(R.id.imageView_icon);
        imageView_close = view.findViewById(R.id.imageView_close);
        imageView_icon.setImageResource(iconId);
    }
    
    private void initListener() {
        imageView_close.setOnClickListener(onClickListener);
        
        designatedView.setFeatureMagnetViewToDesignateViewListener(new FeatureMagnetViewToDesignateViewListener() {
            @Override
            public void onRemove(FeatureMagnetView magnetView) {
            }
            
            @Override
            public void onClick(FeatureMagnetView magnetView) {
                // TODO: 2021/1/11 onClick
                designatedIconViewListener.onClick();
            }
        });
    }
    
    
    /** ********* ********** ********** Implementations ********** ********** ********** */
    
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.imageView_close) {
                designatedIconViewListener.onClose();
            }
        }
    };
    
    
    /** ********* ********** ********** Setter ********** ********** ********** */
    
    public void setDesignatedIconViewListener(DesignatedIconViewListener designatedIconViewListener) {
        this.designatedIconViewListener = designatedIconViewListener;
    }
}
