package features.dashboard

import androidx.lifecycle.ViewModel
import repository.BankingRepository

class DashboardViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val money = "48 000 Ft"

    val currentBank = repository.getCurrentBank()
}
