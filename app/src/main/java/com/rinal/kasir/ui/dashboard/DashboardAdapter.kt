package com.rinal.kasir.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rinal.kasir.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_dashboard_item.view.*

class DashboardAdapter(private val context: Context, private val item : List<ItemDashboard>, private val listener : (ItemDashboard) -> Unit)
    : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.list_dashboard_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bindItem(itemDashboard: ItemDashboard, listener: (ItemDashboard) -> Unit) {
            itemView.apply {
                itemDashboard.imgDashboard?.let {
                    Picasso.get().load(it).fit().into(img_dashboard)
                }
                tv_dashboard.text = itemDashboard.nameDashboard
            }

            itemView.setOnClickListener {
                listener(itemDashboard)
            }
        }
    }
}