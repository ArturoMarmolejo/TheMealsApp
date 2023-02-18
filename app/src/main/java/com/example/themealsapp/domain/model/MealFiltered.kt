package com.example.themealsapp.domain.model

import com.example.themealsapp.data.remote.model.meal.MealDto

data class MealFiltered(
    val strMeal: String,
    val strMealThumb: String,
    val idMeal: String
)
fun List<MealDto>?.mapToMealFiltered(): List<MealFiltered>? =
    this?.map {
        MealFiltered(
            strMeal = it.strMeal?: "not available",
            strMealThumb = it.strMealThumb?: "not available",
            idMeal = it.idMeal?: "id not available"
        )
    }