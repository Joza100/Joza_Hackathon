package com.josip.hackathon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoritesFragment : Fragment() {
    private var dataSet: MutableList<Cocktail?> = ArrayList()
    private var adapter: SearchRecyclerViewAdapter = SearchRecyclerViewAdapter(dataSet)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.favorites_recycler).also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
        MainScope().launch {
            withContext(Dispatchers.Default) {
                dataSet.addAll(CocktailDatabase.cocktailDatabase.cocktailDao().getAll())
            }
            view.run { adapter.notifyDataSetChanged() }
        }
        view.findViewById<ImageButton>(R.id.create_button).setOnClickListener{
            val popup = CreatePopupFragment(true)
            activity?.let { it1 -> popup.show(it1.supportFragmentManager, "Popup") }
        }
    }
}