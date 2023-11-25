package com.njm.coffeeui.domain.usecase

import com.njm.coffeeui.data.ProviderMenuData
import com.njm.coffeeui.domain.model.Drink
import javax.inject.Inject

class GetMenuDrinkUseCase @Inject constructor(private val providerMenuData: ProviderMenuData) {

    operator fun invoke(): List<Drink> {
        return providerMenuData.drinksList
    }
}