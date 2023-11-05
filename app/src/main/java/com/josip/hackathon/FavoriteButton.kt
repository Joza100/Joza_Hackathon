package com.josip.hackathon

import android.content.Context
import android.util.AttributeSet


class FavoriteButton(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    var cocktail : Cocktail? = null
        set(cocktail){
            favorite = cocktail?.favorite
            field = cocktail
        }
    private var favorite : Boolean? = false
        set(favorite){
            if (favorite == true){
                setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                setImageResource(android.R.drawable.btn_star_big_off)
            }
            Thread{
                if (cocktail != null && favorite == true) {
                    CocktailDatabase.setFavorite(cocktail, favorite)
                }
            }.start()
            field = favorite
        }
    init {
        setImageResource(android.R.drawable.btn_star)
        setOnClickListener{
            favorite = favorite != true
        }
    }

}