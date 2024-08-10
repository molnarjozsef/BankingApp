package components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.dp4
import theme.dp8


@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    title: String? = null,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    suffix: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val customTextSelectionColors = TextSelectionColors(
        handleColor = AppTheme.colors.main,
        backgroundColor = AppTheme.colors.main.copy(alpha = 0.5f),
    )

    Column {
        title?.let {
            TextFieldTitle(text = title)
            Spacer(Modifier.height(dp4))
        }

        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder?.let {
                    {
                        Text(
                            text = placeholder,
                            fontWeight = FontWeight.Light
                        )
                    }
                },
                visualTransformation = visualTransformation,
                shape = RoundedCornerShape(dp8),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = AppTheme.colors.textDarker,
                    focusedTextColor = AppTheme.colors.textDarker,
                    cursorColor = AppTheme.colors.main,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedBorderColor = AppTheme.colors.textLight,
                    focusedBorderColor = AppTheme.colors.textLight,
                    unfocusedPlaceholderColor = AppTheme.colors.textDark,
                    focusedPlaceholderColor = AppTheme.colors.textDarker,
                ),
                singleLine = true,
                keyboardOptions = keyboardOptions,
                suffix = suffix?.let {
                    {
                        Text(
                            text = suffix,
                            color = AppTheme.colors.textDark,
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun TextFieldTitle(
    text: String,
) {
    Text(
        text = text,
        color = AppTheme.colors.textDark,
        fontSize = 14.sp,
    )
}
