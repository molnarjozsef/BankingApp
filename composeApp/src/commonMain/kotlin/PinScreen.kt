import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PinScreen(
    navigateToDashboard: () -> Unit,
) {
    var pin by remember { mutableStateOf("") }

    LaunchedEffect(pin) {
        if (pin.length == 6) {
            navigateToDashboard()
        }
    }

    Column(
        modifier = Modifier
            .nestedScroll(object : NestedScrollConnection {})
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "add meg az mPIN kódodat",
            fontSize = 24.sp,
            color = BankColors.dark,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(24.dp))

        Text(
            text = pin.map { "•" }.joinToString(""),
            fontSize = 40.sp,
            color = BankColors.main,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(24.dp))

        PinPad(
            modifier = Modifier.padding(horizontal = 24.dp),
            onClick = { pin += it },
        )

        Spacer(Modifier.height(24.dp))
        Spacer(Modifier.weight(1f))

        Text(
            text = "elfelejtettem az mPIN-kódomat",
            fontSize = 24.sp,
            color = BankColors.main,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun PinPad(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
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
        horizontalArrangement = Arrangement.spacedBy(24.dp)
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
            .size(64.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable { onClick() }
            .border(border = BorderStroke(2.dp, BankColors.dark), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = BankColors.dark,
            fontSize = 24.sp,
        )
    }
}