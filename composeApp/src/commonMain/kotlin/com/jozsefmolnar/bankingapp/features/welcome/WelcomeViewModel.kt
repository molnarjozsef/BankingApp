package com.jozsefmolnar.bankingapp.features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.bankingapp.repository.AuthenticationRepository
import com.jozsefmolnar.bankingapp.repository.ConfigRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WelcomeViewModel(
    configRepository: ConfigRepository,
    authenticationRepository: AuthenticationRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()

    val isLoggedIn = authenticationRepository.getCurrentUser()
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
}
