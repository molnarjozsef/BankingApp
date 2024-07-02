package di

import features.dashboard.DashboardViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    viewModelOf(::DashboardViewModel)
}
