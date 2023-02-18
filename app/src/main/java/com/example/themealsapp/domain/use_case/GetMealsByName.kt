package com.example.themealsapp.domain.use_case

import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsByName @Inject constructor(
    private val repository: MealRepository,
    private val networkState: NetworkState
) {
    operator fun invoke(strMeal: String): Flow<UIState<List<Meal>>> {
        if (strMeal.isBlank() || !networkState.isInternetOn())
            return repository.getMealInfosLocally(strMeal)
        return repository.getMealInfos(strMeal)
    }
}