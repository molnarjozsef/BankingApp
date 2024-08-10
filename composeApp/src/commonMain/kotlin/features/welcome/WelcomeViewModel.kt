package features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import repository.AuthenticationRepository
import repository.ConfigRepository

class WelcomeViewModel(
    configRepository: ConfigRepository,
    authenticationRepository: AuthenticationRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()

    val isLoggedIn = authenticationRepository.getCurrentUser()
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
