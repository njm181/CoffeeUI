package com.njm.coffeeui.presentation.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.njm.coffeeui.domain.model.Coffee
import com.njm.coffeeui.presentation.components.cards.CoffeeCard
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuHorizontalPager(
    coffeeList: List<Coffee>,
    onChangeCoffeeBackgroundonState: (String) -> Unit,
    menuViewModel: MenuViewModel?,
    onChangeIconTintColor: (Boolean) -> Unit
){
    val pagerState = rememberPagerState(pageCount = { coffeeList.size })
    onChangeCoffeeBackgroundonState(coffeeList[pagerState.currentPage].imageBackground)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 50.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 95.dp)
        ) { page ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .graphicsLayer {
                        val pageOffset =
                            pagerState.calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
                                scaleX = scale
                                scaleY = scale

                            }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                contentAlignment = Alignment.Center
            ){
                if (coffeeList[pagerState.currentPage].code == "000") onChangeIconTintColor(false) else onChangeIconTintColor(true)
                CoffeeCard(
                    coffee = coffeeList[page],
                    menuViewModel = menuViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

@Preview
@Composable
fun MenuHorizontalPagerPreview(){
    CoffeeUITheme {
        MenuHorizontalPager(
            coffeeList = listOf(
                Coffee(image = "R.drawable.coffee_1", imageBackground = "#FAA7D4", title = "Coffee One", price = "3.90", code = ""),
                Coffee(image = "R.drawable.coffee_2", imageBackground = "#FAA7D4", title = "Coffee Two", price = "4.10", code = ""),
                Coffee(image = "R.drawable.coffee_3", imageBackground = "#FAA7D4", title = "Coffee Three", price = "3.60", code = ""),
                Coffee(image = "R.drawable.coffee_4", imageBackground = "#FAA7D4", title = "Coffee Four", price = "5.30", code = ""),
            ),
            onChangeCoffeeBackgroundonState = {},
            menuViewModel = null
        ) {}
    }
}