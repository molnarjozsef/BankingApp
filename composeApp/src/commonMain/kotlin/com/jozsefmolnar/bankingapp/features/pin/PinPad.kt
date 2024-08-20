package com.jozsefmolnar.bankingapp.features.pin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp16

@Composable
fun PinPad(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dp16),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PinRow(listOf(1, 2, 3), { onClick(it) })
        PinRow(listOf(4, 5, 6), { onClick(it) })
        PinRow(listOf(7, 8, 9), { onClick(it) })
        PinRow(listOf(0), { onClick(it) })
    }
}

@Composable
private fun PinRow(
    items: List<Int>,
    onClick: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dp16)
    ) {
        items.forEach { item ->
            PinButton(
                text = item.toString(),
                onClick = { onClick(item.toString()) }
            )
        }
    }
}


@Composable
private fun PinButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable { onClick() }
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = AppTheme.colors.textDark
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = AppTheme.colors.textDarkHighContrast,
            fontSize = 22.sp,
        )
    }
}
