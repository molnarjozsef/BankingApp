package features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.BankingRepository

class DashboardViewModel(
    repository: BankingRepository,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : ViewModel() {
    val amount = firebaseFirestore.collection("users")
        .document(firebaseAuth.currentUser?.email ?: error("no current user"))
        .snapshots
        .map { it.get<Int>("amount") }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val currentBank = repository.getCurrentBank()

    fun startTransferToEmail(recipientEmail: String) {
        val currentUserEmail = firebaseAuth.currentUser?.email ?: return

        viewModelScope.launch {
            val currentAmount = firebaseFirestore.collection("users")
                .document(currentUserEmail)
                .get()
                .get<Int>("amount")
            val recipientDocument = firebaseFirestore.collection("users")
                .document(recipientEmail)
                .get()
            if (currentAmount >= 100 && recipientDocument.exists) {
                firebaseFirestore.collection("users")
                    .document(currentUserEmail)
                    .set(mapOf("amount" to currentAmount - 100))
                firebaseFirestore.collection("users")
                    .document(recipientEmail)
                    .set(mapOf("amount" to recipientDocument.get<Int>("amount") + 100))
            }
        }
    }
}
