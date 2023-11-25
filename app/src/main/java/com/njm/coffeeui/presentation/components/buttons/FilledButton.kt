package com.njm.coffeeui.presentation.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.coffeeui.R
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import com.njm.coffeeui.ui.theme.Orange500

@Composable
fun FilledButton(
    onClick: () -> Unit,
    title: String,
    isEnable: Boolean,
    modifier: Modifier = Modifier
){
        Button(
            modifier = modifier.width(100.dp),
            onClick = { onClick() },
            colors = if(isEnable)
                ButtonDefaults.buttonColors(Orange500)
            else
                ButtonDefaults.buttonColors(Color.Transparent),

        ) {
            Row {
                Text(
                    text = title,
                    color = if (isEnable) Color.White else Color.Black
                )
            }
        }
}


@Preview
@Composable
fun FilledButtonPreview(){
    CoffeeUITheme {
        FilledButton(onClick = {}, title = "Title", isEnable = false)
    }
}