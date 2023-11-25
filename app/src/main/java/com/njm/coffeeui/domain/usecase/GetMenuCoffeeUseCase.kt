package com.njm.coffeeui.domain.usecase

import com.njm.coffeeui.data.ProviderMenuData
import com.njm.coffeeui.domain.model.Coffee
import javax.inject.Inject

class GetMenuCoffeeUseCase @Inject constructor(private val providerMenuData: ProviderMenuData) {

    operator fun invoke(): List<Coffee> {
        return providerMenuData.coffeeList
    }
}