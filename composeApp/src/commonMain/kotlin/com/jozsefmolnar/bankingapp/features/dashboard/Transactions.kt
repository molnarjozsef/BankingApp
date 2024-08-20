package com.jozsefmolnar.bankingapp.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp24
import com.jozsefmolnar.bankingapp.theme.dp32

@Composable
fun Transactions() {
    Column {
        Transaction()
        Transaction()
        Transaction()
    }
}

@Composable
private fun Transaction() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dp24),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = dp32)
            .height(dp24),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "06.26.",
            fontSize = 10.sp,
            color = AppTheme.colors.textDarker,
        )
        Text(
            text = "BKK AUTOMATA",
            modifier = Modifier.weight(1f),
            color = AppTheme.colors.textDarker,
            fontSize = 14.sp
        )
        Text(
            text = "-9000 Ft",
            color = AppTheme.colors.textDarker,
            fontSize = 14.sp
        )
    }
}
