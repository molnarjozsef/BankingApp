package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.extras.ExtrasScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun ExtrasScreenPreview() {
    AppTheme {
        ExtrasScreenContent(
            currentBank = BankConfig.Otp,
        )
    }
}
