package features.atmfinder

import Strings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import components.Header
import model.domain.Atm
import theme.BankColors
import theme.dp16
import theme.dp24
import theme.dp8


@Composable
fun AtmFinderScreen(
    viewModel: AtmFinderViewModel,
) {
    val atms = viewModel.atms.collectAsState().value

    Scaffold(
        topBar = { Header(title = Strings.AtmFinder.Title) }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            if (atms == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = BankColors.main)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(dp16),
                    contentPadding = PaddingValues(
                        vertical = dp24,
                        horizontal = dp16
                    )
                ) {
                    atms.forEachIndexed { index, atm ->
                        item {
                            Atm(
                                atm = atm,
                                index = index
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Atm(
    atm: Atm,
    index: Int,
) {
    Surface(
        shadowElevation = dp8,
        color = BankColors.white,
        shape = RoundedCornerShape(dp8),
    ) {
        Row(
            modifier = Modifier.padding(dp16).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dp8),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Filled.Circle,
                    tint = BankColors.darker,
                    contentDescription = null
                )
                if ((index + 1) in 1..5) {
                    Text(
                        text = (index + 1).toString(),
                        fontSize = 8.sp,
                        color = BankColors.white
                    )
                }
            }
            Column {
                Text(
                    text = atm.brand ?: Strings.AtmFinder.DefaultAtmName,
                    fontSize = 13.sp,
                    color = BankColors.darker
                )
                Text(
                    text = "${atm.lat}, ${atm.lon}",
                    fontSize = 10.sp,
                    color = BankColors.darker,
                )
            }
        }
    }
}
