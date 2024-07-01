package features.dashboard

import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import components.Header
import theme.BankColors
import theme.dp16
import theme.dp24
import theme.dp56


@Composable
fun DashboardScreen(
) {
    Scaffold(
        topBar = {
            Header(
                title = Strings.Dashboard.Title,
                backgroundColor = BankColors.light
            )
        },
        bottomBar = {
            BankBottomNavigation()
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BankColors.light)
            ) {
                Spacer(Modifier.height(dp16))

                Accounts()

                Spacer(Modifier.height(dp24))

                Transactions()
            }

            Spacer(Modifier.height(dp24))

            QuickFeatures()

            Spacer(Modifier.height(dp56))
        }
    }
}
