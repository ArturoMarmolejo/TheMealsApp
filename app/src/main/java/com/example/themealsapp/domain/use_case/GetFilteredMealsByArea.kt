package com.example.themealsapp.domain.use_case

import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.domain.repository.MealRepository
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.NullResponse
import com.example.themealsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilteredMealsByArea @Inject constructor(
    private val repository: MealRepository,
    private val networkState: NetworkState
) {
    operator fun invoke(strMeal: String): Flow<UIState<List<MealFiltered>>> {
        if (!networkState.isInternetOn())
            return flow { emit(UIState.ERROR(NullResponse("You need network connection available.")))}
        return repository.getFilteredMealByArea(strMeal)
    }
}