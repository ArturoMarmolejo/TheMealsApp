package com.example.themealsapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealsapp.R
import com.example.themealsapp.databinding.FragmentBookmarksBinding
import com.example.themealsapp.domain.model.Meal
import com.example.themealsapp.presentation.view.adapter.MealsListAdapter
import com.example.themealsapp.utils.BaseFragment
import com.example.themealsapp.utils.UIState

private const val TAG = "BookmarksFragment"
class BookmarksFragment : BaseFragment() {

    private val binding by lazy {
        FragmentBookmarksBinding.inflate(layoutInflater)
    }

    private val mealsListAdapter by lazy {
        MealsListAdapter {
            Log.d(TAG, "meal: ${it}: ")
            mealsViewModel.selectedMealItem = it
            findNavController().navigate(R.id.action_bookmark_fragment_to_meal_detail_fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView: entered in the fragment")
        binding.rvMealSearch.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mealsListAdapter
        }

        filterFavoriteMeals()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun filterFavoriteMeals() {
        mealsViewModel.favoriteMeals.observe(viewLifecycleOwner) { state ->
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