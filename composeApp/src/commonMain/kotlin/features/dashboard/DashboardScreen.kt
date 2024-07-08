package features.dashboard

import Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material.icons.outlined.MoreHoriz
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
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_administrative_transactions
import bankingapp.composeapp.generated.resources.dashboard_financial_transactions
import bankingapp.composeapp.generated.resources.dashboard_more_transactions
import bankingapp.composeapp.generated.resources.dashboard_title
import bankingapp.composeapp.generated.resources.dashboard_todos
import components.Header
import components.HorizontalCardButton
import components.VerticalCardButton
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp32
import theme.dp4
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
                title = stringResource(Res.string.dashboard_title),
                backgroundColor = AppTheme.colors.backgroundColored,
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
                containerColor = AppTheme.colors.backgroundNeutral,
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
                .background(AppTheme.colors.backgroundColored)
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
                    .background(AppTheme.colors.backgroundNeutral)
                    .padding(horizontal = dp16)
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

}

@Composable
private fun MenuButton(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Box(
            modifier = Modifier
                .size(dp24)
                .background(
                    shape = CircleShape,
                    color = AppTheme.colors.bubbleOnColoredBackground
                )
        ) {
            Icon(
                imageVector = Icons.Outlined.MoreHoriz,
                tint = AppTheme.colors.textDarker,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ProfileButton() {
    IconButton(onClick = {}) {
        Box(
            modifier = Modifier
                .size(dp24)
                .background(
                    shape = CircleShape,
                    color = AppTheme.colors.main
                )
        ) {
            Icon(
                modifier = Modifier.padding(dp4),
                imageVector = Icons.Outlined.Person,
                tint = AppTheme.colors.contentOnMainSurface,
                contentDescription = null
            )
        }
    }
}
