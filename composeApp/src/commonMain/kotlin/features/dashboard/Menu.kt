package features.dashboard

import Strings
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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import components.Header
import theme.BankColors
import theme.dp16
import theme.dp24

@Composable
fun Menu(
    navigateToAtmFinder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Header(title = Strings.Dashboard.MenuTitle)

        Spacer(Modifier.height(dp16))

        Text(
            modifier = Modifier.padding(horizontal = dp24),
            text = Strings.Dashboard.MenuExtras,
            fontSize = 14.sp,
            color = BankColors.lightDark
        )

        Spacer(Modifier.height(dp16))

        MenuItem(
            title = Strings.Dashboard.MenuSzepCard,
            icon = Icons.Outlined.BeachAccess,
            onClick = { },
        )
        MenuItem(
            title = Strings.Dashboard.MenuAtmFinder,
            icon = Icons.Outlined.LocationOn,
            onClick = navigateToAtmFinder,
        )
        MenuItem(
            title = Strings.Dashboard.MenuSettings,
            icon = Icons.Outlined.Settings,
            onClick = { },
        )
        MenuItem(
            title = Strings.Dashboard.MenuContact,
            icon = Icons.Outlined.Call,
            onClick = { },
        )
    }
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
            tint = BankColors.main,
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = BankColors.darker,
            fontSize = 16.sp
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = BankColors.dark,
        )
    }
}
