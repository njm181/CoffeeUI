package com.njm.coffeeui.presentation.viewModel

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njm.coffeeui.data.CoffeeRepository
import com.njm.coffeeui.domain.model.Coffee
import com.njm.coffeeui.domain.model.Donut
import com.njm.coffeeui.domain.model.Drink
import com.njm.coffeeui.domain.model.MainMenu
import com.njm.coffeeui.domain.model.OrderHistory
import com.njm.coffeeui.domain.usecase.GetAllOrdersHistoryUseCase
import com.njm.coffeeui.domain.usecase.InsertOrderHistoryUseCase
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val insertOrderHistoryUseCase: InsertOrderHistoryUseCase,
    private val getAllOrdersHistoryUseCase: GetAllOrdersHistoryUseCase
): ViewModel() {

    //regionMutableState
    private var drinkMutableList = mutableListOf<Drink>()
    val drinkSelectedList = drinkMutableList

    private var donutMutableList = mutableListOf<Donut>()
    val donutSelectedList = donutMutableList

    private var coffeeMutableList = mutableListOf<Coffee>()
    val coffeeSelectedList = coffeeMutableList

    private var drinkSelectedState = mutableIntStateOf(0)
    private var donutSelectedState = mutableIntStateOf(0)

    private val _coffeeSelectedMutableState: MutableState<Int> = mutableIntStateOf(0)

    private val _resetMutableLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val resetLiveData: LiveData<Boolean> = _resetMutableLiveData

    private val _orderHistoryListMutableLiveData: MutableLiveData<List<OrderHistory>> = MutableLiveData()
    val orderHistoryListLiveData: LiveData<List<OrderHistory>> = _orderHistoryListMutableLiveData
    //endregionMutableState

    //regionGet
    fun getAmountSelected(mainMenu: MainMenu): Int {
        return when (mainMenu) {
            is Donut -> {
                donutSelectedState.value = donutMutableList.filter {
                    it.code == mainMenu.code
                }.size
                return donutSelectedState.value
            }

            is Drink -> {
                drinkSelectedState.value = drinkMutableList.filter {
                    it.code == mainMenu.code
                }.size
                return drinkSelectedState.value
            }
            is Coffee -> {
                if (resetLiveData.value == true){
                    return 0
                } else {
                    _coffeeSelectedMutableState.value = coffeeMutableList.filter {
                        it.code == mainMenu.code
                    }.size
                    return _coffeeSelectedMutableState.value
                }
            }

            else -> { 0 }
        }
    }

    fun getTotalPurchase(): String {
        var totalPurchase = 0.00
        val purchaseItemsList = drinkSelectedList + donutSelectedList + coffeeSelectedList
        purchaseItemsList.forEach {
            totalPurchase += it.price.toDouble()
        }
        return String.format("%.2f", totalPurchase)
    }

    fun getShoppingList(): Map<MainMenu, Int> {
        val purchaseItemsList = drinkSelectedList + donutSelectedList + coffeeSelectedList
        return purchaseItemsList.groupingBy { it }.eachCount().filter { it.value >= 1}
    }
    //endregionGet

    //regionSet
    private fun setCoffeeSelected(coffee: Coffee){
        _coffeeSelectedMutableState.value = coffeeMutableList.filter {
            it.code == coffee.code
        }.size
    }

    fun setItemToList(mainMenu: MainMenu){
        when(mainMenu) {
            is Donut -> {
                donutMutableList.add(mainMenu)
            }
            is Drink -> {
                drinkMutableList.add(mainMenu)
            }
            is Coffee -> {
                coffeeMutableList.add(mainMenu)
                _resetMutableLiveData.value = false
                setCoffeeSelected(mainMenu)
            }
        }
    }
    //endregionSet

    //regionRemovee
    fun resetCoffeeOrder(): Int {
        _resetMutableLiveData.value = true
        _coffeeSelectedMutableState.value = 0
        coffeeMutableList.clear()
        return 0
    }

    fun removeItemFromList(mainMenu: MainMenu){
        when(mainMenu) {
            is Donut -> {
                val element = donutMutableList.find { it.code == mainMenu.code }
                if (element != null){
                    donutMutableList.remove(element)
                }
            }
            is Drink -> {
                val element = drinkMutableList.find { it.code == mainMenu.code }
                if (element != null){
                    drinkMutableList.remove(element)
                }
            }
            is Coffee -> {
                val element = coffeeMutableList.find { it.code == mainMenu.code }
                if (element != null){
                    coffeeMutableList.remove(element)
                    setCoffeeSelected(mainMenu)
                }
            }
        }
    }
    //endregionRemove

    fun savePurchaseOrder(){
        viewModelScope.launch(Dispatchers.IO) {
            val map = getShoppingList()
            val orderHistory = OrderHistory(
                id = map.toList().map { it.second }[0],
                listOrderHistory = map.toList().map {
                    MainMenu(
                        image = it.first.image,
                        imageBackground = it.first.imageBackground,
                        title = it.first.title,
                        price = it.first.price,
                        code = it.first.code,
                        amount = it.second
                        )
                    }
                )
            insertOrderHistoryUseCase.invoke(orderHistory = orderHistory)
        }
    }

    fun getOrdersHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            _orderHistoryListMutableLiveData.postValue(getAllOrdersHistoryUseCase.invoke())
            _orderHistoryListMutableLiveData.value?.forEach {
                println("GET ORDERS =====>>>> $it")
            }
        }
    }
}
