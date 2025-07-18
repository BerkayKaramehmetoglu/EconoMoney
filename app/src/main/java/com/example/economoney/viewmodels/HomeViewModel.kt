package com.example.economoney.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.economoney.data.entity.Coins
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

    var time: String
        get() = _time.value
        set(value) {
            _time.value = value
        }

    val timeFlow: StateFlow<String> = _time

    init {
        getCoins(time = _time.value)
    }

    fun getCoins(time: String) {
        viewModelScope.launch {
            _coinsList.value = econoMoneyRepo.getCoins(time)
        }
    }
}