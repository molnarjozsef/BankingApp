package features.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import repository.AuthenticationRepository

class ProfileViewModel(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    private val _logoutSuccessfulEvents = MutableSharedFlow<Any>()
    val logoutSuccessfulEvents = _logoutSuccessfulEvents.asSharedFlow()

    var isLoading: Boolean by mutableStateOf(false)
        private set

    var error: String? by mutableStateOf(null)
        private set

    fun logout(
    ) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                authenticationRepository.logout()
                _logoutSuccessfulEvents.emit(Any())
            } catch (e: Exception) {
                println("Exception in logout: $e")
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}
