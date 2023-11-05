package com.josip.hackathon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
@Entity
data class Cocktail (
    @PrimaryKey var idDrink : Int = 0,
    @ColumnInfo var strDrink: String = "",
    @ColumnInfo var strCategory: String = "",
    @ColumnInfo var strAlcoholic: String = "",
    @ColumnInfo var strGlass: String = "",
    @ColumnInfo var strInstructions: String = "",
    @ColumnInfo var strDrinkThumb: String = "",
    @ColumnInfo var strIngredients : MutableList<String> = ArrayList(),
    @ColumnInfo var strMeasures : MutableList<String> = ArrayList(),
    @ColumnInfo var favorite : Boolean = false
) {
    override fun toString(): String {
        return strDrink + strIngredients[0]
    }
}

class CocktailConverterFactory : JsonDeserializer<Cocktail> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Cocktail {
        var cocktail = Cocktail()
        json?.asJsonObject?.get("drinks")?.asJsonArray?.get(0)?.asJsonObject?.apply {
            cocktail.idDrink = get("idDrink").asString.toInt()
            cocktail.strDrink = get("strDrink").asString
            cocktail.strCategory = get("strCategory").asString
            cocktail.strAlcoholic = get("strAlcoholic").asString
            cocktail.strGlass = get("strGlass").asString
            cocktail.strInstructions = get("strInstructions").asString
            cocktail.strDrinkThumb = get("strDrinkThumb").asString
            for (i in 1..15){
                get("strIngredient$i").let {
                    if (!it.isJsonNull){
                        cocktail.strIngredients.add(it.asString)
                    }
                }
            }
            for (i in 1..15){
                get("strMeasure$i").let {
                    if (!it.isJsonNull){
                        cocktail.strMeasures.add(it.asString)
                    }
                }
            }
        }
        return cocktail
    }
    companion object{
        fun create() : Converter.Factory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(Cocktail::class.java, CocktailConverterFactory())
            return GsonConverterFactory.create(gsonBuilder.create())
        }
    }

}