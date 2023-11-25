package com.njm.coffeeui.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njm.coffeeui.domain.usecase.GetMenuCoffeeUseCase
import com.njm.coffeeui.domain.usecase.GetMenuDonutUseCase
import com.njm.coffeeui.domain.usecase.GetMenuDrinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getMenuCoffeeUseCase: GetMenuCoffeeUseCase,
    private val getMenuDonutUseCase: GetMenuDonutUseCase,
    private val getMenuDrinkUseCase: GetMenuDrinkUseCase
): ViewModel(){

    fun getCoffeeList() = getMenuCoffeeUseCase.invoke()
    fun getDrinkList() = getMenuDrinkUseCase.invoke()
    fun getDonutList() = getMenuDonutUseCase.invoke()
}