package com.example.compose_magazin.presentation.catalogScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_magazin.domain.models.Entity.PetProduct
import com.example.compose_magazin.domain.models.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel
@Inject constructor(private val petRepository: PetRepository) : ViewModel() {
    var petProductList: List<PetProduct> = listOf()
    init {
        viewModelScope.launch {
            petProductList = petRepository.getAvaliablePets()
        }
    }
}
