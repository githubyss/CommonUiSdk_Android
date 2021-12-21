// package com.githubyss.mobile.common.ui.scroll_view
//
// import android.animation.FloatEvaluator
// import android.animation.ValueAnimator
// import android.annotation.SuppressLint
// import android.content.Context
// import android.graphics.Rect
// import android.graphics.drawable.AnimationDrawable
// import android.util.AttributeSet
// import android.view.*
// import android.view.animation.LinearInterpolator
// import android.widget.FrameLayout
// import android.widget.ImageView
// import android.widget.LinearLayout
// import android.widget.TextView
// import androidx.core.widget.NestedScrollView
// import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
// import com.githubyss.mobile.common.kit.util.LogUtils
// import com.githubyss.mobile.common.kit.util.ScreenUtils
// import com.githubyss.mobile.common.ui.R
//
//
// /**
//  * SlideRefreshLoadScrollView
//  *
//  * 滑动结束时scrollY在0到100范围内时，强制要求滑到0或100
//  *
//  * @author Ace Yan
//  * @github githubyss
//  * @createdTime 2021/12/10 17:36:38
//  */
// class SlideRefreshLoadScrollView : NestedScrollView {
//
//     /** ********** ********** ********** Companion ********** ********** ********** */
//
//     companion object {
//         private val TAG: String = SlideRefreshLoadScrollView::class.java.simpleName
//         private const val SMALL_TITLE_CHANGE = 100
//
//         /*************************下拉刷新相关 */
//         private const val DELAY_MILLIS: Long = 200
//         private const val RELEASE_To_REFRESH = 0
//         private const val PULL_To_REFRESH = 1
//         private const val REFRESHING = 2
//         private const val DONE = 3
//         private const val LOADING = 4
//         private const val RATIO = 3
//         private const val RELEASE_TO_LOADING = 5
//         private const val SLIDE_UP_TO_LOADING = 6
//     }
//
//     /** ********** ********** ********** Properties ********** ********** ********** */
//
//     var isHomePage = false
//     private var downX = 0
//     private var downY = 0
//     private var mTouchSlop: Int
//     private var scrollViewActualHeight = 0
//     private var headContentHeight = 0
//     private var footerContentHeight = 0
//     private var footerTextHeight = 0
//     private val HEAD_PADDING = 0
//     private val FOOTER_PADDING = 0
//     private var mHeadPadding = 0
//     private var mFooterPadding = 0
//     private var headerView: FrameLayout? = null
//     private var footerView: LinearLayout? = null
//     private var refreshListener: SwipeRefreshLayout.OnRefreshListener? = null
//     private var isRefreshable = false
//     private var state = 0
//     private var ivPullLoading: ImageView? = null
//     private var ivPullUpLoading: ImageView? = null
//     private var animationDrawable: AnimationDrawable? = null
//     private var animationUpDrawable: AnimationDrawable? = null
//     private var tvPullLoading: TextView? = null
//     private var tvPullUpLoading: TextView? = null
//     private var tvPullUpSeeMore: TextView? = null
//     private var canReturn = false
//     private var isRecoded = false
//     private var startY = 0
//     private var scrollTop = 0
//     private var onScrollChangedListener: OnScrollChangedListener? = null
//     private var isDown: Boolean = false
//     private var isToBottom = false
//     var canSlideUp = false
//     private var onSlideUpListener: OnSlideUpListener? = null
//
//
//     /** ********** ********** ********** Constructors ********** ********** ********** */
//
//     constructor(context: Context?) : super(context!!) {
//         mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
//     }
//
//     constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
//         mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
//     }
//
//
//     /** ********** ********** ********** Override ********** ********** ********** */
//
//     /*************************滑动监听相关 */
//     override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
//         super.onScrollChanged(l, t, oldl, oldt)
//         isDown = t - oldt <= 0
//         scrollTop = t
//         if (onScrollChangedListener != null) {
//             onScrollChangedListener!!.onScrollChanged(t, oldt)
//         }
//     }
//
//
//     /** ********** ********** ********** Functions ********** ********** ********** */
//
//     fun setOnScrollChangedListener(onScrollChangedListener: OnScrollChangedListener?) {
//         this.onScrollChangedListener = onScrollChangedListener
//     }
//
//     fun initHead(parent: LinearLayout) {
//         val inflater = LayoutInflater.from(context)
//         initHeadPadding()
//         headerView = inflater.inflate(R.layout.comui_header_circular_progress, null) as FrameLayout
//         tvPullLoading = headerView!!.findViewById(R.id.tv_pull_loading)
//         measureView(headerView)
//         headContentHeight = headerView!!.measuredHeight
//         headerView!!.setPadding(0, -headContentHeight, 0, 0)
//         headerView!!.invalidate()
//         parent.addView(headerView, 0)
//         state = DONE
//         isRefreshable = false
//         canReturn = false
//     }
//
//     fun initFooter(parent: LinearLayout) {
//         val inflater = LayoutInflater.from(context)
//         initFooterPadding()
//         footerView = inflater.inflate(R.layout.layout_refresh_scroll_default_footer2, null) as LinearLayout
//         ivPullUpLoading = footerView?.findViewById(R.id.iv_pull_loading)
//         ivPullUpLoading?.setBackgroundResource(R.drawable.pull_to_refresh_frame_anim)
//         animationUpDrawable = ivPullUpLoading!!.background as AnimationDrawable
//         animationUpDrawable!!.start()
//         tvPullUpLoading = footerView?.findViewById(R.id.tv_pull_loading)
//         measureView(footerView)
//         footerContentHeight = footerView?.measuredHeight ?: 0
//         footerTextHeight = tvPullUpLoading?.measuredHeight ?: 0
//         LogUtils.d("footerView?.measuredHeight: ${footerView?.measuredHeight}, tvPullUpLoading?.measuredHeight: ${tvPullUpLoading?.measuredHeight}")
//         footerView?.setPadding(0, 0, 0, -(footerContentHeight - footerTextHeight))
//         footerView?.invalidate()
//         parent.addView(footerView, parent.childCount)
//         state = DONE
//         canSlideUp = false
//         canReturn = false
//     }
//
//     fun hideFooter() {
//         footerView?.visibility = View.GONE
//     }
//
//     fun showFooter() {
//         footerView?.visibility = View.VISIBLE
//     }
//
//     private fun initHeadPadding() {
//         val scale = context.resources.displayMetrics.density
//         mHeadPadding = (HEAD_PADDING * ScreenUtils.getScreenDensity() + 0.5f).toInt()
//     }
//
//     private fun initFooterPadding() {
//         val scale = context.resources.displayMetrics.density
//         mFooterPadding = (FOOTER_PADDING * scale + 0.5f).toInt()
//     }
//
//     private fun measureView(child: FrameLayout?) {
//         var params = child!!.layoutParams
//         if (params == null) {
//             params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//         }
//         val childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, params.width)
//         val lpHeight = params.height
//         val childHeightSpec: Int = if (lpHeight > 0) {
//             MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY)
//         }
//         else {
//             MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.UNSPECIFIED)
//         }
//         child.measure(childWidthSpec, childHeightSpec)
//     }
//
//     private fun onRefresh() {
//         if (refreshListener != null) {
//             refreshListener!!.onRefresh()
//         }
//     }
//
//     private fun onLoading() {
//         onSlideUpListener?.onSlideUp()
//     }
//
//     fun completeRefresh() {
//         state = DONE
//         changeHeaderViewByState()
//         invalidate()
//         scrollTo(0, 0)
//     }
//
//     fun completeSeeMore() {
//         state = DONE
//         changeFooterViewByState()
//         invalidate()
//         scrollTo(0, getChildAt(0).measuredHeight)
//     }
//
//     private fun changeHeaderViewByState() {
//         when (state) {
//             DONE -> {
//                 val currentTop = headerView!!.paddingTop.toFloat()
//                 val targetTop = -1 * headContentHeight.toFloat()
//                 val animator = ValueAnimator.ofFloat(currentTop, targetTop).setDuration(250)
//                 animator.addUpdateListener { animation ->
//                     val value = animation.animatedValue as Float
//                     headerView!!.setPadding(0, value.toInt(), 0, 0)
//                 }
//                 animator.setEvaluator(FloatEvaluator())
//                 animator.interpolator = LinearInterpolator()
//                 animator.start()
//                 ivPullLoading!!.visibility = View.INVISIBLE
//                 tvPullLoading!!.visibility = View.INVISIBLE
//
//             }
//             PULL_To_REFRESH -> {
//                 ivPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.text = context.getString(R.string.down_refresh)
//                 if (onScrollChangedListener != null) {
//                     onScrollChangedListener!!.onPullStart()
//                 }
//             }
//             RELEASE_To_REFRESH -> {
//                 ivPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.text = context.getString(R.string.up_refresh)
//             }
//             REFRESHING -> {
//                 ivPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.visibility = View.VISIBLE
//                 tvPullLoading!!.text = context.getString(R.string.on_refresh)
//                 headerView!!.setPadding(0, mHeadPadding, 0, mHeadPadding)
//             }
//             else -> {
//             }
//         }
//     }
//
//     private fun changeFooterViewByState() {
//         when (state) {
//             DONE -> {
//                 val currentTop = footerView!!.paddingTop.toFloat()
//                 val targetTop = -1 * (footerContentHeight - footerTextHeight).toFloat()
//                 val animator = ValueAnimator.ofFloat(currentTop, targetTop).setDuration(250)
//                 animator.addUpdateListener { animation ->
//                     val value = animation.animatedValue as Float
//                     footerView!!.setPadding(0, value.toInt(), 0, 0)
//                 }
//                 animator.setEvaluator(FloatEvaluator())
//                 animator.interpolator = LinearInterpolator()
//                 animator.start()
//                 ivPullUpLoading!!.visibility = View.GONE
//                 tvPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.text = "上滑查看更多资讯"
//                 isToBottom = false
//             }
//             SLIDE_UP_TO_LOADING -> {
//                 ivPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.text = "上滑查看更多资讯"
//                 if (onScrollChangedListener != null) {
//                     onScrollChangedListener!!.onPullStart()
//                 }
//             }
//             RELEASE_TO_LOADING -> {
//                 ivPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.text = "松手查看更多资讯"
//             }
//             LOADING -> {
//                 ivPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.visibility = View.VISIBLE
//                 tvPullUpLoading!!.text = "查看更多资讯"
//                 footerView!!.setPadding(0, mFooterPadding, 0, mFooterPadding)
//             }
//             else -> {
//             }
//         }
//     }
//
//     /**
//      * 修复bug当headView显示松开刷新和下拉刷新时，部分界面（子控件过大也有当前点击的子控件超过一屏）
//      * 点击下拉再往上滑时松开刷新字样不会变成下拉刷新，原因是scrollview自身滑动了，getScrollY已经不等于0了。
//      * 修改：当height大于5（理论上是0，容错值)时，scrollview禁止滑动，height>0时，表示下拉头出现，这时禁止滑动
//      */
//     @SuppressLint("ClickableViewAccessibility")
//     override fun onTouchEvent(ev: MotionEvent): Boolean {
//         return if (headerView!!.height > 5) {
//             true
//         }
//         else try {
//             super.onTouchEvent(ev)
//         }
//         catch (e: IllegalArgumentException) {
//             LogUtils.i(TAG, e.message)
//             false
//         }
//     }
//
//     override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//         when (ev.action) {
//             MotionEvent.ACTION_DOWN -> {
//                 scrollViewActualHeight = getChildAt(0).measuredHeight - measuredHeight
//             }
//             MotionEvent.ACTION_MOVE -> {
//                 move(ev)
//             }
//             MotionEvent.ACTION_UP -> {
//                 up()
//                 if (isHomePage) {
//                     val halfScroll = SMALL_TITLE_CHANGE / 2
//                     if (scrollY in 1..halfScroll) {
//                         Handler().post {
//                             smoothScrollTo(0, 0)
//                         }
//                     }
//                     else if (scrollY in (halfScroll + 1)..SMALL_TITLE_CHANGE) {
//                         Handler().post {
//                             smoothScrollTo(0, SMALL_TITLE_CHANGE)
//                         }
//                     }
//                 }
//             }
//         }
//         return try {
//             super.dispatchTouchEvent(ev)
//         }
//         catch (e: IllegalArgumentException) {
//             LogUtils.i(TAG, e.message)
//             false
//         }
//     }
//
//     override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//         //当处于顶部，且为下拉刷新状态的时候，拦截掉触摸事件
//         if (scrollY == 0 && (state == REFRESHING || state == LOADING || state == PULL_To_REFRESH || state == SLIDE_UP_TO_LOADING || state == RELEASE_To_REFRESH || state == RELEASE_TO_LOADING)) {
//             return false
//         }
//         when (ev.action) {
//             MotionEvent.ACTION_DOWN -> {
//                 downX = ev.rawX.toInt()
//                 downY = ev.rawY.toInt()
//             }
//             MotionEvent.ACTION_MOVE -> {
//                 val moveY = ev.rawY.toInt()
//                 if (abs(moveY - downY) > mTouchSlop) {
//                     return true
//                 }
//             }
//         }
//         return super.onInterceptTouchEvent(ev)
//     }
//
//
//     private fun move(ev: MotionEvent): Boolean {
//         val tempY = ev.y.toInt()
//         isToBottom = (scrollTop >= scrollViewActualHeight)
//         var currentScrollViewHeight = getChildAt(0).measuredHeight - measuredHeight
//         if (isRefreshable && !isToBottom) {
//             LogUtils.d("Down >>> isRecoded: $isRecoded, isToBottom: $isToBottom, scrollY: $scrollY, startY: $startY, tempY: $tempY, scrollTop: $scrollTop, scrollViewActualHeight: $scrollViewActualHeight, currentScrollViewHeight: $currentScrollViewHeight")
//             if (!isRecoded && scrollY == 0) {
//                 isRecoded = true
//                 startY = tempY
//             }
//             if (state == DONE) {
//                 if (tempY - startY > 0) {
//                     state = PULL_To_REFRESH
//                     changeHeaderViewByState()
//                 }
//             }
//             if (state == PULL_To_REFRESH) {
//                 canReturn = true
//                 if ((tempY - startY) / RATIO >= headContentHeight) {
//                     state = RELEASE_To_REFRESH
//                     changeHeaderViewByState()
//                 }
//                 else if (tempY - startY <= 0) {
//                     state = DONE
//                     changeHeaderViewByState()
//                 }
//             }
//             if (state != REFRESHING && isRecoded && state != LOADING) {
//                 if (state == RELEASE_To_REFRESH) {
//                     canReturn = true
//                     if ((tempY - startY) / RATIO < headContentHeight && tempY - startY > 0) {
//                         state = PULL_To_REFRESH
//                         changeHeaderViewByState()
//                     }
//                     else if (tempY - startY <= 0) {
//                         state = DONE
//                         changeHeaderViewByState()
//                     }
//                     else {
//                     }
//                 }
//                 if (state == PULL_To_REFRESH) {
//                     val xx = -headContentHeight + (tempY - startY) / RATIO
//                     headerView!!.setPadding(0, xx, 0, 0)
//                 }
//                 if (state == RELEASE_To_REFRESH) {
//                     headerView!!.setPadding(0, -headContentHeight + (tempY - startY) / RATIO, 0, 0)
//                 }
//                 if (canReturn) {
//                     canReturn = false
//                     return true
//                 }
//             }
//         }
//         else if (canSlideUp && isToBottom) {
//             LogUtils.d("Up >>> isRecoded: $isRecoded, isToBottom: $isToBottom, scrollY: $scrollY, startY: $startY, tempY: $tempY, scrollTop: $scrollTop, scrollViewActualHeight: $scrollViewActualHeight, currentScrollViewHeight: $currentScrollViewHeight")
//             if (!isRecoded) {
//                 isRecoded = true
//                 startY = tempY
//             }
//             val deltaY = startY - tempY
//             if (state == DONE) {
//                 if (deltaY > 0) {
//                     state = SLIDE_UP_TO_LOADING
//                     changeFooterViewByState()
//                 }
//             }
//             if (state == SLIDE_UP_TO_LOADING) {
//                 canReturn = true
//                 if ((deltaY) / RATIO >= footerContentHeight) {
//                     state = RELEASE_TO_LOADING
//                     changeFooterViewByState()
//                 }
//                 else if (deltaY <= 0) {
//                     state = DONE
//                     changeFooterViewByState()
//                 }
//             }
//             if (state != REFRESHING && isRecoded && state != LOADING) {
//                 if (state == RELEASE_TO_LOADING) {
//                     canReturn = true
//                     LogUtils.d("RELEASE_TO_LOADING >>> (deltaY) / RATIO: ${(deltaY) / RATIO}, footerContentHeight: $footerContentHeight, deltaY: $deltaY")
//                     if ((deltaY) / RATIO < footerContentHeight && deltaY > 0) {
//                         state = SLIDE_UP_TO_LOADING
//                         changeFooterViewByState()
//                     }
//                     else if (deltaY <= 0) {
//                         state = DONE
//                         changeFooterViewByState()
//                     }
//                     else {
//                     }
//                 }
//                 if (state == SLIDE_UP_TO_LOADING) {
//                     val paddingBottom = -(footerContentHeight - footerTextHeight) + deltaY / RATIO
//                     footerView?.setPadding(0, 0, 0, paddingBottom)
//                 }
//                 if (state == RELEASE_TO_LOADING) {
//                     val paddingBottom = -(footerContentHeight - footerTextHeight) + deltaY / RATIO
//                     footerView?.setPadding(0, 0, 0, paddingBottom)
//                 }
//                 if (canReturn) {
//                     canReturn = false
//                     return true
//                 }
//             }
//         }
//         return false
//     }
//
//     private fun up() {
//         if (state != REFRESHING && state != LOADING) {
//             when (state) {
//                 DONE -> {
//                 }
//                 PULL_To_REFRESH -> {
//                     state = DONE
//                     changeHeaderViewByState()
//                     if (onScrollChangedListener != null) {
//                         onScrollChangedListener!!.onPullFail()
//                     }
//                 }
//                 RELEASE_To_REFRESH -> {
//                     state = REFRESHING
//                     changeHeaderViewByState()
//                     onRefresh()
//                 }
//                 SLIDE_UP_TO_LOADING -> {
//                     state = DONE
//                     changeFooterViewByState()
//                 }
//                 RELEASE_TO_LOADING -> {
//                     state = LOADING
//                     changeFooterViewByState()
//                     onLoading()
//                 }
//             }
//         }
//         isRecoded = false
//     }
//
//     fun setOnRefreshListener(refreshListener: SwipeRefreshLayout.OnRefreshListener?, refreshable: Boolean) {
//         this.refreshListener = refreshListener
//         isRefreshable = refreshable
//     }
//
//     fun setOnSlideUpListener(onSlideUpListener: OnSlideUpListener?) {
//         this.onSlideUpListener = onSlideUpListener
//     }
//
//     interface OnSlideUpListener {
//         fun onSlideUp()
//     }
//
//     //防止自动滑动到底部
//     override fun computeScrollDeltaToGetChildRectOnScreen(rect: Rect): Int {
//         return 0
//     }
//
//
//     //interface OnScrollChangedListener2 {
//     //    /**
//     //     * 用来控制头部的四个小图标
//     //     */
//     //    fun onScrollChanged(top: Int, oldTop: Int)
//     //
//     //    /**
//     //     * 开始往下拉就做一个监听
//     //     */
//     //    fun onPullStart()
//     //    fun onPullFail()
//     //}
//     //
//     //interface OnRefreshListener2 {
//     //    fun onRefresh()
//     //}
// }
//
// interface OnScrollChangedListener {
//     // 用来控制头部的四个小图标
//     fun onScrollChanged(top: Int, oldTop: Int)
//     fun onPullStart()
//     fun onPullFail()
// }
//
// interface onRefreshListener {
//     fun onRefresh()
// }
