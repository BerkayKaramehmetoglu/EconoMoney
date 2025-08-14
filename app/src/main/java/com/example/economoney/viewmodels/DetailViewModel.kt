package com.example.economoney.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.economoney.data.entity.details.DetailCoin
import com.example.economoney.data.repository.EconoMoneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var econoMoneyRepo: EconoMoneyRepo) : ViewModel() {

    private val _detailCoinsList = mutableStateOf<DetailCoin?>(null)
    val detailCoinsList: State<DetailCoin?> = _detailCoinsList

    private val _uuid = MutableStateFlow("Qwsogvtv82FCd")
    val uuid: StateFlow<String> = _uuid

    init {
        getDetailCoins(uuid = _uuid.value, time = "24h")
    }

    fun getDetailCoins(uuid: String, time: String) {
        try {
            viewModelScope.launch {
                val detailCoin = econoMoneyRepo.getDetails(uuid, time)
                if (detailCoin.status == "success") {
                    _detailCoinsList.value = detailCoin.data.coin
                } else {
                    println("Data not loading")
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}