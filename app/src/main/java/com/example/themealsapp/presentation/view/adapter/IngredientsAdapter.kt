package com.example.themealsapp.presentation.view.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealsapp.databinding.RvIngredientItemBinding

private const val TAG = "IngredientsAdapter"

class IngredientsAdapter(
    private val ingredientsItemSet: MutableList<String> = mutableListOf(),
    private val measurementsItemSet: MutableList<String> = mutableListOf()
): RecyclerView.Adapter<IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder =
        IngredientsViewHolder(
            RvIngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    

    override fun getItemCount(): Int = ingredientsItemSet.size
    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: Ingredients Adapter: ${ingredientsItemSet}, Position: $position")
        while (ingredientsItemSet.size > measurementsItemSet.size) {
            measurementsItemSet.add("At taste")
        }
        holder.bind(ingredientsItemSet[position], measurementsItemSet[position])

    }

}


class IngredientsViewHolder(
    private val binding: RvIngredientItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(ingredient: String, measurement: String?) {
        Log.d(TAG, "ingredients list: $ingredient, Measurement: $measurement ")
        //position bigger than measurements.size => return another value.

        binding.tvIngredientItem.text = "${measurement} ${ingredient }"
    }
}