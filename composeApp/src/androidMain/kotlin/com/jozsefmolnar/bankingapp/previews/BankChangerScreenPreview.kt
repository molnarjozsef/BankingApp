package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.bankchanger.BankChangerScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun BankChangerScreenPreview() {
    AppTheme {
        BankChangerScreenContent(
            currentBank = BankConfig.Otp,
            setCurrentBank = { },
            navigateUp = { },
        )
    }
}
