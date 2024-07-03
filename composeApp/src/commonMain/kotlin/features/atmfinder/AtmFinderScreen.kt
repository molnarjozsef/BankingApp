package features.atmfinder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import theme.BankColors
import theme.dp16
import theme.dp24
import theme.dp8


@Composable
fun AtmFinderScreen(
    viewModel: AtmFinderViewModel,
) {
    val atms = viewModel.atms.collectAsState().value

    if (atms == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dp16),
            contentPadding = PaddingValues(dp24)
        ) {
            atms.forEach { atm ->
                item {
                    Atm(atm = atm)
                }
            }
        }
    }
}

@Composable
private fun Atm(
    atm: Atm,
) {
    Surface(
        shadowElevation = dp8,
        color = BankColors.light,
        shape = RoundedCornerShape(dp8),
    ) {
        Column(
            modifier = Modifier.padding(dp16).fillMaxWidth(),
        ) {
            Text(text = atm.brand ?: "ATM")
            Text(text = atm.lat.toString())
            Text(text = atm.lon.toString())
        }
    }
}
