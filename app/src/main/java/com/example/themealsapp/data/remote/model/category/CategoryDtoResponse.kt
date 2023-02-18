package com.example.themealsapp.data.remote.model.category


import com.google.gson.annotations.SerializedName

data class CategoryDtoResponse(
    @SerializedName("categories")
    val categories: List<CategoryDto>? = listOf()
)