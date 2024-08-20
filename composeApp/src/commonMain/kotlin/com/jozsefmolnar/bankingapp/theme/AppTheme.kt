package com.jozsefmolnar.bankingapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colors = if (darkTheme) darkColors else lightColors

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            typography = defaultTypography,
            colorScheme = MaterialTheme.colorScheme.copy(background = AppTheme.colors.backgroundNeutral)
        ) {
            content()
        }
    }
}

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

