@file:OptIn(ExperimentalMaterial3Api::class)

package components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import theme.AppTheme

@Composable
fun Header(
    title: String,
    containerColor: Color = AppTheme.colors.backgroundNeutral,
    startButton: @Composable (() -> Unit)? = null,
    endButton: @Composable (() -> Unit)? = null,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(containerColor = containerColor),
        windowInsets = windowInsets
    )
}
