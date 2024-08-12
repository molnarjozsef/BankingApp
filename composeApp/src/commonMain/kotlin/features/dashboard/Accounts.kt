package features.dashboard

import BankConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_credit_account_name
import bankingapp.composeapp.generated.resources.dashboard_debit_account_name
import bankingapp.composeapp.generated.resources.dashboard_savings_account_name
import components.formatMoney
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.DefaultCardElevation
import theme.ProductCardWidth
import theme.dp16
import theme.dp4
import theme.dp8

@Composable
fun Accounts(
    currentBank: BankConfig,
    money: Int?,
) {

    LazyRow(
        contentPadding = PaddingValues(horizontal = dp16),
        horizontalArrangement = Arrangement.spacedBy(dp16)
    ) {
        item {
            Account(
                money = money,
                accountName = stringResource(Res.string.dashboard_debit_account_name, currentBank),
                color = AppTheme.colors.main
            )
        }
        item {
            Account(
                money = 0,
                accountName = stringResource(Res.string.dashboard_credit_account_name, currentBank),
                color = AppTheme.colors.productOrange
            )
        }
        item {
            Account(
                money = 0,
                accountName = stringResource(Res.string.dashboard_savings_account_name, currentBank),
                color = AppTheme.colors.productGreen
            )
        }
    }
}

@Composable
private fun Account(
    accountName: String,
    money: Int?,
    color: Color,
) {
    Card(
        modifier = Modifier
            .width(ProductCardWidth)
            .aspectRatio(0.8f),
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
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(dp8),
            ) {
                Text(
                    text = accountName,
                    fontSize = 12.sp,
                    color = AppTheme.colors.textLight,
                    letterSpacing = 0.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.height(dp4))

                Text(
                    text = money?.let { formatMoney(it) } ?: "...",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = AppTheme.colors.textDarker,
                )
            }
        }
    }
}
