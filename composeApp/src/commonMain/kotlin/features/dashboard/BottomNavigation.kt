package features.dashboard

import Strings
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.Store
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.lerp
import theme.BankColors

@Composable
fun BankBottomNavigation() {
    var selected by remember { mutableStateOf(BottomNavigationRoute.Dashboard) }

    BottomNavigation(
        backgroundColor = BankColors.white,
        contentColor = BankColors.lightDark
    ) {
        BottomNavigationItem(
            route = BottomNavigationRoute.Dashboard,
            selected = selected == BottomNavigationRoute.Dashboard,
            onClick = { selected = BottomNavigationRoute.Dashboard },
        )
        BottomNavigationItem(
            route = BottomNavigationRoute.Products,
            selected = selected == BottomNavigationRoute.Products,
            onClick = { selected = BottomNavigationRoute.Products },
        )
        BottomNavigationItem(
            route = BottomNavigationRoute.Extras,
            selected = selected == BottomNavigationRoute.Extras,
            onClick = { selected = BottomNavigationRoute.Extras },
        )
    }
}

@Composable
private fun RowScope.BottomNavigationItem(
    route: BottomNavigationRoute,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val icon = if (selected) route.selectedIcon else route.unselectedIcon
    val fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    val animation by animateFloatAsState(if (selected) 1f else 0f, spring())
    val textUnit = lerp(
        TextUnit(12f, TextUnitType.Sp),
        TextUnit(14f, TextUnitType.Sp),
        animation
    )

    BottomNavigationItem(
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
        selectedContentColor = BankColors.main,
        unselectedContentColor = BankColors.lightDark,
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
