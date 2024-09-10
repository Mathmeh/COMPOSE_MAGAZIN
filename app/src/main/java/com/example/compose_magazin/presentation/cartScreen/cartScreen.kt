package com.example.compose_magazin.presentation.cartScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose_magazin.presentation.catalogScreen.CatalogScreenViewModel
import com.example.compose_magazin.presentation.productCard.ProductCardsViewModel
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel

@Composable
fun CartScreen(
    navController: NavHostController,
    catalogScreenViewModel: CatalogScreenViewModel,
    scaffoldViewModel: ScaffoldViewModel,
    productCardsViewModel: ProductCardsViewModel
) {
    val productsInCart =
        productCardsViewModel.productAmounts.toList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(productsInCart.size) { index ->
            val id = productsInCart[index].first
            val product = catalogScreenViewModel.getPetById(id)
            val amount = productCardsViewModel.getProductAmountById(id) ?: 0

            CartProductItem(
                navController = navController,
                productAmount = amount,
                product = product,
                onRemove = {
                    scaffoldViewModel.removeItem(amount)
                    productCardsViewModel.removeAllProductsById(
                        productId = product.id
                    )
                },
                onIncrease = {
                    productCardsViewModel.addProductAmount(id, 1)
                    scaffoldViewModel.addItem(1)
                },
                onDecrease = {
                    productCardsViewModel.removeProductAmount(
                        productId = id,
                        changeAmountValue = 1
                    )
                    scaffoldViewModel.removeItem(1)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
