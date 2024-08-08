package features.bankchanger

import BankConfig
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.bank_changer_back_button
import bankingapp.composeapp.generated.resources.bank_changer_title
import components.BackButton
import components.Header
import components.HorizontalCardButton
import components.MainButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16

@Composable
fun BankChangerScreen(navController: NavHostController) {
    val viewModel = koinViewModel<BankChangerViewModel>()
    val currentBank by viewModel.currentBank.collectAsState(null)

    BankChangerScreenContent(
        navigateUp = navController::navigateUp,
        currentBank = currentBank,
        setCurrentBank = viewModel::setCurrentBank,
    )
}


@Composable
fun BankChangerScreenContent(
    currentBank: BankConfig?,
    setCurrentBank: (BankConfig) -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.bank_changer_title),
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(all = dp16),
        ) {
            BankConfig.entries.forEach { bank ->
                HorizontalCardButton(
                    text = bank.bankName,
                    icon = painterResource(bank.iconRes),
                    onClick = { setCurrentBank(bank) },
                    endContent = {
                        RadioButton(
                            selected = bank == currentBank,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = AppTheme.colors.main,
                                unselectedColor = AppTheme.colors.radioButtonUnselected
                            )
                        )
                    }
                )
                Spacer(Modifier.height(dp16))
            }

            Spacer(Modifier.weight(1f))

            MainButton(
                text = stringResource(Res.string.bank_changer_back_button),
                onClick = navigateUp,
            )
        }
    }
}
