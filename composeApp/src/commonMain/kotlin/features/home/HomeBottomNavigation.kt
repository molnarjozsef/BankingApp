package features.home

import Config
import Routes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_extras
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_home
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_products
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme

@Composable
fun HomeBottomNavigation(
    homeNavController: NavController,
) {
    val items = mutableListOf(
        BottomNavigationRoute.Dashboard,
        BottomNavigationRoute.Products,
        BottomNavigationRoute.Extras,
    )

    NavigationBar(
        containerColor = AppTheme.colors.backgroundNeutral,
        contentColor = AppTheme.colors.textLight
    ) {
        val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                item = item,
                currentDestination = currentDestination,
                homeNavController = homeNavController
            )
        }
    }
}

@Composable
private fun RowScope.NavigationBarItem(
    item: BottomNavigationRoute,
    currentDestination: NavDestination?,
    homeNavController: NavController,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val icon = if (selected) item.selectedIcon else item.unselectedIcon
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val animation by animateFloatAsState(if (selected) 1f else 0f, spring())

    NavigationBarItem(
        selected = selected,
        onClick = {
            homeNavController.navigate(item.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(homeNavController.graph.findStartDestination().route!!) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        label = {
            Text(
                modifier = Modifier.scale(1 + (animation * 0.1f)),
                text = item.label(),
                fontWeight = fontWeight,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppTheme.colors.main,
            selectedTextColor = AppTheme.colors.main,
            unselectedIconColor = AppTheme.colors.textLight,
            unselectedTextColor = AppTheme.colors.textLight,
            indicatorColor = AppTheme.colors.backgroundColored,
        ),
    )

}

enum class BottomNavigationRoute(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
    val label: @Composable () -> String,
) {
    Dashboard(
        selectedIcon = Icons.Filled.House,
        unselectedIcon = Icons.Outlined.House,
        route = Routes.RouteDashboard,
        label = { stringResource(Res.string.dashboard_bottom_navigation_home) },
    ),
    Products(
        selectedIcon = Icons.Filled.Store,
        unselectedIcon = Icons.Outlined.Store,
        route = Routes.RouteProducts,
        label = { stringResource(Res.string.dashboard_bottom_navigation_products) },
    ),
    Extras(
        selectedIcon = Icons.Filled.ConfirmationNumber,
        unselectedIcon = Icons.Outlined.ConfirmationNumber,
        route = Routes.RouteExtras,
        label = { stringResource(Res.string.dashboard_bottom_navigation_extras, Config.currentBank.bankName) },
    )
}
