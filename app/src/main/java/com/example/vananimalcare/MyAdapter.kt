package com.example.vananimalcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val animalList : ArrayList<Animal>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = animalList[position]
//        val nextitem = animalList[position+1]

        if (position%2 == 0) {
            holder.name1.text = currentitem.Name
            holder.breed1.text = currentitem.Breed
            holder.sex1.text = currentitem.Sex
//            holder.name2.text = nextitem.Name
//            holder.breed2.text = nextitem.Breed
//            holder.sex2.text = nextitem.Sex
        }


    }

    override fun getItemCount(): Int {


        return animalList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name1 : TextView = itemView.findViewById(R.id.animalName1)
        val breed1 : TextView = itemView.findViewById(R.id.animalBreed1)
        val sex1 : TextView = itemView.findViewById(R.id.animalType1)
//        val name2 : TextView = itemView.findViewById(R.id.animalName2)
//        val breed2 : TextView = itemView.findViewById(R.id.animalBreed2)
//        val sex2 : TextView = itemView.findViewById(R.id.animalType2)


    }
}