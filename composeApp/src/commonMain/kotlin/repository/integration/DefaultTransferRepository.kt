package repository.integration

import AppConstants.AmountFieldKey
import AppConstants.UsersCollection
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import repository.TransferRepository
import repository.TransferRepository.TransferMoneyResult

class DefaultTransferRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : TransferRepository {

    private val transferRecipientEmail = MutableStateFlow<String?>(null)
    private val transferAmount = MutableStateFlow<Int?>(null)

    override fun getCurrentAmount() = firebaseFirestore.collection(UsersCollection)
        .document(firebaseAuth.currentUser?.email ?: error("no current user"))
        .snapshots
        .map { it.get<Int>(AmountFieldKey) }

    override fun setTransferRecipientEmail(recipientEmail: String) {
        transferRecipientEmail.value = recipientEmail
    }

    override fun getTransferRecipientEmail() = transferRecipientEmail.asStateFlow()

    override fun setTransferAmount(amount: Int) {
        transferAmount.value = amount
    }

    override fun getTransferAmount() = transferAmount.asStateFlow()

    override suspend fun transferMoney(
        recipientEmail: String,
        amount: Int,
    ): TransferMoneyResult {
        val currentUserEmail = firebaseAuth.currentUser?.email!!

        val currentAmount = firebaseFirestore.collection(UsersCollection)
            .document(currentUserEmail)
            .get()
            .get<Int>(AmountFieldKey)

        val recipientDocument = firebaseFirestore.collection(UsersCollection)
            .document(recipientEmail)
            .get()

        return when {
            currentAmount < amount -> TransferMoneyResult.InsufficientFunds
            !recipientDocument.exists -> TransferMoneyResult.RecipientNotFound
            else -> {
                firebaseFirestore.collection(UsersCollection)
                    .document(currentUserEmail)
                    .set(mapOf(AmountFieldKey to currentAmount - amount))
                firebaseFirestore.collection(UsersCollection)
                    .document(recipientEmail)
                    .set(mapOf(AmountFieldKey to recipientDocument.get<Int>(AmountFieldKey) + amount))
                TransferMoneyResult.Success
            }
        }
    }
}


