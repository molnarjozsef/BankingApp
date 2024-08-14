package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.transfer.SuccessTransferScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun SuccessTransferScreenPreview() {
    AppTheme {
        SuccessTransferScreenContent(
            userEmail = "test@email.com",
            recipientEmail = "test2@email.com",
            amount = 1_234_567,
            navigateUp = { },
            navigateToHome = { },
        )
    }
}
