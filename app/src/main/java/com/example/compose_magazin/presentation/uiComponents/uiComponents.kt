package com.example.compose_magazin.presentation.uiComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
