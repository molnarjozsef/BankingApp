package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.bankchanger.BankChangerScreenContent

@Preview
@Composable
fun BankChangerScreenPreview() {
    BankChangerScreenContent(
        currentBank = BankConfig.Otp,
        setCurrentBank = { },
    )
}
