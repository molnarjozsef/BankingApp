package features.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import repository.BankingRepository

class LoginViewModel(
    repository: BankingRepository,
    preferences: DataStore<Preferences>,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()
}
