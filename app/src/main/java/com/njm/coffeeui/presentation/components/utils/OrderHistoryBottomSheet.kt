package com.njm.coffeeui.presentation.components.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.coffeeui.presentation.components.cards.OrderHistoryCard
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryBottomSheet(
    sheetState: SheetState,
    showOrderHistorySheet: Boolean,
    onChangeShowOrderHistorySheet: (Boolean) -> Unit,
    menuViewModel: MenuViewModel?
) {

    val orderHistoryList = menuViewModel?.orderHistoryListLiveData?.observeAsState()?.value

    val sheetFraction by animateFloatAsState(
        targetValue = 0.90f,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    if (showOrderHistorySheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onChangeShowOrderHistorySheet(false)
            },
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(sheetFraction)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, bottom = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxHeight()) {
                    Text(text = "Order History", fontSize = 22.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ){
                        orderHistoryList?.let {
                            items(it) { item ->
                                OrderHistoryCard(orderHistory = item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OrderHistoryBottomSheetPreview(){
    CoffeeUITheme {
        OrderHistoryBottomSheet(
            showOrderHistorySheet = true,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onChangeShowOrderHistorySheet = {true},
            menuViewModel = null
        )
    }
}