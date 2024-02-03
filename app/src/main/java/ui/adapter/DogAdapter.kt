package com.example.filtrador_buscador_kotlin

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapicleanarchitecture.R
import data.models.Dog

class DogAdapter(
    var dogList: ArrayList<Dog> = arrayListOf()
): RecyclerView.Adapter<DogAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombreDog = itemView.findViewById(R.id.textViewName) as TextView
        val imagenDog = itemView.findViewById(R.id.imageViewDog) as ImageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogList[position]

        holder.nombreDog.text = dog.name
        Glide.with(holder.itemView.context)
            .load(dog.image)
            .into(holder.imagenDog)
    }


    override fun getItemCount(): Int {
        return dogList.size
    }

    fun filtrar(listaFiltrada: ArrayList<Dog>) {
        this.dogList = listaFiltrada
        notifyDataSetChanged()
    }
}