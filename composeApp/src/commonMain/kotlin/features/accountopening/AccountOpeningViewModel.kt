package features.accountopening

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import repository.BankingRepository

class AccountOpeningViewModel(
    private val repository: BankingRepository,
) : ViewModel() {

    private val _accountOpeningSuccessfulEvents = MutableSharedFlow<Any>()
    val accountOpeningSuccessfulEvents = _accountOpeningSuccessfulEvents.asSharedFlow()

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
                repository.createAccount(
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
