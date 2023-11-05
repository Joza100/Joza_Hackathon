package com.josip.hackathon

import retrofit2.Call
import retrofit2.http.GET

interface CocktailApi {
    @GET("api/json/v1/1/random.php")
    fun getCocktail(): Call<Cocktail>
}