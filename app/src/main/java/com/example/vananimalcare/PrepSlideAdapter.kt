package com.example.vananimalcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PrepSlideAdapter (private val prepSlides: List<PrepSlide>) :
    RecyclerView.Adapter<PrepSlideAdapter.PrepSlideViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrepSlideViewHolder {
        return PrepSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return prepSlides.size
    }

    override fun onBindViewHolder(holder: PrepSlideViewHolder, position: Int) {
        holder.bind(prepSlides[position])
    }

    inner class PrepSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        //private val imageIcon = view.findViewById<TextView>(R.id.imageSlideIcon)

        fun bind(prepSlide: PrepSlide){
            textTitle.text = prepSlide.title
            textDescription.text = prepSlide.description
            //imageIcon.setBackgroundResource(prepSlide.icon)
        }
    }
}