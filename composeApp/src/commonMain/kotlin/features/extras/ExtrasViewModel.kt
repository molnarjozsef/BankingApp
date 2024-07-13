package features.extras

import androidx.lifecycle.ViewModel
import repository.BankingRepository

class ExtrasViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()
}
