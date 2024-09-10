package com.example.compose_magazin.presentation.catalogScreen

import ErrorScreen
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.compose_magazin.presentation.productCard.ProductCard
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.compose_magazin.presentation.productCard.ProductCardsViewModel
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel

@Composable
fun CatalogScreen(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel,
    catalogScreenViewModel: CatalogScreenViewModel,
    productCardsViewModel: ProductCardsViewModel
) {
    val isLoading by catalogScreenViewModel.isLoading.collectAsState()
    val isSuccessful by catalogScreenViewModel.isSuccesful.collectAsState()

    if (isLoading) {
        LoadingSpinner()
    } else if (isSuccessful) {
        HomeScreenContent(
            navController = navController,
            scaffoldViewModel = scaffoldViewModel,
            catalogScreenViewModel = catalogScreenViewModel,
            productCardsViewModel = productCardsViewModel
        )
    } else {
        ErrorScreen(
            message = catalogScreenViewModel.errorMess,
            onRetryClick = { catalogScreenViewModel.fetchPets() }
        )
    }
}

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    scaffoldViewModel: ScaffoldViewModel,
    catalogScreenViewModel: CatalogScreenViewModel,
    productCardsViewModel: ProductCardsViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(catalogScreenViewModel.petProductList) { product ->

            ProductCard(
                product = product,
                scaffoldViewModel = scaffoldViewModel,
                productCardsViewModel = productCardsViewModel,
                navController = navController
            )
        }
    }
}

@Composable
fun LoadingSpinner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
