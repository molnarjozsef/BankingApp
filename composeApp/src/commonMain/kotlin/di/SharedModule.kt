package di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.icerock.moko.biometry.BiometryAuthenticator
import features.atmfinder.AtmFinderViewModel
import features.bankchanger.BankChangerViewModel
import features.dashboard.DashboardViewModel
import features.extras.ExtrasViewModel
import features.home.HomeViewModel
import features.login.LoginViewModel
import features.pin.PinViewModel
import features.products.ProductsViewModel
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

val networkModule = module {
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

val dataModule = module {
    single<BankingRepository> {
        DefaultBankingRepository(
            bankingService = get()
        )
    }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(repository = get())
    }
    viewModel { (biometryAuthenticator: BiometryAuthenticator) ->
        PinViewModel(
            biometryAuthenticator = biometryAuthenticator
        )
    }
    viewModel {
        HomeViewModel(repository = get())
    }
    viewModel {
        DashboardViewModel(repository = get())
    }
    viewModel {
        ProductsViewModel(repository = get())
    }
    viewModel {
        ExtrasViewModel(repository = get())
    }
    viewModel {
        AtmFinderViewModel(
            repository = get()
        )
    }
    viewModel {
        BankChangerViewModel(
            repository = get()
        )
    }
}

val sharedModule = module {
    includes(
        networkModule,
        dataModule,
        viewModelModule,
    )
}
