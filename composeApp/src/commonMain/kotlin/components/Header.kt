@file:OptIn(ExperimentalMaterial3Api::class)

package components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.dp56

@Composable
fun Header(
    title: String,
    containerColor: Color = AppTheme.colors.backgroundNeutral,
    startButton: @Composable (() -> Unit)? = null,
    endButton: @Composable (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = AppTheme.colors.textDarker,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
            )
        },
        navigationIcon = { startButton?.invoke() },
        actions = { endButton?.invoke() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(containerColor = containerColor)
    )
}

@Composable
fun BackButton(
    navigateBack: () -> Unit,
) {
    IconButton(
        onClick = navigateBack,
        modifier = Modifier.size(dp56),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = AppTheme.colors.main,
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = null
        )
    }
}
