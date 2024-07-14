package features.extras

import BankConfig
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.automirrored.outlined.SendToMobile
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Loyalty
import androidx.compose.material.icons.outlined.Tram
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.extras_available_extras
import bankingapp.composeapp.generated.resources.extras_cheque_payment
import bankingapp.composeapp.generated.resources.extras_offer_cashback
import bankingapp.composeapp.generated.resources.extras_offers
import bankingapp.composeapp.generated.resources.extras_public_transport
import bankingapp.composeapp.generated.resources.extras_public_transport_interurban
import bankingapp.composeapp.generated.resources.extras_public_transport_local
import bankingapp.composeapp.generated.resources.extras_top_up
import bankingapp.composeapp.generated.resources.extras_top_up_payment
import components.Product
import components.ProductSection
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp40

@Composable
fun ExtrasScreen() {
    val viewModel = koinViewModel<ExtrasViewModel>()
    val currentBank by viewModel.currentBank.collectAsState()

    ExtrasScreenContent(
        currentBank = currentBank
    )
}

@Composable
fun ExtrasScreenContent(
    currentBank: BankConfig,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = dp40)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = dp16),
            text = stringResource(Res.string.extras_available_extras, currentBank.bankName),
            color = AppTheme.colors.textDarker,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(Modifier.height(dp24))

        Offers(currentBank = currentBank)

        Spacer(Modifier.height(dp24))

        PublicTransport()

        Spacer(Modifier.height(dp24))

        TopUpPayment()
    }

}


@Composable
private fun Offers(
    currentBank: BankConfig,
) {
    ProductSection(
        title = stringResource(Res.string.extras_offers),
        products = listOf(
            Product(
                name = stringResource(Res.string.extras_offer_cashback, currentBank.bankName),
                icon = Icons.Outlined.Loyalty,
            )
        ),
        color = AppTheme.colors.main,
    )
}


@Composable
private fun PublicTransport() {
    ProductSection(
        title = stringResource(Res.string.extras_public_transport),
        products = listOf(
            Product(
                name = stringResource(Res.string.extras_public_transport_local),
                icon = Icons.Outlined.Tram,
            ),
            Product(
                name = stringResource(Res.string.extras_public_transport_interurban),
                icon = Icons.Outlined.DirectionsBus,
            ),
        ),
        color = AppTheme.colors.productPurple,
    )
}

@Composable
private fun TopUpPayment() {
    ProductSection(
        title = stringResource(Res.string.extras_top_up_payment),
        products = listOf(
            Product(
                name = stringResource(Res.string.extras_top_up),
                icon = Icons.AutoMirrored.Outlined.SendToMobile,
            ),
            Product(
                name = stringResource(Res.string.extras_cheque_payment),
                icon = Icons.AutoMirrored.Outlined.ReceiptLong,
            ),
        ),
        color = AppTheme.colors.productOrange,
    )
}

@Preview
@Composable
fun ExtrasScreenPreview() {
    ExtrasScreenContent(
        currentBank = BankConfig.Otp,
    )
}
