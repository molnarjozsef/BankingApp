package repository.integration

import components.GpsPosition
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.domain.Atm
import repository.AtmFinderRepository
import service.BankingService
import kotlin.math.PI
import kotlin.math.cos

class DefaultAtmFinderRepository(
    private val bankingService: BankingService,
) : AtmFinderRepository {

    private val atms = MutableStateFlow<List<Atm>?>(null)
    private val lastLocation = MutableStateFlow<GpsPosition?>(null)

    override fun getAtms() = atms.asStateFlow()

    override suspend fun fetchAtmsIfNeeded(location: GpsPosition) {
        val shouldFetchForNewLocation = lastLocation.value?.let {
            it.distanceToInMeters(location) > DistanceThresholdMeters
        } ?: true

        if (atms.value == null || shouldFetchForNewLocation) {
            val boundingBox = getBoundingBox(location, 1000.0)
            val response = bankingService.getAtms(data = getAtmQueryData(boundingBox))

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

            lastLocation.value = location
            atms.value = fetchedAtms
        }
    }

    private fun getAtmQueryData(boundingBox: BoundingBox) =
        "[out:json];node[amenity=atm](${boundingBox.south},${boundingBox.west}," +
                "${boundingBox.north},${boundingBox.east});out%20meta;"

    private fun mapOsmToBoolean(osmBoolean: String?) = when (osmBoolean) {
        "yes" -> true
        "no" -> false
        else -> null
    }

    private fun getBoundingBox(
        coordinate: GpsPosition,
        radiusMeters: Double,
    ): BoundingBox {
        val earthRadius = 6378137.0 // Earth's radius in meters

        val deltaLatitude = radiusMeters / earthRadius
        val deltaLongitude = radiusMeters / (earthRadius * cos(coordinate.latitude * PI / 180))

        val north = coordinate.latitude + deltaLatitude * 180 / PI
        val south = coordinate.latitude - deltaLatitude * 180 / PI
        val east = coordinate.longitude + deltaLongitude * 180 / PI
        val west = coordinate.longitude - deltaLongitude * 180 / PI

        return BoundingBox(
            north = north,
            east = east,
            south = south,
            west = west
        )
    }

    companion object {
        data class BoundingBox(
            val north: Double,
            val east: Double,
            val south: Double,
            val west: Double,
        )
    }
}

private const val DistanceThresholdMeters = 100
