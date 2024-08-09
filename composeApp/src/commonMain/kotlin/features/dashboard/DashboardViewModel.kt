package features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import repository.BankingRepository

class DashboardViewModel(
    private val repository: BankingRepository,
) : ViewModel() {

    val amount = repository.getCurrentAmount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val currentBank = repository.getCurrentBank()

    fun setRecipientEmail(email: String) {
        repository.setTransferRecipientEmail(email)
    }
}
