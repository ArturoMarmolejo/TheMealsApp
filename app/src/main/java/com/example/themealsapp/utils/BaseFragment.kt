package com.example.themealsapp.utils

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.themealsapp.presentation.viewmodel.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val mealsViewModel: MealsViewModel by lazy {
        ViewModelProvider(requireActivity())[MealsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun showError(message: String, action: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("Error Occurred")
            .setMessage(message)
            .setNegativeButton("DISMISS") {dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}