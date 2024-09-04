package com.example.compose_magazin.presentation.catalogScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    var petProductList: List<PetProduct> = listOf()

    init {
        fetchPets()
    }

    private fun fetchPets() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                petProductList = petRepository.getAvaliablePets()
            } catch (e: Exception) {
                // TODO сделать экран ошибки
            } finally {
                _isLoading.value = false
            }
        }
    }
}
