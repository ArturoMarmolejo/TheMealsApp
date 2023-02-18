package com.example.themealsapp.data.remote.model.ingredient


import com.google.gson.annotations.SerializedName

data class IngredientDtoResponse(
    @SerializedName("meals")
    val meals: List<IngredientDto>? = null
)