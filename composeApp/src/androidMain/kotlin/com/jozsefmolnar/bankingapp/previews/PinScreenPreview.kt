package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.pin.PinScreenContent

@Preview
@Composable
fun PinScreenPreview() {
    PinScreenContent(
        navigateToDashboard = {},
        navigateUp = {},
    )
}
