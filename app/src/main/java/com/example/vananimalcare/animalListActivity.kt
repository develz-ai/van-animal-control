package com.example.vananimalcare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.vananimalcare.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.jar.Attributes
import kotlin.collections.ArrayList

class animalListActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var animalRecyclerView: RecyclerView
    private lateinit var animalArrayList: ArrayList<Animal>
    private lateinit var tempArrayList: ArrayList<Animal>
    private lateinit var nameArrayList: ArrayList<Animal>
    var animalBreed = ArrayList<String>()
    var animalType = ArrayList<String>()
    var animalColor = ArrayList<String>()
    var animalName = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        animalRecyclerView = findViewById(R.id.animalList)
        animalRecyclerView.layoutManager = LinearLayoutManager(this)
        animalRecyclerView.setHasFixedSize(true)

        animalArrayList = arrayListOf<Animal>()
        tempArrayList = arrayListOf<Animal>()
        nameArrayList = arrayListOf()


        dbref = FirebaseDatabase.getInstance().getReference("Animals")




//        loadArrayListItems()

        getAnimalData()


    }

    private fun typeFilter() {
        dbref.orderByChild("Type").equalTo("Dog").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    animalArrayList.clear()
                    for (i in snapshot.children){
                        val dog = i.getValue(Animal::class.java)
                        animalArrayList.add(dog!!)
                    }
//                    var adapter = MyAdapter(animalArrayList)
//                    animalRecyclerView.adapter = adapter
                } else {
                    Toast.makeText(applicationContext, "Data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


//    private fun loadArrayListItems() {
//        dbref.orderByChild("Name").addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                animalArrayList.clear()
//                for (snap in snapshot.children) {
////                    animalName.add(snap.child("Name").getValue(String::class.java)!!)
//////                    Toast.makeText(this@animalListActivity, "${animalName.toString()}", Toast.LENGTH_SHORT).show()
////                    animalBreed.add(snap.child("Breed").getValue(String::class.java)!!)
////                    animalType.add(snap.child("Type").getValue(String::class.java)!!)
////                    animalColor.add(snap.child("Color").getValue(String::class.java)!!)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//
//    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item, menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView



        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    animalArrayList.forEach{
                        if (it.Name.toString().lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        } else if (it.Breed.toString().lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        } else if (it.Color.toString().lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }

                    }

                    animalRecyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    tempArrayList.clear()
                    tempArrayList.addAll(animalArrayList)
                    animalRecyclerView.adapter!!.notifyDataSetChanged()
                }

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getAnimalData() {

//        dbref = FirebaseDatabase.getInstance().getReference("Animals")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (animalSnapshot in snapshot.children){
                        val animal = animalSnapshot.getValue(Animal::class.java)
//                        val animalName = animalSnapshot.child("Name").getValue(Animal::class.java)
                        animalArrayList.add(animal!!)
//                        nameArrayList.add(animalName!!)
                    }

                    val dogFilter = findViewById<ImageButton>(R.id.imageDogFilter)
                    val catFilter = findViewById<ImageButton>(R.id.imageCatFilter)
                    val birdFilter = findViewById<ImageButton>(R.id.imageBirdFilter)
                    val rabbitFilter = findViewById<ImageButton>(R.id.imageRabbitFilter)


                    dogFilter.setOnClickListener{
                        tempArrayList.clear()
                        Toast.makeText(this@animalListActivity,"Only Dogs showing", Toast.LENGTH_SHORT).show()
                        animalArrayList.forEach {
                            if (it.Type.toString() == "Dog") {
                                tempArrayList.add(it)
                            }
                        }
                        animalRecyclerView.adapter!!.notifyDataSetChanged()
                    }

                    catFilter.setOnClickListener{
                        tempArrayList.clear()
                        Toast.makeText(this@animalListActivity,"Only Cats showing", Toast.LENGTH_SHORT).show()
                        animalArrayList.forEach {
                            if (it.Type.toString() == "Cat") {
                                tempArrayList.add(it)
                            }
                        }
                        animalRecyclerView.adapter!!.notifyDataSetChanged()
                    }

                    birdFilter.setOnClickListener{
                        tempArrayList.clear()
                        Toast.makeText(this@animalListActivity,"Only Birds showing", Toast.LENGTH_SHORT).show()
                        animalArrayList.forEach {
                            if (it.Type.toString() == "Bird") {
                                tempArrayList.add(it)
                            }
                        }
                        animalRecyclerView.adapter!!.notifyDataSetChanged()
                    }

                    rabbitFilter.setOnClickListener{
                        tempArrayList.clear()
                        Toast.makeText(this@animalListActivity,"Only Rabbits showing", Toast.LENGTH_SHORT).show()
                        animalArrayList.forEach {
                            if (it.Type.toString() == "Rabbit") {
                                tempArrayList.add(it)
                            }
                        }
                        animalRecyclerView.adapter!!.notifyDataSetChanged()
                    }

                    tempArrayList.addAll(animalArrayList)

                    var adapter = MyAdapter(tempArrayList)
                    animalRecyclerView.adapter = adapter
                    adapter.setOnItemClickListener(object : MyAdapter.onItemClickListerner{
                        override fun onItemclick(position: Int) {
                            //Toast.makeText(this@animalListActivity, "you clicked on item no. $position", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@animalListActivity, AnimalDetailsActivity::class.java)
                            intent.putExtra("intVariableName", position.toString())
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}