package components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp40
import theme.dp8

@Composable
fun BankSecondaryButton(
    text: String,
    icon: Painter? = null,
    textColor: Color = BankColors.white,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = textColor),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(dp40),
    ) {
        icon?.let {
            Icon(
                painter = icon,
                contentDescription = null,
            )
            Spacer(Modifier.width(dp8))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
