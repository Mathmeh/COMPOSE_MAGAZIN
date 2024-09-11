package com.example.compose_magazin.presentation.productCard

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCardsViewModel @Inject constructor() : ViewModel() {

    private val _productAmounts = mutableStateMapOf<Long, Int>()
    val productAmounts: SnapshotStateMap<Long, Int> get() = _productAmounts

    fun addProductAmount(productId: Long, changeAmountValue: Int) {
        if (_productAmounts[productId] == null) {
            _productAmounts[productId] = 0
            _productAmounts[productId] = _productAmounts[productId]!! + 1
        } else {
            _productAmounts[productId] = _productAmounts[productId]!! + 1
        }
    }

    fun removeProductAmount(productId: Long, changeAmountValue: Int) {
        _productAmounts.get(productId)?.let {
            if (it > 0 && it >= changeAmountValue) {
                _productAmounts[productId] = _productAmounts[productId]!! - changeAmountValue
            }
        }
        if (_productAmounts[productId] == 0) {
            _productAmounts.remove(productId)
        }
    }

    fun getProductAmountById(productId: Long): Int? {
        return productAmounts[productId]
    }

    fun removeAllProductsById(productId: Long) {
        _productAmounts.remove(productId)
    }
}
