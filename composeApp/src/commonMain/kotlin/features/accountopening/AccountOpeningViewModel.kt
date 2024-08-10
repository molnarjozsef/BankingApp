package features.accountopening

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

class AccountOpeningViewModel(
    private val authenticationRepository: AuthenticationRepository,
    configRepository: ConfigRepository,
) : ViewModel() {

    private val _accountOpeningSuccessfulEvents = MutableSharedFlow<Any>()
    val accountOpeningSuccessfulEvents = _accountOpeningSuccessfulEvents.asSharedFlow()

    val currentBank = configRepository.getCurrentBank()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    var isLoading: Boolean by mutableStateOf(false)
        private set

    var error: String? by mutableStateOf(null)
        private set

    fun openAccount(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                authenticationRepository.createAccount(
                    email = email,
                    password = password
                )
                _accountOpeningSuccessfulEvents.emit(Any())
            } catch (e: Exception) {
                println("Exception in openAccount: $e")
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}
