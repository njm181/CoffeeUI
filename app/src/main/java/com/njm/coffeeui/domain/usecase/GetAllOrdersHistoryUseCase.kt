package com.njm.coffeeui.domain.usecase

import com.njm.coffeeui.data.CoffeeRepository
import com.njm.coffeeui.domain.model.MainMenu
import com.njm.coffeeui.domain.model.OrderHistory
import javax.inject.Inject

class GetAllOrdersHistoryUseCase @Inject constructor(private val repository: CoffeeRepository) {

    suspend operator fun invoke(): List<OrderHistory> {
        return repository.getAllOrdersHistory()
    }
}