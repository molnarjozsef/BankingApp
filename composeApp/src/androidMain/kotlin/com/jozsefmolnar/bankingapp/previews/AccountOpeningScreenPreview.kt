package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.accountopening.AccountOpeningScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun AccountOpeningScreenPreview() {
    AppTheme {
        AccountOpeningScreenContent(
            openAccount = { _, _ -> },
            navigateUp = { },
            error = "error",
        )
    }
}
