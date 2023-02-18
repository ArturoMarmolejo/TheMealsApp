package com.example.themealsapp.data.remote.model.ingredient

import com.google.gson.annotations.SerializedName


data class IngredientDto(
    @SerializedName("idIngredient")
    val idIngredient: String? = null,
    @SerializedName("strDescription")
    val strDescription: String? = null,
    @SerializedName("strIngredient")
    val strIngredient: String? = null,
    @SerializedName("strType")
    val strType: String? = null
) {

}