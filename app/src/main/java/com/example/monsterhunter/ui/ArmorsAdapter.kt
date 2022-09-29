package com.example.monsterhunter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterhunter.data.models.Armor
import com.example.monsterhunter.databinding.ArmorsListItemBinding

class ArmorsAdapter(private val onItemClickListener: (Armor) -> Unit) : ListAdapter<Armor, ArmorsAdapter.ViewHolder>(ArmorsDiffUtilCallback()) {

    class ViewHolder(private val binding: ArmorsListItemBinding, private val onItemClickListener: (Armor) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Armor) {
            binding.name.text = item.name
            binding.rank.text = item.rank.uppercase()
            binding.baseDefense.text = "${item.defense.base}"
            binding.decorationSlots.text = "${item.slots.size}"
            Armor.Types.getIconRes(item.type)?.let { typeIconRes ->
                binding.typeIcon.setImageResource(typeIconRes)
            } ?: run {
                binding.typeIcon.visibility = View.INVISIBLE
            }

            binding.root.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }

    class ArmorsDiffUtilCallback : DiffUtil.ItemCallback<Armor>() {
        override fun areItemsTheSame(oldItem: Armor, newItem: Armor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Armor, newItem: Armor): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArmorsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}