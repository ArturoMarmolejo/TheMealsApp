package com.example.themealsapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentMealSearchBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.presentation.view.adapter.MealsListAdapter
import com.example.themealsapp.utils.BaseFragment
import com.example.themealsapp.utils.UIState

private const val TAG = "MealSearchFragment"
class MealSearchFragment : BaseFragment() {

    private val binding by lazy {
        FragmentMealSearchBinding.inflate(layoutInflater)
    }

    private val mealsListAdapter by lazy {
        MealsListAdapter {
            Log.d(TAG, "meal: ${it}: ")
            mealsViewModel.selectedMealItem = it
            findNavController().navigate(R.id.action_navigate_to_meal_detail)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.rvMealSearch.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mealsListAdapter
        }
        searchMealsByName()
        binding.btnSearch.setOnClickListener {
            if (!mealsViewModel.getNetworkState()){
                showError("Couldn't access the network. Displaying results from Database") {}
            }
            val searchQuery = binding.etSearch.text.toString()
            mealsViewModel.onSearchMealsByName(searchQuery)
        }



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun searchMealsByName() {
        mealsViewModel.meals.observe(viewLifecycleOwner) { state ->
            when (state){
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS<List<Meal>> -> {
                    Log.d(TAG, "searchMeals: It brought the reponse to the fragment")
                    mealsListAdapter.updateMeals(state.response)
                }
                is UIState.ERROR -> {
                    Log.e(TAG, "searchMealsByName: UIState error: ", )
                    showError(state.error.localizedMessage, ) {}
                }
            }
        }
    }
}