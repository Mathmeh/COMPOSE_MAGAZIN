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
    private val petRepository: PetRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isSuccesful = MutableStateFlow(true)
    val isSuccesful: StateFlow<Boolean> get() = _isSuccesful

    var errorMess: String = ""

    var petProductList: List<PetProduct> = listOf()

    init {
        fetchPets()
    }

    fun fetchPets() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isSuccesful.value = true
                errorMess = ""
                petProductList = petRepository.getAvailablePets()
            } catch (e: Exception) {
                _isSuccesful.value = false
                errorMess = e.message.toString()
                println("ERROR:  ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
