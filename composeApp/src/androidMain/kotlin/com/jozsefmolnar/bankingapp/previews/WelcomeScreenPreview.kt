package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.welcome.WelcomeScreenContent
import theme.AppTheme

@Preview
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreenContent(
            currentBank = BankConfig.Otp,
            isLoggedIn = true,
            navigateToPinScreen = { },
            navigateToBankChanger = { },
            navigateToAccountOpeningScreen = { },
            navigateToLoginScreen = { },
        )
    }
}
