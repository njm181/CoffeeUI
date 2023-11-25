package com.njm.coffeeui.data

import com.njm.coffeeui.data.database.dao.OrderHistoryDao
import com.njm.coffeeui.data.database.entities.PurchaseOrderHistoryEntity
import com.njm.coffeeui.domain.model.OrderHistory
import com.njm.coffeeui.domain.model.toDomain
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val orderHistoryDao: OrderHistoryDao
) {

    suspend fun getAllOrdersHistory(): List<OrderHistory> {
        val response = orderHistoryDao.getAllOrdersHistory()
        val result = response.map { it.toDomain() }
        println(result)
        return result
    }

    suspend fun insertOrderHistory(purchaseOrderHistoryEntity: PurchaseOrderHistoryEntity){
        purchaseOrderHistoryEntity.orderHistory.forEach {
            println("RESULTADO FINAL ====>>>> ${it.amount}")
        }
        orderHistoryDao.insertOrderHistory(purchaseOrderHistoryEntity)

    }
}