package com.example.vananimalcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import java.util.*
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
    lateinit var breedFilter : Spinner
    lateinit var colorFilter : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        animalRecyclerView = findViewById(R.id.animalList)
        animalRecyclerView.layoutManager = GridLayoutManager(this, 2)
        animalRecyclerView.setHasFixedSize(true)

        animalArrayList = arrayListOf<Animal>()
        tempArrayList = arrayListOf<Animal>()
        nameArrayList = arrayListOf()


        dbref = FirebaseDatabase.getInstance().getReference("Animals")

        breedFilter = findViewById(R.id.spinner1) as Spinner
        colorFilter = findViewById(R.id.spinner2) as Spinner

        val breedOptions = arrayOf("Select Breed", "Labrador", "Bulldog", "Poodle")
        var colorOptions = arrayOf("Select Color", "Black", "Golden", "White")

        breedFilter.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,breedOptions)
        colorFilter.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,colorOptions)

//        breedFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if (p2 == 0) {
//                    Toast.makeText(this@animalListActivity,"you selected option 0", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }


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

    private fun clearFilter() {
        tempArrayList.clear()
        tempArrayList.addAll(animalArrayList)
        animalRecyclerView.adapter!!.notifyDataSetChanged()
    }


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
                    clearFilter()
                }

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun imageFilter(Str: String) {
        tempArrayList.clear()
        val type = Str + "s"
        Toast.makeText(this@animalListActivity,"Only $type showing", Toast.LENGTH_SHORT).show()
        animalArrayList.forEach {
            if (it.Type.toString() == Str) {
                tempArrayList.add(it)
            }
        }
        animalRecyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun spinnerFilter(Str: String) {
        tempArrayList.clear()
        animalArrayList.forEach {
            if (it.Breed.toString().lowercase(Locale.getDefault()).contains(Str)) {
                tempArrayList.add(it)
            }
        }
        animalRecyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun spinnerFilter2(Str: String) {
        tempArrayList.clear()
        animalArrayList.forEach {
            if (it.Color.toString().lowercase(Locale.getDefault()).contains(Str)) {
                tempArrayList.add(it)
            }
        }
        animalRecyclerView.adapter!!.notifyDataSetChanged()
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
                    val clearButton = findViewById<Button>(R.id.clearButton)

                    dogFilter.setOnClickListener{
                        imageFilter("Dog")
                    }

                    catFilter.setOnClickListener{
                        imageFilter("Cat")
                    }

                    birdFilter.setOnClickListener{
                        imageFilter("Bird")
                    }

                    rabbitFilter.setOnClickListener{
                        imageFilter("Rabbit")
                    }

                    clearButton.setOnClickListener{
                        clearFilter()
                    }

                    breedFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            if (p2 == 1) {
                                spinnerFilter("labrador")
                            } else if (p2 == 2) {
                                spinnerFilter("bulldog")
                            } else if (p2 == 3) {
                                spinnerFilter("poodle")
                            } else {
                                clearFilter()
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }

                    colorFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            if (p2 == 1) {
                                spinnerFilter2("black")
                            } else if (p2 == 2) {
                                spinnerFilter2("golden")
                            } else if (p2 == 3) {
                                spinnerFilter2("white")
                            } else {
                                clearFilter()
                            }
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("Not yet implemented")
                        }
                    }

                    tempArrayList.addAll(animalArrayList)

                    var adapter = MyAdapter(this@animalListActivity, tempArrayList)
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