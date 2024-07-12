package features.atmfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import repository.BankingRepository

class AtmFinderViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val atms = repository.getAtms()

    init {
        viewModelScope.launch {
            repository.fetchAtmsIfNeeded()
        }
    }
}
