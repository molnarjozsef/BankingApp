package features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.AuthenticationRepository
import repository.ConfigRepository

class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
    configRepository: ConfigRepository,
) : ViewModel() {

    private val _loginSuccessfulEvents = MutableSharedFlow<Any>()
    val loginSuccessfulEvents = _loginSuccessfulEvents.asSharedFlow()

    val currentBank = configRepository.getCurrentBank()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    var isLoading: Boolean by mutableStateOf(false)
        private set

    var error: String? by mutableStateOf(null)
        private set

    fun login(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                authenticationRepository.login(
                    email = email,
                    password = password
                )
                _loginSuccessfulEvents.emit(Any())
            } catch (e: Exception) {
                println("Exception in login: $e")
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}
