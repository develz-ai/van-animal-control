package com.example.vananimalcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class animalListActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var animalRecyclerView: RecyclerView
    private lateinit var animalArrayList: ArrayList<Animal>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        animalRecyclerView = findViewById(R.id.animalList)
        animalRecyclerView.layoutManager = LinearLayoutManager(this)
        animalRecyclerView.setHasFixedSize(true)

        animalArrayList = arrayListOf<Animal>()
        getAnimalData()
    }

    private fun getAnimalData() {

        dbref = FirebaseDatabase.getInstance().getReference("Animals")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (animalSnapshot in snapshot.children){
                        val animal = animalSnapshot.getValue(Animal::class.java)
                        animalArrayList.add(animal!!)
                    }

                    animalRecyclerView.adapter = MyAdapter(animalArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}