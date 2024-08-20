package com.jozsefmolnar.bankingapp.repository

import com.jozsefmolnar.bankingapp.components.GpsPosition
import com.jozsefmolnar.bankingapp.model.domain.Atm
import kotlinx.coroutines.flow.StateFlow

interface AtmFinderRepository {

    fun getAtms(): StateFlow<List<Atm>?>

    suspend fun fetchAtmsIfNeeded(location: GpsPosition)
}
