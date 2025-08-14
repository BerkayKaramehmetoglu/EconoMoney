package com.example.economoney.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.economoney.data.entity.coins.Coins
import com.example.economoney.data.repository.EconoMoneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendViewModel @Inject constructor(
    var econoMoneyRepo: EconoMoneyRepo,
) : ViewModel() {

    private val _trendCoinsList = mutableStateOf<List<Coins>>(emptyList())
    val trendCoinsList: State<List<Coins>> = _trendCoinsList

    init {
        getTrendCoins(limit = 100, time = "24h")
    }

    fun getTrendCoins(limit: Int, time: String) {
        try {
            viewModelScope.launch {
                val trendCoins = econoMoneyRepo.getTrendCoins(limit, time)
                if (trendCoins.status == "success") {
                    _trendCoinsList.value = trendCoins.data.coins
                } else {
                    println("Data not loading")
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}