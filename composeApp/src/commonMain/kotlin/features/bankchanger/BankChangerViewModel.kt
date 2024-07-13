package features.bankchanger

import BankConfig
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import repository.BankingRepository

class BankChangerViewModel(
    private val repository: BankingRepository,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()

    fun setCurrentBank(bank: BankConfig) {
        viewModelScope.launch {
            repository.setCurrentBank(bank)
        }
    }
}
