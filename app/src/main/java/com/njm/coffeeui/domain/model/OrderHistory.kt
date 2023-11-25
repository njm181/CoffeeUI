package com.njm.coffeeui.domain.model

import com.njm.coffeeui.data.database.entities.MenuEntity
import com.njm.coffeeui.data.database.entities.PurchaseOrderHistoryEntity

data class OrderHistory(val id: Int, val listOrderHistory: List<MainMenu>)

fun PurchaseOrderHistoryEntity.toDomain() = OrderHistory(id = id, listOrderHistory = orderHistory.map { it.toDomain() })

fun List<MenuEntity>.toDomain() = this.map { it.toDomain() }

fun MenuEntity.toDomain() = MainMenu(image, imageBackground, title, price, code, amount)

