package com.example.compose_magazin.presentation.productCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.scaffoldComponents.AboutProductData
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel
import com.example.compose_magazin.presentation.uiComponents.AddToCartButton
import com.example.compose_magazin.presentation.uiComponents.ProductImage

@Composable
fun ProductCard(
    navController: NavHostController,
    product: PetProduct,
    productCardsViewModel: ProductCardsViewModel,
    scaffoldViewModel: ScaffoldViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    AboutProductData(
                        id = product.id
                    )
                )
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            product.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            ProductImage(
                product = product,
                imgSize = 150
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${product.category?.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            AddToCartButton(
                scaffoldViewModel = scaffoldViewModel,
                productCardsViewModel = productCardsViewModel,
                productId = product.id
            )
        }
    }
}
