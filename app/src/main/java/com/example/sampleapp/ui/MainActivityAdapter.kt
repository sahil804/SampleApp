package com.example.sampleapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.data.models.Rows
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivityAdapter: RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    private var rowList:List<Rows> = ArrayList();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text = rowList.get(position).description
    }

    fun updateList(rows: List<Rows>) {
        rowList = rows
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName = itemView.tv_name
//        val txtName = itemView.findViewById<TextView>(R.id.txtName)
//        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)

    }


}