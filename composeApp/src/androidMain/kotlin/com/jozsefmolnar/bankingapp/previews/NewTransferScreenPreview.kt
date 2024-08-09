package com.jozsefmolnar.bankingapp.previews

import DefaultBank
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.transfer.NewTransferScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun NewTransferScreenPreview() {
    AppTheme {
        NewTransferScreenContent(
            currentBank = DefaultBank,
            currentAmount = 1_234_567,
            recipientEmail = "test@email.com",
            onContinueClick = {  },
            navigateUp = { },
        )
    }
}
