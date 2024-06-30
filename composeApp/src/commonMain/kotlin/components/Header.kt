package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import theme.BankColors
import theme.dp8

@Composable
fun Header(
    title: String,
    backgroundColor: Color = BankColors.white
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .padding(dp8),
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
            color = BankColors.darker,
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
