package features.home

import BankConfig
import Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_title
import bankingapp.composeapp.generated.resources.extras_title
import bankingapp.composeapp.generated.resources.products_title
import components.Header
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp24
import theme.dp4

@Composable
fun HomeHeader(
    currentBank: BankConfig,
    homeNavController: NavController,
    showMenu: () -> Unit,
) {
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val title = when (currentDestination?.route) {
        Routes.RouteDashboard -> stringResource(Res.string.dashboard_title)
        Routes.RouteProducts -> stringResource(Res.string.products_title)
        Routes.RouteExtras -> stringResource(Res.string.extras_title, currentBank.bankName)
        else -> null
    }

    title?.let {
        Header(
            title = title,
            containerColor = AppTheme.colors.backgroundColored,
            startButton = { MenuButton(onClick = showMenu) },
            endButton = { ProfileButton() }
        )
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
                    color = AppTheme.colors.buttonDisabled
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
