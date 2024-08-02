package di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.permissions.PermissionsController
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
import org.koin.compose.viewmodel.dsl.viewModelOf
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
    viewModelOf(::LoginViewModel)
    viewModel { (biometryAuthenticator: BiometryAuthenticator) ->
        PinViewModel(
            biometryAuthenticator = biometryAuthenticator
        )
    }
    viewModelOf(::HomeViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::ExtrasViewModel)
    viewModel { (permissionsController: PermissionsController) ->
        AtmFinderViewModel(
            permissionsController = permissionsController,
            repository = get()
        )
    }
    viewModelOf(::BankChangerViewModel)
}

val sharedModule = module {
    includes(
        networkModule,
        dataModule,
        viewModelModule,
    )
}
