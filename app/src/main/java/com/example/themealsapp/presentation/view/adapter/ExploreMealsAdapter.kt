package com.example.themealsapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FilteredMealItemBinding
import com.example.themealsapp.databinding.RvMealItemBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.model.MealFiltered

class ExploreMealsAdapter(
    private var itemSet : MutableList<MealFiltered> = mutableListOf()
): RecyclerView.Adapter<ExploreMealsViewHolder>() {

    fun updateMeals(newItems: List<MealFiltered>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreMealsViewHolder =
        ExploreMealsViewHolder(
            FilteredMealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: ExploreMealsViewHolder, position: Int) {
        holder.bind(itemSet[position])
    }

}

class ExploreMealsViewHolder(
    private val binding: FilteredMealItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MealFiltered){
        binding.mealTitle.text = item.strMeal
        Glide
            .with(binding.root)
            .load(item.strMealThumb)
            .centerCrop()
            .placeholder(R.drawable.baseline_fastfood_24)
            .error(R.drawable.baseline_no_food_24)
            .into(binding.mealImage)
    }
}