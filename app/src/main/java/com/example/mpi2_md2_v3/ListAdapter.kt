package com.example.mpi2_md2_v3

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val list: MutableList<Repository>) : RecyclerView.Adapter<ListAdapter.CosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CosViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return CosViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CosViewHolder, position: Int) {
        val repo = list[position]

        holder.view.name.text = repo.name
        holder.view.owner.text = "Owner: " + repo.owner.login

    }

    class CosViewHolder(val view: View): RecyclerView.ViewHolder(view)

}