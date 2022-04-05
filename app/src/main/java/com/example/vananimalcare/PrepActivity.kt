package com.example.vananimalcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class PrepActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    private val prepSlideAdapter = PrepSlideAdapter(
        listOf(
            PrepSlide(
                "Title1",
                "Description1",
                R.drawable.cats),
            PrepSlide(
                "Title2",
                "Description2",
                R.drawable.cats),
            PrepSlide(
                "Title3",
                "Description3",
                R.drawable.cats)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prep)

        viewPager = findViewById(R.id.prepSliderViewPager)

        viewPager.adapter = prepSlideAdapter
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(prepSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }
}