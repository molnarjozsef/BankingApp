package repository

import BankConfig
import components.GpsPosition
import kotlinx.coroutines.flow.StateFlow
import model.domain.Atm

interface BankingRepository {
    fun getAtms(): StateFlow<List<Atm>?>

    fun getCurrentBank(): StateFlow<BankConfig>

    suspend fun fetchAtmsIfNeeded(location: GpsPosition)

    suspend fun setCurrentBank(bank: BankConfig)
}
