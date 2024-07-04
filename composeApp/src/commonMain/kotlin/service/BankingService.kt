package service

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import model.api.AtmsResponse

interface BankingService {

    @GET("https://www.overpass-api.de/api/interpreter")
    suspend fun getPosts(
        @Query(
            "data",
            encoded = true
        ) data: String = "[out:json];node[amenity=atm](47.322544031913196,18.81065368652344,47.65291006548286,19.35379028320313);out%20meta;",
    ): AtmsResponse
}
