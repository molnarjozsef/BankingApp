package features.dashboard

import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.BankColors
import theme.dp16
import theme.dp4
import theme.dp8

@Composable
fun Accounts(money: String) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dp16)
    ) {
        item {
            Account(money = money)
        }
    }
}

@Composable
private fun Account(money: String) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .aspectRatio(1.5f)
            .background(BankColors.cardSilver)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .background(BankColors.cardSilver)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .background(BankColors.white)
                    .fillMaxWidth()
                    .padding(dp8),
            ) {
                Text(
                    text = Strings.Dashboard.AccountName,
                    fontSize = 12.sp,
                    color = BankColors.lightDark
                )

                Spacer(Modifier.height(dp4))

                Text(
                    text = money,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = BankColors.darker,
                )
            }
        }
    }
}
