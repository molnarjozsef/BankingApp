package features.dashboard

import Strings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp16
import theme.dp32
import theme.dp8

@Composable
fun QuickFeatures() {
    Row(
        modifier = Modifier.padding(horizontal = dp16),
        horizontalArrangement = Arrangement.spacedBy(dp16)
    ) {
        QuickFeatureButton(
            title = Strings.Dashboard.QuickSettingsCurrentTransfer,
            icon = rememberVectorPainter(Icons.Outlined.Payments)
        )
        QuickFeatureButton(
            title = Strings.Dashboard.QuickSettingsLimitChange,
            icon = rememberVectorPainter(Icons.Outlined.Speed)
        )
        QuickFeatureButton(
            title = Strings.Dashboard.QuickSettingsBillPayment,
            icon = rememberVectorPainter(Icons.AutoMirrored.Outlined.ReceiptLong)
        )
    }
}

@Composable
private fun RowScope.QuickFeatureButton(
    title: String,
    icon: Painter,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = Modifier.weight(1f),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BankColors.main,
            contentColor = BankColors.white,
        ),
        contentPadding = PaddingValues(dp8)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(dp32),
            )
            Text(
                text = title,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                letterSpacing = 0.sp
            )
        }
    }
}
