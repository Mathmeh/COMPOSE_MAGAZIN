package com.example.compose_magazin.presentation.productCard

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.uiComponents.ChangeProductAmountButton

@Composable
fun ProductCard(
    product: PetProduct,
    modifier: Modifier = Modifier
) {
    val productAmountState = rememberSaveable {
        mutableStateOf(0)
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            product.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${product.category?.name}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 16.sp
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            AddToCartButton(productAmountState)
        }
    }
}

@Composable
fun AddToCartButton(count: MutableState<Int>) {
    if (count.value == 0) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { count.value++ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "add to cart")
            }
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            ChangeProductAmountButton(
                buttonText = "-",
                size = 32.dp,
                onClickLambda = { if (count.value > 0) count.value-- }
            )

            Text(
                text = count.value.toString(),
                fontSize = 32.sp,
                modifier = Modifier.fillMaxHeight()
            )

            ChangeProductAmountButton(
                buttonText = "+",
                size = 32.dp,
                onClickLambda = { count.value++ }
            )
        }
    }
}
