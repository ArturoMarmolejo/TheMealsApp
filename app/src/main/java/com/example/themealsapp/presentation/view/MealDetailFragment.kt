package com.example.themealsapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentMealDetailBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.presentation.view.adapter.IngredientsAdapter
import com.example.themealsapp.presentation.view.adapter.InstructionsAdapter
import com.example.themealsapp.utils.BaseFragment
import com.example.themealsapp.utils.UIState

private const val TAG = "MealDetailFragment"

class MealDetailFragment() : BaseFragment() {

    private val binding by lazy {
        FragmentMealDetailBinding.inflate(layoutInflater)
    }

    lateinit var ingredientsAdapter: IngredientsAdapter
    lateinit var instructionsAdapter: InstructionsAdapter

    private lateinit var selectedItem: Meal


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        selectedItem = mealsViewModel.selectedMealItem
        ingredientsAdapter = IngredientsAdapter( selectedItem.ingredients.toMutableList(), selectedItem.measurements.toMutableList() )
        Log.d(TAG, "onCreateView Selected Item testing: ${selectedItem}")
        binding.rvIngredientList.apply {
            layoutManager = LinearLayoutManager (
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = ingredientsAdapter
        }
        instructionsAdapter = InstructionsAdapter( selectedItem.instructions.toMutableList())

        binding.rvInstructionList.apply {
            layoutManager = LinearLayoutManager (
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = instructionsAdapter
        }



        binding.mealImage.setImageResource(R.drawable.baseline_fastfood_24)
        binding.mealTitle.text = selectedItem.strMeal
        binding.tvMealInfo.text = "Area: ${selectedItem.strArea}, Category: ${selectedItem.strCategory}"
        checkBookmarkImage()
        binding.btnBookmarkToggle.setOnClickListener {
            selectedItem.isFavorite = if (selectedItem.isFavorite == 1) 0 else 1
            mealsViewModel.onFilterFavoriteMeals(selectedItem)
        }

        Glide
            .with(binding.root)
            .load(selectedItem.strMealThumb)
            .centerCrop()
            .placeholder(R.drawable.baseline_fastfood_24)
            .error(R.drawable.baseline_no_food_24)
            .into(binding.mealImage)

        toggleFavoriteMeal()

        return binding.root

    }

    private fun checkBookmarkImage(){
        if (selectedItem.isFavorite == 1)
            binding.btnBookmarkToggle.setImageResource(R.drawable.baseline_bookmark_24)
        else
            binding.btnBookmarkToggle.setImageResource(R.drawable.baseline_bookmark_border_24)
    }

    private fun toggleFavoriteMeal() {
        mealsViewModel.meals.observe(viewLifecycleOwner) { state ->
            when (state){
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS<List<Meal>> -> {
                    Log.d(TAG, "toggle meal favorites: Bookmark = ${selectedItem.isFavorite}")
                    mealsViewModel.selectedMealItem = selectedItem
                    checkBookmarkImage()
//
                }
                is UIState.ERROR -> {
                    Log.e(TAG, "searchMealsByName: UIState error: ", )
                    showError(state.error.localizedMessage, ) {}
                }
            }
        }
    }


}