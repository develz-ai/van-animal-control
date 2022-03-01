package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ToolBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView) {
            R.id.app_bar_search -> Toast.makeText(applicationContext,"Search clicked", Toast.LENGTH_SHORT).show()
            R.id.app_return -> Toast.makeText(applicationContext,"Return clicked", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}