package com.example.economoney.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.economoney.data.entity.Coins
import com.example.economoney.data.repository.EconoMoneyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var econoMoneyRepo: EconoMoneyRepo) : ViewModel() {

    private val _coinsList = mutableStateOf<List<Coins>>(emptyList())
    val coinsList: State<List<Coins>> = _coinsList

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            _coinsList.value = econoMoneyRepo.getCoins()
        }
    }

}