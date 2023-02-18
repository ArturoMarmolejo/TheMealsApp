package com.example.themealsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.domain.use_case.GetFilteredMealsByArea
import com.example.themealsapp.domain.use_case.GetMealsByFavorite
import com.example.themealsapp.domain.use_case.GetMealsByName
import com.example.themealsapp.domain.use_case.ToggleMealFavoriteFlag
import com.example.themealsapp.utils.NetworkState
import com.example.themealsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

private const val TAG = "MealsViewModel"
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsByName: GetMealsByName,
    private val getFilteredMealsByArea: GetFilteredMealsByArea,
    private val getMealsByFavorite: GetMealsByFavorite,
    private val setToggleMealFavoriteFlag: ToggleMealFavoriteFlag,
    private val networkState: NetworkState
): ViewModel(){
    fun getNetworkState(): Boolean{
        return networkState.isInternetOn()
    }
    init {
        onSearchMealsByName()
        onFilterFavoriteMeals()
    }

    lateinit var selectedMealItem : Meal
    private var currentSearchQuery = ""

    private val _meals : MutableLiveData<UIState<List<Meal>>> = MutableLiveData(UIState.LOADING)
    val meals : MutableLiveData<UIState<List<Meal>>> get() = _meals

    private val _filteredMeals : MutableLiveData<UIState<List<MealFiltered>>> = MutableLiveData(UIState.LOADING)
    val filteredMeals : MutableLiveData<UIState<List<MealFiltered>>> get() = _filteredMeals

    private val _favoriteMeals : MutableLiveData<UIState<List<Meal>>> = MutableLiveData(UIState.LOADING)
    val favoriteMeals : MutableLiveData<UIState<List<Meal>>> get() = _favoriteMeals


    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    fun onSearchMealsByName(strMeal: String = "") {
        Log.d(TAG, "onSearchMealsByName: Entered in the ViewModel")
        currentSearchQuery = strMeal
        viewModelScope.launch {
            getMealsByName(strMeal).collect { result ->
                _meals.postValue(result)
            }
        }
    }

    fun onFilterMealsByArea(strMeal: String) {
        viewModelScope.launch {
            getFilteredMealsByArea(strMeal).collect { result ->
                Log.d(TAG, "onFilterMealsByArea: $result")
                _filteredMeals.postValue(result)
            }
        }
    }

    fun onFilterFavoriteMeals(toggleMeal: Meal? = null) {
        toggleMeal?.let {
            viewModelScope.launch {
                setToggleMealFavoriteFlag(toggleMeal).collect { result ->
                    Log.d(TAG, "onFilterFavoriteMeals: meal Bookmark successfully changed")
                    _favoriteMeals.postValue(result)
                    onSearchMealsByName(currentSearchQuery)
                }
            }
        } ?: run{
            viewModelScope.launch {
                getMealsByFavorite().collect { result ->
                    Log.d(TAG, "onFilterMealsByArea: $result")
                    _favoriteMeals.postValue(result)
                }
            }
        }
    }
}