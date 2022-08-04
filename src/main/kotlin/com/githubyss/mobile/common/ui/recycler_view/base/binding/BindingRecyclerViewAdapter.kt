package com.githubyss.mobile.common.ui.recycler_view.base.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.BR


/**
 * BindingRecyclerViewAdapter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/28 13:08:23
 */
class BindingRecyclerViewAdapter() : RecyclerView.Adapter<BindingRecyclerViewAdapter.BindingViewHolder>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BindingRecyclerViewAdapter::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private lateinit var items: List<BindingAdapterItem>

    /**  */
    lateinit var onItemClickListener: OnItemClickListener


    /** ****************************** Constructors ****************************** */

    /**  */
    constructor(items: List<BindingAdapterItem>) : this() {
        this.items = items
    }


    /** ****************************** Abstract ****************************** */


    /** ****************************** Override ****************************** */

    /**
     * onCreateViewHolder(ViewGroup, Int) 方法负责将获取将 ViewHolder 取出。
     *
     * @param
     * @return RecyclerView.ViewHolder 的子类实例
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        // 通过 DataBindingUtil 的 inflate 方法获取一个 ViewDataBinding，包含了 Layout 中所有的控件。
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false)
        return BindingViewHolder(binding)
    }

    /**
     * onBindViewHolder(BindingHolder, Int) 负责将实体类的内容一条一条的通过 set 方法显示到对应的界面上。
     *
     * @param
     * @return
     */
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bindXmlData(items[position])
        holder.bindXmlClickListener(onItemClickListener)
    }

    /**  */
    override fun getItemCount() = items.size

    /**  */
    override fun getItemViewType(position: Int): Int {
        return items[position].layoutId
    }

    /** ****************************** Functions ****************************** */

    /**  */
    fun updateDataList(items: List<BindingAdapterItem>) {
        this.items = items
        notifyDataSetChanged()
    }


    /** ****************************** Class ****************************** */

    /**
     * BindingViewHolder
     * ViewHolder 用来通过 findViewById 来存放 item 对应的 layout 里边的 View 控件。
     *
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2022/07/28 11:43:44
     */
    inner class BindingViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding = binding

        fun bindXmlData(item: BindingAdapterItem) {
            binding.setVariable(BR.item, item)
        }

        fun bindXmlClickListener(onItemClickListener: OnItemClickListener) {
            binding.setVariable(BR.onItemClickListener, onItemClickListener)
        }
    }


    /** ****************************** Interface ****************************** */

    /**  */
    interface OnItemClickListener {
        fun onItemClick(data: BindingAdapterItem)
    }
}
