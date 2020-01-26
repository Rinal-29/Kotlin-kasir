package com.rinal.kasir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val itemDashboard : MutableList<ItemDashboard> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.dashboard)

        initData()

        rv_dashboard.layoutManager = GridLayoutManager(applicationContext, 2)
        rv_dashboard.adapter = DashboardAdapter(applicationContext, itemDashboard){
            Toast.makeText(applicationContext, it.nameDashboard, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initData(){
        val nameDashboard = resources.getStringArray(R.array.menu_name)
        val imgDashboard = resources.obtainTypedArray(R.array.menu_img)

        itemDashboard.clear()
        for (i in nameDashboard.indices){
            itemDashboard.add(
                ItemDashboard(imgDashboard.getResourceId(i,0), nameDashboard[i])
            )
        }

        imgDashboard.recycle()
    }
}
