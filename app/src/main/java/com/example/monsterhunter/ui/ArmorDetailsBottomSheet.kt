package com.example.monsterhunter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.monsterhunter.data.models.Armor
import com.example.monsterhunter.databinding.BottomSheetArmorDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ArmorDetailsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetArmorDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetArmorDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Armor>(ARMOR_PARAM)?.let { armor ->
            Armor.Types.getIconRes(armor.type)?.let { iconRes ->
                binding.icon.setImageResource(iconRes)
            } ?: run {
                binding.icon.isVisible = false
            }
            binding.name.text = armor.name
            binding.rank.text = "Rank: ${armor.rank.uppercase()}"
            binding.decorationSlots.text = "Decoration slots:\n${armor.slots.size}"
            binding.shield.text = "Base defense:\n${armor.defense.base}"
        }

    }

    companion object {
        const val TAG = "ArmorDetailsBottomSheet"
        private const val ARMOR_PARAM = "armor_param"

        @JvmStatic
        fun newInstance(armor: Armor) = ArmorDetailsBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(ARMOR_PARAM, armor)
            }
        }
    }
}