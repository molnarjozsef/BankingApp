package com.jozsefmolnar.bankingapp.features.extras

import androidx.lifecycle.ViewModel
import com.jozsefmolnar.bankingapp.repository.ConfigRepository

class ExtrasViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
