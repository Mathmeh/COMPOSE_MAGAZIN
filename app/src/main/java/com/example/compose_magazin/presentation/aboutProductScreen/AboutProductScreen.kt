package com.example.compose_magazin.presentation.aboutProductScreen

import ErrorScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.catalogScreen.LoadingSpinner

@Composable
fun AboutProductScreen(
    aboutProductViewModel: AboutProductViewModel = hiltViewModel()
) {
    val isLoading by aboutProductViewModel.isLoading.collectAsState()
    val isSuccessful by aboutProductViewModel.isSuccesful.collectAsState()

    if (isLoading) {
        LoadingSpinner()
    } else if (isSuccessful) {
        Text(text = "ID: ${aboutProductViewModel.petProduct.name}")
        PetInfoScreen(product = aboutProductViewModel.petProduct)
    } else {
        ErrorScreen(
            message = aboutProductViewModel.errorMess,
            onRetryClick = { aboutProductViewModel.fetchPetById(aboutProductViewModel.id) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetInfoScreen(product: PetProduct) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        product.name?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        val carouselItems = product.photoUrls
        if (carouselItems != null) {
            HorizontalMultiBrowseCarousel(
                state = rememberCarouselState { carouselItems.count() },
                modifier = Modifier
                    .width(412.dp)
                    .height(221.dp),
                preferredItemWidth = 186.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) { i ->
                val item = carouselItems[i]
            }
        }
    }
}
