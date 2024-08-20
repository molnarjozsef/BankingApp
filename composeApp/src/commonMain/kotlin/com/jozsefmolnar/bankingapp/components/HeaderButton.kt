package com.jozsefmolnar.bankingapp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp56


@Composable
fun BackButton(
    onClick: () -> Unit,
) {
    HeaderButton(
        icon = Icons.AutoMirrored.Outlined.ArrowBack,
        onClick = onClick,
    )
}

@Composable
fun CloseButton(
    onClick: () -> Unit,
) {
    HeaderButton(
        icon = Icons.Outlined.Close,
        onClick = onClick,
    )
}


@Composable
fun HeaderButton(
    icon: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(dp56),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = AppTheme.colors.main,
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}
