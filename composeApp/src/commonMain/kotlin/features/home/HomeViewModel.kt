package features.home

import androidx.lifecycle.ViewModel
import repository.ConfigRepository

class HomeViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
