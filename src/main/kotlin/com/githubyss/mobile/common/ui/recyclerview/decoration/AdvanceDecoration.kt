package com.githubyss.mobile.common.ui.recyclerview.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.debug.recyclerview.type.MultiType
import com.githubyss.mobile.common.kit.util.ResourceUtils
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 * AdvanceDecoration
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 17:15:38
 */
class AdvanceDecoration : RecyclerView.ItemDecoration {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    // 采用系统内置的风格的分割线
    private val attrs = IntArray(1) { android.R.attr.listDivider }
    private var divider: Drawable? = null
    private var dividerWeight: Int = 1
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    @OrientationType
    private var orientation = OrientationType.VERTICAL
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(context: Context?, @OrientationType orientation: Int, drawableId: Int? = null, dividerWeight: Int? = null, dividerColor: Int? = null) : super() {
        if (orientation != OrientationType.VERTICAL && orientation != OrientationType.HORIZONTAL) {
            throw IllegalArgumentException("请输入正确的参数！")
        }
        this.orientation = orientation
        val typedArray = context?.obtainStyledAttributes(attrs)
        
        divider = if (drawableId == null) {
            typedArray?.getDrawable(0)
        } else {
            ResourceUtils.getDrawable(drawableId)
        }
        this.dividerWeight = dividerWeight ?: (when (orientation) {
            OrientationType.HORIZONTAL -> divider?.intrinsicWidth ?: 0
            OrientationType.VERTICAL -> divider?.intrinsicHeight ?: 0
            else -> this.dividerWeight
        })
        
        dividerColor?.let {
            paint.color = dividerColor
            paint.style = Paint.Style.FILL
        }
        
        typedArray?.recycle()
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    /**
     * 获取项目的偏离
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.childCount - 1
        when (parent.adapter?.getItemViewType(position)) {
            MultiType.EMPTY, MultiType.HEADER, MultiType.FOOTER -> {
                outRect.set(0, 0, 0, 0)
            }
            else -> {
                when (orientation) {
                    OrientationType.HORIZONTAL -> {
                        outRect.set(0, 0, dividerWeight, 0)
                    }
                    OrientationType.VERTICAL -> {
                        outRect.set(0, 0, 0, dividerWeight)
                    }
                }
            }
        }
    }
    
    /**
     * 进行自定义绘制
     *
     * @param
     * @return
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        when (orientation) {
            OrientationType.VERTICAL -> {
                drawHorizontalDecoration(c, parent)
            }
            OrientationType.HORIZONTAL -> {
                drawVerticalDecoration(c, parent)
            }
        }
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    /**
     * 绘制水平方向的分割线（横线）
     *
     * @param c
     * @param parent
     */
    private fun drawHorizontalDecoration(c: Canvas, parent: RecyclerView) {
        // 获取分割线的左边距，即 RecyclerView 的 padding 值
        val left = parent.paddingLeft
        // 分割线右边距
        val right = parent.measuredWidth - parent.paddingRight
        val childCount = parent.childCount
        // 遍历所有 item view，为它们的下方绘制分割线
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + dividerWeight
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
    
    /**
     * 绘制垂直方向的分割线（竖线）
     *
     * @param c
     * @param parent
     */
    private fun drawVerticalDecoration(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + (divider?.intrinsicWidth ?: 0)
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
    
    
    /** ********** ********** ********** Class ********** ********** **********  */
    
    @Documented
    @IntDef(OrientationType.HORIZONTAL, OrientationType.VERTICAL)
    @Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @Retention(RetentionPolicy.SOURCE)
    annotation class OrientationType {
        companion object {
            const val HORIZONTAL = 0x00
            const val VERTICAL = 0x01
        }
    }
}
