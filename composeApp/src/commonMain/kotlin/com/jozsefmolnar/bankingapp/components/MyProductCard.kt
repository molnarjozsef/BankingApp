package com.jozsefmolnar.bankingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.DefaultCardElevation
import com.jozsefmolnar.bankingapp.theme.ProductCardWidth
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp48
import com.jozsefmolnar.bankingapp.theme.dp8

@Composable
fun MyProductCard(
    text: String,
    subtitle: String,
    icon: ImageVector,
) {
    Card(
        modifier = Modifier
            .width(ProductCardWidth)
            .aspectRatio(0.9f),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceNeutral,
            contentColor = AppTheme.colors.textDarker,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = DefaultCardElevation)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(dp48),
                imageVector = icon,
                tint = AppTheme.colors.main,
                contentDescription = null,
            )
            Spacer(Modifier.height(dp8))
            Text(
                text = text,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(dp16))
            Text(
                text = subtitle,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.sp
            )
        }
    }
}
