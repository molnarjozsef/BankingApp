package features.transfer

import Routes.RouteHome
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.kuvik
import bankingapp.composeapp.generated.resources.kuvik_logo
import bankingapp.composeapp.generated.resources.success_transfer_back_to_dashboard
import bankingapp.composeapp.generated.resources.success_transfer_heading
import bankingapp.composeapp.generated.resources.success_transfer_title
import bankingapp.composeapp.generated.resources.transfer_instant
import bankingapp.composeapp.generated.resources.transfer_payee_email
import bankingapp.composeapp.generated.resources.transfer_payee_information
import bankingapp.composeapp.generated.resources.transfer_payer_email
import bankingapp.composeapp.generated.resources.transfer_payer_information
import components.BackButton
import components.Header
import components.MainButton
import components.TransactionTypeSection
import components.TransferProfileSection
import components.formatMoney
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp32
import theme.dp40

@Composable
fun SuccessTransferScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<SuccessTransferViewModel>()

    val recipientEmail by viewModel.recipientEmail.collectAsState(null)
    val amount by viewModel.amount.collectAsState(null)
    val userEmail by viewModel.userEmail.collectAsState(null)

    SuccessTransferScreenContent(
        navigateToHome = {
            navController.navigate(RouteHome) {
                popUpTo(RouteHome)
            }
        },
        recipientEmail = recipientEmail,
        amount = amount,
        userEmail = userEmail,
        navigateUp = navController::navigateUp,
    )
}

@Composable
fun SuccessTransferScreenContent(
    userEmail: String?,
    recipientEmail: String?,
    amount: Int?,
    navigateToHome: () -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.success_transfer_title),
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
                    text = stringResource(Res.string.success_transfer_back_to_dashboard),
                    onClick = navigateToHome,
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
            Icon(
                painter = rememberVectorPainter(Icons.Filled.CheckCircle),
                contentDescription = null,
                tint = AppTheme.colors.success,
                modifier = Modifier.size(SuccessCheckmarkSize)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(dp32))

            Text(
                text = stringResource(Res.string.success_transfer_heading),
                fontSize = 24.sp,
                color = AppTheme.colors.textDarker
            )

            Spacer(modifier = Modifier.height(dp16))

            TransactionTypeSection()

            Spacer(modifier = Modifier.height(dp16))

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

            Spacer(modifier = Modifier.height(dp40))

            Image(
                painter = painterResource(Res.drawable.kuvik_logo),
                modifier = Modifier
                    .fillMaxWidth(KuvikPaymentLogoWidthRatio)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Image(
                painter = painterResource(Res.drawable.kuvik),
                modifier = Modifier
                    .fillMaxWidth(KuvikPaymentIllustrationWidthRatio)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null,
            )
        }
    }
}

private val SuccessCheckmarkSize = 160.dp
private const val KuvikPaymentLogoWidthRatio = 0.6f
private const val KuvikPaymentIllustrationWidthRatio = 0.5f
