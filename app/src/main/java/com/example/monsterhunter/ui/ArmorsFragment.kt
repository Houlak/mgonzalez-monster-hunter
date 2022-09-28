package com.example.monsterhunter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.monsterhunter.data.models.Armor
import com.example.monsterhunter.databinding.FragmentArmorsBinding
import com.example.monsterhunter.utils.AlertUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArmorsFragment : Fragment() {

    private var _binding: FragmentArmorsBinding? = null
    private val binding: FragmentArmorsBinding
        get() = _binding!!

    private val viewModel by viewModel<ArmorsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArmorsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchView()
        setupList()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.armors.observe(viewLifecycleOwner) { armors ->
            submitArmorsToList(armors)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            AlertUtils.showErrorAlert(requireContext(), message = errorMessage)
        }
    }

    private fun submitArmorsToList(armors: List<Armor>) {
        binding.emptyState.isVisible = armors.isEmpty()
        (binding.armorsRecyclerView.adapter as ArmorsAdapter).submitList(armors)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                submitArmorsToList(viewModel.filterArmors(newText ?: ""))
                return true
            }

        })
    }

    private fun setupList() {
        binding.armorsRecyclerView.adapter = ArmorsAdapter()
    }

}