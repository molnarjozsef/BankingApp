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
                    name = element.tags.brand ?: element.tags.operator ?: element.tags.name,
                    postcode = element.tags.addrPostcode,
                    city = element.tags.addrCity,
                    street = element.tags.addrStreet,
                    houseNumber = element.tags.addrHouseNumber,
                    wheelchair = mapOsmToBoolean(element.tags.wheelchair),
                    indoor = mapOsmToBoolean(element.tags.indoor),
                    cashIn = mapOsmToBoolean(element.tags.cashIn),
                )
            }

            atms.value = fetchedAtms
        }
    }

    private fun mapOsmToBoolean(osmBoolean: String?) = when (osmBoolean) {
        "yes" -> true
        "no" -> false
        else -> null
    }
}
