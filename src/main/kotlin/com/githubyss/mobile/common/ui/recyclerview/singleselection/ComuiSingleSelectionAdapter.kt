package com.githubyss.mobile.common.ui.recyclerview.singleselection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.githubyss.mobile.common.ui.R

/**
 * ComuiSingleSelectionAdapter
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComuiSingleSelectionAdapter constructor(private val dataList: List<ComuiSingleSelectionModel>) : RecyclerView.Adapter<ComuiSingleSelectionAdapter.SingleSelectionViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    private var selectedPosition = -1
    private var onItemClickListener: OnItemClickListener? = null


    init {
        initData()
    }


    inner class SingleSelectionViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.findViewById<View>(R.id.tvTitle) as TextView
        var tvContent = view.findViewById<View>(R.id.tvContent) as TextView
        var ivSingle = view.findViewById<View>(R.id.ivSingle) as ImageView
    }


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this@ComuiSingleSelectionAdapter.onItemClickListener = onItemClickListener
    }

    private fun initData() {
        (0 until dataList.size)
                .filter { dataList[it].selectStatus }
                .forEach { selectedPosition = it }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleSelectionViewHolder {
        return SingleSelectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comui_item_single_selection, parent, false))
    }

    override fun onBindViewHolder(holder: SingleSelectionViewHolder, position: Int) {
        val dataModel = dataList[position]
        holder.tvTitle.text = dataModel.title
        holder.tvContent.text = dataModel.content
        holder.ivSingle.isSelected = dataModel.selectStatus
        holder.ivSingle.visibility = if (dataModel.selectStatus) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            if (selectedPosition != position) {
                dataList[selectedPosition].selectStatus = false
                notifyItemChanged(selectedPosition)

                selectedPosition = position

                dataList[selectedPosition].selectStatus = true
                notifyItemChanged(selectedPosition)

                onItemClickListener?.onItemClick(holder.itemView, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
