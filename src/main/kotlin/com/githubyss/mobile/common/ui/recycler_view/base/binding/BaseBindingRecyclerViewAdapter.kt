package com.githubyss.mobile.common.ui.recycler_view.base.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * BaseBindingRecyclerViewAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/26 13:30:45
 */
abstract class BaseBindingRecyclerViewAdapter<B : ViewDataBinding, D>(@LayoutRes val layoutId: Int) : RecyclerView.Adapter<BaseBindingRecyclerViewAdapter<B, D>.BaseBindingViewHolder>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BaseBindingRecyclerViewAdapter::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private lateinit var dataList: List<D>

    /**  */
    var onItemClickListener = object : OnItemClickListener<D> {
        override fun onItemClick(data: D) {
        }
    }


    /** ****************************** Constructors ****************************** */

    /**  */
    constructor(@LayoutRes layoutId: Int, dataList: List<D>) : this(layoutId) {
        this.dataList = dataList
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    abstract fun onBindViewHolder(binding: B, data: D)


    /** ****************************** Override ****************************** */

    /**
     * onCreateViewHolder(ViewGroup, Int) 方法负责将获取将 ViewHolder 取出。
     *
     * @param
     * @return RecyclerView.ViewHolder 的子类实例
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        // 通过 DataBindingUtil 的 inflate 方法获取一个 ViewDataBinding，包含了 Layout 中所有的控件。
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    /**
     * onBindViewHolder(BindingHolder, Int) 负责将实体类的内容一条一条的通过 set 方法显示到对应的界面上。
     *
     * @param
     * @return
     */
    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = DataBindingUtil.findBinding<B>(holder.itemView) ?: return
        onBindViewHolder(binding, dataList[position])
        binding.executePendingBindings()
    }

    /**  */
    /**
     *
     *
     * @param
     * @return 
     */
    override fun getItemCount() = dataList.size


    /** ****************************** Functions ****************************** */

    /**  */
    fun updateDataList(dataList: List<D>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }


    /** ****************************** Class ****************************** */

    /**
     * BaseBindingViewHolder
     * ViewHolder 用来通过 findViewById 来存放 item 对应的 layout 里边的 View 控件。
     *
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2022/07/28 11:43:44
     */
    inner class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    /** ****************************** Interface ****************************** */

    /**  */
    interface OnItemClickListener<D> {
        fun onItemClick(data: D)
    }
}
