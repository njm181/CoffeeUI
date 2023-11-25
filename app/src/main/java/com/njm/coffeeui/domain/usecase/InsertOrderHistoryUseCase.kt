package com.njm.coffeeui.domain.usecase

import com.njm.coffeeui.data.CoffeeRepository
import com.njm.coffeeui.data.database.entities.toDatabase
import com.njm.coffeeui.domain.model.OrderHistory
import javax.inject.Inject

class InsertOrderHistoryUseCase @Inject constructor(private val repository: CoffeeRepository) {

    suspend operator fun invoke(orderHistory: OrderHistory){
        repository.insertOrderHistory(purchaseOrderHistoryEntity = orderHistory.toDatabase())
    }
}