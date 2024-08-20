package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.features.dashboard.DashboardScreenContent
import com.jozsefmolnar.bankingapp.model.domain.DefaultBank
import com.jozsefmolnar.bankingapp.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreenContent(
            currentBank = DefaultBank,
            money = 12345,
            showNewTransferSheet = { },
        )
    }
}
