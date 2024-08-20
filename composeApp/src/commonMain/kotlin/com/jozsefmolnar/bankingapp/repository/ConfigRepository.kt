package com.jozsefmolnar.bankingapp.repository

import com.jozsefmolnar.bankingapp.model.domain.BankConfig
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {

    fun getCurrentBank(): Flow<BankConfig>

    suspend fun setCurrentBank(bank: BankConfig)
}
