package com.jozsefmolnar.bankingapp.previews

import DefaultBank
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.login.LoginScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreenContent(
            currentBank = DefaultBank,
            error = null,
            login = { _, _ -> },
            navigateUp = { },
        )
    }
}
