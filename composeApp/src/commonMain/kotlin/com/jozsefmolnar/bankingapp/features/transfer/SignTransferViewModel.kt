package com.jozsefmolnar.bankingapp.features.transfer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.jozsefmolnar.bankingapp.repository.AuthenticationRepository
import com.jozsefmolnar.bankingapp.repository.TransferRepository
import com.jozsefmolnar.bankingapp.repository.TransferRepository.TransferMoneyResult

class SignTransferViewModel(
    private val transferRepository: TransferRepository,
    authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    private val _transferSuccessEvents = MutableSharedFlow<Any?>()
    val transferSuccessEvents = _transferSuccessEvents.asSharedFlow()

    var error by mutableStateOf<TransferMoneyResult.TransferMoneyError?>(null)
        private set

    val recipientEmail = transferRepository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val amount = transferRepository.getTransferAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val userEmail = authenticationRepository.getUserEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun startTransferToEmail(
        recipientEmail: String,
        amount: Int,
    ) {

        viewModelScope.launch {
            val result = transferRepository.transferMoney(recipientEmail = recipientEmail, amount = amount)
            when (result) {
                TransferMoneyResult.Success -> _transferSuccessEvents.emit(Any())
                is TransferMoneyResult.TransferMoneyError -> error = result
            }
        }
    }
}
