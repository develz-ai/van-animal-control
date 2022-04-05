package com.example.vananimalcare

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vananimalcare.databinding.ActivityAnimalDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AnimalDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalDetailsBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid : String
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        getAnimalDetailData()

    }

    private fun getAnimalDetailData() {
        val position = intent.getStringExtra("intVariableName")

        databaseReference = FirebaseDatabase.getInstance().reference.child("Animals")
        databaseReference.child(position.toString()).get().addOnSuccessListener {
            if (it.exists()){
                val breed = it.child("Breed").value
                val color = it.child("Color").value
                val birthDate = it.child("Date").value
                val name = it.child("Name").value
                val gender = it.child("Sex").value
                val type = it.child("Type").value
                Toast.makeText(this, "Successfully linked", Toast.LENGTH_SHORT).show()
                binding.detailBreed.text = breed.toString()
                binding.animalDetailName.text = name.toString()
                binding.detailSex.text = gender.toString()
                binding.AnimalDetail.text = "This animal is a marvelous ${type.toString()}. \n This animal is born in ${birthDate.toString()}."
                binding.detailColor.text = color.toString()


            } else {
                Toast.makeText(this, "Failed to get data", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }





    }

}