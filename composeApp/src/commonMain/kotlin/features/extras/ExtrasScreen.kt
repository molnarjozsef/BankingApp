package features.extras

import Config
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.automirrored.outlined.SendToMobile
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Loyalty
import androidx.compose.material.icons.outlined.Tram
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp40
import theme.dp72
import theme.dp8

@Composable
fun ExtrasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = dp40)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = dp16),
            text = stringResource(Res.string.extras_available_extras, Config.currentBank.bankName),
            color = AppTheme.colors.textDarker,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(Modifier.height(dp24))

        Offers()

        Spacer(Modifier.height(dp24))

        PublicTransport()

        Spacer(Modifier.height(dp24))

        TopUpPayment()
    }
}

@Composable
private fun Offers() {
    ProductSection(
        title = stringResource(Res.string.extras_offers),
        products = listOf(
            Product(
                name = stringResource(Res.string.extras_offer_cashback, Config.currentBank.bankName),
                icon = Icons.Outlined.Loyalty,
                color = AppTheme.colors.main,
            )
        )
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
        )
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
        )
    )
}

@Composable
fun ProductSection(
    title: String,
    products: List<Product>,
) {
    Column {
        Text(
            text = title,
            color = AppTheme.colors.textDark,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = dp16)
        )

        Spacer(Modifier.height(dp16))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16),

            ) {
            products.forEach { product ->
                FeatureCard(
                    text = product.name,
                    icon = product.icon,
                    color = product.color,
                )
            }
        }
    }
}

data class Product(
    val name: String,
    val icon: ImageVector,
    val color: Color = Color.Unspecified,
)

@Composable
private fun FeatureCard(
    text: String,
    icon: ImageVector,
    color: Color,
) {
    Card(
        modifier = Modifier
            .height(dp72)
            .aspectRatio(2f),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceNeutral,
            contentColor = AppTheme.colors.textDarker,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = dp8)
    ) {
        Row {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dp16)
                    .padding(vertical = dp16),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.sp
            )
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .offset(x = dp16, y = dp16)
                    .rotate(IconRotationDegrees),
                imageVector = icon,
                tint = color,
                contentDescription = null,
            )
        }
    }
}

val IconRotationDegrees = -15f
