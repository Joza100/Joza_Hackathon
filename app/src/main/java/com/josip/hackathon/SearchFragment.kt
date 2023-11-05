package com.josip.hackathon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class SearchFragment : Fragment() {
    private val cocktailList : MutableList<Cocktail?> = arrayListOf(null, null, null, null, null)
    private val retrofit : CocktailApi = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com")
        .addConverterFactory(CocktailConverterFactory.create())
        .build().create(CocktailApi::class.java)
    private lateinit var adapter : SearchRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(i in 0..4){
            putRandomCocktail(i)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.search_cocktail_recycler)

        adapter = SearchRecyclerViewAdapter(cocktailList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
    private fun putRandomCocktail(position: Int) {
        retrofit.getCocktail().enqueue(object : Callback<Cocktail> {
            override fun onResponse(call: Call<Cocktail>, response: Response<Cocktail>) {
                cocktailList[position] = response.body()
                adapter.notifyItemChanged(position)
            }

            override fun onFailure(call: Call<Cocktail>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}