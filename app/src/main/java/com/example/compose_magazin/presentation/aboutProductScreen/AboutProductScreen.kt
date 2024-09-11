package com.example.compose_magazin.presentation.aboutProductScreen

import ErrorScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.compose_magazin.R
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.domain.entity.PetTag
import com.example.compose_magazin.presentation.catalogScreen.LoadingSpinner
import com.example.compose_magazin.presentation.productCard.ProductCardsViewModel
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel
import com.example.compose_magazin.presentation.uiComponents.AddToCartButton

@Composable
fun AboutProductScreen(
    scaffoldViewModel: ScaffoldViewModel,
    navController: NavController,
    productCardsViewModel: ProductCardsViewModel,
    aboutProductViewModel: AboutProductViewModel = hiltViewModel()
) {
    val isLoading by aboutProductViewModel.isLoading.collectAsState()
    val isSuccessful by aboutProductViewModel.isSuccesful.collectAsState()

    if (isLoading) {
        LoadingSpinner()
    } else if (isSuccessful) {
        PetInfoScreen(
            product = aboutProductViewModel.petProduct,
            productCardsViewModel = productCardsViewModel,
            scaffoldViewModel = scaffoldViewModel
        )
    } else {
        ErrorScreen(
            message = aboutProductViewModel.errorMess,
            onRetryClick = { aboutProductViewModel.fetchPetById(aboutProductViewModel.id) }
        )
    }
}

@Composable
fun PetInfoScreen(
    product: PetProduct,
    scaffoldViewModel: ScaffoldViewModel,
    productCardsViewModel: ProductCardsViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        product.name?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    shadow = Shadow(
                        color = Color.Cyan,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
        }
        ProductPhotoCarousel(carouselItems = product.photoUrls)
        Spacer(modifier = Modifier.height(8.dp))
        product.tags?.let { PetTagsRow(petTags = it) }
        Spacer(modifier = Modifier.height(16.dp))
        product.category?.name?.let {
            Text(
                text = "Category: $it",
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 26.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)

        ) {
            AddToCartButton(
                scaffoldViewModel = scaffoldViewModel,
                productCardsViewModel = productCardsViewModel,
                productId = product.id
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPhotoCarousel(carouselItems: List<String?>?) {
    if (carouselItems != null) {
        val isCarouselScrollable = carouselItems.size > 1
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { carouselItems.count() },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            preferredItemWidth = 500.dp,
            itemSpacing = if (isCarouselScrollable) 16.dp else 0.dp
        ) { i ->
            val item = carouselItems[i]
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.extraLarge)
                    .drawWithContent {
                        drawContent()
                    }
            ) {
                AsyncImage(
                    model = item,
                    contentDescription = "carousel image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = rememberAsyncImagePainter(model = R.drawable.placeholder),
                    error = rememberAsyncImagePainter(model = R.drawable.placeholder)
                )
            }
        }
    }
}

@Composable
fun PetTagsRow(petTags: List<PetTag>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(petTags) { tag ->
            PetTagCard(tag)
        }
    }
}

@Composable
fun PetTagCard(tag: PetTag) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp)
            .clickable { /* TODO: do tag navigation*/ }

    ) {
        Text(
            text = tag.name ?: "Unknown",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp
        )
    }
}
