package com.jozsefmolnar.bankingapp.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import com.jozsefmolnar.bankingapp.repository.ConfigRepository
import com.jozsefmolnar.bankingapp.repository.TransferRepository

class DashboardViewModel(
    configRepository: ConfigRepository,
    private val transferRepository: TransferRepository,
) : ViewModel() {

    val amount = transferRepository.getCurrentAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val currentBank = configRepository.getCurrentBank()

    fun setRecipientEmail(email: String) {
        transferRepository.setTransferRecipientEmail(email)
    }
}
