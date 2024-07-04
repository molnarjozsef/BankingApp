package repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.domain.Atm
import service.BankingService

class DefaultBankingRepository(
    private val bankingService: BankingService,
) : BankingRepository {
    private val atms = MutableStateFlow<List<Atm>?>(null)

    override fun getAtms() = atms.asStateFlow()

    override suspend fun fetchAtmsIfNeeded() {
        if (atms.value == null) {
            val response = bankingService.getPosts()

            val fetchedAtms = response.elements.map { element ->
                Atm(
                    lat = element.lat,
                    lon = element.lon,
                    brand = element.tags.brand
                )
            }

            atms.value = fetchedAtms
        }
    }
}
