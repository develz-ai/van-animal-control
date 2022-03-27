package com.example.vananimalcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.vananimalcare.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

//    companion object {
//        lateinit var auth: FirebaseAuth
//
//        const val EMAIL = "test@oddinstitute.com"
//        const val NAME = "Derek L"
//        const val PASS = "StrongPass1234@"
//    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)




        val view = binding.root
        setContentView(view)
        println("start")



//        binding.homeFrame1.setOnClickListener(this)
//        binding.homeFrame2.setOnClickListener(this)
//        binding.homeFrame3.setOnClickListener(this)

        val button1 = binding.homeFrame1
        button1.setOnClickListener{
            val intent = Intent(this, animalListActivity::class.java)
            startActivity(intent)
        }
        val button2 = binding.homeFrame2
        button2.setOnClickListener{
            val intent = Intent(this, PrepActivity::class.java)
            startActivity(intent)
        }
        val button3 = binding.homeFrame3
        button3.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

//        setContentView(R.layout.activity_toolbar)
//        setSupportActionBar(findViewById(R.id.toolbar))

//        var toolbar = ToolBarActivity()
//        toolbar.show(supportFragmentManager, "CustomDialog")

    }

    override fun onClick(view: View?) {
        //val intent = Intent(this, InventoryActivity::class.java)

        //startActivity(intent)

//        val button1 = binding.homeFrame1
//        button1.setOnClickListener{
//            val intent = Intent(this, InventoryActivity::class.java)
//            startActivity(intent)
//        }
//        val button2 = binding.homeFrame2
//        button2.setOnClickListener{
//            val intent = Intent(this, PrepActivity::class.java)
//            startActivity(intent)
//        }
//        val button3 = binding.homeFrame3
//        button3.setOnClickListener{
//            val intent = Intent(this, MapActivity::class.java)
//            startActivity(intent)
//        }


    }

}