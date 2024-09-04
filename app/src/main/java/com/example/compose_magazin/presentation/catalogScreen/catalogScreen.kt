package com.example.compose_magazin.presentation.catalogScreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.compose_magazin.presentation.productCard.ProductCard
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CatalogScreen(
    navController: NavHostController,
    viewModel: CatalogScreenViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    if (isLoading) {
        LoadingSpinner()
    } else {
        HomeScreenContent(
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: CatalogScreenViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
    ) {
        items(viewModel.petProductList) { product ->
            ProductCard(product = product)
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
