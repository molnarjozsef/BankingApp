package com.jozsefmolnar.bankingapp.features.home

import androidx.lifecycle.ViewModel
import com.jozsefmolnar.bankingapp.repository.ConfigRepository

class HomeViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
