package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import android.widget.ImageView
import android.widget.LinearLayout


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.homeFrame1.setOnClickListener(this)
        binding.homeFrame2.setOnClickListener(this)
        binding.homeFrame3.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, InventoryActivity::class.java)

        startActivity(intent)
    }






}