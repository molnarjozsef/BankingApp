package features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.dashboard_quick_settings_bill_payment
import bankingapp.composeapp.generated.resources.dashboard_quick_settings_current_transfer
import bankingapp.composeapp.generated.resources.dashboard_quick_settings_limit_change
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.DefaultCardElevation
import theme.dp16
import theme.dp32
import theme.dp8

@Composable
fun QuickFeatures() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dp16)
    ) {
        QuickFeatureButton(
            title = stringResource(Res.string.dashboard_quick_settings_current_transfer),
            icon = rememberVectorPainter(Icons.Outlined.Payments)
        )
        QuickFeatureButton(
            title = stringResource(Res.string.dashboard_quick_settings_limit_change),
            icon = rememberVectorPainter(Icons.Outlined.Speed)
        )
        QuickFeatureButton(
            title = stringResource(Res.string.dashboard_quick_settings_bill_payment),
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
            containerColor = AppTheme.colors.main,
            contentColor = AppTheme.colors.contentOnMainSurface,
        ),
        contentPadding = PaddingValues(dp8),
        shape = RoundedCornerShape(dp8),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = DefaultCardElevation),
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
