package features.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.BankingRepository

class NewTransferViewModel(
    private val repository: BankingRepository,
) : ViewModel() {

    val currentAmount = repository.getCurrentAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val currentBank = repository.getCurrentBank()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val recipientEmail = repository.getTransferRecipientEmail()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setTransferAmount(amount: Int) {
        repository.setTransferAmount(amount)
    }
}
