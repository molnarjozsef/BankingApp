package features.dashboard

import Strings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import theme.dp8

@Composable
fun Accounts() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dp16)
    ) {
        item {
            Account()
        }
    }
}

@Composable
private fun Account() {
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
                verticalArrangement = Arrangement.spacedBy(dp8)
            ) {
                Text(
                    text = "${Strings.BankName} számlacsomag",
                    fontSize = 12.sp
                )
                Text(
                    text = "47 000 Ft",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}