package com.example.vananimalcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.vananimalcare.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        println("start")

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(
                    applicationContext,
                    "Clicked Home",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_adopt -> {
                    val intent = Intent(this, animalListActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_prep -> {
                    val intent = Intent(this, PrepActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_map -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        val button1 = binding.homeFrame1
        button1.setOnClickListener {
            val intent = Intent(this, animalListActivity::class.java)
            startActivity(intent)
        }
        val button2 = binding.homeFrame2
        button2.setOnClickListener {
            val intent = Intent(this, PrepActivity::class.java)
            startActivity(intent)
        }
        val button3 = binding.homeFrame3
        button3.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }

//        setContentView(R.layout.activity_toolbar)
//        setSupportActionBar(findViewById(R.id.toolbar))

//        var toolbar = ToolBarActivity()
//        toolbar.show(supportFragmentManager, "CustomDialog")



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
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

        //    companion object {
//        lateinit var auth: FirebaseAuth
//
//        const val EMAIL = "test@oddinstitute.com"
//        const val NAME = "Derek L"
//        const val PASS = "StrongPass1234@"
//    }


    }

}