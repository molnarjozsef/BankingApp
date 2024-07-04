package features.atmfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import repository.BankingRepository

class AtmFinderViewModel(
    bankingRepository: BankingRepository,
) : ViewModel() {
    val atms = bankingRepository.getAtms()

    init {
        viewModelScope.launch {
            bankingRepository.fetchAtmsIfNeeded()
        }
    }
}
