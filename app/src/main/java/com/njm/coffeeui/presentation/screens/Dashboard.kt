package com.njm.coffeeui.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.njm.coffeeui.R
import com.njm.coffeeui.domain.model.Coffee
import com.njm.coffeeui.domain.model.Donut
import com.njm.coffeeui.domain.model.Drink
import com.njm.coffeeui.presentation.components.buttons.FilledButton
import com.njm.coffeeui.presentation.components.buttons.FloatingButton
import com.njm.coffeeui.presentation.components.buttons.customIconButton
import com.njm.coffeeui.presentation.components.cards.MenuCard
import com.njm.coffeeui.presentation.components.pager.MenuHorizontalPager
import com.njm.coffeeui.presentation.components.utils.CurveBackground
import com.njm.coffeeui.presentation.components.utils.OrderHistoryBottomSheet
import com.njm.coffeeui.presentation.components.utils.PurchaseBottomSheet
import com.njm.coffeeui.presentation.viewModel.DashboardViewModel
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = viewModel(),
    menuViewModel: MenuViewModel = viewModel()
) {

    var drinksButtonState by remember { mutableStateOf(true) }
    var donutsButtonState by remember { mutableStateOf(false) }
    var coffeeBackgroundOrder by remember { mutableStateOf(dashboardViewModel.getCoffeeList().first().imageBackground) }

    val scope = rememberCoroutineScope()
    val purchaseSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showPurchaseBottomSheet by remember { mutableStateOf(false) }
    var iconTintColor by remember { mutableStateOf(true) }

    val orderHistorySheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showOrderHistoryBottomSheet by remember { mutableStateOf(false) }

    DashboardContent(
        drinksButtonState = drinksButtonState,
        donutsButtonState = donutsButtonState,
        onChangeDrinksButtonState = { value ->
            drinksButtonState = value
        },
        onChangeDonutButtonState = { value ->
            donutsButtonState = value
        },
        drinksList = dashboardViewModel.getDrinkList(),
        donutsList = dashboardViewModel.getDonutList(),
        coffeeList = dashboardViewModel.getCoffeeList(),
        coffeeBackgroundOrder = coffeeBackgroundOrder,
        onChangeCoffeeBackgroundonState = { value ->
            coffeeBackgroundOrder = value
        },
        menuViewModel = menuViewModel,
        scope = scope,
        purchaseSheetState = purchaseSheetState,
        showBottomSheet = showPurchaseBottomSheet,
        onShowPurchaseBottonSheetChange = { value -> showPurchaseBottomSheet = value },
        iconTintColor = iconTintColor,
        onChangeIconTintColor = { value -> iconTintColor = value },
        orderHistorySheetState = orderHistorySheetState,
        onShowOrderHistorySheetChange = { value -> showOrderHistoryBottomSheet = value },
        showOrderHistorySheet = showOrderHistoryBottomSheet
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    drinksButtonState: Boolean,
    donutsButtonState: Boolean,
    onChangeDrinksButtonState: (Boolean) -> Unit,
    onChangeDonutButtonState: (Boolean) -> Unit,
    drinksList: List<Drink>,
    donutsList: List<Donut>,
    coffeeList: List<Coffee>,
    coffeeBackgroundOrder: String,
    onChangeCoffeeBackgroundonState: (String) -> Unit,
    menuViewModel: MenuViewModel,
    purchaseSheetState: SheetState,
    showBottomSheet: Boolean,
    onShowPurchaseBottonSheetChange: (Boolean) -> Unit,
    scope: CoroutineScope,
    iconTintColor: Boolean,
    onChangeIconTintColor: (Boolean) -> Unit,
    orderHistorySheetState: SheetState,
    showOrderHistorySheet: Boolean,
    onShowOrderHistorySheetChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                CurveBackground(color = if (showBottomSheet) "#ffa938" else coffeeBackgroundOrder)
                MenuHorizontalPager(
                    coffeeList = coffeeList,
                    onChangeCoffeeBackgroundonState = onChangeCoffeeBackgroundonState,
                    menuViewModel = menuViewModel,
                    onChangeIconTintColor = onChangeIconTintColor
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .weight(1f)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FilledButton(
                            onClick = {
                                onChangeDrinksButtonState(true)
                                onChangeDonutButtonState(false)
                            },
                            title = "Drinks",
                            isEnable = drinksButtonState
                        )
                        FilledButton(
                            onClick = {
                                onChangeDonutButtonState(true)
                                onChangeDrinksButtonState(false)
                            },
                            title = "Donuts",
                            isEnable = donutsButtonState
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ){
                        if (drinksButtonState) {
                            items(drinksList) { item ->
                                MenuCard(item, menuViewModel)
                            }
                        } else {
                            items(donutsList) { item ->
                                MenuCard(item, menuViewModel)
                            }
                        }
                    }
                }
            }
        }
        FloatingButton(onClick = { onShowPurchaseBottonSheetChange(true) })

        customIconButton(
            onClick = {
                menuViewModel.getOrdersHistory()
                onShowOrderHistorySheetChange(true)
                      },
            icon = R.drawable.baseline_format_list_24_white,
            tintColor = if (iconTintColor) Color.White else Color.Black
        )

        OrderHistoryBottomSheet(
            sheetState = orderHistorySheetState,
            showOrderHistorySheet = showOrderHistorySheet,
            onChangeShowOrderHistorySheet = onShowOrderHistorySheetChange,
            menuViewModel = menuViewModel
        )

        PurchaseBottomSheet(
            showBottomSheet = showBottomSheet,
            sheetState = purchaseSheetState,
            onChangeShowBottomSheet = onShowPurchaseBottonSheetChange,
            scope = scope,
            menuViewModel = menuViewModel
        )
    }
}



@Composable
@Preview(showBackground = true)
fun DashboardScreenPreview(){
    CoffeeUITheme {
        DashboardScreen()
    }
}