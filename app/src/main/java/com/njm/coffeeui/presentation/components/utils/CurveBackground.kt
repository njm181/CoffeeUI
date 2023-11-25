package com.njm.coffeeui.presentation.components.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.njm.coffeeui.ui.theme.CoffeeUITheme

@Composable
fun CurveBackground(color: String){

    val animatedButtonColor = animateColorAsState(
        targetValue = color.toColor(),
        animationSpec = tween(300, 0, LinearEasing), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(
                RoundedCornerShape(
                    bottomStart = 250.dp,
                    bottomEnd = 250.dp
                )
            )
            .background(color = animatedButtonColor.value)
    )
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))

@Composable
@Preview
fun CurveBackground(){
    CoffeeUITheme {
        CurveBackground(color = "#03DAC5")
    }
}