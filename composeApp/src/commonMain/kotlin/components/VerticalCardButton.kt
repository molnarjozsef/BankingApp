package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp32
import theme.dp8

@Composable
fun VerticalCardButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BankColors.white,
            contentColor = BankColors.main,
        ),
        shape = RoundedCornerShape(dp8),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp8)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(dp32)
            )

            Text(
                text = text,
                fontSize = 12.sp,
                color = BankColors.lightDark,
                letterSpacing = 0.sp,
            )
        }
    }
}
