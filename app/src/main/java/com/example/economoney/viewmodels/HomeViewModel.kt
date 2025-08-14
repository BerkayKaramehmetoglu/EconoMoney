package com.example.economoney.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.economoney.data.entity.coins.Coins
import com.example.economoney.data.repository.EconoMoneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var econoMoneyRepo: EconoMoneyRepo) : ViewModel() {

    private val _coinsList = mutableStateOf<List<Coins>>(emptyList())
    val coinsList: State<List<Coins>> = _coinsList

    private val _time = MutableStateFlow("24h")
    val time: StateFlow<String> = _time

    private val _orderDirection = MutableStateFlow("desc")
    val orderDirection: StateFlow<String> = _orderDirection

    private val _orderBy = MutableStateFlow("marketCap")
    val oderBy: StateFlow<String> = _orderBy

    init {
        getCoins(
            limit = 100,
            time = _time.value,
            orderDirection = _orderDirection.value,
            orderBy = _orderBy.value
        )
    }

    fun getCoins(limit: Int, time: String, orderDirection: String, orderBy: String) {
        try {
            viewModelScope.launch {
                _coinsList.value = econoMoneyRepo.getCoins(limit, time, orderDirection, orderBy)
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun setTime(newTime: String) {
        _time.value = newTime
    }

    fun setOrderDirection(newOrderDirection: String) {
        _orderDirection.value = newOrderDirection
    }

    fun setOrderBy(newOrderBy: String) {
        _orderBy.value = newOrderBy
    }
}