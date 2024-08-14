package features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.ConfigRepository
import repository.TransferRepository

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
