package features.transfer

import Routes.RouteHome
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.kuvik
import components.BackButton
import components.Header
import components.MainButton
import components.formatMoney
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
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
                    text = "back to home",
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
            Spacer(modifier = Modifier.height(dp40))

            Image(
                painter = painterResource(Res.drawable.kuvik),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(KuvikPaymentLogoWidth),
                contentDescription = null,
            )
        }
    }
}

private val SuccessCheckmarkSize = 160.dp
private val KuvikPaymentLogoWidth = 160.dp
