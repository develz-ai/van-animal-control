package com.example.vananimalcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vananimalcare.databinding.ActivityInventoryBinding

class InventoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityInventoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        binding = ActivityInventoryBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

//        binding.animal1.setOnClickListener(this)

        binding.animal1.setOnClickListener{
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}