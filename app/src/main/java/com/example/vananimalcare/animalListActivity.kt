package com.example.vananimalcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vananimalcare.databinding.ActivityMainBinding
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

                    var adapter = MyAdapter(animalArrayList)
                    animalRecyclerView.adapter = adapter
                    adapter.setOnItemClickListener(object : MyAdapter.onItemClickListerner{
                        override fun onItemclick(position: Int) {
                            Toast.makeText(this@animalListActivity, "you clicked on item no. $position", Toast.LENGTH_SHORT).show()
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