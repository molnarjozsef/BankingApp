package features.atmfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AtmFinderViewModel : ViewModel() {
    val atms = MutableStateFlow(listOf("bank1", "bank2"))

    private val client = HttpClient()

    init {
        viewModelScope.launch {
            atms.value = listOf(getAtmsData())
        }
    }

    suspend fun getAtmsData(): String {
        val response = client.get(
            "https://www.overpass-api.de/api/interpreter?data=[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;"
        )
        return response.bodyAsText()
    }
}
