package features.transfer

import Routes.RouteSuccessTransfer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import components.BackButton
import components.Header
import components.MainButton
import components.formatMoney
import org.koin.compose.viewmodel.koinViewModel
import repository.TransferRepository.TransferMoneyResult
import theme.AppTheme
import theme.dp16
import theme.dp40

@Composable
fun SignTransferScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<SignTransferViewModel>()

    val recipientEmail by viewModel.recipientEmail.collectAsState(null)
    val amount by viewModel.amount.collectAsState(null)
    val userEmail by viewModel.userEmail.collectAsState(null)

    LaunchedEffect(viewModel) {
        viewModel.transferSuccessEvents.collect {
            navController.navigate(RouteSuccessTransfer)
        }
    }

    SignTransferScreenContent(
        signTransfer = {
            viewModel.startTransferToEmail(
                recipientEmail = recipientEmail!!,
                amount = amount!!,
            )
        },
        recipientEmail = recipientEmail,
        amount = amount,
        userEmail = userEmail,
        error = viewModel.error,
        navigateUp = navController::navigateUp,
    )
}

@Composable
fun SignTransferScreenContent(
    recipientEmail: String?,
    userEmail: String?,
    amount: Int?,
    error: TransferMoneyResult.TransferMoneyError?,
    signTransfer: () -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Header(
                title = "sign transfer",
                startButton = { BackButton(navigateUp) },
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(AppTheme.colors.backgroundNeutral)
                    .padding(dp16)
            ) {
                MainButton(
                    text = "sign transfer",
                    onClick = signTransfer,
                )
            }
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(dp16)
        ) {
            recipientEmail?.let {
                Text(
                    text = recipientEmail,
                    color = AppTheme.colors.textDarker,
                    fontSize = 17.sp,
                )
            }
            Spacer(modifier = Modifier.height(dp16))
            amount?.let {
                Text(
                    text = formatMoney(amount),
                    fontSize = 30.sp,
                    color = AppTheme.colors.textDarker,
                )
            }

            if (error != null) {
                Text(
                    text = error.toString(),
                    color = AppTheme.colors.error
                )
            }

            recipientEmail?.let {
                Spacer(modifier = Modifier.height(dp40))
                components.TransferProfileSection(
                    sectionTitle = "recipient",
                    contentTitle = "email",
                    contentDescription = recipientEmail,
                )
            }
            userEmail?.let {
                Spacer(modifier = Modifier.height(dp40))
                components.TransferProfileSection(
                    sectionTitle = "sender",
                    contentTitle = "source",
                    contentDescription = userEmail,
                )
            }

        }
    }
}
