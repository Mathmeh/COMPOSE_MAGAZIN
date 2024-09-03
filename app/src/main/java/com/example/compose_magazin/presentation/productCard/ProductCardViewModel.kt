package com.example.compose_magazin.presentation.productCard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCardViewModel @Inject constructor() : ViewModel() {
    val productAmountState: MutableState<Int> = mutableStateOf(0)

    fun increaseProductAmount() {
        productAmountState.value++
    }

    fun decreaseProductAmount() {
        if (productAmountState.value > 0) {
            productAmountState.value--
        }
    }
}
