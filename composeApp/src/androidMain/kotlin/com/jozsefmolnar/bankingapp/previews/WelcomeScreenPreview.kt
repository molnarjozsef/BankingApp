package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.features.welcome.WelcomeScreenContent
import com.jozsefmolnar.bankingapp.model.domain.DefaultBank
import com.jozsefmolnar.bankingapp.theme.AppTheme

@Preview
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreenContent(
            currentBank = DefaultBank,
            isLoggedIn = true,
            navigateToPinScreen = { },
            navigateToBankChanger = { },
            navigateToAccountOpeningScreen = { },
            navigateToLoginScreen = { },
        )
    }
}
