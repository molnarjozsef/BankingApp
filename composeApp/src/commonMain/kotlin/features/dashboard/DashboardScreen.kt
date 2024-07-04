package features.dashboard

import Routes
import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    viewModel: DashboardViewModel,
    navController: NavController,
) {
    DashboardScreenContent(
        money = viewModel.money,
        navigateToAtmFinder = { navController.navigate(Routes.RouteAtmFinder) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenContent(
    money: String,
    navigateToAtmFinder: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(
                title = Strings.Dashboard.Title,
                backgroundColor = BankColors.light,
                startButton = { MenuButton(onClick = { showMenu = true }) },
                endButton = { ProfileButton() }
            )
        },
        bottomBar = { BankBottomNavigation() },
    ) { contentPadding ->

        if (showMenu) {
            ModalBottomSheet(
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                onDismissRequest = { showMenu = false },
                containerColor = BankColors.white,
                dragHandle = null,
                shape = RoundedCornerShape(
                    topStart = dp8,
                    topEnd = dp8,
                ),
            ) {
                Menu(
                    navigateToAtmFinder = navigateToAtmFinder,
                    modifier = Modifier.navigationBarsPadding(),
                )
            }
        }

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

                Accounts(
                    money = money
                )

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
private fun MenuButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
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
