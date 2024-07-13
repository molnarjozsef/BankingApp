package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.extras.ExtrasScreenContent

@Preview
@Composable
fun ExtrasScreenPreview() {
    ExtrasScreenContent(
        currentBank = BankConfig.Otp,
    )
}
