package com.njm.coffeeui.presentation.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.njm.coffeeui.R
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import com.njm.coffeeui.ui.theme.Orange500

@Composable
fun FloatingButton(onClick: () -> Unit){
    Box(
        Modifier.fillMaxSize().padding(bottom = 8.dp),
        contentAlignment = Alignment.BottomCenter
        ) {
        LargeFloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
            containerColor = Orange500,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier.size(48.dp)
        ) {
            Image(painterResource(id = R.drawable.baseline_shopping_cart_24), "Floating action button.")
        }
    }
}

@Preview
@Composable
fun FloatingButtonPreview(){
    CoffeeUITheme {
        FloatingButton(onClick = {})
    }
}