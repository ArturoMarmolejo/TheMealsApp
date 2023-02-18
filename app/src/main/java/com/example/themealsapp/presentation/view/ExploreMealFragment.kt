package com.example.themealsapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themealsapp.databinding.FragmentExploreMealBinding
import com.example.themealsapp.domain.model.MealFiltered
import com.example.themealsapp.presentation.view.adapter.ExploreMealsAdapter
import com.example.themealsapp.utils.BaseFragment
import com.example.themealsapp.utils.UIState

private const val TAG = "ExploreMealFragment"
class ExploreMealFragment : BaseFragment(), AdapterView.OnItemSelectedListener {

    private val binding by lazy {
        FragmentExploreMealBinding.inflate(layoutInflater)
    }
    private val mealsExploreAdapter = ExploreMealsAdapter()

    private val countries = mutableListOf(
        "American", "British", "Canadian", "Chinese", "Croatian", "Dutch", "Egyptian", "French", "Greek", "Indian", "Irish", "Italian", "Jamaican", "Japanese", "Kenyan", "Malaysian", "Mexican", "Moroccan", "Polish", "Portuguese", "Russian", "Spanish", "Thai", "Tunisian", "Turkish", "Unknown", "Vietnamese"
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.countrySpinner.adapter = spinnerAdapter
        with(binding.countrySpinner){
            adapter = spinnerAdapter
            setSelection(0, false)
            onItemSelectedListener = this@ExploreMealFragment
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER
        }

        binding.rvMealExplore.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = mealsExploreAdapter
        }
        // Inflate the layout for this fragment
        filterMealsByCategory()
        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(requireContext(), "${countries[position]} selected", Toast.LENGTH_SHORT).show()
        mealsViewModel.onFilterMealsByArea(countries[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(requireContext(), "Nothing selected", Toast.LENGTH_SHORT).show()
    }

    private fun filterMealsByCategory() {
        mealsViewModel.filteredMeals.observe(viewLifecycleOwner) { state ->
            when (state){
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS<List<MealFiltered>> -> {
                    Log.d(TAG, "searchMeals: It brought the reponse to the fragment = ${state.response}")
                    mealsExploreAdapter.updateMeals(state.response)
                }
                is UIState.ERROR -> {
                    Log.e(TAG, "searchMealsByName: UIState error: ", )
                    showError(state.error.localizedMessage, ) {}
                }
            }
        }
    }
}