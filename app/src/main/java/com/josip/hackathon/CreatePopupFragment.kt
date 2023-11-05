package com.josip.hackathon

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.streams.toList

class CreatePopupFragment : DialogFragment {

    private var editable: Boolean
    private lateinit var nameEdit: EditText
    private lateinit var instructionsEdit: EditText
    private lateinit var categoryEdit: EditText
    private lateinit var alcoholicEdit: EditText
    private lateinit var glassEdit: EditText
    private lateinit var ingredientEdit: IngredientInputView
    private var oldCocktail: Cocktail? = null

    constructor(editable: Boolean){
        this.editable = editable
    }

    fun setData(cocktail: Cocktail?){
        this.oldCocktail = cocktail
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_popup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameEdit = view.findViewById(R.id.cocktail_name_edit)
        instructionsEdit = view.findViewById(R.id.cocktail_instructions_edit)
        categoryEdit = view.findViewById(R.id.cocktail_category_edit)
        alcoholicEdit = view.findViewById(R.id.cocktail_alcoholic_edit)
        glassEdit = view.findViewById(R.id.cocktail_glass_edit)
        ingredientEdit = view.findViewById(R.id.ingredient_input_view)
        if (!editable){
            view.findViewById<TextView>(R.id.create_text).text = "Cocktail details"
            nameEdit.isEnabled = false
            instructionsEdit.isEnabled = false
            categoryEdit.isEnabled = false
            alcoholicEdit.isEnabled = false
            glassEdit.isEnabled = false
            ingredientEdit.setNotEditable()
            view.findViewById<Button>(R.id.create_button).isEnabled = false
            nameEdit.setText(oldCocktail?.strDrink)
            instructionsEdit.setText(oldCocktail?.strInstructions)
            categoryEdit.setText(oldCocktail?.strCategory)
            alcoholicEdit.setText(oldCocktail?.strAlcoholic)
            glassEdit.setText(oldCocktail?.strGlass)
            var ingredients: MutableList<Ingredient> = ArrayList()
            for (i in 0..< oldCocktail?.strIngredients!!.size){
                ingredients.add(Ingredient(oldCocktail!!.strIngredients[i], oldCocktail!!.strMeasures[i]))
            }
            ingredientEdit.dataSet = ingredients as ArrayList
        }
        view.findViewById<Button>(R.id.create_button).setOnClickListener{
            MainScope().launch {
                val cocktail = getCocktail()
                withContext(Dispatchers.Default) {
                    CocktailDatabase.cocktailDatabase.cocktailDao().insertAll(cocktail)
                }
            }

            dismiss()
        }
        view.findViewById<Button>(R.id.cancel_button).setOnClickListener{
            dismiss()
        }
    }

    private fun getCocktail(): Cocktail{
        var cocktail = Cocktail()
        cocktail.idDrink = Random.nextInt(100000, 1000000)
        cocktail.strDrink = nameEdit.text.toString()
        cocktail.strInstructions = instructionsEdit.text.toString()
        cocktail.strCategory = categoryEdit.text.toString()
        cocktail.strAlcoholic = alcoholicEdit.text.toString()
        cocktail.strGlass = glassEdit.text.toString()
        val ingredients = ingredientEdit.getIngredients()
        cocktail.strIngredients = ArrayList(ingredients.stream().map(Ingredient::ingredient).toList())
        cocktail.strMeasures = ArrayList(ingredients.stream().map(Ingredient::measure).toList())
        return cocktail

    }
}