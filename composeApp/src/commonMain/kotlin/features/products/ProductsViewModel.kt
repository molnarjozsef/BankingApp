package features.products

import androidx.lifecycle.ViewModel
import repository.ConfigRepository

class ProductsViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
