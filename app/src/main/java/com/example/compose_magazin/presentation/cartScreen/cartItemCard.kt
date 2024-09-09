package com.example.compose_magazin.presentation.cartScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_magazin.domain.entity.PetProduct
import com.example.compose_magazin.presentation.uiComponents.ProductImage

@Composable
fun CartProductItem(
    productAmount: Int,
    product: PetProduct,
    onRemove: (PetProduct) -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    val amount = remember {
        mutableStateOf(productAmount)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .align(Alignment.CenterVertically)
            ) {
                ProductImage(
                    product = product,
                    imgSize = 64
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Name: ${product.name}",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Status: ${product.status}",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "${product.category?.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {
                        onIncrease()
                        amount.value++
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = amount.value.toString(),
                    fontSize = 32.sp,
                    modifier = Modifier.fillMaxHeight()
                )
                IconButton(
                    onClick = {
                        onDecrease()
                        amount.value--
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            IconButton(
                onClick = { onRemove(product) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}
