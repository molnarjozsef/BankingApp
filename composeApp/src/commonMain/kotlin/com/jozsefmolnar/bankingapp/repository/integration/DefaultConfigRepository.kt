package com.jozsefmolnar.bankingapp.repository.integration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jozsefmolnar.bankingapp.model.domain.BankConfig
import kotlinx.coroutines.flow.map
import com.jozsefmolnar.bankingapp.repository.ConfigRepository

class DefaultConfigRepository(
    private val dataStore: DataStore<Preferences>,
) : ConfigRepository {

    override fun getCurrentBank() = dataStore.data.map { data ->
        val currentBankName = data[bankPreferencesKey]
        val currentBank = BankConfig.entries.firstOrNull { bank -> bank.name == currentBankName }

        currentBank ?: BankConfig.Otp
    }

    override suspend fun setCurrentBank(bank: BankConfig) {
        dataStore.edit {
            it[bankPreferencesKey] = bank.name
        }
    }
}

private val bankPreferencesKey = stringPreferencesKey("bank")
