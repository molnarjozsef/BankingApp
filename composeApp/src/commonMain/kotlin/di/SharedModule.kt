package di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.icerock.moko.biometry.BiometryAuthenticator
import features.atmfinder.AtmFinderViewModel
import features.dashboard.DashboardViewModel
import features.pin.PinViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repository.BankingRepository
import repository.DefaultBankingRepository
import service.BankingService
import service.createBankingService

val sharedModule = module {
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
    single<BankingRepository> {
        DefaultBankingRepository(
            bankingService = get()
        )
    }

    viewModel {
        DashboardViewModel()
    }
    viewModel { (biometryAuthenticator: BiometryAuthenticator) ->
        PinViewModel(
            biometryAuthenticator = biometryAuthenticator
        )
    }
    viewModel {
        AtmFinderViewModel(
            bankingRepository = get()
        )
    }
}
