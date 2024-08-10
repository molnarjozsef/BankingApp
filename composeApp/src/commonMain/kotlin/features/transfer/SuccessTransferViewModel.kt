package features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.AuthenticationRepository
import repository.TransferRepository

class SuccessTransferViewModel(
    authenticationRepository: AuthenticationRepository,
    transferRepository: TransferRepository,
) : ViewModel() {

    val userEmail = authenticationRepository.getUserEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val recipientEmail = transferRepository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val amount = transferRepository.getTransferAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
