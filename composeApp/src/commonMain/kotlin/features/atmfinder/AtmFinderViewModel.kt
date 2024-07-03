package features.atmfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AtmFinderViewModel(
    val bankingService: BankingService
) : ViewModel() {
    val atms = MutableStateFlow(listOf("bank1", "bank2"))

    private val client = HttpClient()

    init {
        viewModelScope.launch {
            atms.value = listOf(getAtmsData())
        }
    }

    suspend fun getAtmsData(): String {
        //val response = client.get("https://www.overpass-api.de/api/interpreter?data=[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;")

        val response = bankingService.getPosts().toString()
        return response
    }
}

interface BankingService {

    @GET("https://www.overpass-api.de/api/interpreter")
    suspend fun getPosts(
        @Query("data", encoded = true) data: String = "[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;"
    ): Any

    @GET("pokemon/{name}")
    suspend fun getDetails(@Path("name") name: String): Any
}
