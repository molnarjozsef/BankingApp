package features.atmfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class AtmFinderViewModel(
    val bankingService: BankingService,
) : ViewModel() {
    val atms = MutableStateFlow<List<Atm>?>(null)

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        viewModelScope.launch {
            atms.value = getAtmsData()
        }
    }

    suspend fun getAtmsData(): List<Atm> {
        //val response = client.get("https://www.overpass-api.de/api/interpreter?data=[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;")

        val response = bankingService.getPosts()
        val atms = response.elements.map { element ->
            Atm(
                lat = element.lat,
                lon = element.lon,
                brand = element.tags.brand
            )
        }
        return atms
    }
}

interface BankingService {

    @GET("https://www.overpass-api.de/api/interpreter")
    suspend fun getPosts(
        @Query(
            "data",
            encoded = true
        ) data: String = "[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;",
    ): AtmsResponse
}

data class Atm(
    val lat: Double,
    val lon: Double,
    val brand: String?,
)

@Serializable
data class AtmsResponse(
    val elements: List<Element>,
)

@Serializable
data class Element(
    val type: String,
    val id: Long,
    val lat: Double,
    val lon: Double,
    val timestamp: String,
    val version: Long,
    val changeset: Long,
    val user: String,
    val uid: Long,
    val tags: Tags,
)

@Serializable
data class Tags(
    val amenity: String,
    val brand: String?,
)
