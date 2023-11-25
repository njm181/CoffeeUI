package com.njm.coffeeui.domain.model

open class MainMenu(
    open val image: String,
    open val imageBackground: String,
    open val title: String,
    open val price: String,
    open val code: String,
    val amount: Int = 0
)
