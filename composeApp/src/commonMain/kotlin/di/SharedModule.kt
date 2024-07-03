package di

import de.jensklingenberg.ktorfit.Ktorfit
import features.atmfinder.AtmFinderViewModel
import features.atmfinder.BankingService
import features.atmfinder.createBankingService
import features.dashboard.DashboardViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::AtmFinderViewModel)

    single<Ktorfit> { Ktorfit.Builder().build() }
    single<BankingService> { get<Ktorfit>().createBankingService()}
}
