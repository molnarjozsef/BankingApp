package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.dp56

@Composable
fun Header(
    title: String,
    backgroundColor: Color = AppTheme.colors.backgroundNeutral,
    startButton: @Composable (() -> Unit)? = null,
    endButton: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .height(dp56),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(dp56),
            contentAlignment = Alignment.Center,
        ) {
            startButton?.invoke()
        }

        Text(
            text = title,
            color = AppTheme.colors.textDarker,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
        )

        Box(
            modifier = Modifier.size(dp56),
            contentAlignment = Alignment.Center,
        ) {
            endButton?.invoke()
        }
    }
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
