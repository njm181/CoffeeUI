package com.njm.coffeeui.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "image_background") val imageBackground: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "amount") val amount: Int,
)
