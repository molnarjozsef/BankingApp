package features.login

import androidx.lifecycle.ViewModel
import repository.BankingRepository

class LoginViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()
}
