package com.example.themealsapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themealsapp.R
import com.example.themealsapp.databinding.RvMealItemBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.presentation.viewmodel.MealsViewModel
import javax.inject.Inject

class MealsListAdapter(
    private val itemSet: MutableList<Meal> = mutableListOf(),
    private val onItemClick: (item: Meal) -> Unit,
): RecyclerView.Adapter<MealSearchViewHolder>() {

    fun updateMeals(newItems: List<Meal>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealSearchViewHolder =
        MealSearchViewHolder(
            RvMealItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: MealSearchViewHolder, position: Int) {
        holder.bind(itemSet[position], onItemClick)
    }

}


class MealSearchViewHolder(
    private val binding: RvMealItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Meal, onItemClick: (Meal) -> Unit) {
        binding.mealImage.setImageResource(R.drawable.baseline_fastfood_24)
        binding.mealTitle.text = item.strMeal
        binding.mealArea.text = "Area: ${item.strArea}"
        binding.mealCategory.text = "Category: ${item.strCategory}"

        itemView.setOnClickListener {
            item.let { onItemClick(it) }
        }


        Glide
            .with(binding.root)
            .load(item.strMealThumb)
            .centerCrop()
            .placeholder(R.drawable.baseline_fastfood_24)
            .error(R.drawable.baseline_no_food_24)
            .into(binding.mealImage)
    }
}