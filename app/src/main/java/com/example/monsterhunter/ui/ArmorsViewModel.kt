package com.example.monsterhunter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monsterhunter.data.models.Armor
import com.example.monsterhunter.data.repositories.ArmorRepository
import com.example.monsterhunter.data.responses.GetArmorsFailure
import com.example.monsterhunter.data.responses.GetArmorsSuccess
import kotlinx.coroutines.launch

class ArmorsViewModel(private val armorRepository: ArmorRepository) : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _armors = MutableLiveData<List<Armor>>()
    val armors: LiveData<List<Armor>> get() = _armors

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getArmors()
    }

    fun getArmors() {
        viewModelScope.launch {
            _loading.value = true

            val response = armorRepository.fetchArmors()

            _loading.value = false

            when (response) {
                is GetArmorsSuccess -> {
                    _armors.value = response.armors
                }
                is GetArmorsFailure -> {
                    _error.value = response.error.message
                }
            }
        }
    }

    fun filterArmors(newText: String): List<Armor> {
        return armors.value?.filter { it.name.contains(newText, ignoreCase = true) } ?: emptyList()
    }
}