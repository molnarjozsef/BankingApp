package features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.ConfigRepository
import repository.TransferRepository

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
