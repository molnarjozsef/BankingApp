package repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TransferRepository {
    fun getCurrentAmount(): Flow<Int>

    fun setTransferRecipientEmail(recipientEmail: String)

    fun getTransferRecipientEmail(): StateFlow<String?>

    fun setTransferAmount(amount: Int)

    fun getTransferAmount(): StateFlow<Int?>

    suspend fun transferMoney(recipientEmail: String, amount: Int): TransferMoneyResult

    sealed interface TransferMoneyResult {
        sealed interface TransferMoneyError : TransferMoneyResult
        data object Success : TransferMoneyResult
        data object InsufficientFunds : TransferMoneyError
        data object RecipientNotFound : TransferMoneyError
    }
}

