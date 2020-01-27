package com.rinal.kasir.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import com.rinal.kasir.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private val itemDashboard : MutableList<ItemDashboard> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        rv_dashboard.layoutManager = GridLayoutManager(context, 2)
        rv_dashboard.adapter = context?.let {
            DashboardAdapter(
                it,
                itemDashboard
            ) { item ->
                Toast.makeText(context, item.nameDashboard, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initData(){
        val nameDashboard = resources.getStringArray(R.array.menu_name)
        val imgDashboard = resources.obtainTypedArray(R.array.menu_img)

        itemDashboard.clear()
        for (i in nameDashboard.indices){
            itemDashboard.add(
                ItemDashboard(
                    imgDashboard.getResourceId(
                        i,
                        0
                    ), nameDashboard[i]
                )
            )
        }
        imgDashboard.recycle()
    }
}
