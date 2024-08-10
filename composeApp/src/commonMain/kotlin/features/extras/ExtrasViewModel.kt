package features.extras

import androidx.lifecycle.ViewModel
import repository.ConfigRepository

class ExtrasViewModel(
    configRepository: ConfigRepository,
) : ViewModel() {
    val currentBank = configRepository.getCurrentBank()
}
