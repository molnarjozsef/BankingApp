package com.jozsefmolnar.bankingapp.features.bankchanger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.bankingapp.model.domain.BankConfig
import com.jozsefmolnar.bankingapp.repository.ConfigRepository
import kotlinx.coroutines.launch

class BankChangerViewModel(
    private val configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()

    fun setCurrentBank(bank: BankConfig) {
        viewModelScope.launch {
            configRepository.setCurrentBank(bank)
        }
    }
}
