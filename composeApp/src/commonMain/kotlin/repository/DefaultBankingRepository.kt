package repository

import BankConfig
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import components.GpsPosition
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import model.domain.Atm
import service.BankingService
import kotlin.math.PI
import kotlin.math.cos

class DefaultBankingRepository(
    private val bankingService: BankingService,
    private val dataStore: DataStore<Preferences>,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : BankingRepository {
    private val transferRecipientEmail = MutableStateFlow<String?>(null)
    private val transferAmount = MutableStateFlow<Int?>(null)

    private val atms = MutableStateFlow<List<Atm>?>(null)
    private val lastLocation = MutableStateFlow<GpsPosition?>(null)

    override fun getTransferRecipientEmail() = transferRecipientEmail.asStateFlow()
    override fun setTransferRecipientEmail(recipientEmail: String) {
        transferRecipientEmail.value = recipientEmail
    }

    override fun getUserEmail() = firebaseAuth.authStateChanged.map { it?.email }

    override fun getTransferAmount() = transferAmount.asStateFlow()
    override fun setTransferAmount(amount: Int) {
        transferAmount.value = amount
    }

    override fun getCurrentAmount() = firebaseFirestore.collection("users")
        .document(firebaseAuth.currentUser?.email ?: error("no current user"))
        .snapshots
        .map { it.get<Int>("amount") }

    override fun getAtms() = atms.asStateFlow()

    override fun getCurrentBank() = dataStore.data.map { data ->
        val currentBankName = data[bankPreferencesKey]
        val currentBank = BankConfig.entries.firstOrNull { bank -> bank.name == currentBankName }

        currentBank ?: BankConfig.Otp
    }

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

    override suspend fun transferMoney(
        recipientEmail: String,
        amount: Int,
    ): TransferMoneyResult {
        val currentUserEmail = firebaseAuth.currentUser?.email!!

        val currentAmount = firebaseFirestore.collection("users")
            .document(currentUserEmail)
            .get()
            .get<Int>("amount")

        val recipientDocument = firebaseFirestore.collection("users")
            .document(recipientEmail)
            .get()

        return when {
            currentAmount < amount -> TransferMoneyResult.InsufficientFunds
            !recipientDocument.exists -> TransferMoneyResult.RecipientNotFound
            else -> {
                firebaseFirestore.collection("users")
                    .document(currentUserEmail)
                    .set(mapOf("amount" to currentAmount - amount))
                firebaseFirestore.collection("users")
                    .document(recipientEmail)
                    .set(mapOf("amount" to recipientDocument.get<Int>("amount") + amount))
                TransferMoneyResult.Success
            }
        }
    }

    override suspend fun setCurrentBank(bank: BankConfig) {
        dataStore.edit {
            it[bankPreferencesKey] = bank.name
        }
    }

    override suspend fun createAccount(
        email: String,
        password: String,
    ) {
        val result = firebaseAuth.createUserWithEmailAndPassword(
            email = email,
            password = password
        )
        firebaseFirestore
            .collection("users")
            .document(email)
            .set(mapOf("amount" to AccountOpeningCampaignCredit))
    }

    private fun getAtmQueryData(boundingBox: BoundingBox) =
        "[out:json];node[amenity=atm](${boundingBox.south},${boundingBox.west}," +
                "${boundingBox.north},${boundingBox.east});out%20meta;"

    private fun mapOsmToBoolean(osmBoolean: String?) = when (osmBoolean) {
        "yes" -> true
        "no" -> false
        else -> null
    }

    data class BoundingBox(
        val north: Double,
        val east: Double,
        val south: Double,
        val west: Double,
    )

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

        return BoundingBox(north, east, south, west)
    }
}

private const val DistanceThresholdMeters = 100
private val bankPreferencesKey = stringPreferencesKey("bank")
private const val AccountOpeningCampaignCredit = 30_000

sealed interface TransferMoneyResult {
    sealed interface TransferMoneyError : TransferMoneyResult
    data object Success : TransferMoneyResult
    data object InsufficientFunds : TransferMoneyError
    data object RecipientNotFound : TransferMoneyError
}
