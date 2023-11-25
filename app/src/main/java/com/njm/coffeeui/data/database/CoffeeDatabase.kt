package com.njm.coffeeui.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.njm.coffeeui.data.database.dao.OrderHistoryDao
import com.njm.coffeeui.data.database.entities.MenuEntity
import com.njm.coffeeui.data.database.entities.PurchaseOrderHistoryEntity
import com.njm.coffeeui.data.database.utils.ListConverter

@Database(entities = [MenuEntity::class, PurchaseOrderHistoryEntity::class], version = 1)
@TypeConverters(value = [ListConverter::class])
abstract class CoffeeDatabase: RoomDatabase() {

    abstract fun getOrderHistoryDao(): OrderHistoryDao
}