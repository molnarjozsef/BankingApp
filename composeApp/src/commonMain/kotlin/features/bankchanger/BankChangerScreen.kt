package features.bankchanger

import BankConfig
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import components.HorizontalCardButton
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import theme.dp16

@Composable
fun BankChangerScreen() {
    val viewModel = koinViewModel<BankChangerViewModel>()
    val currentBank by viewModel.currentBank.collectAsState()

    BankChangerScreenContent(
        currentBank = currentBank,
        setCurrentBank = viewModel::setCurrentBank,
    )
}


@Composable
fun BankChangerScreenContent(
    currentBank: BankConfig,
    setCurrentBank: (BankConfig) -> Unit,
) {
    Scaffold(
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(all = dp16),
            verticalArrangement = Arrangement.spacedBy(dp16),
        ) {
            BankConfig.entries.forEach { bank ->
                HorizontalCardButton(
                    text = bank.bankName,
                    icon = painterResource(bank.iconRes),
                    onClick = { setCurrentBank(bank) }
                )
            }
        }
    }
}
