package com.example.compose_magazin.presentation.catalogScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose_magazin.domain.models.Product
import com.example.compose_magazin.presentation.productCard.ProductCard

@Composable
fun CatalogScreen(navController: NavHostController) {
    val products = listOf(
        Product(1, "Product 1", 19.99),
        Product(2, "Product 2", 29.99),
        Product(3, "Product 3", 39.99),
        Product(4, "Product 4", 49.99),
        Product(5, "Product 5", 59.99),
        Product(6, "Product 6", 69.99),
        Product(7, "Product 6", 69.99),
        Product(8, "Product 6", 69.99),
        Product(9, "Product 6", 69.99),
        Product(10, "Product 6", 69.99),
        Product(11, "Product 6", 69.99),
        Product(12, "Product 6", 69.99),
        Product(13, "Product 6", 69.99)
    )

    HomeScreenContent(navController = navController)
}

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: CatalogScreenViewModel = hiltViewModel()
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(viewModel.petProductList) { product ->
            ProductCard(product = product)
        }
    }
}
