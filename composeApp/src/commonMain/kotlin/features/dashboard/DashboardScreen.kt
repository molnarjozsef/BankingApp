package features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_administrative_transactions
import bankingapp.composeapp.generated.resources.dashboard_financial_transactions
import bankingapp.composeapp.generated.resources.dashboard_more_transactions
import bankingapp.composeapp.generated.resources.dashboard_todos
import components.HorizontalCardButton
import components.VerticalCardButton
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp32
import theme.dp56
import theme.dp8


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
) {
    DashboardScreenContent(
        money = viewModel.money,
    )
}

@Composable
fun DashboardScreenContent(
    money: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(AppTheme.colors.backgroundColored)
        ) {
            Spacer(Modifier.height(dp16))

            Accounts(
                money = money
            )

            Spacer(Modifier.height(dp24))

            Transactions()

            Spacer(Modifier.height(dp8))
        }

        Column(
            Modifier.padding(horizontal = dp16)
        ) {
            Spacer(Modifier.height(dp24))

            QuickFeatures()

            Spacer(Modifier.height(dp24))

            VerticalCardButton(
                text = stringResource(Res.string.dashboard_more_transactions),
                icon = rememberVectorPainter(Icons.Filled.MoreHoriz),
                onClick = {},
            )

            Spacer(Modifier.height(dp32))

            Text(
                text = stringResource(Res.string.dashboard_todos),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.textDarker
            )

            Spacer(Modifier.height(dp16))

            HorizontalCardButton(
                text = stringResource(Res.string.dashboard_financial_transactions),
                icon = rememberVectorPainter(Icons.Filled.SyncAlt),
                onClick = {},
            )

            Spacer(Modifier.height(dp16))

            HorizontalCardButton(
                text = stringResource(Res.string.dashboard_administrative_transactions),
                icon = rememberVectorPainter(Icons.Filled.Description),
                onClick = {},
            )

            Spacer(Modifier.height(dp56))
        }
    }
}

