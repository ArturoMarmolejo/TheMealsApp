package com.example.themealsapp.domain.use_case

import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsByFavorite @Inject constructor(
    private val repository: MealRepository
) {
    operator fun invoke(): Flow<UIState<List<Meal>>> {
        return repository.getFavoriteMeals()
    }
}