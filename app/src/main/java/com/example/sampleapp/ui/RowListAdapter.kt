package com.example.sampleapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.databinding.ItemMainBinding

class RowListAdapter() : RecyclerView.Adapter<RowListAdapter.ViewHolder>() {


    private var rowList:List<Rows> = ArrayList();

    class ViewHolder(val itemMainBinding: ItemMainBinding) : RecyclerView.ViewHolder(itemMainBinding.getRoot()) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMainBinding: ItemMainBinding = DataBindingUtil.inflate<ItemMainBinding>(LayoutInflater.from(parent.context),
            R.layout.item_main, parent, false)
        return ViewHolder(itemMainBinding)
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    fun updateList(rows: List<Rows>) {
        Log.d("Sahil"," *** "+rows.size + " "+Thread.currentThread().name)
        rowList = rows
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.itemMainBinding.tvName?.text = rowList.get(position).description
        holder.itemMainBinding.row = rowList.get(position)
        holder.itemMainBinding.executePendingBindings()
    }
}