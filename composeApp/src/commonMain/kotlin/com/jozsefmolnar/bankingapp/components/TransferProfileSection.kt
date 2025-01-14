package com.jozsefmolnar.bankingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp24
import com.jozsefmolnar.bankingapp.theme.dp4
import com.jozsefmolnar.bankingapp.theme.dp8

@Composable
internal fun TransferProfileSection(
    sectionTitle: String,
    contentTitle: String,
    contentDescription: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(dp16)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dp8)
        ) {
            Icon(
                painter = rememberVectorPainter(Icons.Outlined.Person),
                contentDescription = null,
                modifier = Modifier.size(dp24),
                tint = AppTheme.colors.textDark,
            )
            Text(
                text = sectionTitle,
                fontSize = 18.sp,
                color = AppTheme.colors.textDarker
            )
        }
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(dp8),
        ) {
            VerticalDivider()
            Column(verticalArrangement = Arrangement.spacedBy(dp4)) {
                Text(
                    text = contentTitle,
                    color = AppTheme.colors.textLight,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                )
                Text(
                    text = contentDescription,
                    color = AppTheme.colors.textDarker,
                    fontSize = 16.sp
                )
            }
        }
    }
}
