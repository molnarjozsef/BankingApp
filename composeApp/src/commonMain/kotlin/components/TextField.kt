package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.AppTheme
import theme.dp8

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        shape = RoundedCornerShape(dp8),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedLabelColor = AppTheme.colors.textDark,
            focusedLabelColor = AppTheme.colors.textDarker,
            unfocusedTextColor = AppTheme.colors.textDarker,
            focusedTextColor = AppTheme.colors.textDarker,
            cursorColor = AppTheme.colors.main,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = AppTheme.colors.textLight,
            focusedBorderColor = AppTheme.colors.textLight,
        ),
        singleLine = true,
    )
}
