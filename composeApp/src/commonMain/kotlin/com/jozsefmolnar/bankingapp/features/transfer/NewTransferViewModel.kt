package com.jozsefmolnar.bankingapp.features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import com.jozsefmolnar.bankingapp.repository.ConfigRepository
import com.jozsefmolnar.bankingapp.repository.TransferRepository

class NewTransferViewModel(
    configRepository: ConfigRepository,
    private val transferRepository: TransferRepository,
) : ViewModel() {

    val currentAmount = transferRepository.getCurrentAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val currentBank = configRepository.getCurrentBank()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val recipientEmail = transferRepository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setTransferAmount(amount: Int) {
        transferRepository.setTransferAmount(amount)
    }
}
