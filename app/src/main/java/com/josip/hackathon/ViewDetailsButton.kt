package com.josip.hackathon

import android.content.Context
import android.util.AttributeSet

class ViewDetailsButton(context: Context, attrs: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    var cocktail: Cocktail? = null

    init {
        setOnClickListener {
            var popup = CreatePopupFragment(false)
            if (cocktail != null){
                popup.setData(cocktail)
            }
            (context as MainActivity).let { it1 -> popup.show(it1.supportFragmentManager, "Popup") }
        }
    }
}