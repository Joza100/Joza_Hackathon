package com.josip.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CocktailDatabase.initDatabase(applicationContext)
        /*Thread{
            CocktailDatabase.cocktailDatabase.cocktailDao().clear()
        }.start()*/
    }
}