package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.dashboard.DashboardScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreenContent(
            currentBank = BankConfig.Otp,
            money = 12345,
            showNewTransferSheet = { },
        )
    }
}
