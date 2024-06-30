package features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp24
import theme.dp8

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
            .padding(
                horizontal = dp24,
                vertical = dp8
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "06.26.",
            fontSize = 11.sp,
            color = BankColors.darker,
        )
        Text(
            text = "BKK automata",
            modifier = Modifier.weight(1f),
            color = BankColors.darker,
            fontSize = 15.sp
        )
        Text(
            text = "-9000 Ft",
            color = BankColors.darker,
            fontSize = 15.sp
        )
    }
}