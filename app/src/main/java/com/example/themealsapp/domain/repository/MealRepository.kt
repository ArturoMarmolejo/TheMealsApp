package com.example.themealsapp.domain.repository

import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getMealInfos(strMeal: String): Flow<UIState<List<Meal>>>
    fun getMealInfosLocally(strMeal: String): Flow<UIState<List<Meal>>>
    fun getFilteredMealByArea(strArea: String): Flow<UIState<List<MealFiltered>>>
    fun getFavoriteMeals(): Flow<UIState<List<Meal>>>
    fun toggleFavoriteMealFlag(mealToggled: Meal): Flow<UIState<List<Meal>>>
}