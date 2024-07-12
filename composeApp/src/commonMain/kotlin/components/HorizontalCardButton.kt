package components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.DefaultCardElevation
import theme.dp16
import theme.dp24
import theme.dp8

@Composable
fun HorizontalCardButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.surfaceNeutral,
            contentColor = AppTheme.colors.main,
        ),
        shape = RoundedCornerShape(dp8),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp16),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = DefaultCardElevation)
    ) {
        Icon(
            modifier = Modifier.size(dp24),
            painter = icon,
            contentDescription = null,
        )

        Spacer(Modifier.width(dp16))

        Text(
            text = text,
            fontSize = 16.sp,
            color = AppTheme.colors.textDarker,
            modifier = Modifier.weight(1f),
            letterSpacing = 0.sp,
        )
    }
}
