package features.dashboard

import Config
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.BeachAccess
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_extras
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_home
import bankingapp.composeapp.generated.resources.dashboard_bottom_navigation_products
import bankingapp.composeapp.generated.resources.dashboard_menu_atm_finder
import bankingapp.composeapp.generated.resources.dashboard_menu_contact
import bankingapp.composeapp.generated.resources.dashboard_menu_extras
import bankingapp.composeapp.generated.resources.dashboard_menu_navigation
import bankingapp.composeapp.generated.resources.dashboard_menu_settings
import bankingapp.composeapp.generated.resources.dashboard_menu_szep_card
import bankingapp.composeapp.generated.resources.dashboard_menu_title
import bankingapp.composeapp.generated.resources.dashboard_menu_whats_new
import components.Header
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp32

@Composable
fun Menu(
    navigateToAtmFinder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Header(title = stringResource(Res.string.dashboard_menu_title))

        Spacer(Modifier.height(dp16))

        NavigationSection()

        Spacer(Modifier.height(dp32))

        ExtrasSection(navigateToAtmFinder = navigateToAtmFinder)
    }
}

@Composable
private fun NavigationSection() {
    Text(
        modifier = Modifier.padding(horizontal = dp24),
        text = stringResource(Res.string.dashboard_menu_navigation),
        fontSize = 14.sp,
        color = AppTheme.colors.textLight
    )

    Spacer(Modifier.height(dp16))

    MenuItem(
        title = stringResource(Res.string.dashboard_bottom_navigation_home),
        icon = Icons.Outlined.House,
        onClick = { },
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_bottom_navigation_products),
        icon = Icons.Outlined.Store,
        onClick = { },
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_bottom_navigation_extras, Config.currentBank.bankName),
        icon = Icons.Outlined.ConfirmationNumber,
        onClick = { },
    )
}

@Composable
private fun ExtrasSection(
    navigateToAtmFinder: () -> Unit,
) {
    Text(
        modifier = Modifier.padding(horizontal = dp24),
        text = stringResource(Res.string.dashboard_menu_extras),
        fontSize = 14.sp,
        color = AppTheme.colors.textLight
    )

    Spacer(Modifier.height(dp16))

    MenuItem(
        title = stringResource(Res.string.dashboard_menu_szep_card, Config.currentBank.bankName),
        icon = Icons.Outlined.BeachAccess,
        onClick = { },
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_menu_atm_finder),
        icon = Icons.Outlined.LocationOn,
        onClick = navigateToAtmFinder,
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_menu_settings),
        icon = Icons.Outlined.Settings,
        onClick = { },
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_menu_contact),
        icon = Icons.Outlined.Call,
        onClick = { },
    )
    MenuItem(
        title = stringResource(Res.string.dashboard_menu_whats_new),
        icon = Icons.Outlined.Celebration,
        onClick = { },
    )
}


@Composable
private fun MenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onClick() }
            .padding(
                horizontal = dp24,
                vertical = dp16
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dp16),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppTheme.colors.main,
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = AppTheme.colors.textDarker,
            fontSize = 16.sp
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = AppTheme.colors.textDark,
        )
    }
}
