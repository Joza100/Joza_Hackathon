package com.josip.hackathon

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM Cocktail")
    fun getAll() : List<Cocktail>
    @Query("SELECT * FROM Cocktail WHERE idDrink = :idDrink")
    fun getById(idDrink: Int?) : List<Cocktail>
    @Query("UPDATE Cocktail SET favorite = :favorite WHERE idDrink = :idDrink")
    fun updateFavorite(idDrink: Int?, favorite: Boolean?)
    @Insert
    fun insertAll(vararg cocktails: Cocktail?)
    @Delete
    fun deleteAll(vararg cocktails: Cocktail)
    @Query("DELETE FROM Cocktail")
    fun clear()
}