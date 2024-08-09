package features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.BankingRepository

class SuccessTransferViewModel(
    bankingRepository: BankingRepository,
) : ViewModel() {

    val userEmail = bankingRepository.getUserEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val recipientEmail = bankingRepository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val amount = bankingRepository.getTransferAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
