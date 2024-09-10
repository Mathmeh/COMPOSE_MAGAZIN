package com.example.compose_magazin.presentation.productCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.scaffoldComponents.AboutProductData
import com.example.compose_magazin.presentation.scaffoldComponents.Catalog
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel
import com.example.compose_magazin.presentation.uiComponents.ChangeProductAmountButton
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
                {  }
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

@Composable
fun AddToCartButton(
    scaffoldViewModel: ScaffoldViewModel,
    productCardsViewModel: ProductCardsViewModel,
    productId: Long
) {
    if (
        productCardsViewModel.getProductAmountById(productId) == 0 ||
        productCardsViewModel.getProductAmountById(productId) == null
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    productCardsViewModel.addProductAmount(
                        productId = productId,
                        changeAmountValue = 1
                    )
                    scaffoldViewModel.addItem(1)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "add to cart")
            }
        }
    } else if (productCardsViewModel.getProductAmountById(productId)!! > 0) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            ChangeProductAmountButton(buttonText = "-", size = 32.dp, onClickLambda = {
                productCardsViewModel.removeProductAmount(
                    productId = productId,
                    changeAmountValue = 1
                )
                scaffoldViewModel.removeItem(1)
            })

            Text(
                text = productCardsViewModel.getProductAmountById(productId).toString(),
                fontSize = 32.sp,
                modifier = Modifier.fillMaxHeight()
            )

            ChangeProductAmountButton(buttonText = "+", size = 32.dp, onClickLambda = {
                productCardsViewModel.addProductAmount(
                    productId = productId,
                    changeAmountValue = 1
                )
                scaffoldViewModel.addItem(1)
            })
        }
    }
}
