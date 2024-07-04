package repository

import kotlinx.coroutines.flow.StateFlow
import model.domain.Atm

interface BankingRepository {
    fun getAtms(): StateFlow<List<Atm>?>

    suspend fun fetchAtmsIfNeeded()
}
