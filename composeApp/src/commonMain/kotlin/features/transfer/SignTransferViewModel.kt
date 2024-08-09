package features.transfer

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
import repository.BankingRepository
import repository.TransferMoneyResult

class SignTransferViewModel(
    private val repository: BankingRepository,
) : ViewModel() {

    private val _transferSuccessEvents = MutableSharedFlow<Any?>()
    val transferSuccessEvents = _transferSuccessEvents.asSharedFlow()

    var error by mutableStateOf<TransferMoneyResult.TransferMoneyError?>(null)
        private set

    val recipientEmail = repository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val amount = repository.getTransferAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val userEmail = repository.getUserEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun startTransferToEmail(
        recipientEmail: String,
        amount: Int,
    ) {

        viewModelScope.launch {
            val result = repository.transferMoney(recipientEmail = recipientEmail, amount = amount)
            when (result) {
                TransferMoneyResult.Success -> _transferSuccessEvents.emit(Any())
                is TransferMoneyResult.TransferMoneyError -> error = result
            }
        }
    }
}
