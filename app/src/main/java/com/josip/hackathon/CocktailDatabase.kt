package com.josip.hackathon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Cocktail::class], version = 1)
@TypeConverters(Converters::class)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {
        lateinit var cocktailDatabase : CocktailDatabase

        fun initDatabase(context: Context){
            cocktailDatabase = Room.databaseBuilder(context, CocktailDatabase::class.java, "db").build()
        }
        fun setFavorite(cocktail: Cocktail?, favorite: Boolean?){
            cocktailDatabase.cocktailDao().apply {
                if (getById(cocktail?.idDrink).isEmpty()){
                    cocktailDatabase.cocktailDao().insertAll(cocktail)
                }
                updateFavorite(cocktail?.idDrink, favorite)
            }
        }

    }
}


