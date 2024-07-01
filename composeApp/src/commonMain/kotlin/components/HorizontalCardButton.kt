package components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp16
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
            backgroundColor = BankColors.white,
            contentColor = BankColors.main,
        ),
        shape = RoundedCornerShape(dp8),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dp16)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
        )

        Spacer(Modifier.width(dp16))

        Text(
            text = text,
            fontSize = 16.sp,
            color = BankColors.darker,
            modifier = Modifier.weight(1f),
            letterSpacing = 0.sp,
        )
    }
}
