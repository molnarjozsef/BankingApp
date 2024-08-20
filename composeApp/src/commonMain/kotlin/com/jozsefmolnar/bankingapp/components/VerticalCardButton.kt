package com.jozsefmolnar.bankingapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.DefaultCardElevation
import com.jozsefmolnar.bankingapp.theme.dp32
import com.jozsefmolnar.bankingapp.theme.dp8

@Composable
fun VerticalCardButton(
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
        contentPadding = PaddingValues(dp8),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = DefaultCardElevation)
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
                color = AppTheme.colors.textLight,
                letterSpacing = 0.sp,
            )
        }
    }
}
