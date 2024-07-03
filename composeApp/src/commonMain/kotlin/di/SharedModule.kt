package di

import de.jensklingenberg.ktorfit.Ktorfit
import features.atmfinder.AtmFinderViewModel
import features.atmfinder.BankingService
import features.atmfinder.createBankingService
import features.dashboard.DashboardViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::AtmFinderViewModel)

    single<Ktorfit> {
        Ktorfit.Builder()
            .httpClient(
                HttpClient {
                    install(ContentNegotiation) {
                        json(
                            Json {
                                isLenient = true
                                ignoreUnknownKeys = true
                                explicitNulls = false
                            }
                        )
                    }
                }
            )
            .build()
    }
    single<BankingService> { get<Ktorfit>().createBankingService() }
}
