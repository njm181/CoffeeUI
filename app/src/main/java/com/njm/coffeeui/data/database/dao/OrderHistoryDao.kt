package com.njm.coffeeui.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.njm.coffeeui.data.database.entities.PurchaseOrderHistoryEntity

@Dao
interface OrderHistoryDao {

    @Query("SELECT * FROM purchase_order_history ORDER BY id")
    suspend fun getAllOrdersHistory(): List<PurchaseOrderHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderHistory(purchaseOrderHistoryEntity: PurchaseOrderHistoryEntity)
}