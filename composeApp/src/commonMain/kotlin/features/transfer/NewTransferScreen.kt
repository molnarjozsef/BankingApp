package features.transfer

import BankConfig
import Routes.RouteSignTransfer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_debit_account_name
import bankingapp.composeapp.generated.resources.default_currency
import bankingapp.composeapp.generated.resources.new_transfer_amount
import bankingapp.composeapp.generated.resources.new_transfer_payee
import bankingapp.composeapp.generated.resources.new_transfer_payer
import bankingapp.composeapp.generated.resources.new_transfer_sign_transaction
import bankingapp.composeapp.generated.resources.new_transfer_title
import components.BackButton
import components.Header
import components.MainButton
import components.TextField
import components.formatMoney
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.DefaultCardElevation
import theme.dp16
import theme.dp24
import theme.dp32
import theme.dp4
import theme.dp8

@Composable
fun NewTransferScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<NewTransferViewModel>()

    val currentAmount by viewModel.currentAmount.collectAsState()
    val currentBank by viewModel.currentBank.collectAsState(null)
    val recipientEmail by viewModel.recipientEmail.collectAsState()

    NewTransferScreenContent(
        currentBank = currentBank,
        onContinueClick = { amount ->
            viewModel.setTransferAmount(amount)
            navController.navigate(RouteSignTransfer)
        },
        recipientEmail = recipientEmail,
        currentAmount = currentAmount,
        navigateUp = navController::navigateUp,
    )
}

@Composable
fun NewTransferScreenContent(
    currentBank: BankConfig?,
    currentAmount: Int?,
    recipientEmail: String?,
    onContinueClick: (Int) -> Unit,
    navigateUp: () -> Unit,
) {
    var amount by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.new_transfer_title),
                startButton = { BackButton(navigateUp) }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(dp16)
        ) {
            TransferParticipantsSection(
                currentBank = currentBank,
                currentAmount = currentAmount,
                recipientEmail = recipientEmail
            )

            Spacer(Modifier.height(dp16))

            TextField(
                title = stringResource(Res.string.new_transfer_amount),
                value = amount.toString().dropWhile { it == '0' },
                onValueChange = { newValue ->
                    amount = newValue
                        .filter { char -> char.isDigit() }
                        .ifEmpty { "0" }
                        .toInt()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                suffix = stringResource(Res.string.default_currency)
            )
            Spacer(Modifier.height(dp16))
            Spacer(Modifier.weight(1f))
            MainButton(
                modifier = Modifier
                    .padding(horizontal = dp24)
                    .padding(bottom = dp8),
                text = stringResource(Res.string.new_transfer_sign_transaction),
                onClick = { onContinueClick(amount) },
            )
        }
    }
}

@Composable
private fun TransferParticipantsSection(
    currentBank: BankConfig?,
    currentAmount: Int?,
    recipientEmail: String?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        PayerCard(
            currentBank = currentBank,
            currentAmount = currentAmount,
            modifier = Modifier.weight(2f)
        )
        Icon(
            painter = rememberVectorPainter(Icons.AutoMirrored.Rounded.ArrowForward),
            tint = AppTheme.colors.textDark,
            contentDescription = null,
            modifier = Modifier.size(dp32).weight(1f),
        )
        PayeeCard(recipientEmail, Modifier.weight(2f))
    }
}

@Composable
private fun PayerCard(
    currentBank: BankConfig?,
    currentAmount: Int?,
    modifier: Modifier = Modifier,
) {
    TransferParticipantCard(
        participantName = stringResource(Res.string.new_transfer_payer),
        modifier = modifier,
        color = AppTheme.colors.main,
    ) {
        Column {
            currentBank?.let {
                Text(
                    text = stringResource(Res.string.dashboard_debit_account_name, currentBank),
                    color = AppTheme.colors.textDark,
                    maxLines = 1,
                    letterSpacing = 0.sp,
                )
            }
            currentAmount?.let {
                Text(
                    text = formatMoney(currentAmount),
                    color = AppTheme.colors.textDarker,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp,
                )
            }
        }
    }
}

@Composable
private fun PayeeCard(
    recipientEmail: String?,
    modifier: Modifier = Modifier,
) {
    TransferParticipantCard(
        participantName = stringResource(Res.string.new_transfer_payee),
        modifier = modifier,
        color = AppTheme.colors.cardSilver,
    ) {
        Column {
            recipientEmail?.let {
                Text(
                    text = recipientEmail,
                    color = AppTheme.colors.textDarker,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp,
                )
            }
        }
    }
}

@Composable
private fun TransferParticipantCard(
    participantName: String,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = participantName,
            color = AppTheme.colors.textDark,
            letterSpacing = 0.sp,
            fontSize = 14.sp,
        )
        Spacer(Modifier.height(dp4))
        Card(
            modifier = Modifier.aspectRatio(0.8f),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = DefaultCardElevation),
            colors = CardDefaults.cardColors(
                containerColor = AppTheme.colors.surfaceNeutralOnColored,
            )
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                        .background(color)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .padding(dp8)
                ) {
                    content()
                }
            }
        }
    }
}
