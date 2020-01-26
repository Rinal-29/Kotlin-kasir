package com.rinal.kasir.slider

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.google.android.material.tabs.TabLayout
import com.rinal.kasir.login.LoginActivity
import com.rinal.kasir.R
import kotlinx.android.synthetic.main.activity_slider.*

class SliderActivity : AppCompatActivity() {

    private var listItem: MutableList<SliderItem> = mutableListOf()
    private lateinit var sliderPager : SliderPagerAdapter

    private var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // make an activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // check when this activity want to launch
        if (restorePrefData()){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // adding content view
        setContentView(R.layout.activity_slider)

        // hide the support action bar from app
        supportActionBar?.hide()

        // add list item to SliderItem
        listItem.add(
            SliderItem(
                "Page 1",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. ",
                R.drawable.undraw_shared_workspace
            )
        )
        listItem.add(
            SliderItem(
                "Page 2",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. ",
                R.drawable.undraw_code_thinking
            )
        )
        listItem.add(
            SliderItem(
                "Page 3",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. ",
                R.drawable.undraw_moving_forward
            )
        )

        // setup ViewPager with PagerAdapter
        sliderPager = SliderPagerAdapter(this, listItem)
        view_pager.adapter = sliderPager

        // setUp TabLayout with ViewPager
        tab_indicator.setupWithViewPager(view_pager)

        // when button next clicked
        btn_next.setOnClickListener {
            position = view_pager.currentItem

            if (position < listItem.size){

                // make button change view with current item position
                position ++
                view_pager.currentItem = position
            }

            if (position + 1 == listItem.size){
                loadLastScreen()
            }
        }

        // add tabListener when page change
        tab_indicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab?.position?.plus(1) == listItem.size) loadPreviousScreen()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position?.plus(1) == listItem.size) loadLastScreen()
            }
        })

        getStartedClicked()
    }

    private fun getStartedClicked() {
        btn_start.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            // save state so next time user run app
            // we know that he is already looked slider activity
            // put boolean to know the state
            savePrefsData()
            finish()
        }
    }

    private fun restorePrefData() : Boolean {
        // check isSliderLooked when it true or false
        val prefs = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("isSliderLooked", false)
    }

    private fun savePrefsData() {
        // using shared preferences
        // if slider looked set state to true
        val prefs = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("isSliderLooked", true)
        editor.apply()
    }

    private fun loadLastScreen() {
        btn_next.visibility = View.INVISIBLE
        btn_start.visibility = View.VISIBLE
        tab_indicator.visibility = View.INVISIBLE

        // button animation
        val animation = AnimationUtils.loadAnimation(applicationContext,
            R.anim.button_from_up_anim
        )
        btn_start.animation = animation
    }

    private fun loadPreviousScreen() {
        btn_next.visibility = View.VISIBLE
        tab_indicator.visibility = View.VISIBLE

        // hide button with animation
        val animation = AnimationUtils.loadAnimation(applicationContext,
            R.anim.button_to_up_anim
        )
        btn_start.animation = animation
        btn_start.visibility = View.INVISIBLE
    }
}
