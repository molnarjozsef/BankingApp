import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DashboardScreen(
) {
    Scaffold(
        topBar = { Header(title = "kezdőlap") },
        backgroundColor = BankColors.light
    ) {
        Column(
            Modifier.background(BankColors.light)
                .padding(vertical = 24.dp)
                .fillMaxSize()
        ) {
            Accounts()

            Spacer(Modifier.height(24.dp))

            Transactions()

            Spacer(Modifier.height(24.dp))

            QuickFeatures()
        }
    }
}

@Composable
private fun Transactions() {
    Column {
        Transaction()
        Transaction()
        Transaction()
    }
}

@Composable
private fun QuickFeatures() {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        QuickFeature(
            title = "forintátutalás",
            icon = rememberVectorPainter(Icons.Outlined.Payments)
        )
        QuickFeature(
            title = "limitmódosítás",
            icon = rememberVectorPainter(Icons.Outlined.Speed)
        )
        QuickFeature(
            title = "csekkbefizetés",
            icon = rememberVectorPainter(Icons.AutoMirrored.Outlined.ReceiptLong)
        )
    }
}

@Composable
private fun RowScope.QuickFeature(
    title: String,
    icon: Painter,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = Modifier.weight(1f),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BankColors.main,
            contentColor = BankColors.white,
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = null
            )
            Text(
                text = title,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun Transaction() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
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

@Composable
private fun Accounts() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp)
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
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "K&H számlacsomag",
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

@Composable
private fun Header(title: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                tint = BankColors.dark,
                contentDescription = null
            )
        }
        Text(
            text = title,
            color = BankColors.dark,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Person,
                tint = BankColors.main,
                contentDescription = null
            )
        }
    }
}
