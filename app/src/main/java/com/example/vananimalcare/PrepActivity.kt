package com.example.vananimalcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class PrepActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    private val prepSlideAdapter = PrepSlideAdapter(
        listOf(
            PrepSlide(
                "Adopting a Dog",
                "-License your dog.\n" +
                        "-Make sure it wears its collar and licence tag.\n" +
                        "-Teach basic obedience.\n" +
                        "-Use a leash.\n" +
                        "-Pick up its waste.",
                R.drawable.care_dog),
            PrepSlide(
                "Selecting your Pet",
                "-Choose the right pet for your personality and circumstances.\n" +
                        "-Research the behaviours and characteristics of the pet you're considering.",
                R.drawable.care_cats),
            PrepSlide(
                "Living Situation",
                "-Check restrictions on pets at your home.\n" +
                        "-Ensure you have enough space.\n" +
                        "-Consider your surrounding outdoor areas.",
                R.drawable.care_house),
            PrepSlide(
                "Lifestyle",
                "-Prepare for a long-term emotional and financial commitment.\n" +
                        "-Make time to interact with your pet.\n" +
                        "-Consider the allergies and health conditions of people in your home.",
                R.drawable.care_lifestyle),
            PrepSlide(
                "Are you Ready to Adopt a Pet?",
                "All animals need attention, interaction, and adequate exercise. Recognize the impact of bringing a pet into your home.",
                R.drawable.care_confused)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prep)

        viewPager = findViewById(R.id.prepSliderViewPager)
        viewPager.adapter = prepSlideAdapter

        findViewById<Button>(R.id.buttonNext).setOnClickListener{
            if(viewPager.currentItem + 1 < prepSlideAdapter.itemCount) {
                viewPager.currentItem += 1
            } else {
                Intent(applicationContext, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        findViewById<TextView>(R.id.textSkip).setOnClickListener{
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}