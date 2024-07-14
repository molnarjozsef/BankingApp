package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.dashboard.DashboardScreenContent

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreenContent(
        currentBank = BankConfig.Otp,
        money = "12 345 Ft",
        showNewTransferSheet = { },
    )
}
