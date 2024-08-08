package features.accountopening

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AccountOpeningViewModel(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
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
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password)
                firebaseFirestore
                    .collection("users")
                    .document(email)
                    .set(mapOf("amount" to 10000))
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
