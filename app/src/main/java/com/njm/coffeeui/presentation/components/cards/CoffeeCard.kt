package com.njm.coffeeui.presentation.components.cards

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.njm.coffeeui.R
import com.njm.coffeeui.domain.model.Coffee
import com.njm.coffeeui.presentation.viewModel.MenuViewModel
import com.njm.coffeeui.ui.theme.CoffeeUITheme
import com.njm.coffeeui.ui.theme.Orange600

@Composable
fun CoffeeCard(
    coffee: Coffee,
    modifier: Modifier = Modifier,
    menuViewModel: MenuViewModel?
) {

    var itemCounter by remember { mutableIntStateOf(value = menuViewModel?.getAmountSelected(coffee) ?: 0) }
    val wasReset = menuViewModel?.resetLiveData?.observeAsState()
    val context = LocalContext.current


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(150.dp)
            .wrapContentHeight()
    ) {
        if (coffee.image == "not_coffee") {
            Image(
                painter = painterResource(id = R.drawable.baseline_do_not_available_24),
                contentDescription = "not coffee",
                modifier = Modifier
                    .clip(CircleShape)
                    .height(150.dp)
                    .width(80.dp),
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coffee.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.baseline_downloading_24),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(150.dp)
                    .width(80.dp),
            )
        }
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = coffee.title,
                color = Orange600,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (coffee.image == "not_coffee") "" else "$ ${coffee.price}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
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
                    .size(width = 20.dp, height = 20.dp)
                    .clickable {
                        if (coffee.code == "000") {
                            menuViewModel?.resetCoffeeOrder()
                            Toast
                                .makeText(context, "Reset coffee list", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            itemCounter++
                            menuViewModel?.setItemToList(coffee)
                        }
                    },
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (coffee.code == "000"){
                        Icon(Icons.Filled.Close, contentDescription = "clear cart")
                    }else{
                        Icon(Icons.Filled.Add, contentDescription = "Add item")
                    }
                }
            }
            if (itemCounter > 0 && wasReset?.value == false) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = itemCounter.toString(),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .size(width = 20.dp, height = 20.dp)
                        .clickable {
                            itemCounter--
                            menuViewModel?.removeItemFromList(coffee)
                        },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                            contentDescription = "Add item"
                        )
                    }
                }
            }
        }

    }
}

@Preview()
@Composable
fun CoffeeCardPreview(){
    CoffeeUITheme {
        CoffeeCard(
            coffee = Coffee(
                image = "https://static.vecteezy.com/system/resources/previews/011/048/029/non_2x/iced-coffee-watercolor-set-free-png.png",
                imageBackground = "",
                title = "Bali Caramel Latte",
                price = "4.95",
                code = ""
            ),
            menuViewModel = null
        )
    }
}