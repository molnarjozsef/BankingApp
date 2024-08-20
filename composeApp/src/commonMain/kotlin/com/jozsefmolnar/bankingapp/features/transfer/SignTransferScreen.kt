package com.jozsefmolnar.bankingapp.features.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.sign_transfer_sign_transaction
import bankingapp.composeapp.generated.resources.sign_transfer_title
import bankingapp.composeapp.generated.resources.transfer_instant
import bankingapp.composeapp.generated.resources.transfer_payee_email
import bankingapp.composeapp.generated.resources.transfer_payee_information
import bankingapp.composeapp.generated.resources.transfer_payer_email
import bankingapp.composeapp.generated.resources.transfer_payer_information
import com.jozsefmolnar.bankingapp.Routes
import com.jozsefmolnar.bankingapp.components.BackButton
import com.jozsefmolnar.bankingapp.components.Header
import com.jozsefmolnar.bankingapp.components.MainButton
import com.jozsefmolnar.bankingapp.components.TransactionTypeSection
import com.jozsefmolnar.bankingapp.components.TransferProfileSection
import com.jozsefmolnar.bankingapp.components.formatMoney
import com.jozsefmolnar.bankingapp.repository.TransferRepository.TransferMoneyResult
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp24
import com.jozsefmolnar.bankingapp.theme.dp40
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

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
            navController.navigate(Routes.RouteSuccessTransfer) {
                popUpTo(Routes.RouteHome)
            }
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
                title = stringResource(Res.string.sign_transfer_title),
                startButton = { BackButton(navigateUp) },
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .background(AppTheme.colors.backgroundNeutral)
                    .navigationBarsPadding()
                    .padding(
                        horizontal = dp40,
                        vertical = dp24
                    )
            ) {
                MainButton(
                    text = stringResource(Res.string.sign_transfer_sign_transaction),
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
            TransactionTypeSection()

            Spacer(Modifier.height(dp16))

            recipientEmail?.let {
                Text(
                    text = recipientEmail,
                    color = AppTheme.colors.textDarker,
                    fontSize = 17.sp,
                )
            }
            Text(
                text = stringResource(Res.string.transfer_instant),
                fontSize = 12.sp,
                color = AppTheme.colors.textDarker,
            )

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
                TransferProfileSection(
                    sectionTitle = stringResource(Res.string.transfer_payee_information),
                    contentTitle = stringResource(Res.string.transfer_payee_email),
                    contentDescription = recipientEmail,
                )
            }

            userEmail?.let {
                Spacer(modifier = Modifier.height(dp40))
                TransferProfileSection(
                    sectionTitle = stringResource(Res.string.transfer_payer_information),
                    contentTitle = stringResource(Res.string.transfer_payer_email),
                    contentDescription = userEmail,
                )
            }

        }
    }
}

