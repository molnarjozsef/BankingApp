package features.dashboard

import Strings
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import theme.AppTheme

@Composable
fun BankBottomNavigation() {
    var selected by remember { mutableStateOf(BottomNavigationRoute.Dashboard) }

    NavigationBar(
        containerColor = AppTheme.colors.background,
        contentColor = AppTheme.colors.lightDark
    ) {
        NavigationBarItem(
            route = BottomNavigationRoute.Dashboard,
            selected = selected == BottomNavigationRoute.Dashboard,
            onClick = { selected = BottomNavigationRoute.Dashboard },
        )
        NavigationBarItem(
            route = BottomNavigationRoute.Products,
            selected = selected == BottomNavigationRoute.Products,
            onClick = { selected = BottomNavigationRoute.Products },
        )
        NavigationBarItem(
            route = BottomNavigationRoute.Extras,
            selected = selected == BottomNavigationRoute.Extras,
            onClick = { selected = BottomNavigationRoute.Extras },
        )
    }
}

@Composable
private fun RowScope.NavigationBarItem(
    route: BottomNavigationRoute,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val icon = if (selected) route.selectedIcon else route.unselectedIcon
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val animation by animateFloatAsState(if (selected) 1f else 0f, spring())

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        label = {
            Text(
                modifier = Modifier.scale(1 + (animation * 0.1f)),
                text = route.label,
                fontWeight = fontWeight,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = AppTheme.colors.main,
            selectedTextColor = AppTheme.colors.main,
            unselectedIconColor = AppTheme.colors.lightDark,
            unselectedTextColor = AppTheme.colors.lightDark,
            indicatorColor = AppTheme.colors.lightBackground,
        ),
    )
}

enum class BottomNavigationRoute(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
) {
    Dashboard(
        selectedIcon = Icons.Filled.House,
        unselectedIcon = Icons.Outlined.House,
        label = Strings.Dashboard.BottomNavigationHome,
    ),
    Products(
        selectedIcon = Icons.Filled.Store,
        unselectedIcon = Icons.Outlined.Store,
        label = Strings.Dashboard.BottomNavigationProducts,
    ),
    Extras(
        selectedIcon = Icons.Filled.ConfirmationNumber,
        unselectedIcon = Icons.Outlined.ConfirmationNumber,
        label = Strings.Dashboard.BottomNavigationExtras,
    )
}
