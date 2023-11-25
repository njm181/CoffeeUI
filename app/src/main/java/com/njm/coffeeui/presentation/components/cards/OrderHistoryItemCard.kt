package com.njm.coffeeui.presentation.components.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.coffeeui.R
import com.njm.coffeeui.domain.model.OrderHistory
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import com.njm.coffeeui.ui.theme.Orange500
import com.njm.coffeeui.ui.theme.Orange600

@Composable
fun OrderHistoryCard(orderHistory: OrderHistory){

    Card(
        colors = CardDefaults.cardColors(containerColor = Orange500),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        OrderHistoryItem(orderHistory = orderHistory)
    }
}

@Composable
fun OrderHistoryItem(orderHistory: OrderHistory,){
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "Order: ",
                fontSize = 16.sp,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Text(
                text = orderHistory.id.toString(),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                fontSize = 18.sp,
                color = Color.White
            )
            if (expanded) {
                orderHistory.listOrderHistory.forEach { item ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item.title, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium)
                        Text(text = item.amount.toString()+"x", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium)
                    }
                }

            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                painter = painterResource(
                id = if (expanded) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24),
                contentDescription = "expand icon",
                tint = Color.White
            )
        }

    }
}


@Preview
@Composable
fun OrderHistoryItemCardPreview(){
    CoffeeUITheme {
        OrderHistoryCard(orderHistory = OrderHistory(1, listOf()))
    }
}