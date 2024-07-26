package service

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import model.api.AtmsResponse

interface BankingService {

    @GET("https://www.overpass-api.de/api/interpreter")
    suspend fun getAtms(
        @Query(
            "data",
            encoded = true
        ) data: String = "",
    ): AtmsResponse
}
