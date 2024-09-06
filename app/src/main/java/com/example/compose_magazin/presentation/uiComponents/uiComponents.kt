package com.example.compose_magazin.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
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
