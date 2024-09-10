package com.example.compose_magazin.presentation.aboutProductScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_magazin.domain.PetRepository
import com.example.compose_magazin.domain.entity.PetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutProductViewModel @Inject constructor(
    private val petRepository: PetRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isSuccesful = MutableStateFlow(true)
    val isSuccesful: StateFlow<Boolean> get() = _isSuccesful

    var errorMess: String = ""
    val id: Long = savedStateHandle["id"] ?: -1


    var petProduct : PetProduct = PetProduct(
        -1,
        null,
        null,
        null,
        null,
        null
    )

    init {
        fetchPetById(id)
    }

    fun fetchPetById(id: Long) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _isSuccesful.value = true
                errorMess = ""
                petProduct = petRepository.getPetById(id)
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
