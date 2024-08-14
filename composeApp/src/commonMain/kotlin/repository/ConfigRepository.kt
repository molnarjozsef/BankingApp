package repository

import BankConfig
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {

    fun getCurrentBank(): Flow<BankConfig>

    suspend fun setCurrentBank(bank: BankConfig)
}
