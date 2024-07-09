package features.dashboard

import Config
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_account_name
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp4
import theme.dp8

@Composable
fun Accounts(money: String) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dp16)
    ) {
        item {
            Account(money = money)
        }
    }
}

@Composable
private fun Account(money: String) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .aspectRatio(1.5f),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceNeutralOnColored,
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .background(AppTheme.colors.cardSilver)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(dp8),
            ) {
                Text(
                    text = stringResource(Res.string.dashboard_account_name, Config.currentBank.bankName),
                    fontSize = 12.sp,
                    color = AppTheme.colors.textLight
                )

                Spacer(Modifier.height(dp4))

                Text(
                    text = money,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = AppTheme.colors.textDarker,
                )
            }
        }
    }
}
