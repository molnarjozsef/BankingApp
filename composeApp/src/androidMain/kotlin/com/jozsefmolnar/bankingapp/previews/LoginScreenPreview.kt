package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.login.LoginScreenContent

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        currentBank = BankConfig.Otp,
        navigateToPinScreen = { },
        navigateToBankChanger = { },
    )
}
