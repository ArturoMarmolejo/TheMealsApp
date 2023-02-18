package com.example.themealsapp.data.remote.model.meal


import com.google.gson.annotations.SerializedName

data class MealDtoResponse(
    @SerializedName("meals")
    val meals: List<MealDto>? = null
)