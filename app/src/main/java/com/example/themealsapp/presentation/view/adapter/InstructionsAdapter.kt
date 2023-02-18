package com.example.themealsapp.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themealsapp.databinding.RvIngredientItemBinding
import com.example.themealsapp.databinding.RvInstructionItemBinding

private const val TAG = "InstructionsAdapter"
class InstructionsAdapter(
    private val instructionsItemSet: MutableList<String> = mutableListOf(),
): RecyclerView.Adapter<InstructionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder =
        InstructionsViewHolder(
            RvInstructionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = instructionsItemSet.size
    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: Instructions Adapter: ${instructionsItemSet}")
        holder.bind(instructionsItemSet[position])
    }

}


class InstructionsViewHolder(
    private val binding: RvInstructionItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(instruction: String) {
        binding.tvInstructionItem.text = "$instruction"
    }
}
