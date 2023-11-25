package com.njm.coffeeui.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.njm.coffeeui.domain.model.MainMenu
import com.njm.coffeeui.domain.model.OrderHistory

@Entity(tableName = "purchase_order_history")
data class PurchaseOrderHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "order_history") var orderHistory: List<MenuEntity>
)

fun OrderHistory.toDatabase() = PurchaseOrderHistoryEntity(orderHistory = listOrderHistory.map { it.toDatabase() })

fun MainMenu.toDatabase() = MenuEntity(image= image, imageBackground = imageBackground, title = title, price = price, code = code, amount = amount)
