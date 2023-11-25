package com.njm.coffeeui.domain.model

data class PurchaseItem(
    override val image: String,
    override val imageBackground: String,
    override val title: String,
    override val price: String,
    override val code: String,
): MainMenu(image, imageBackground, title, price, code)
