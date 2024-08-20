package com.jozsefmolnar.bankingapp.features.products

import androidx.lifecycle.ViewModel
import com.jozsefmolnar.bankingapp.repository.ConfigRepository

class ProductsViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
