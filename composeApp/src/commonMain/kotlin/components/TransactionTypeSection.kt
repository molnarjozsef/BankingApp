package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.transfer_huf_payment
import bankingapp.composeapp.generated.resources.transfer_transaction_type
import org.jetbrains.compose.resources.stringResource
import theme.AppTheme
import theme.dp16
import theme.dp32
import theme.dp48
import theme.dp8

@Composable
fun TransactionTypeSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dp16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(dp48)
                .background(
                    color = AppTheme.colors.transaction,
                    shape = RoundedCornerShape(dp8)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Outlined.Payments),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(dp32)
            )
        }
        Column {
            Text(
                text = stringResource(Res.string.transfer_transaction_type),
                color = AppTheme.colors.textDarker,
                fontWeight = FontWeight.Light,
                fontSize = 11.sp,
                lineHeight = 13.sp,
            )
            Text(
                text = stringResource(Res.string.transfer_huf_payment),
                color = AppTheme.colors.textDarker,
                fontSize = 13.sp,
                lineHeight = 15.sp,
            )
        }
    }
}
