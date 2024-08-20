package com.jozsefmolnar.bankingapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jozsefmolnar.bankingapp.data.createDataStore
import com.jozsefmolnar.bankingapp.features.accountopening.AccountOpeningViewModel
import com.jozsefmolnar.bankingapp.features.atmfinder.AtmFinderViewModel
import com.jozsefmolnar.bankingapp.features.bankchanger.BankChangerViewModel
import com.jozsefmolnar.bankingapp.features.dashboard.DashboardViewModel
import com.jozsefmolnar.bankingapp.features.extras.ExtrasViewModel
import com.jozsefmolnar.bankingapp.features.home.HomeViewModel
import com.jozsefmolnar.bankingapp.features.login.LoginViewModel
import com.jozsefmolnar.bankingapp.features.pin.PinViewModel
import com.jozsefmolnar.bankingapp.features.products.ProductsViewModel
import com.jozsefmolnar.bankingapp.features.profile.ProfileViewModel
import com.jozsefmolnar.bankingapp.features.transfer.NewTransferViewModel
import com.jozsefmolnar.bankingapp.features.transfer.SignTransferViewModel
import com.jozsefmolnar.bankingapp.features.transfer.SuccessTransferViewModel
import com.jozsefmolnar.bankingapp.features.welcome.WelcomeViewModel
import com.jozsefmolnar.bankingapp.repository.AtmFinderRepository
import com.jozsefmolnar.bankingapp.repository.AuthenticationRepository
import com.jozsefmolnar.bankingapp.repository.ConfigRepository
import com.jozsefmolnar.bankingapp.repository.TransferRepository
import com.jozsefmolnar.bankingapp.repository.integration.DefaultAtmFinderRepository
import com.jozsefmolnar.bankingapp.repository.integration.DefaultAuthenticationRepository
import com.jozsefmolnar.bankingapp.repository.integration.DefaultConfigRepository
import com.jozsefmolnar.bankingapp.repository.integration.DefaultTransferRepository
import com.jozsefmolnar.bankingapp.service.BankingService
import com.jozsefmolnar.bankingapp.service.createBankingService
import de.jensklingenberg.ktorfit.Ktorfit
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.permissions.PermissionsController
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

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
