package com.njm.coffeeui.presentation.components.utils

import android.os.CountDownTimer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njm.coffeeui.presentation.components.buttons.FilledButton
import com.njm.coffeeui.presentation.components.cards.MenuCard
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseBottomSheet(
    sheetState: SheetState,
    showBottomSheet: Boolean,
    onChangeShowBottomSheet: (Boolean) -> Unit,
    scope: CoroutineScope,
    menuViewModel: MenuViewModel?
) {
    var isSuccess by remember {
        mutableStateOf(false)
    }

    val sheetFraction by animateFloatAsState(
        targetValue = if (isSuccess) 1f else 0.95f,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onChangeShowBottomSheet(false)
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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Your Cart", fontSize = 22.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "${menuViewModel?.getShoppingList()?.size} Items", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ){
                        items(menuViewModel?.getShoppingList()?.toList() ?: listOf()) { item ->
                            MenuCard(
                                mainMenu = item.first,
                                menuViewModel = menuViewModel,
                                showBill = true,
                                itemAmount = item.second
                            )
                        }
                    }
                }
                Row(
                    Modifier
                        .weight(0.1f)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Total:", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                        Text(text = "$${menuViewModel?.getTotalPurchase()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    FilledButton(
                        onClick = {
                            if (menuViewModel?.getShoppingList()?.isNotEmpty() == true){
                                val timer = object: CountDownTimer(2000, 4000) {
                                    override fun onTick(millisUntilFinished: Long) {}

                                    override fun onFinish() {
                                        menuViewModel?.savePurchaseOrder()
                                        isSuccess = true
                                    }
                                }
                                timer.start()
                            }
                        },
                        title = "Checkout",
                        isEnable = !menuViewModel?.getShoppingList().isNullOrEmpty(),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    )
                }
            }
            if (isSuccess){
                SuccessAnimation(
                    onFinished = {
                        onChangeShowBottomSheet(false)
                        isSuccess = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CustomBottomSheetPreview(){
    CoffeeUITheme {
        PurchaseBottomSheet(
            showBottomSheet = true,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onChangeShowBottomSheet = {true},
            scope = rememberCoroutineScope(),
            menuViewModel = null
        )
    }
}