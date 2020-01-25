package com.rinal.kasir.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.rinal.kasir.R
import kotlinx.android.synthetic.main.layout_screen.view.*

class SliderPagerAdapter (private val context: Context, private val listSlider: List<SliderItem>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutSlider = inflater.inflate(R.layout.layout_screen, null)

        layoutSlider.apply {
            slide_image.setImageResource(listSlider[position].img)
            intro_title.text = listSlider[position].title
            intro_description.text = listSlider[position].description
        }

        container.addView(layoutSlider)
        return layoutSlider
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = listSlider.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}