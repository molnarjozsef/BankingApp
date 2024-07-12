package features.products

import androidx.lifecycle.ViewModel
import repository.BankingRepository

class ProductsViewModel(
    repository: BankingRepository,
) : ViewModel() {
    val currentBank = repository.getCurrentBank()
}
