package com.josip.hackathon

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
class IngredientInputView (context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){
    var dataSet: ArrayList<Ingredient> = ArrayList()
    private var adapter: IngredientRecyclerViewAdapter = IngredientRecyclerViewAdapter(dataSet)
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_ingredient_input, this, true)
        for (i in 1..3){
            dataSet.add(Ingredient("", ""))
        }
        findViewById<RecyclerView>(R.id.ingredient_input_recycler).also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
        }
        findViewById<Button>(R.id.ingredient_add_button).setOnClickListener{
            dataSet.add(Ingredient("", ""))
            adapter.notifyDataSetChanged()
        }
        findViewById<Button>(R.id.ingredient_remove_button).setOnClickListener{
            if(dataSet.isNotEmpty()){
                dataSet.removeLast()
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun setNotEditable(){
        adapter.setNotEditable()
    }
    fun getIngredients(): MutableList<Ingredient>{
        adapter.notifyDataSetChanged()
        return dataSet
    }
}