package com.example.compose_magazin.presentation.scaffoldComponents

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor() : ViewModel() {
    private var _cartItemCount = mutableStateOf(0)
    val cartItemCount: MutableState<Int> get() = _cartItemCount

    fun addItem(int: Int) {
        _cartItemCount.value += int
    }
    fun removeItem(int: Int) {
        _cartItemCount.value -= int
    }
}
