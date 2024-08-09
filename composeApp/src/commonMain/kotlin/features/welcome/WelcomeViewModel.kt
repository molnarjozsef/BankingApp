package features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import repository.BankingRepository

class WelcomeViewModel(
    repository: BankingRepository,
    firebaseAuth: FirebaseAuth,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()

    val isLoggedIn = firebaseAuth.authStateChanged
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
