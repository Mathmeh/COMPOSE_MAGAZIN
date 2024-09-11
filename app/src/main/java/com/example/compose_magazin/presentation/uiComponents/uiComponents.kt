package com.example.compose_magazin.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.compose_magazin.R
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.productCard.ProductCardsViewModel
import com.example.compose_magazin.presentation.scaffoldComponents.ScaffoldViewModel

@Composable
fun ChangeProductAmountButton(
    buttonText: String,
    size: Dp,
    onClickLambda: () -> Unit
) {
    Button(
        onClick = onClickLambda,
        shape = CutCornerShape(8.dp),
        modifier = Modifier.size(size),
        contentPadding = PaddingValues(4.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 20.sp,
            color = Color.Cyan,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun CartBadgedBox(
    text: String,
    icon: ImageVector,
    label: String
) {
    BadgedBox(
        badge = {
            Badge(
                containerColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier.size(28.dp)
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label
        )
    }
}

@Composable
fun ProductImage(
    product: PetProduct,
    imgSize: Int
) {
    val defaultPainter = painterResource(id = R.drawable.placeholder)

    product.photoUrls?.firstOrNull()?.let { imageUrl ->
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                .error(R.drawable.placeholder).placeholder(R.drawable.placeholder).build()
        )

        Image(
            painter = painter,
            contentDescription = product.name,
            modifier = Modifier
                .size(imgSize.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    } ?: run {
        Image(
            painter = defaultPainter,
            contentDescription = product.name,
            modifier = Modifier
                .size(imgSize.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
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
