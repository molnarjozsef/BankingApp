package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import createDataStore
import de.jensklingenberg.ktorfit.Ktorfit
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.permissions.PermissionsController
import features.accountopening.AccountOpeningViewModel
import features.atmfinder.AtmFinderViewModel
import features.bankchanger.BankChangerViewModel
import features.dashboard.DashboardViewModel
import features.extras.ExtrasViewModel
import features.home.HomeViewModel
import features.login.LoginViewModel
import features.pin.PinViewModel
import features.products.ProductsViewModel
import features.profile.ProfileViewModel
import features.transfer.NewTransferViewModel
import features.transfer.SignTransferViewModel
import features.transfer.SuccessTransferViewModel
import features.welcome.WelcomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import repository.AtmFinderRepository
import repository.AuthenticationRepository
import repository.ConfigRepository
import repository.integration.DefaultAtmFinderRepository
import repository.integration.DefaultAuthenticationRepository
import repository.integration.DefaultConfigRepository
import repository.integration.DefaultTransferRepository
import repository.TransferRepository
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
    single<FirebaseAuth> {
        Firebase.auth
    }
    single<FirebaseFirestore> {
        Firebase.firestore
    }
}

val dataModule = module {
    singleOf(::DefaultAtmFinderRepository)
    singleOf(::DefaultAuthenticationRepository)
    singleOf(::DefaultConfigRepository)
    singleOf(::DefaultTransferRepository)
    single<ConfigRepository> { get<DefaultConfigRepository>() }
    single<AtmFinderRepository> { get<DefaultAtmFinderRepository>() }
    single<AuthenticationRepository> { get<DefaultAuthenticationRepository>() }
    single<TransferRepository> { get<DefaultTransferRepository>() }

    single<DataStore<Preferences>> {
        createDataStore()
    }
}

val viewModelModule = module {
    viewModelOf(::WelcomeViewModel)
    viewModel { (biometryAuthenticator: BiometryAuthenticator) ->
        PinViewModel(
            biometryAuthenticator = biometryAuthenticator
        )
    }
    viewModelOf(::AccountOpeningViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::ProductsViewModel)
    viewModelOf(::ExtrasViewModel)
    viewModel { (permissionsController: PermissionsController) ->
        AtmFinderViewModel(
            permissionsController = permissionsController,
            atmFinderRepository = get()
        )
    }
    viewModelOf(::BankChangerViewModel)
    viewModelOf(::NewTransferViewModel)
    viewModelOf(::SignTransferViewModel)
    viewModelOf(::SuccessTransferViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::LoginViewModel)
}

val sharedModule = module {
    includes(
        networkModule,
        dataModule,
        viewModelModule,
    )
}
