package com.josip.hackathon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SearchRecyclerViewAdapter(private val dataSet: MutableList<Cocktail?>) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewHolder>() {
    class SearchRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image : ViewDetailsButton
        val nameText : TextView
        val instructionsText : TextView
        val favoriteButton : FavoriteButton
        init{
            image = view.findViewById(R.id.cocktail_image)
            nameText = view.findViewById(R.id.cocktail_name_text)
            instructionsText = view.findViewById(R.id.cocktail_instructions_text)
            favoriteButton = view.findViewById(R.id.favorite_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_item, parent, false)
        return SearchRecyclerViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        if (dataSet[position]?.strDrinkThumb != ""){
            Picasso.get().load(dataSet[position]?.strDrinkThumb + "/preview").into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.drink_icon)
        }
        holder.nameText.text = dataSet[position]?.strDrink
        holder.instructionsText.text = dataSet[position]?.strInstructions
        holder.favoriteButton.cocktail = dataSet[position]
        holder.image.cocktail = dataSet[position]
    }
}