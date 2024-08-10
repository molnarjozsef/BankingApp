package features.bankchanger

import BankConfig
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import repository.ConfigRepository

class BankChangerViewModel(
    private val configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()

    fun setCurrentBank(bank: BankConfig) {
        viewModelScope.launch {
            configRepository.setCurrentBank(bank)
        }
    }
}
