package repository

import components.GpsPosition
import kotlinx.coroutines.flow.StateFlow
import model.domain.Atm

interface AtmFinderRepository {

    fun getAtms(): StateFlow<List<Atm>?>

    suspend fun fetchAtmsIfNeeded(location: GpsPosition)
}
