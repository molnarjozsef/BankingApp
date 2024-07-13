package features.home

import androidx.lifecycle.ViewModel
import repository.BankingRepository

class HomeViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()
}
