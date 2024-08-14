package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.pin.PinScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun PinScreenPreview() {
    AppTheme {
        PinScreenContent(
            navigateToDashboard = {},
            navigateUp = {},
        )
    }
}
