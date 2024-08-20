package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.features.pin.PinScreenContent
import com.jozsefmolnar.bankingapp.theme.AppTheme

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
