package components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.dp8

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        text = text,
        onClick = onClick,
        containerColor = AppTheme.colors.main,
        contentColor = AppTheme.colors.backgroundNeutral,
    )
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        text = text,
        onClick = onClick,
        containerColor = AppTheme.colors.contentOnMainBackground,
        contentColor = AppTheme.colors.backgroundMain,
    )
}


@Composable
private fun Button(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        contentPadding = PaddingValues(dp8),
        elevation = null,
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
