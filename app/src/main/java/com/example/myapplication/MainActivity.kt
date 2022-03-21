package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import com.example.myapplication.databinding.ActivityMainBinding
import android.widget.ImageView
import android.widget.LinearLayout



class MainActivity : AppCompatActivity() {

//    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))

//        binding = ActivityMainBinding.inflate(layoutInflater)

//        val view = binding.root
//        setContentView(view)


//        binding.homeFrame1.setOnClickListener(this)
//        binding.homeFrame2.setOnClickListener(this)
//        binding.homeFrame3.setOnClickListener(this)

//        val button1 = findViewById<Button>(R.id.adopt_button)
//        button1.setOnClickListener{
//            val intent = Intent(this, InventoryActivity::class.java)
//            startActivity(intent)
//        }
//        val button2 = findViewById<Button>(R.id.learn_pet_button)
//        button2.setOnClickListener{
//            val intent = Intent(this, PrepActivity::class.java)
//            startActivity(intent)
//        }
//        val button3 = findViewById<Button>(R.id.find_shelter_button)
//        button3.setOnClickListener{
//            val intent = Intent(this, MapActivity::class.java)
//            startActivity(intent)
//        }

    }
//
//    override fun onClick(view: View?) {
//        val intent = Intent(this, InventoryActivity::class.java)
//
//        startActivity(intent)
//
//
//    }








}