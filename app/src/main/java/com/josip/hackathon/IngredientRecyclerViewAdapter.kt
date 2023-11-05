package com.josip.hackathon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class IngredientRecyclerViewAdapter(private val dataSet: MutableList<Ingredient>)
    : RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientRecyclerViewHolder>() {
    private var editable = true
    class IngredientRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ingredientEditText: EditText
        val measureEditText: EditText
        init {
            ingredientEditText = view.findViewById(R.id.ingredient_edit_text)
            measureEditText = view.findViewById(R.id.measure_edit_text)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientRecyclerViewHolder {
        val editText = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_edit_text, parent, false)
        return IngredientRecyclerViewHolder(editText)
    }
    fun setNotEditable(){
        editable = false
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: IngredientRecyclerViewHolder, position: Int) {
        if (!editable){
            holder.ingredientEditText.isEnabled = false
            holder.measureEditText.isEnabled = false
        }
        if (editable){
            dataSet[position].ingredient = holder.ingredientEditText.text.toString()
            dataSet[position].measure = holder.measureEditText.text.toString()
        }
    }
}