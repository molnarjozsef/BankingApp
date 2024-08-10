package features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import repository.AuthenticationRepository

class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    private val _loginSuccessfulEvents = MutableSharedFlow<Any>()
    val loginSuccessfulEvents = _loginSuccessfulEvents.asSharedFlow()

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
