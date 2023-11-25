package com.njm.coffeeui.presentation.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.njm.coffeeui.R
import com.njm.coffeeui.ui.theme.CoffeeUITheme

@Composable
fun customIconButton(icon: Int, onClick: () -> Unit, tintColor: Color) {
    IconButton(
        onClick = { onClick() },
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "history",
            tint = tintColor
        )
    }
}


@Preview
@Composable
fun IconButtonPreview(){
    CoffeeUITheme {
        customIconButton(R.drawable.baseline_format_list_24_white, {}, Color.White)
    }
}