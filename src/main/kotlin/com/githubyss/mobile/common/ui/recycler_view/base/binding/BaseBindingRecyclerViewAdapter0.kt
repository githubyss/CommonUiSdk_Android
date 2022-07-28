package com.githubyss.mobile.common.ui.recycler_view.base.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * BaseBindingRecyclerViewAdapter0
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/28 10:15:14
 */
abstract class BaseBindingRecyclerViewAdapter0<B : ViewDataBinding, D>() : RecyclerView.Adapter<BaseBindingRecyclerViewAdapter0<B, D>.BaseBindingViewHolder>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BaseBindingRecyclerViewAdapter0::class.java.simpleName
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
    constructor(dataList: List<D>) : this() {
        this.dataList = dataList
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    abstract fun onBindViewHolder(binding: B, data: D)

    @LayoutRes
    abstract fun setLayoutId(): Int


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return BaseBindingViewHolder(parent, setLayoutId())
    }

    /**  */
    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = DataBindingUtil.findBinding<B>(holder.itemView) ?: return
        onBindViewHolder(binding, dataList[position])
        binding.executePendingBindings()
    }

    /**  */
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
     * ViewHolder 用来通过 findViewById 来存放 item 对应的 layout 里边的 View 控件
     *
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2022/07/28 11:44:22
     */
    inner class BaseBindingViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int) : RecyclerView.ViewHolder(DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutId, parent, false).root) {
        val binding = DataBindingUtil.findBinding<B>(itemView)
    }


    /** ****************************** Interface ****************************** */

    /**  */
    interface OnItemClickListener<D> {
        fun onItemClick(data: D)
    }
}
