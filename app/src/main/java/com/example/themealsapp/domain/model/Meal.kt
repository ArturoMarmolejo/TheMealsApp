package com.example.themealsapp.domain.model

import com.example.themealsapp.data.local.entity.MealEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strArea: String,
    val strCategory: String,
    val instructions: List<String>,
    val strMealThumb: String,
    val strYoutube: String,
    val ingredients: List<String>,
    val measurements: List<String>,
    var isFavorite: Int
)

fun List<MealEntity>.mapFromEntityToMeal(): List<Meal> =
    this.map {
        val gson = Gson()
        val stringType = object : TypeToken<List<String>>() {}.type
        Meal(
            idMeal = it.idMeal,
            strMeal = it.strMeal,
            strArea = it.strArea,
            strCategory = it.strCategory,
            instructions = gson.fromJson(it.strInstructions, stringType) ?: emptyList(),
            strMealThumb = it.strMealThumb,
            strYoutube = it.strYoutube,
            ingredients = gson.fromJson(it.ingredients, stringType) ?: emptyList(),
            measurements = gson.fromJson(it.measurements, stringType) ?: emptyList(),
            isFavorite = it.isFavorite
        )
    }

