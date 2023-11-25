package com.njm.coffeeui.presentation.components.cards

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.njm.coffeeui.R
import com.njm.coffeeui.domain.model.Drink
import com.njm.coffeeui.domain.model.MainMenu
import com.njm.coffeeui.presentation.components.utils.toColor
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme

@Composable
fun MenuCard(
    mainMenu: MainMenu,
    menuViewModel: MenuViewModel?,
    showBill: Boolean = false,
    itemAmount: Int = 0
){
    val animatedButtonColor = animateColorAsState(
        targetValue = mainMenu.imageBackground.toColor(),
        animationSpec = tween(300, 0, LinearEasing), label = ""
    )

    var itemCounter by remember { mutableIntStateOf(menuViewModel?.getAmountSelected(mainMenu) ?: 0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(isHalfSize(showBill = showBill, 90.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(isHalfSize(showBill = showBill, 90.dp))
                .background(color = animatedButtonColor.value),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mainMenu.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.baseline_downloading_24),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(isHalfSize(showBill = showBill, 70.dp))
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "${mainMenu.title}",
                        fontSize = if (showBill) 12.sp else 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "$${mainMenu.price}",
                        fontSize = if (showBill) 12.sp else 16.sp
                    )
                }
                if (showBill){
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray,
                        ),
                        border = BorderStroke(1.dp, Color.LightGray),
                        modifier = Modifier.size(width = 40.dp, height = 30.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = "${itemAmount}x", fontSize = 12.sp)
                        }
                    }
                } else {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                            ),
                            border = BorderStroke(1.dp, Color.Black),
                            modifier = Modifier
                                .size(width = 30.dp, height = 30.dp)
                                .clickable {
                                    itemCounter++
                                    menuViewModel?.setItemToList(mainMenu)
                                },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Icon(Icons.Filled.Add, contentDescription = "Add item")
                            }
                        }
                        if (itemCounter > 0 ){
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(text = itemCounter.toString(), fontSize = 16.sp)

                            Spacer(modifier = Modifier.width(4.dp))
                            OutlinedCard(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier
                                    .size(width = 30.dp, height = 30.dp)
                                    .clickable {
                                        itemCounter--
                                        menuViewModel?.removeItemFromList(mainMenu)
                                    },
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Icon(painter = painterResource(id = R.drawable.baseline_horizontal_rule_24), contentDescription = "Add item")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun isHalfSize(showBill: Boolean, value: Dp) = if (showBill) value/2 else value

@Preview
@Composable
fun MenuCardPreview(){
    CoffeeUITheme {
        MenuCard(
            Drink(
                image = "https://static.vecteezy.com/system/resources/previews/027/727/984/non_2x/tasty-cola-drink-with-ice-cubes-in-a-glass-ai-generative-free-png.png",
                imageBackground = "#FAA7D4",
                title = "Drink One",
                price = "3.10",
                code = "001"
                ),
            menuViewModel = null,
            itemAmount = 5
        )
    }
}