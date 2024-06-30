package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.login.LoginScreen

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navigateToPinScreen = {},
    )
}