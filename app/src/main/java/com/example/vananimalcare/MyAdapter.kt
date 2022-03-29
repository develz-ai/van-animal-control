package com.example.vananimalcare

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class MyAdapter(private val context: Activity, private val arrayList: ArrayList<Animal>) : ArrayAdapter<Animal>(context, R.layout.list_item,arrayList) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        val inflater : LayoutInflater = LayoutInflater.from(context)
//        val view : View = inflater.inflate(R.layout.list_item, null)
//
//        val imageView : ImageView = view.findViewById(R.id.animal_pic)
//        val name : TextView = view.findViewById(R.id.animalName)
//        val breed : TextView = view.findViewById(R.id.breed)
//        val gender : TextView = view.findViewById(R.id.gender)
//
//        imageView.setImageResource(arrayList[position].Id)
//        name.text = arrayList[position].Name
//        breed.text = arrayList[position].Breed
//        gender.text = arrayList[position].Sex
//
//
//        return view
//    }
//}


class MyAdapter(private val animalList : ArrayList<Animal>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListerner

    interface onItemClickListerner{
        fun onItemclick(position: Int)
    }

    fun setOnItemClickListener(listerner: onItemClickListerner){
        mListener = listerner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = animalList[position]
//        val nextitem = animalList[position+1]

        //if (position%2 == 0) {
            holder.name1.text = currentitem.Name
            holder.breed1.text = currentitem.Breed
            holder.sex1.text = currentitem.Sex
//            holder.color1.text = currentitem.Color
//            holder.name2.text = nextitem.Name
//            holder.breed2.text = nextitem.Breed
//            holder.sex2.text = nextitem.Sex
        //}


    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    class MyViewHolder(itemView: View, listerner: onItemClickListerner) : RecyclerView.ViewHolder(itemView){

        val name1 : TextView = itemView.findViewById(R.id.animalName1)
        val breed1 : TextView = itemView.findViewById(R.id.animalBreed1)
        val sex1 : TextView = itemView.findViewById(R.id.animalType1)
//        val color1 : TextView = itemView.findViewById(R.id.detailColor)
//        val name2 : TextView = itemView.findViewById(R.id.animalName2)
//        val breed2 : TextView = itemView.findViewById(R.id.animalBreed2)
//        val sex2 : TextView = itemView.findViewById(R.id.animalType2)

        init {
            itemView.setOnClickListener {
                listerner.onItemclick(adapterPosition)
            }
        }
    }
}