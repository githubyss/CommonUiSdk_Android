package com.githubyss.mobile.common.ui.dialog.voice_select

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
abstract class BaseBindingRecyclerViewAdapter<B : ViewDataBinding, D>(@LayoutRes val layoutId: Int) : RecyclerView.Adapter<BaseBindingRecyclerViewAdapter.BaseViewHolder>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BaseBindingRecyclerViewAdapter::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private var dataList = ArrayList<D>()

    /**  */
    var onItemClickListener = object : OnItemClickListener<D> {
        override fun onItemClick(data: D) {
        }
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    abstract fun updateDataBinding(binding: B, data: D)


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BaseViewHolder(binding.root)
    }

    /**  */
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding = DataBindingUtil.findBinding<B>(holder.itemView) ?: return
        updateDataBinding(binding, dataList[position])
        binding.executePendingBindings()
    }

    /**  */
    override fun getItemCount() = dataList.size


    /** ****************************** Functions ****************************** */

    /**  */
    fun updateDataList(dataList: List<D>) {
        when {
            dataList.isEmpty() -> this.dataList.clear()
            else -> {
                this.dataList.clear()
                this.dataList.addAll(dataList)
                notifyDataSetChanged()
            }
        }
    }


    /** ****************************** Class ****************************** */

    /**  */
    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    /** ****************************** Interface ****************************** */

    /**  */
    interface OnItemClickListener<D> {
        fun onItemClick(data: D)
    }
}
