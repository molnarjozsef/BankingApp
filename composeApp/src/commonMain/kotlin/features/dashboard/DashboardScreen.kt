package features.dashboard

import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import components.Header
import components.HorizontalCardButton
import components.VerticalCardButton
import theme.BankColors
import theme.dp16
import theme.dp24
import theme.dp32
import theme.dp56
import theme.dp8


@Composable
fun DashboardScreen(
) {
    Scaffold(
        topBar = {
            Header(
                title = Strings.Dashboard.Title,
                backgroundColor = BankColors.light,
                startButton = { MenuButton() },
                endButton = { ProfileButton() }
            )
        },
        bottomBar = { BankBottomNavigation() },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .background(BankColors.light)
                .padding(contentPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.height(dp16))

                Accounts()

                Spacer(Modifier.height(dp24))

                Transactions()

                Spacer(Modifier.height(dp8))
            }

            Column(
                Modifier
                    .background(BankColors.white)
                    .padding(horizontal = dp16)
            ) {

                Spacer(Modifier.height(dp24))

                QuickFeatures()

                Spacer(Modifier.height(dp24))

                VerticalCardButton(
                    text = Strings.Dashboard.MoreTransactions,
                    icon = rememberVectorPainter(Icons.Filled.MoreHoriz),
                    onClick = {},
                )

                Spacer(Modifier.height(dp32))

                Text(
                    text = Strings.Dashboard.Todos,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(Modifier.height(dp16))

                HorizontalCardButton(
                    text = Strings.Dashboard.FinancialTransactions,
                    icon = rememberVectorPainter(Icons.Filled.SyncAlt),
                    onClick = {},
                )

                Spacer(Modifier.height(dp16))

                HorizontalCardButton(
                    text = Strings.Dashboard.AdministrativeTransactions,
                    icon = rememberVectorPainter(Icons.Filled.Description),
                    onClick = {},
                )

                Spacer(Modifier.height(dp56))
            }
        }
    }
}

@Composable
private fun MenuButton() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Outlined.Menu,
            tint = BankColors.dark,
            contentDescription = null
        )
    }
}

@Composable
private fun ProfileButton() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Outlined.Person,
            tint = BankColors.main,
            contentDescription = null
        )
    }
}
