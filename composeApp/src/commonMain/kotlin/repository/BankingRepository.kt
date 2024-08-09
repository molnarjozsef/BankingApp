package repository

import BankConfig
import components.GpsPosition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import model.domain.Atm

interface BankingRepository {
    fun getAtms(): StateFlow<List<Atm>?>

    fun getCurrentBank(): Flow<BankConfig>

    suspend fun fetchAtmsIfNeeded(location: GpsPosition)

    suspend fun setCurrentBank(bank: BankConfig)
    fun getTransferRecipientEmail(): StateFlow<String?>
    fun setTransferRecipientEmail(recipientEmail: String)
    fun getTransferAmount(): StateFlow<Int?>
    fun setTransferAmount(amount: Int)
    suspend fun createAccount(email: String, password: String)
    fun getCurrentAmount(): Flow<Int>
    suspend fun transferMoney(recipientEmail: String, amount: Int): TransferMoneyResult
    fun getUserEmail(): Flow<String?>
}
