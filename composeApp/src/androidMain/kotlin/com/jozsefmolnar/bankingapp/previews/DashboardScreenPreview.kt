package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.dashboard.DashboardScreenContent

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreenContent(
        money = "12 345 Ft",
        navigateToAtmFinder = { },
    )
}
