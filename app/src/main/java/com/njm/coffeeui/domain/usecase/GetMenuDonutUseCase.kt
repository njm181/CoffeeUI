package com.njm.coffeeui.domain.usecase

import com.njm.coffeeui.data.ProviderMenuData
import com.njm.coffeeui.domain.model.Donut
import javax.inject.Inject

class GetMenuDonutUseCase @Inject constructor(private val providerMenuData: ProviderMenuData) {

    operator fun invoke(): List<Donut> {
        return providerMenuData.donutsList
    }
}