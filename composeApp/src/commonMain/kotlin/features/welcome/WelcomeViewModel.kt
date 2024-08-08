package features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.BankingRepository

class WelcomeViewModel(
    repository: BankingRepository,
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()

    val isLoggedIn = firebaseAuth.authStateChanged
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun login() {
        viewModelScope.launch {
            firebaseAuth.createUserWithEmailAndPassword("email@google.com", "Test1234")
        }
    }
}
