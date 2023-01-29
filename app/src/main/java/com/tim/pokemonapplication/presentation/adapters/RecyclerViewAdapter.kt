package com.tim.pokemonapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.Fragments.PokemonListFragment

class RecyclerViewAdapter(private var names: List<String>, private var images: List<Int>)
    :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemImage: ImageView = itemView.findViewById(R.id.PList_item_image)
        var itemName: TextView = itemView.findViewById(R.id.PList_item_name)

        init {

            itemView.setOnClickListener {

                PokemonListFragment().switchToDetailFragment(itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemImage.setImageResource(images[position])
        holder.itemName.text = names[position]
    }
}